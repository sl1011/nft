package com.shitouren.core.dto;

import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class TxInfo {

    private String hash;
    private String nonce;
    private String blockHash;
    private String blockNumber;
    private String transactionIndex;
    private String from;
    private String to;
    private String value;
    private String gasPrice;
    private String gas;
    private String input;
    private String creates;
    private String publicKey;
    private String raw;
    private String r;
    private String s;
    private long v;

    public TxInfo() {
    }

    public TxInfo(Transaction transaction) {
        this.hash = transaction.getHash();
        this.nonce = transaction.getNonceRaw();
        this.blockHash = transaction.getBlockHash();
        this.blockNumber = transaction.getBlockNumberRaw();
        this.transactionIndex = transaction.getTransactionIndexRaw();
        this.from = transaction.getFrom();
        this.to = transaction.getTo();
        this.value = transaction.getValueRaw();
        this.gasPrice = transaction.getGasPriceRaw();
        this.gas = transaction.getGasRaw();
        this.input = transaction.getInput();
        this.creates = transaction.getCreates();
        this.publicKey = transaction.getPublicKey();
        this.raw = transaction.getRaw();
        this.r = transaction.getR();
        this.s = transaction.getS();
        this.v = transaction.getV();
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigInteger getNonce() {
        return Numeric.decodeQuantity(this.nonce);
    }

    public String getNonceRaw() {
        return this.nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getBlockHash() {
        return this.blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public BigInteger getBlockNumber() {
        return Numeric.decodeQuantity(this.blockNumber);
    }

    public String getBlockNumberRaw() {
        return this.blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public BigInteger getTransactionIndex() {
        return Numeric.decodeQuantity(this.transactionIndex);
    }

    public String getTransactionIndexRaw() {
        return this.transactionIndex;
    }

    public void setTransactionIndex(String transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigInteger getValue() {
        return Numeric.decodeQuantity(this.value);
    }

    public String getValueRaw() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigInteger getGasPrice() {
        return Numeric.decodeQuantity(this.gasPrice);
    }

    public String getGasPriceRaw() {
        return this.gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigInteger getGas() {
        return Numeric.decodeQuantity(this.gas);
    }

    public String getGasRaw() {
        return this.gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getCreates() {
        return this.creates;
    }

    public void setCreates(String creates) {
        this.creates = creates;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRaw() {
        return this.raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getR() {
        return this.r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getS() {
        return this.s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public long getV() {
        return this.v;
    }

    public Long getChainId() {
        if (this.v != 27L && this.v != 28L) {
            return (this.v - 35L) / 2L;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "txInfo { " +
                "hash ='" + hash + '\'' +
                ", nonce ='" + nonce + '\'' +
                ", blockHash ='" + blockHash + '\'' +
                ", blockNumber ='" + blockNumber + '\'' +
                ", transactionIndex ='" + transactionIndex + '\'' +
                ", from ='" + from + '\'' +
                ", to ='" + to + '\'' +
                ", value ='" + value + '\'' +
                ", gasPrice ='" + gasPrice + '\'' +
                ", gas ='" + gas + '\'' +
                ", input ='" + input + '\'' +
                ", creates ='" + creates + '\'' +
                ", raw ='" + raw + '\'' +
                ", r ='" + gasPrice + '\'' +
                ", s ='" + gas + '\'' +
                ", v ='" + input + '\'' +
                " }";
    }
}
