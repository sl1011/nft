package com.shitouren.core.service;

import java.math.BigDecimal;

public interface AliPayService {

    String aliPay(int userId, String orderno);

    void updPay(String orderNo, BigDecimal money);
    void updPay1(String orderNo, BigDecimal money);

    void rechargeupdPay(String orderNo, BigDecimal money);

}
