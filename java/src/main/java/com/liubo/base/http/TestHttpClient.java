package com.liubo.base.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TestHttpClient
 * @ProjectName fortech
 * @Author Admin
 * @Date 2018-10-18 22:12
 * @Description:
 */
public class TestHttpClient {
    public static void main(String[] args) {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000) //ConnectTimeout : 连接超时,连接建立时间,三次握手完成时间
                .setSocketTimeout(50000)//SocketTimeout : 请求超时,数据传输过程中数据包之间间隔的最大时间
                .setConnectionRequestTimeout(1000)//ConnectionRequestTimeout : 使用连接池来管理连接,从连接池获取连接的超时时间
                .build();

        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();

        //设置连接池大小
        ConnectingIOReactor ioReactor = null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }

        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(1);//最大连接数设置
        connManager.setDefaultMaxPerRoute(1);//per route最大连接数设置

        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        //构造请求
        String url = "http://127.0.0.1:9200/_bulk";
        List<HttpPost> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = null;
            try {
                String a = "{ \"index\": { \"_index\": \"test\", \"_type\":\"test\"}}\n" + "{\"name\": \"上海\",\"age\": 33}\n";
                entity = new StringEntity(a);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entity);
            list.add(httpPost);
        }

        //start
        client.start();

        //异步请求
        for (int i = 0; i < 2; i++) {
            client.execute(list.get(i), new Back());
        }

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Back implements FutureCallback<HttpResponse> {
        private long start = System.currentTimeMillis();

        Back() {
        }

        @Override
        public void completed(HttpResponse httpResponse) {
            try {
                System.out.println("cost is:" + (System.currentTimeMillis() - start) + ":" + EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Exception e) {
            e.printStackTrace();
            System.out.println("cost is:" + (System.currentTimeMillis() - start) + ":" + e);
        }

        @Override
        public void cancelled() {
        }
    }
}
