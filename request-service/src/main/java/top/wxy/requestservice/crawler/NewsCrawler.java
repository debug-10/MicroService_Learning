package top.wxy.requestservice.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

/**
 * @author 笼中雀
 */

public class NewsCrawler {
    private static final String TARGET_URL = "https://www.qimao.com/paihang";

    public static void main(String[] args) {
        // 1. 创建 HttpClient 实例（带连接池配置）
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .build()) {
            // 2. 构建 GET 请求
            HttpGet httpGet = new HttpGet(TARGET_URL);
            httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
            // 3. 执行请求并解析响应
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String html = EntityUtils.toString(entity, "UTF-8");
                    // 4. 使用HtmlCleaner解析HTML
                    HtmlCleaner cleaner = new HtmlCleaner();
                    TagNode rootNode = cleaner.clean(html);
                    // 5. XPath 定位标题元素（需根据实际页面结构调整）
                    Object[] titleNodes = rootNode.evaluateXPath("//*[@id=\"__layout\"]/div/div[3]/div/div[2]/div[2]/div/div/ul/li/div/div/div/a");
                    System.out.println(titleNodes.length);
                    // 6. 输出结果
                    System.out.println("----- 最新新闻标题 -----");
                    for (Object node : titleNodes) {
                        if (node instanceof TagNode) {
                            System.out.println(((TagNode) node).getText());
                        } else if (node instanceof String) {
                            System.out.println(node);
                        }
                    }
                }
            }
        } catch (XPatherException e) {
            System.err.println("XPath解析失败，请检查表达式：" + e.getMessage());
        } catch (Exception e) {
            System.err.println("请求异常：" + e.getMessage());
        }
    }
}