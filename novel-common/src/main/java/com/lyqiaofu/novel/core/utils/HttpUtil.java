package com.lyqiaofu.novel.core.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class HttpUtil {

//    private static RestTemplate restTemplate = RestTemplateUtil.getInstance("utf-8");

    private static RestTemplate restTemplate = RestTemplateUtil.getInstance("GBK");


    public static String getByHttpClient(String url) {
        try {
            ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
            if (forEntity.getStatusCode() == HttpStatus.OK) {
                return forEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("采集异常url：" + url);
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        //https://baishuzhai.com/ibook/144/144086/7336052.html
        //https://baishuzhai.com/ibook/76/76442/4720107.html



        String content = getByHttpClient("https://baishuzhai.com/ibook/143/143568/7751095.html");
        System.out.println("content = " + content);
    }

}
