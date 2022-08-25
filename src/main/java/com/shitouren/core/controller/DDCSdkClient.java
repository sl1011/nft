package com.shitouren.core.controller;



import com.shitouren.core.config.ConfigCache;
import com.shitouren.core.constant.ErrorMessage;
import com.shitouren.core.exception.DDCException;
import com.shitouren.core.listener.SignEventListener;
import com.shitouren.core.service.AuthorityService;
import com.shitouren.core.service.ChargeService;
import com.shitouren.core.service.DDC1155Service;
import com.shitouren.core.service.DDC721Service;
import com.shitouren.core.utils.Web3jUtils;

public class DDCSdkClient {
    private SignEventListener signEventListener;

    /**
     * SDK 初始化方法
     */
    private DDCSdkClient(Builder builder) {
        this.signEventListener = builder.signEventListener;
    }

    public static class Builder {
        private String gasPrice;
        private String gasLimit;
        private String ddc721Address;
        private String ddc1155Address;
        private String authorityLogicAddress;
        private String chargeLogicAddress;
        private SignEventListener signEventListener;



        public Builder setGasPrice(String gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public Builder setGasLimit(String gasLimit) {
            this.gasLimit = gasLimit;
            return this;
        }

        public Builder setDDC721Address(String ddc721Address) {
            this.ddc721Address = ddc721Address;
            return this;
        }

        public Builder setDDC1155Address(String ddc1155Address) {
            this.ddc1155Address = ddc1155Address;
            return this;
        }

        public Builder setAuthorityLogicAddress(String authorityLogicAddress) {
            this.authorityLogicAddress = authorityLogicAddress;
            return this;
        }

        public Builder setChargeLogicAddress(String chargeLogicAddress) {
            this.chargeLogicAddress = chargeLogicAddress;
            return this;
        }

        public Builder setSignEventListener(SignEventListener signEventListener) {
            if (signEventListener == null) {
                throw new DDCException(ErrorMessage.NO_SIGN_EVENT_LISTNER);
            }
            this.signEventListener = signEventListener;
            return this;
        }

        public DDCSdkClient init() {
            ConfigCache.initCache(gasPrice, gasLimit, ddc721Address, ddc1155Address, authorityLogicAddress, chargeLogicAddress);
            return new DDCSdkClient(this);
        }
    }

    public Boolean setGatewayUrl(String gatewayUrl) {
        if (gatewayUrl.isEmpty()) {
            return false;
        }
        ConfigCache.get().setOpbGatewayAddress(gatewayUrl);
        Web3jUtils.reset();
        return true;
    }

    public Boolean setGatewayApiKey(String apiKey) {
        if (apiKey.isEmpty()) {
            return false;
        }
        ConfigCache.get().setHeaderKey(apiKey);
        Web3jUtils.reset();
        return true;
    }

    public Boolean setGatewayApiValue(String apiValue) {
        if (apiValue.isEmpty()) {
            return false;
        }
        ConfigCache.get().setHeaderValue(apiValue);
        Web3jUtils.reset();
        return true;
    }

    /**
     * 获取权限管理服务的示例
     *
     * @return 返回权限管理服务的实例
     */
    public AuthorityService getAuthorityService() {
        return new AuthorityService(signEventListener);
    }

    /**
     * 获取费用管理服务的实例
     *
     * @return 返回费用管理服务的实例
     */
    public ChargeService getChargeService() {
        return new ChargeService(signEventListener);
    }

    /**
     * 获取BSN-DDC-1155合约服务的实例
     *
     * @return 返回BSN-DDC-1155合约服务的实例
     */
    public DDC1155Service getDDC1155Service() {
        return new DDC1155Service(signEventListener);
    }

    /**
     * 获取BSN-DDC-721合约服务的实例
     *
     * @return 返回BSN-DDC-721合约服务的实例
     */
    public DDC721Service getDDC721Service() {
        return new DDC721Service(signEventListener);
    }

}
