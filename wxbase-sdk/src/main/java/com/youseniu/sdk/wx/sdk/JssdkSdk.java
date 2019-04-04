package com.youseniu.sdk.wx.sdk;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youseniu.sdk.wx.config.WxConfig;
import com.youseniu.sdk.wx.constant.Constants;
import com.youseniu.sdk.wx.dto.JsapiTicket;
import com.youseniu.sdk.wx.exception.JsapiTicketException;
import com.youseniu.sdk.wx.util.HttpUtils;
import com.youseniu.sdk.wx.util.JsonUtils;
import com.youseniu.sdk.wx.util.SHA1Util;

/**
 * JS-SDK相关业务apid
 * @author liyuchang
 * @date 2018-01-12 17:04:20
 */
public class JssdkSdk {
  
  private Logger logger = LoggerFactory.getLogger( getClass());
  
  private WxConfig wxConfig;
  
  public JssdkSdk(WxConfig wxConfig) {
    super();
    this.wxConfig = wxConfig;
  }

  /**
   * 获取公众号用于调用微信JS接口的临时票据jsapi_ticket，缓存7000秒（默认过期7200）
   * @param baseToken
   * @return String
   * @throws JsapiTicketException 
   */
  public String getJsapiTicket(String baseToken) throws JsapiTicketException {
    //是否缓存存在
//    String jsapiTicket = this.cachedService.get(Constants.JSAPI_TICKET_CACHE_KEY);
//    if(StringUtil.isNotBlank(jsapiTicket)) {
//      System.out.println("get from cache jsapiTicket : "+jsapiTicket);
//      return jsapiTicket;
//    }
    //调微信api获取
    String url = String.format(Constants.JSAPI_TICKET_URL, baseToken);
    try {
      String jsonStr = HttpUtils.doGet(url);
      JsapiTicket result = JsonUtils.parse(jsonStr, JsapiTicket.class);
      // https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
      if(result.getErrcode()!=null && !result.getErrcode().equals("0")) {
        logger.warn("调微信api获取tickek失败，url = {}, errorcode = {}, errormsg = {}", url, result.getErrcode(), result.getErrmsg());
        throw new JsapiTicketException("errorcode = "+result.getErrcode()+", errormsg = "+result.getErrmsg());
      }
      return result.getTicket();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("接口获取基础access_token异常，url = {}, error = {}", url, e.getMessage(), e);
      throw new JsapiTicketException(e.getMessage());
    }
  }
  
  /**
   * 获取jsapi_ticket签名
   * @param url
   * @return
   * @throws JsapiTicketException 
   */
  public JsapiTicket getSign(String url, String jsapiTicket) throws JsapiTicketException {
    Long timestamp = Calendar.getInstance().getTime().getTime()/1000l;
    String noncestr = this.getUUID(16);
    String signature = null;
//    String jsapiTicket = this.getJsapiTicket(baseToken);
    try {
      signature = SHA1Util.genWithAmple("jsapi_ticket=" + jsapiTicket, "noncestr=" + noncestr,"timestamp=" + timestamp, "url=" + url);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    JsapiTicket result = new JsapiTicket();
    result.setTicket(jsapiTicket);
    result.setNonceStr(noncestr);
    result.setTimestamp(timestamp.toString());
    result.setSignature(signature);
    result.setAppId(wxConfig.getAppid());
    result.setUrl(url);
    return result;
  }

  

  /**
   * 取uuid去掉-后从后向前截取指定长度随机串 
   * length @return @return String @throws
   */
  private String getUUID(int length) {
    String uuid = UUID.randomUUID().toString();
    uuid = uuid.replace("-", "");
    if (uuid.length() > length) {
      uuid = uuid.substring(uuid.length() - length, uuid.length());
    }
    return uuid;
  }
}
