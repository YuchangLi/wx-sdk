package com.youseniu.sdk.wx.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * jsapi_ticket，开发者必须在自己的服务全局缓存jsapi_ticket 
 * @author liyuchang
 * @date 2018-01-12 16:21:56
 */
public class JsapiTicket extends Error {
  private String ticket;// jsapiticket
  @JSONField(name="expires_in")
  private Long expiresIn;// 证超时时间，单位（秒），默认7200
  private String nonceStr;// 随机字符串 对应wx.config中的nonceStr
  private String timestamp;// 时间戳 对应wx.config中的timestamp
  private String signature;// 签名 对应wx.config中的signature
  private String appId;// appid 对应wx.config中的appId
  private String url;// url
  /**
   * @return the ticket
   */
  public String getTicket() {
    return ticket;
  }
  /**
   * @param ticket the ticket to set
   */
  public void setTicket(String ticket) {
    this.ticket = ticket;
  }
  /**
   * @return the expiresIn
   */
  public Long getExpiresIn() {
    return expiresIn;
  }
  /**
   * @param expiresIn the expiresIn to set
   */
  public void setExpiresIn(Long expiresIn) {
    this.expiresIn = expiresIn;
  }
  
  /**
   * @return the timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }
  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
  /**
   * @return the signature
   */
  public String getSignature() {
    return signature;
  }
  /**
   * @param signature the signature to set
   */
  public void setSignature(String signature) {
    this.signature = signature;
  }
  /**
   * @return the nonceStr
   */
  public String getNonceStr() {
    return nonceStr;
  }
  /**
   * @param nonceStr the nonceStr to set
   */
  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }
  /**
   * @return the appId
   */
  public String getAppId() {
    return appId;
  }
  /**
   * @param appId the appId to set
   */
  public void setAppId(String appId) {
    this.appId = appId;
  }
  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }
  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "JsapiTicket [ticket=" + ticket + ", expiresIn=" + expiresIn + ", getErrcode()="
        + getErrcode() + ", getErrmsg()=" + getErrmsg() + "]";
  }
  
}
