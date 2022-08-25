package com.shitouren.core.service;


import com.google.common.collect.ImmutableList;
import com.shitouren.core.config.ConfigCache;
import com.shitouren.core.dto.Account;
import com.shitouren.core.dto.TxInfo;
import com.shitouren.core.exception.DDCException;
import com.shitouren.core.listener.SignEvent;
import com.shitouren.core.listener.SignEventListener;
import com.shitouren.core.utils.Bech32Utils;
import com.shitouren.core.utils.CommonUtils;
import com.shitouren.core.utils.GasProvider;
import com.shitouren.core.utils.Web3jUtils;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.crypto.*;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tx.Contract;
import sun.security.provider.SecureRandom;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
public class BaseService {
    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

    protected SignEventListener signEventListener;

    /**
     * 获取区块信息
     *
     * @param blockNumber 区块高度
     * @return 区块信息
     * @throws IOException
     */
    public EthBlock.Block getBlockByNumber(BigInteger blockNumber) throws IOException {
        return Web3jUtils.getWeb3j().ethGetBlockByNumber(CommonUtils.getDefaultBlockParamter(blockNumber.toString()), true).send().getBlock();
    }

    /**
     * 获取最新的块高
     *
     * @return 区块高度
     * @throws IOException
     */
    public BigInteger getLatestBlockNumber() throws IOException {
        return Web3jUtils.getWeb3j().ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock().getNumber();
    }

    /**
     * 查询交易回执
     *
     * @param hash 交易哈希
     * @return 交易回执
     * @throws InterruptedException
     */
    public TransactionReceipt getTransReceipt(String hash) throws InterruptedException, ExecutionException {
        return Web3jUtils.getWeb3j().ethGetTransactionReceipt(hash).sendAsync().get().getTransactionReceipt().get();
    }

    /**
     * 查询交易信息
     *
     * @param hash 交易哈希
     * @return 交易信息
     * @throws IOException
     */
    public TxInfo getTransByHash(String hash) throws IOException {
        Transaction transaction = Web3jUtils.getWeb3j().ethGetTransactionByHash(hash).send().getTransaction().get();
        return new TxInfo(transaction);
    }

    /**
     * 查询交易状态
     *
     * @param hash 交易哈希
     * @return 交易状态
     * @throws IOException
     */
    public Boolean getTransByStatus(String hash) throws IOException {
        return Web3jUtils.getWeb3j().ethGetTransactionReceipt(hash).send().getTransactionReceipt().get().isStatusOK();
    }

    /**
     * 初始化gasLimit集合
     *
     * @param gasLimit
     */
    public void setFuncGasLimit(String gasLimit) {
        ConfigCache.get().setFuncGasLimit(gasLimit);
    }

    /**
     * 签名并发送
     *
     * @param contract          合约实例
     * @param functionName      调用的方法名
     * @param encodedFunction   经过RLP序列化编码的function
     * @param signEventListener 负责签名的实例
     * @return EthSendTransaction 交易的结果
     * @throws ExecutionException
     */
    public EthSendTransaction signAndSend(Contract contract, String functionName, String encodedFunction, SignEventListener signEventListener, String sender) throws ExecutionException, InterruptedException {

        Web3j web3j = Web3jUtils.getWeb3j();
        GasProvider gasProvider = new GasProvider();

        BigInteger gasPrice = gasProvider.getGasPrice();
        BigInteger gasLimit = gasProvider.getGasLimit(functionName);

        //目标合约地址
        String contractAddr = contract.getContractAddress();

        // 获取调用者的交易笔数
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(sender, DefaultBlockParameterName.PENDING).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        // 生成待签名的交易
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddr, encodedFunction);

        SignEvent signEvent = new SignEvent(sender, rawTransaction);

        // 调用签名方法，获取签名后的hexString
        String signedMessage = signEventListener.signEvent(signEvent);

        // 向链上发送交易
        EthSendTransaction sendTransaction = web3j.ethSendRawTransaction(signedMessage).sendAsync().get();
        // 捕获链上返回的异常
        Response.Error error = sendTransaction.getError();
        if (error != null) {
            throw new DDCException(error.getCode(), error.getMessage());
        }
        // 返回交易结果
        return sendTransaction;
    }

    /**
     * 平台方或终端用户通过该方法进行离线账户生成。
     *
     * @return 返回 Account
     */
    public Account createAccountHex() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
        secureRandom.engineNextBytes(entropy);
        List<String> str = null;
        try {
            str = MnemonicCode.INSTANCE.toMnemonic(entropy);
        } catch (MnemonicException.MnemonicLengthException e) {
            e.printStackTrace();
        }
        byte[] seed = MnemonicCode.toSeed(str, "");
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy.deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        String addr = Keys.getAddress(keyPair.getPublicKey());
        return new Account(str.toString(), keyPair.getPublicKey().toString(16), keyPair.getPrivateKey().toString(16), addr);
    }

    /**
     * 平台方或终端用户通过该方法进行HEX格式账户转换。
     *
     * @param addr HEX格式账户
     * @return 返回Bech32格式账户
     */
    public String accountHexToBech32(String addr) {
        String hrp = "iaa";
        return Bech32Utils.hexToBech32(hrp, addr);
    }

    /**
     * 平台方或终端用户通过该方法进行Bech32格式账户转换。
     *
     * @param addr Bech32格式账户
     * @return 返回HEX格式账户
     */
    public String accountBech32ToHex(String addr) {
        return Bech32Utils.bech32ToHex(addr);
    }
}
