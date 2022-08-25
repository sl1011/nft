package com.shitouren.core.config;


import com.shitouren.core.contract.Authority;
import com.shitouren.core.contract.Charge;
import com.shitouren.core.contract.DDC1155;
import com.shitouren.core.contract.DDC721;

import java.util.HashMap;
import java.util.Map;

public class ConfigInfo {
    private Map<String, String> map = new HashMap<>();

    private String opbGatewayAddress = "http://192.168.150.43:8545";

    private String headerKey;

    private String headerValue;

    private String gasPrice = "10000000";

    private String gasLimit = "300000";

    private String funcGasLimit = "0";


    //authority合约
    private String authorityLogicBIN;
    private String authorityLogicAddress = "0x368d4064762a22640E8a79cA6B62F0815f3e2F9C";

    //charge合约
    private String chargeLogicBIN;
    private String chargeLogicAddress = "0xcA65BAbCB23053B0b1Fb3bB2d79E487e56e1FBC4";

    //721合约
    private String ddc721BIN;
    private String ddc721Address = "0x02d40d287C851b760342126922f5D239321Dc4BC";

    //1155合约
    private String ddc1155BIN;
    private String ddc1155Address = "0xf6Bc71043a176114A8E4cfF686D6F417b971d5bA";

    public ConfigInfo() {
        map.put(Authority.FUNC_ACCOUNTAVAILABLE, "0");
        map.put(Authority.FUNC_ADDACCOUNTBYOPERATOR, "0");
        map.put(Authority.FUNC_ADDFUNCTION, "0");
        map.put(Authority.FUNC_ADDOPERATOR, "0");
        map.put(Authority.FUNC_CHECKAVAILABLEANDROLE, "0");
        map.put(Authority.FUNC_DELFUNCTION, "0");
        map.put(Authority.FUNC_GETACCOUNT, "0");
        map.put(Authority.FUNC_GETFUNCTIONS, "0");
        map.put(Authority.FUNC_HASFUNCTIONPERMISSION, "0");
        map.put(Authority.FUNC_INITIALIZE, "0");
        map.put(Authority.FUNC_ONEPLATFORMCHECK, "0");
        map.put(Authority.FUNC_OWNER, "0");
        map.put(Authority.FUNC_RENOUNCEOWNERSHIP, "0");
        map.put(Authority.FUNC_TRANSFEROWNERSHIP, "0");
        map.put(Authority.FUNC_UPGRADETO, "0");
        map.put(Authority.FUNC_UPDATEACCOUNTSTATE, "0");
        map.put(Authority.FUNC_UPGRADETOANDCALL, "0");
        map.put(Authority.FUNC_CROSSPLATFORMAPPROVAL, "0");
        map.put(Authority.FUNC_CROSSPLATFORMCHECK, "0");

        map.put(Charge.FUNC_BALANCEOF, "0");
        map.put(Charge.FUNC_DELDDC, "0");
        map.put(Charge.FUNC_DELFEE, "0");
        map.put(Charge.FUNC_INITIALIZE, "0");
        map.put(Charge.FUNC_OWNER, "0");
        map.put(Charge.FUNC_PAY, "0");
        map.put(Charge.FUNC_QUERYFEE, "0");
        map.put(Charge.FUNC_RECHARGE, "0");
        map.put(Charge.FUNC_RENOUNCEOWNERSHIP, "0");
        map.put(Charge.FUNC_SELFRECHARGE, "0");
        map.put(Charge.FUNC_SETAUTHORITYPROXYADDRESS, "0");
        map.put(Charge.FUNC_SETFEE, "0");
        map.put(Charge.FUNC_SETTLEMENT, "0");
        map.put(Charge.FUNC_TOTALSUPPLY, "0");
        map.put(Charge.FUNC_TRANSFEROWNERSHIP, "0");
        map.put(Charge.FUNC_UPGRADETO, "0");
        map.put(Charge.FUNC_UPGRADETOANDCALL, "0");
        map.put(DDC721.FUNC_APPROVE, "0");
        map.put(DDC721.FUNC_BALANCEOF, "0");
        map.put(DDC721.FUNC_BURN, "0");
        map.put(DDC721.FUNC_DDCURI, "0");
        map.put(DDC721.FUNC_FREEZE, "0");
        map.put(DDC721.FUNC_GETAPPROVED, "0");
        map.put(DDC721.FUNC_INITIALIZE, "0");
        map.put(DDC721.FUNC_ISAPPROVEDFORALL, "0");
        map.put(DDC721.FUNC_MINT, "0");
        map.put(DDC721.FUNC_NAME, "0");
        map.put(DDC721.FUNC_OWNER, "0");
        map.put(DDC721.FUNC_OWNEROF, "0");
        map.put(DDC721.FUNC_RENOUNCEOWNERSHIP, "0");
        map.put(DDC721.FUNC_SAFEMINT, "0");
        map.put(DDC721.FUNC_SAFETRANSFERFROM, "0");
        map.put(DDC721.FUNC_SETAPPROVALFORALL, "0");
        map.put(DDC721.FUNC_SETAUTHORITYPROXYADDRESS, "0");
        map.put(DDC721.FUNC_SETCHARGEPROXYADDRESS, "0");
        map.put(DDC721.FUNC_SETNAMEANDSYMBOL, "0");
        map.put(DDC721.FUNC_SUPPORTSINTERFACE, "0");
        map.put(DDC721.FUNC_SYMBOL, "0");
        map.put(DDC721.FUNC_TRANSFERFROM, "0");
        map.put(DDC721.FUNC_TRANSFEROWNERSHIP, "0");
        map.put(DDC721.FUNC_UNFREEZE, "0");
        map.put(DDC721.FUNC_UPGRADETO, "0");
        map.put(DDC721.FUNC_UPGRADETOANDCALL, "0");
        map.put(DDC721.FUNC_SETURI, "0");
        map.put(DDC1155.FUNC_BALANCEOF, "0");
        map.put(DDC1155.FUNC_BALANCEOFBATCH, "0");
        map.put(DDC1155.FUNC_BURN, "0");
        map.put(DDC1155.FUNC_BURNBATCH, "0");
        map.put(DDC1155.FUNC_DDCURI, "0");
        map.put(DDC1155.FUNC_FREEZE, "0");
        map.put(DDC1155.FUNC_INITIALIZE, "0");
        map.put(DDC1155.FUNC_ISAPPROVEDFORALL, "0");
        map.put(DDC1155.FUNC_OWNER, "0");
        map.put(DDC1155.FUNC_RENOUNCEOWNERSHIP, "0");
        map.put(DDC1155.FUNC_SAFEBATCHTRANSFERFROM, "0");
        map.put(DDC1155.FUNC_SAFEMINT, "0");
        map.put(DDC1155.FUNC_SAFEMINTBATCH, "0");
        map.put(DDC1155.FUNC_SAFETRANSFERFROM, "0");
        map.put(DDC1155.FUNC_SETAPPROVALFORALL, "0");
        map.put(DDC1155.FUNC_SETAUTHORITYPROXYADDRESS, "0");
        map.put(DDC1155.FUNC_SETCHARGEPROXYADDRESS, "0");
        map.put(DDC1155.FUNC_SUPPORTSINTERFACE, "0");
        map.put(DDC1155.FUNC_TRANSFEROWNERSHIP, "0");
        map.put(DDC1155.FUNC_UNFREEZE, "0");
        map.put(DDC1155.FUNC_UPGRADETO, "0");
        map.put(DDC1155.FUNC_UPGRADETOANDCALL, "0");
        map.put(DDC1155.FUNC_SETURI, "0");
    }

