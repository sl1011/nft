package com.shitouren.core.utils;


import com.shitouren.core.config.ConfigCache;
import com.shitouren.core.contract.Authority;
import com.shitouren.core.contract.Charge;
import com.shitouren.core.contract.DDC1155;
import com.shitouren.core.contract.DDC721;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Strings;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Web3jUtils {
    private static Web3j web3j;
    private static Authority authority;
    private static Charge charge;
    private static DDC721 ddc721;
    private static DDC1155 ddc1155;

    private static ECKeyPair ecKeyPair;

    static {
        try {
            ecKeyPair = Keys.createEcKeyPair();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    private Web3jUtils() {
    }

    public static Authority getAuthority() {
        if (authority == null) {
            authority = Authority.load(ConfigCache.get().getAuthorityLogicAddress(), Web3jUtils.getWeb3j(), Credentials.create(ecKeyPair), new GasProvider());
        }
        return authority;
    }

    public static Charge getCharge() {
        if (charge == null) {
            charge = Charge.load(ConfigCache.get().getChargeLogicAddress(), Web3jUtils.getWeb3j(), Credentials.create(ecKeyPair), new GasProvider());
        }
        return charge;
    }

    public static DDC721 getDDC721() {
        if (ddc721 == null) {
            ddc721 = DDC721.load(ConfigCache.get().getDdc721Address(), Web3jUtils.getWeb3j(), Credentials.create(ecKeyPair), new GasProvider());
        }
        return ddc721;
    }

    public static DDC1155 getDDC1155() {
        if (ddc1155 == null) {
            ddc1155 = DDC1155.load(ConfigCache.get().getDdc1155Address(), Web3jUtils.getWeb3j(), Credentials.create(ecKeyPair), new GasProvider());
        }
        return ddc1155;
    }

    public static Web3j getWeb3j() {

        if (web3j == null) {

            HttpService httpService = new HttpService(ConfigCache.get().getOpbGatewayAddress());
            if (!Strings.isEmpty(ConfigCache.get().getHeaderKey()) && !Strings.isEmpty(ConfigCache.get().getHeaderValue())) {
                httpService.addHeader(ConfigCache.get().getHeaderKey(), ConfigCache.get().getHeaderValue());
            }
            web3j = Web3j.build(httpService);
        }

        return web3j;
    }

    public static void reset() {
        web3j = null;
        authority = null;
        charge = null;
        ddc721 = null;
        ddc1155 = null;
    }
}
