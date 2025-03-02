package top.wxy.requestservice.controller;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 笼中雀  服务消费者
 */
@RestController
public class ConsumerController {
    private final String SERVICE_URL="http://localhost:8080";

    @GetMapping("/httpClientTest")
    public String httpClientTest() throws IOException{
        //创建http客户端对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get请求对象
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/hello");
        CloseableHttpResponse response;
        //执行get请求
        response=httpClient.execute(httpGet);
        if(response.getStatusLine().getStatusCode()==200){
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result);
        }
        return "请求成功";
    }
}
