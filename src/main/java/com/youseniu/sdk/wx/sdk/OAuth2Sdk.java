package com.youseniu.sdk.wx.sdk;

import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youseniu.sdk.wx.config.WxConfig;
import com.youseniu.sdk.wx.constant.Constants;
import com.youseniu.sdk.wx.dto.AccessTokenInfo;
import com.youseniu.sdk.wx.dto.UserInfo;
import com.youseniu.sdk.wx.exception.AccessTokenException;
import com.youseniu.sdk.wx.exception.UserInfoException;
import com.youseniu.sdk.wx.util.HttpUtils;
import com.youseniu.sdk.wx.util.JsonUtils;

/**
 * 网页授权相关业务api
 * @author liyuchang
 * @date 2018-01-12 17:04:20
 */
public class OAuth2Sdk {
  
  private Logger logger = LoggerFactory.getLogger( getClass());
  
  private WxConfig wxConfig;
  
//  /**
//   * 获取code的跳转授权地址
//   * @param scope snsapi_base：静默只能获取openid; snsapi_userinfo:授权获取用户信息
//   * @param state 自定义参数
//   * @return
//   */
//  public String getOAuth2AuthorizeURL(String scope, String state) {
//    return String.format(Constants.OAUTH2_AUTHORIZE_URL, this.wxConfig.getAppid(), this.wxConfig.getRedirectUri(), scope, state);
//  }
  
  public OAuth2Sdk(WxConfig wxConfig) {
    super();
    this.wxConfig = wxConfig;
  }

  /**
   * 通过code换取网页授权access_token
   * @param code
   * @return
   * @throws AccessTokenException AccessTokenInfo
   */
  public AccessTokenInfo getAccessToken(String code) throws AccessTokenException {
    String url = String.format(Constants.OAUTH2_ACCESS_TOKEN_URL, this.wxConfig.getAppid(), this.wxConfig.getSecret(), code);
    try {
      String jsonStr = HttpUtils.doGet(url);
      AccessTokenInfo accessToken = JsonUtils.parse(jsonStr, AccessTokenInfo.class);
      if(accessToken.getErrcode() != null) {
        logger.warn("code换取网页授权access_token失败，url = {}, errorcode = {}, errormsg = {}", url, accessToken.getErrcode(), accessToken.getErrmsg());
        throw new AccessTokenException("errorcode = "+accessToken.getErrcode()+", errormsg = "+accessToken.getErrmsg());
      }
      return accessToken;
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("code换取网页授权access_token异常，url = {}, error = {}", url, e.getMessage(), e);
      throw new AccessTokenException(e.getMessage());
    }
  }
  
  /**
   * 拉取用户信息(需scope为 snsapi_userinfo)
   * @param accessToken
   * @param openid
   * @return
   */
  public UserInfo getUserInfo(String accessToken, String openid) {
    String url = String.format(Constants.OAUTH2_USERINFO_URL, accessToken, openid);
    try {
      String jsonStr = HttpUtils.doGet(url);
      // 转码下
      UserInfo userInfo = JsonUtils.parse(new String(jsonStr.getBytes(CharEncoding.ISO_8859_1), CharEncoding.UTF_8), UserInfo.class);
      if(userInfo.getErrcode() != null) {
        logger.warn("拉取用户信息失败，url = {}, errorcode = {}, errormsg = {}", url, userInfo.getErrcode(), userInfo.getErrmsg());
        throw new UserInfoException("errorcode = "+userInfo.getErrcode()+", errormsg = "+userInfo.getErrmsg());
      }
      logger.info("拉取用户信息成功，" + userInfo);
      return userInfo;
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("拉取用户信息失败异常，url = {}, error = {}", url, e.getMessage(), e);
      throw new UserInfoException(e.getMessage());
    }
    
  }

}
