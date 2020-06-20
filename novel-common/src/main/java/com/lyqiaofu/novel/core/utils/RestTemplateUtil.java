package com.lyqiaofu.novel.core.utils;

import lombok.SneakyThrows;
import org.apache.http.Header;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class RestTemplateUtil {



    @SneakyThrows
    public static RestTemplate getInstance(String charset) {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        //忽略证书
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", csf)
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);

        //连接池的最大连接数，0代表不限；如果取0，需要考虑连接泄露导致系统崩溃的后果
        connectionManager.setMaxTotal(1000);
        //每个路由的最大连接数,如果只调用一个地址,可以将其设置为最大连接数
        connectionManager.setDefaultMaxPerRoute(300);

        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"));
        headerList.add(new BasicHeader("cookie","UM_distinctid=172090eb922403-007d3bdcd3940f-d373666-144000-172090eb9239a2; bcolor=; font=; size=; fontcolor=; width=; CNZZDATA1278861227=609828574-1589286055-https%253A%252F%252Fwww.baidu.com%252F%7C1591403690; CNZZDATA1278862532=253161636-1589286275-https%253A%252F%252Fwww.baidu.com%252F%7C1591403187; bookid=144736%2C102886%2C56526%2C39754%2C143568; chapterid=8251853%2C5725646%2C3009790%2C2289643%2C7751095; chaptername=%25u7B2C230%25u7AE0%2520%25u8BA4%25u547D%25u4E86%2C%25u7B2C109%25u7AE0%2520%25u6700%25u5F3A%25u4E4B%25u610F%2C%25u7B2C%25u4E00%25u90E8%25u5206%253A%25u661F%25u671F%25u4E00%25u65E9%25u6668%2520%25u4E00%25u5207%25u90FD%25u662F%25u5306%25u5306%25u5FD9%25u5FD9%25u7684%2C%25u7B2C001%25u7AE0%2520%25u4E2D%25u5927%25u5956%25u7684%25u673A%25u4F1A%2C%25u4E0A%25u67B6%25u611F%25u8A00"));
        headerList.add(new BasicHeader("pragma","no-cache"));
        headerList.add(new BasicHeader("sec-fetch-dest","document"));
        headerList.add(new BasicHeader("sec-fetch-mode","navigate"));
        headerList.add(new BasicHeader("sec-fetch-site","none"));
        headerList.add(new BasicHeader("sec-fetch-user","?1"));
        headerList.add(new BasicHeader("upgrade-insecure-requests","1"));
        headerList.add(new BasicHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36"));

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultHeaders(headerList)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectionRequestTimeout(3000);
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(10000);



        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if(httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName(charset));
                break;
            }
        }
        return restTemplate;
    }

}
