package com.youseniu.sdk.wx.test;

import org.junit.Before;
import org.junit.Test;

import com.youseniu.sdk.wx.config.WxConfig;
import com.youseniu.sdk.wx.dto.AccessTokenInfo;
import com.youseniu.sdk.wx.exception.AccessTokenException;
import com.youseniu.sdk.wx.sdk.OAuth2Sdk;
import com.youseniu.sdk.wx.util.JsonUtils;

public class OAuth2SdkTest {
  private WxConfig wxConfig;
  private OAuth2Sdk oAuth2Sdk;
  
  @Before
  public void before() {
    this.wxConfig = new WxConfig("wx28420642520a26ec", "a0e6433cb2fd5e72636c35154703b6df");
    this.oAuth2Sdk = new OAuth2Sdk(this.wxConfig);
  }
  
  @Test
  public void testGetAccessToken() {
    AccessTokenInfo token;
    try {
      token = this.oAuth2Sdk.getAccessToken("081GwoiM0OAE4b2GMGjM0Ry3iM0Gwois");
      System.out.println(JsonUtils.toString(token));
    } catch (AccessTokenException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
