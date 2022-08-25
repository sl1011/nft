package com.shitouren.core.utils;

import org.bitcoinj.core.AddressFormatException;
import org.web3j.crypto.WalletUtils;

import java.io.ByteArrayOutputStream;

public class AddressUtils {

    private AddressUtils() {
    }

    /**
     * 验证传入参数是否为有效的区块链账户地址格式
     *
     * @param address 地址
     * @return 返回验证结果，true或者false。
     */
    public static boolean isValidAddress(String address) {
        return WalletUtils.isValidAddress(address);
    }

    /**
     * Helper for re-arranging bits into groups.
     *
     * @param in       数组
     * @param inStart  开始
     * @param inLen    数组长度
     * @param fromBits 转换前
     * @param toBits   转换后
     * @param pad      是否转换
     * @return 返回byte数组
     * @throws AddressFormatException
     */
    public static byte[] convertBits(final byte[] in, final int inStart, final int inLen, final int fromBits,
                                     final int toBits, final boolean pad) throws AddressFormatException {
        int acc = 0;
        int bits = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        final int maxv = (1 << toBits) - 1;
        final int max_acc = (1 << (fromBits + toBits - 1)) - 1;
        for (int i = 0; i < inLen; i++) {
            int value = in[i + inStart] & 0xff;
            if ((value >>> fromBits) != 0) {
                throw new AddressFormatException(
                        String.format("Input value '%X' exceeds '%d' bit size", value, fromBits));
            }
            acc = ((acc << fromBits) | value) & max_acc;
            bits += fromBits;
            while (bits >= toBits) {
                bits -= toBits;
                out.write((acc >>> bits) & maxv);
            }
        }
        if (pad) {
            if (bits > 0) {
                out.write((acc << (toBits - bits)) & maxv);
            }
        } else if (bits >= fromBits || ((acc << (toBits - bits)) & maxv) != 0) {
            throw new AddressFormatException("Could not convert bits, invalid padding");
        }
        return out.toByteArray();
    }
}
