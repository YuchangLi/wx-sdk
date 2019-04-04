package com.youseniu.sdk.wx.test;

import org.junit.Before;
import org.junit.Test;

import com.youseniu.sdk.wx.config.WxConfig;
import com.youseniu.sdk.wx.dto.JsapiTicket;
import com.youseniu.sdk.wx.exception.AccessTokenException;
import com.youseniu.sdk.wx.exception.JsapiTicketException;
import com.youseniu.sdk.wx.sdk.BaseAccessTokenSdk;
import com.youseniu.sdk.wx.sdk.JssdkSdk;
import com.youseniu.sdk.wx.util.JsonUtils;

public class JssdkSdkTest {
  private WxConfig wxConfig;
  private BaseAccessTokenSdk baseAccessTokenSdk;
  private JssdkSdk jssdkSdk;
  
  @Before
  public void before() {
    this.wxConfig = new WxConfig("wx28420642520a26ec", "a0e6433cb2fd5e72636c35154703b6df");
    this.baseAccessTokenSdk = new BaseAccessTokenSdk(this.wxConfig);
    this.jssdkSdk = new JssdkSdk(this.wxConfig);
  }
  
  @Test
  public void testGetSign() throws JsapiTicketException, AccessTokenException {
    String url = "http://www.baidu.com";
    String jsticket = this.jssdkSdk.getJsapiTicket(this.baseAccessTokenSdk.getAccessToken());
    JsapiTicket ticket = this.jssdkSdk.getSign(url, jsticket);
    System.out.println("ticket = "+JsonUtils.toString(ticket));
  }
  
  @Test
  public void testGetAccessToken() throws AccessTokenException {
    String baseToken = this.baseAccessTokenSdk.getAccessToken();
    System.out.println("baseToken = "+baseToken);
  }
}
