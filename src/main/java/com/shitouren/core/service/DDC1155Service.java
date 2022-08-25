package com.shitouren.core.service;


import com.google.common.collect.Multimap;
import com.shitouren.core.constant.ErrorMessage;
import com.shitouren.core.contract.DDC1155;
import com.shitouren.core.exception.DDCException;
import com.shitouren.core.listener.SignEventListener;
import com.shitouren.core.utils.AddressUtils;
import com.shitouren.core.utils.Web3jUtils;
import org.web3j.utils.Strings;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class DDC1155Service extends BaseService {
    private final DDC1155 ddc1155;
    private String encodedFunction;

    public DDC1155Service(SignEventListener signEventListener) {
        super.signEventListener = signEventListener;
        this.ddc1155 = Web3jUtils.getDDC1155();
    }

    /**
     * 安全生成DDC
     * 平台方或终端用户可以通过调用该方法进行DDC的安全生成。
     *
     * @param sender 调用者地址
     * @param to     接收者账户
     * @param amount DDC数量
     * @param ddcURI DDCURI
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String safeMint(String sender, String to, BigInteger amount, String ddcURI, byte[] data) throws Exception {
        //1.检查sender为标准备address格式
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        //2.检查接收者账户地址是否为空
        if (Strings.isEmpty(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        //3.检查接收者账户地址是否正确
        if (!AddressUtils.isValidAddress(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        //4.检查需要生成的DDC数量是否大于0
        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }
        //5.检查ddcURI是否为空
        if (Strings.isEmpty(ddcURI)) {
            throw new DDCException(ErrorMessage.DDCURI_IS_EMPTY);
        }

        encodedFunction = ddc1155.safeMint(to, amount, ddcURI, data).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_SAFEMINT, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 批量安全生成
     * 平台方或终端用户可以通过调用该方法进行DDC的批量安全生成。
     *
     * @param sender  调用者地址
     * @param to      接收者账户
     * @param ddcInfo DDC信息
     * @param data    附加数据
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String safeMintBatch(String sender, String to, Multimap<BigInteger, String> ddcInfo, byte[] data) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (Strings.isEmpty(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(to)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        List<BigInteger> amounts = new ArrayList<>();
        List<String> ddcURIS = new ArrayList<>();

        ddcInfo.forEach((amount, ddcURI) -> {
            //检查生成的DDC数量集合中每个DDC数量是否大于0；
            if (amount == null || amount.intValue() <= 0) {
                throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
            }
            //检查生成的ddcURI集合中每个ddcURI是否为空；
            if (Strings.isEmpty(ddcURI)) {
                throw new DDCException(ErrorMessage.DDCURI_IS_EMPTY);
            }
            amounts.add(amount);
            ddcURIS.add(ddcURI);

        });

        encodedFunction = ddc1155.safeMintBatch(to, amounts, ddcURIS, data).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_SAFEMINTBATCH, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 账户授权
     * DDC拥有者可以通过调用该方法进行账户授权，发起者需要是DDC的拥有者。
     *
     * @param sender   调用者地址
     * @param operator 授权者账户
     * @param approved 授权标识
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String setApprovalForAll(String sender, String operator, Boolean approved) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (Strings.isEmpty(operator)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(operator)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        encodedFunction = ddc1155.setApprovalForAll(operator, approved).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_SETAPPROVALFORALL, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 账户授权查询
     * 运营方、平台方或终端用户可以通过调用该方法进行账户授权查询。
     *
     * @param owner    拥有者账户
     * @param operator 授权者账户
     * @return 授权结果（boolean）
     * @throws Exception Exception
     */
    public Boolean isApprovedForAll(String owner, String operator) throws Exception {
        if (Strings.isEmpty(owner)) {
            throw new DDCException(ErrorMessage.FROM_ACCOUNT_IS_EMPTY);
        }
        if (Strings.isEmpty(operator)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(owner)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (!AddressUtils.isValidAddress(operator)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        return Web3jUtils.getDDC1155().isApprovedForAll(owner, operator).send();
    }

    /**
     * 安全转移
     * DDC拥有者或DDC授权者可以通过调用该方法进行DDC的转移。
     *
     * @param sender 调用者地址
     * @param from   拥有者账户
     * @param to     接收者账户
     * @param ddcId  DDCID
     * @param amount 需要转移的DDC数量
     * @param data   附加数据
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String safeTransferFrom(String sender, String from, String to, BigInteger ddcId, BigInteger amount, byte[] data) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (Strings.isEmpty(from)) {
            throw new DDCException(ErrorMessage.FROM_ACCOUNT_IS_EMPTY);
        }
        if (Strings.isEmpty(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(from) || !AddressUtils.isValidAddress(to)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (ddcId == null || ddcId.intValue() <= 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }
        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }

        encodedFunction = ddc1155.safeTransferFrom(from, to, ddcId, amount, data).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_SAFETRANSFERFROM, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 批量安全转移
     * DDC拥有者或DDC授权者可以通过调用该方法进行DDC的批量转移。
     *
     * @param sender 调用者地址
     * @param from   拥有者账户
     * @param to     接收者账户
     * @param ddcs   拥有者的ddcID集合
     * @param data   附加数据
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String safeBatchTransferFrom(String sender, String from, String to, Multimap<BigInteger, BigInteger> ddcs, byte[] data) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (Strings.isEmpty(from)) {
            throw new DDCException(ErrorMessage.FROM_ACCOUNT_IS_EMPTY);
        }
        if (Strings.isEmpty(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(from) || !AddressUtils.isValidAddress(to)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (ddcs == null) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        ArrayList<BigInteger> ddcIds = new ArrayList();
        ArrayList<BigInteger> amounts = new ArrayList();
        ddcs.forEach((ddcId, amount) -> {
            if (ddcId == null || ddcId.intValue() <= 0) {
                throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
            }
            if (amount == null || amount.intValue() <= 0) {
                throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
            }
            ddcIds.add(ddcId);
            amounts.add(amount);
        });

        encodedFunction = ddc1155.safeBatchTransferFrom(from, to, ddcIds, amounts, data).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_SAFEBATCHTRANSFERFROM, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 冻结
     * 运营方可以通过调用该方法进行DDC的冻结。
     *
     * @param sender 调用者地址
     * @param ddcId  DDC唯一标识
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String freeze(String sender, BigInteger ddcId) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (ddcId == null || ddcId.intValue() <= 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        encodedFunction = ddc1155.freeze(ddcId).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_FREEZE, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 解冻
     * 运营方可以通过调用该方法进行DDC的解冻。
     *
     * @param sender 调用者地址
     * @param ddcId  DDC唯一标识
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String unFreeze(String sender, BigInteger ddcId) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (ddcId == null || ddcId.intValue() <= 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        encodedFunction = ddc1155.unFreeze(ddcId).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_UNFREEZE, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 销毁
     * DDC拥有者可以通过调用该方法进行DDC的销毁。
     *
     * @param sender 调用者地址
     * @param owner  拥有者账户
     * @param ddcId  DDC唯一标识
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String burn(String sender, String owner, BigInteger ddcId) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (Strings.isEmpty(owner)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(owner)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (ddcId == null || ddcId.intValue() <= 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        encodedFunction = ddc1155.burn(owner, ddcId).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_BURN, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 批量销毁
     * DDC拥用者可以通过调用该方法进行DDC的批量销毁。
     *
     * @param sender 调用者地址
     * @param owner  拥有者账户
     * @param ddcIds DDC唯一标识的集合
     * @return 交易哈希
     * @throws Exception Exception
     */
    public String burnBatch(String sender, String owner, List<BigInteger> ddcIds) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (Strings.isEmpty(owner)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(owner)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (ddcIds == null) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        encodedFunction = ddc1155.burnBatch(owner, ddcIds).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_BURNBATCH, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 查询数量
     * 运营方、平台方以及终端用户可以通过调用该方法进行查询当前账户拥有的DDC的数量。
     *
     * @param owner 拥有者账户
     * @param ddcId DDC唯一标识
     * @return 拥有者账户拥有对应的ddcId的数量
     * @throws Exception
     */
    public BigInteger balanceOf(String owner, BigInteger ddcId) throws Exception {

        if (Strings.isEmpty(owner)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }
        if (!AddressUtils.isValidAddress(owner)) {
            throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }
        if (ddcId == null || ddcId.intValue() <= 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        return Web3jUtils.getDDC1155().balanceOf(owner, ddcId).send();
    }

    /**
     * 批量查询数量
     * 运营方、平台方以及终端用户可以通过调用该方法进行批量查询账户拥有的DDC的数量。
     *
     * @param ddcs 拥有者ddcID集合
     * @return 拥有者账户拥有对应的ddcId的数量
     * @throws Exception
     */
    public List balanceOfBatch(Multimap<String, BigInteger> ddcs) throws Exception {

        if (ddcs == null || ddcs.size() == 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        ArrayList<String> owners = new ArrayList<>();
        ArrayList<BigInteger> ddcIds = new ArrayList<>();
        ddcs.forEach((owner, ddcId) -> {
            if (Strings.isEmpty(owner)) {
                throw new DDCException(ErrorMessage.ACC_ADDR_IS_EMPTY);
            }
            if (!AddressUtils.isValidAddress(owner)) {
                throw new DDCException(ErrorMessage.ACCOUNT_IS_NOT_ADDRESS_FORMAT);
            }
            if (ddcId == null || ddcId.intValue() <= 0) {
                throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
            }
            owners.add(owner);
            ddcIds.add(ddcId);
        });

        return Web3jUtils.getDDC1155().balanceOfBatch(owners, ddcIds).send();
    }

    /**
     * 获取ddcURI
     * 运营方、平台方以及终端用户可以通过调用该方法进行查询当前DDC的资源标识符。
     *
     * @param ddcId ddcId
     * @return ddcURI DDC唯一标识
     * @throws Exception Exception
     */
    public String ddcURI(BigInteger ddcId) throws Exception {
        if (ddcId == null || ddcId.intValue() <= 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        return Web3jUtils.getDDC1155().ddcURI(ddcId).send();
    }

    /**
     * URI设置
     * DDC拥有者通过调用该方法对DDC的资源标识符进行设置。
     *
     * @param sender 调用者地址
     * @param owner  拥有者
     * @param ddcId  DDC唯一标识
     * @param ddcURI DDC资源标识符
     * @return 返回交易哈希
     * @throws Exception Exception
     */
    public String setURI(String sender, String owner, BigInteger ddcId, String ddcURI) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (!AddressUtils.isValidAddress(owner)) {
            throw new DDCException(ErrorMessage.OWNER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (ddcId == null || ddcId.intValue() <= 0) {
            throw new DDCException(ErrorMessage.DDCID_IS_WRONG);
        }

        if (Strings.isEmpty(ddcURI)) {
            throw new DDCException(ErrorMessage.DDCURI_IS_EMPTY);
        }

        encodedFunction = ddc1155.setURI(owner, ddcId, ddcURI).encodeFunctionCall();
        return signAndSend(ddc1155, DDC1155.FUNC_SETURI, encodedFunction, signEventListener, sender).getTransactionHash();
    }
}
