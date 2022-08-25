package com.shitouren.core.service;

import java.util.Map;

public interface WXAppPayService {
    Map<String, String> dounifiedOrder(String type, String out_trade_no, String total_fee) throws Exception;

    String payBack(String resXml);
}
