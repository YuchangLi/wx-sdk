package com.youseniu.sdk.wx.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: HttpUtils
 * @Description: http请求工具类
 * @author liyuchang
 * @date 2018年8月27日
 */
public class HttpUtils {

  private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

  private static Charset chartset = Charset.forName(CharEncoding.UTF_8);

  /**
   * <pre>
   * post发送json数据请求
   * 设置了Content-Type=application/json
   * </pre>
   * 
   * @param url：请求路径
   * @param jsonFormString：json格式的字符串
   * @return
   * @throws Exception
   */
  public static JSONObject doPostJson(String url, String jsonFormString) throws Exception {
    logger.info("doPostJson请求内容：{}", jsonFormString);

    HttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    post.addHeader("text/plain", CharEncoding.UTF_8);
    post.addHeader("Content-Type", "application/json");

    StringEntity s = new StringEntity(jsonFormString, chartset);
    post.setEntity(s);
    HttpResponse httpResponse = client.execute(post);
    JSONObject response = null;
    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
      String result = EntityUtils.toString(httpResponse.getEntity());
      response = JSONObject.parseObject(result);
    } else {
      String errorMsg = EntityUtils.toString(httpResponse.getEntity());
      throw new RuntimeException(errorMsg);
    }
    if (response != null) {
      logger.info("doPostJson响应结果：{}", response.toJSONString());
    }
    return response;
  }

  public static String doPost(String url, Map<String, String> params) {
    String output = null;
    HttpResponse response = null;
    CloseableHttpClient httpClient = null;
    try {
      httpClient = HttpClients.createDefault();
      HttpPost request = new HttpPost(url);
      request.setHeader("Accept", "*/*");
      // add request header
      // if (headers != null && !headers.isEmpty()) {
      // for (Entry<String, String> entry : headers.entrySet()) {
      // request.setHeader(entry.getKey(), entry.getValue());
      // }
      // }
      // add request body
      if (params != null && !params.isEmpty()) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
          nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        request.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
      }
      logger.debug("request url={}, params={}", url,
          params != null ? JSON.toJSONString(params) : null);
      response = httpClient.execute(request);
      output = EntityUtils.toString(response.getEntity(), "UTF-8");
    } catch (Exception e) {
      logger.error("", e);
      throw new RuntimeException("POST调用异常");
    }
    if (response.getStatusLine().getStatusCode() != 200) {
      logger.warn("POST调用异常，返回code : {}", response.getStatusLine().getStatusCode());
      logger.warn("url={}, params={}", url, params != null ? JSON.toJSONString(params) : null);
      throw new RuntimeException("非200状态码,调用方异常."); // 当前针对短信发送,只处理此状态码
    }

    logger.debug("response {}", output);

    return output;
  }
  
  public static String doGet(String url) throws Exception {
    return doGet(url, null);
  }

  /**
   * 执行一个HTTP GET请求，返回请求响应的string
   * 
   * @param url 请求的URL地址
   * @param headers
   * @return 返回请求响应的HTML
   * @throws IOException
   * @throws ClientProtocolException
   */
  public static String doGet(String url, Map<String, String> headers) throws Exception {
    logger.info("url = 【{}】", url);
    HttpClient client = HttpClients.createDefault();
    HttpGet method = new HttpGet(url);
    if(headers!=null && !headers.isEmpty()) {
      headers.forEach((key, value)->{
        method.addHeader(key, value);
        logger.info("Headers, 【{}】 = 【{}】", key, value);
      });
    }
    HttpResponse httpResponse = client.execute(method);
    logger.info("HttpResponse = 【{}】", httpResponse.toString());
    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
      String result = EntityUtils.toString(httpResponse.getEntity());
      logger.info("responseString = 【{}】", result);
      return result;
    } else {
      String errorMsg = EntityUtils.toString(httpResponse.getEntity());
      throw new RuntimeException(errorMsg);
    }
  }

  /**
   * @throws Exception 
   * @Title: doGet
   * @Description: get请求
   * @param url 地址
   * @param headers 请求头
   * @param querys 请求参数
   * @return HttpResponse
   * @throws
   */
  public static String doGet(String host, String uri, Map<String, String> querys) throws Exception {
    StringBuilder sb = new StringBuilder(host+uri);
    if(querys != null && !querys.isEmpty()) {
      sb.append("?");
      querys.forEach((key, value)->{
        sb.append("&").append(key).append("=").append(value);
      });
    }
    return doGet(sb.toString().replaceFirst("&", ""), null);
  }

}
