package com.youseniu.sdk.wx.config;

/**
 * @author liyuchang
 * @date 2018-01-12 17:06:21
 */
public class WxConfig {
  /**
   * 微信公众号appid
   */
  private String appid;
  /**
   * 微信公众号appsecret
   */
  private String secret;
//  /**
//   * 微信公众号appsecret
//   */
//  private String secret;
  
  /**
   * @return appid
   */
  public String getAppid() {
    return appid;
  }
  public WxConfig(String appid, String secret) {
    super();
    this.appid = appid;
    this.secret = secret;
  }
  public void setAppid(String appid) {
    this.appid = appid;
  }
  /**
   * @return secret
   */
  public String getSecret() {
    return secret;
  }
  public void setSecret(String secret) {
    this.secret = secret;
  }
  
}
