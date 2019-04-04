package com.youseniu.sdk.wx.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youseniu.sdk.wx.config.WxConfig;
import com.youseniu.sdk.wx.constant.Constants;
import com.youseniu.sdk.wx.dto.AccessTokenInfo;
import com.youseniu.sdk.wx.exception.AccessTokenException;
import com.youseniu.sdk.wx.util.HttpUtils;
import com.youseniu.sdk.wx.util.JsonUtils;

/**
 * 获取基础access_tokenapi
 * @author liyuchang
 * @date 2018-01-12 17:04:20
 */
public class BaseAccessTokenSdk {
  
  private Logger logger = LoggerFactory.getLogger( getClass());
  
  private WxConfig wxConfig;
  
  public BaseAccessTokenSdk(WxConfig wxConfig) {
    super();
    this.wxConfig = wxConfig;
  }

  /**
   * 获取基础access_token, 缓存7000秒（默认过期7200）
   * @return accessToken
   * @throws AccessTokenException 
   */
  public String getAccessToken() throws AccessTokenException {
    //调微信api获取
    String url = String.format(Constants.BASE_ACCESS_TOKEN_URL, this.wxConfig.getAppid(), this.wxConfig.getSecret());
    try {
      String jsonStr = HttpUtils.doGet(url);
      AccessTokenInfo accessTokenResult = JsonUtils.parse(jsonStr, AccessTokenInfo.class);
      if(accessTokenResult.getErrcode()!=null && !accessTokenResult.getErrcode().equals("0")) {
        logger.warn("接口获取基础access_token失败，url = {}, errorcode = {}, errormsg = {}", url, accessTokenResult.getErrcode(), accessTokenResult.getErrmsg());
        throw new AccessTokenException("errorcode = "+accessTokenResult.getErrcode()+", errormsg = "+accessTokenResult.getErrmsg());
      }
      return accessTokenResult.getAccessToken();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("接口获取基础access_token异常，url = {}, error = {}", url, e.getMessage(), e);
      throw new AccessTokenException(e.getMessage());
    }
  }
}
