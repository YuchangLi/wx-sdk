package com.youseniu.sdk.wx.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 网页授权接口调用凭证
 * @author liyuchang
 * @date 2018-01-12 16:21:56
 */
public class AccessTokenInfo extends Error {
  @JSONField(name="access_token")
  private String accessToken;// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
  @JSONField(name="expires_in")
  private Long expiresIn;// access_token接口调用凭证超时时间，单位（秒）
  @JSONField(name="refresh_token")
  private String refreshToken;// 用户刷新access_token
  private String openid;// 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
  private String scope;// 用户授权的作用域，使用逗号（,）分隔
  /**
   * @return the accessToken
   */
  public String getAccessToken() {
    return accessToken;
  }
  /**
   * @param accessToken the accessToken to set
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
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
   * @return the refreshToken
   */
  public String getRefreshToken() {
    return refreshToken;
  }
  /**
   * @param refreshToken the refreshToken to set
   */
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
  /**
   * @return the openid
   */
  public String getOpenid() {
    return openid;
  }
  /**
   * @param openid the openid to set
   */
  public void setOpenid(String openid) {
    this.openid = openid;
  }
  /**
   * @return the scope
   */
  public String getScope() {
    return scope;
  }
  /**
   * @param scope the scope to set
   */
  public void setScope(String scope) {
    this.scope = scope;
  }
}
