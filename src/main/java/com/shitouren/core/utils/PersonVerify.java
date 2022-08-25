//package com.shitouren.core.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class PersonVerify {
//
//    public static String personVerify(String path, String name, String id_card_number, String access_token) {
//        // 请求url
//        String url = "https://aip.baidubce.com/rest/2.0/face/v3/person/verify";
//        try {
//            Map<String, Object> map = new HashMap<>();
//            map.put("image", path);
//            map.put("liveness_control", "HIGH");
//            map.put("name", name);
//            map.put("id_card_number", id_card_number);
//            map.put("image_type", "URL");
//            map.put("quality_control", "LOW");
////            String param = GsonUtils.toJson(map);
//            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = access_token;
//            String result = HttpUtil.post(url, accessToken, "application/json", param);
//            System.out.println("百度云返回的信息>>>>>>>>>>>");
//            System.out.println(result);
//            System.out.println("百度云返回的信息<<<<<<<<<<<<<<<<<<<");
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
