package com.shitouren.core.service;


import com.shitouren.core.constant.ErrorMessage;
import com.shitouren.core.contract.Authority;
import com.shitouren.core.dto.AccountInfo;
import com.shitouren.core.exception.DDCException;
import com.shitouren.core.listener.SignEventListener;
import com.shitouren.core.utils.AddressUtils;
import com.shitouren.core.utils.Web3jUtils;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.utils.Strings;

import java.math.BigInteger;


public class AuthorityService extends BaseService {
    private Authority authority;
    private String encodedFunction;

    public AuthorityService(SignEventListener signEventListener) {
        super.signEventListener = signEventListener;
        this.authority = Web3jUtils.getAuthority();
    }

    /**
     * 运营方可以通过调用该方法直接对平台方或平台方的终端用户进行创建。
     *
     * @param sender    调用者地址
     * @param account   DDC链账户地址
     * @param accName   DDC账户对应的账户名称
     * @param accDID    DDC账户对应的DID信息
     * @param leaderDID 该普通账户对应的上级账户的DID
     * @return 返回交易哈希
     * @throws Exception
     */
    public String addAccountByOperator(String sender, String account, String accName, String accDID, String leaderDID) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(account)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(account)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(accName)) {
            throw new DDCException(ErrorMessage.ACCOUNT_NAME_IS_EMPTY);
        }

        encodedFunction = authority.addAccountByOperator(account, accName, accDID, leaderDID).encodeFunctionCall();
        return signAndSend(authority, Authority.FUNC_ADDACCOUNTBYOPERATOR, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 删除账户
     *
     * @param account DDC链账户地址
     * @param sender  此方法暂不使用
     * @return 返回交易哈希
     * @throws Exception
     */
    public String delAccount(String sender, String account) throws Exception {
        throw new DDCException(ErrorMessage.UNKNOWN_ERROR);
    }


    /**
     * 运营方、平台方以及终端用户可以通过调用该方法进行DDC账户信息的查询。
     *
     * @param account DDC用户链账户地址
     * @return 返回DDC账户信息
     * @throws Exception
     */
    public AccountInfo getAccount(String account) throws Exception {
        if (Strings.isEmpty(account)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(account)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        Tuple7<String, String, BigInteger, String, BigInteger, BigInteger, String> res = Web3jUtils.getAuthority().getAccount(account).send();
        return new AccountInfo(res.component1(), res.component2(), res.component3().toString(), res.component4(), res.component5().toString(), res.component6().toString(), res.component7());
    }

    /**
     * 运营方或平台方可以通过调用该方法对终端用户进行DDC账户信息状态的更改。
     *
     * @param sender              调用者地址
     * @param account             DDC用户链账户地址
     * @param state               状态 ：Frozen - 冻结状态 ； Active - 活跃状态
     * @param changePlatformState 修改平台方状态标识
     * @return 返回交易哈希
     * @throws Exception
     */
    public String updateAccState(String sender, String account, BigInteger state, boolean changePlatformState) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(account)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(account)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (state == null) {
            throw new DDCException(ErrorMessage.ACCOUNT_STASTUS_IS_EMPTY);
        }

        encodedFunction = authority.updateAccountState(account, state, changePlatformState).encodeFunctionCall();
        return signAndSend(authority, Authority.FUNC_UPDATEACCOUNTSTATE, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 运营方可以通过调用该方法对DDC的跨平台操作进行授权。
     *
     * @param sender   调用者地址
     * @param from     授权者
     * @param to       接收者
     * @param approved 授权标识
     * @return 返回交易哈希
     * @throws Exception
     */
    public String crossPlatformApproval(String sender, String from, String to, Boolean approved) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(from)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(from)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(to)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(to)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        encodedFunction = authority.crossPlatformApproval(from, to, approved).encodeFunctionCall();
        return signAndSend(authority, Authority.FUNC_CROSSPLATFORMAPPROVAL, encodedFunction, signEventListener, sender).getTransactionHash();
    }

}
