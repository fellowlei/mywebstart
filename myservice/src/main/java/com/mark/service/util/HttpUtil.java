package com.mark.service.util;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by fellowlei on 2018/4/17.
 */
public class HttpUtil {
    public static String ip = "192.168.0.1";

    public static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000)   //设置连接超时时间
            .setConnectionRequestTimeout(5000) // 设置请求超时时间
            .setSocketTimeout(5000)
            .setRedirectsEnabled(true)//默认允许自动重定向
            .build();



    public static String querySkuInfo(String id)  {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = String.format("http://%s/query?id=%s",ip, id);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Host", "mark.com");

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
                System.out.println("Error Response: " + response.getStatusLine().toString());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}