    public String getOpbGatewayAddress() {
        return opbGatewayAddress;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    public void setOpbGatewayAddress(String opbGatewayAddress) {
        this.opbGatewayAddress = opbGatewayAddress;
    }

    public String getDdc721Address() {
        return ddc721Address;
    }

    public void setDdc721Address(String ddc721Address) {
        this.ddc721Address = ddc721Address;
    }

    public String getDdc1155Address() {
        return ddc1155Address;
    }

    public void setDdc1155Address(String ddc1155Address) {
        this.ddc1155Address = ddc1155Address;
    }

    public String getAuthorityLogicAddress() {
        return authorityLogicAddress;
    }

    public void setAuthorityLogicAddress(String authorityLogicAddress) {
        this.authorityLogicAddress = authorityLogicAddress;
    }

    public String getChargeLogicAddress() {
        return chargeLogicAddress;
    }

    public void setChargeLogicAddress(String chargeLogicAddress) {
        this.chargeLogicAddress = chargeLogicAddress;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(String gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getDdc721BIN() {
        return ddc721BIN;
    }

    public void setDdc721BIN(String ddc721BIN) {
        this.ddc721BIN = ddc721BIN;
    }

    public String getDdc1155BIN() {
        return ddc1155BIN;
    }

    public void setDdc1155BIN(String ddc1155BIN) {
        this.ddc1155BIN = ddc1155BIN;
    }

    public String getAuthorityLogicBIN() {
        return authorityLogicBIN;
    }

    public void setAuthorityLogicBIN(String authorityLogicBIN) {
        this.authorityLogicBIN = authorityLogicBIN;
    }

    public String getChargeLogicBIN() {
        return chargeLogicBIN;
    }

    public String getFuncGasLimit() {
        return funcGasLimit;
    }

    public void setFuncGasLimit(String customerGasLimit) {
        this.funcGasLimit = customerGasLimit;
    }

    public void setChargeLogicBIN(String chargeLogicBIN) {
        this.chargeLogicBIN = chargeLogicBIN;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
