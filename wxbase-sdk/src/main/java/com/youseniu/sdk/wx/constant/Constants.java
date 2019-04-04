package com.youseniu.sdk.wx.constant;

public abstract interface Constants {
  
  /**
   * 通过code换取openid和网页授权access_token的地址
   */
  public static final String OAUTH2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
      + "appid=%s&secret=%s&code=%s&grant_type=authorization_code";
  
  /**
   * 刷新access_token的地址
   */
  public static final String OAUTH2_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?"
      + "appid=%s&grant_type=refresh_token&refresh_token=%s";
  
  /**
   * 拉取用户信息(需scope为 snsapi_userinfo)的地址
   */
  public static final String OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?"
      + "access_token=%s&openid=%s&lang=zh_CN";

  /**
   * 获取普通access_token地址（不同于网页授权access_token）
   */
  public static final String BASE_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

  /**
   * jsapi_ticket获取地址
   */
  public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
}
