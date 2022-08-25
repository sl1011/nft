package com.shitouren.core.service;


import com.shitouren.core.constant.ErrorMessage;
import com.shitouren.core.contract.Charge;
import com.shitouren.core.exception.DDCException;
import com.shitouren.core.listener.SignEventListener;
import com.shitouren.core.utils.AddressUtils;
import com.shitouren.core.utils.HexUtils;
import com.shitouren.core.utils.Web3jUtils;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;

import java.math.BigInteger;

public class ChargeService extends BaseService {
    private Charge charge;
    private String encodedFunction;

    public ChargeService(SignEventListener signEventListener) {
        super.signEventListener = signEventListener;
        this.charge = Web3jUtils.getCharge();
    }


    /**
     * 运营方、平台方调用该接口为所属同一方的同一级别账户或者下级账户充值；
     *
     * @param sender 调用者地址
     * @param to     充值账户的地址
     * @param amount 充值金额
     * @return 返回交易哈希
     * @throws Exception
     */
    public String recharge(String sender, String to, BigInteger amount) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(to)) {
            throw new DDCException(ErrorMessage.TO_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }
        encodedFunction = charge.recharge(to, amount).encodeFunctionCall();

        return signAndSend(charge, Charge.FUNC_RECHARGE, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 查询指定账户的余额。
     *
     * @param accAddr 查询的账户地址
     * @return 返回账户所对应的业务费余额
     * @throws Exception
     */
    public BigInteger balanceOf(String accAddr) throws Exception {

        if (Strings.isEmpty(accAddr)) {
            throw new DDCException(ErrorMessage.ACC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(accAddr)) {
            throw new DDCException(ErrorMessage.ACC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        return Web3jUtils.getCharge().balanceOf(accAddr).send();
    }

    /**
     * 查询指定的DDC业务主逻辑合约的方法所对应的调用业务费用。
     *
     * @param ddcAddr DDC业务合约地址
     * @param sig     Hex格式的合约方法ID
     * @return 返回查询的DDC合约业务费
     * @throws Exception
     */
    public BigInteger queryFee(String ddcAddr, String sig) throws Exception {

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_EMPTY);
        }

        if (!HexUtils.isValid4ByteHash(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_NOT_4BYTE_HASH);
        }
        byte[] sigInByte = Numeric.hexStringToByteArray(sig);

        return Web3jUtils.getCharge().queryFee(ddcAddr, sigInByte).send();
    }

    /**
     * 运营方调用为自己的账户增加业务费。
     *
     * @param sender 调用者地址
     * @param amount 对运营方账户进行充值的业务费
     * @return 返回交易哈希
     * @throws Exception
     */
    public String selfRecharge(String sender, BigInteger amount) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }
        encodedFunction = charge.selfRecharge(amount).encodeFunctionCall();

        return signAndSend(charge, Charge.FUNC_SELFRECHARGE, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 运营方调用接口设置指定的DDC主合约的方法调用费用。
     *
     * @param sender  调用者地址
     * @param ddcAddr DDC业务合约地址
     * @param sig     Hex格式的合约方法ID
     * @param amount  业务费用
     * @return 返回交易哈希
     * @throws Exception
     */
    public String setFee(String sender, String ddcAddr, String sig, BigInteger amount) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_EMPTY);
        }

        if (!HexUtils.isValid4ByteHash(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_NOT_4BYTE_HASH);
        }

        if (amount == null || amount.intValue() <= 0) {
            throw new DDCException(ErrorMessage.AMOUNT_IS_EMPTY);
        }
        byte[] sigInByte = Numeric.hexStringToByteArray(sig);
        encodedFunction = charge.setFee(ddcAddr, sigInByte, amount).encodeFunctionCall();

        return signAndSend(charge, Charge.FUNC_SETFEE, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 运营方调用接口删除指定的DDC主合约的方法调用费用。
     *
     * @param sender  调用者地址
     * @param ddcAddr DDC业务合约地址
     * @param sig     Hex格式的合约方法ID
     * @return 返回交易哈希
     * @throws Exception
     */
    public String delFee(String sender, String ddcAddr, String sig) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_EMPTY);
        }

        if (!HexUtils.isValid4ByteHash(sig)) {
            throw new DDCException(ErrorMessage.SIG_IS_NOT_4BYTE_HASH);
        }

        byte[] sigInByte = Numeric.hexStringToByteArray(sig);
        encodedFunction = charge.delFee(ddcAddr, sigInByte).encodeFunctionCall();

        return signAndSend(charge, Charge.FUNC_DELFEE, encodedFunction, signEventListener, sender).getTransactionHash();
    }

    /**
     * 运营方调用该接口删除指定的DDC业务主逻辑合约授权。
     *
     * @param sender  调用者地址'
     * @param ddcAddr DDC业务合约地址
     * @return 返回交易哈希
     * @throws Exception
     */
    public String delDDC(String sender, String ddcAddr) throws Exception {
        if (!AddressUtils.isValidAddress(sender)) {
            throw new DDCException(ErrorMessage.SENDER_ACCOUNT_IS_NOT_ADDRESS_FORMAT);
        }

        if (Strings.isEmpty(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_EMPTY);
        }

        if (!AddressUtils.isValidAddress(ddcAddr)) {
            throw new DDCException(ErrorMessage.DDC_ADDR_IS_NOT_ADDRESS_FORMAT);
        }

        encodedFunction = charge.delDDC(ddcAddr).encodeFunctionCall();

        return signAndSend(charge, Charge.FUNC_DELDDC, encodedFunction, signEventListener, sender).getTransactionHash();
    }

}