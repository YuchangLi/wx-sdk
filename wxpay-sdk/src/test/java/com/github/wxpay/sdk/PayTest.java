package com.github.wxpay.sdk;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.github.wxpay.sdk.WXPayConstants.SignType;

public class PayTest {
  private WXPayConfig config;
  private WXPay wxpay;
  
  @Before
  public void before() {
    try {
//       沙箱
//      this.config = new WXPayConfigDefault("wxc0eb64163b67964f", "1263875401", "c7b6f93ed25005c9bdca390bf29483e4", null, "api.mch.weixin.qq.com");
      // 线上
    this.config = new WXPayConfigDefault("wx28420642520a26ec", "1528406281", "X2PLoUchpg2wmHaVVlhwM375ZcxUJjXu", null, "api.mch.weixin.qq.com");
      this.wxpay = new WXPay(this.config, "http://api-dev.youseniu.com/portal/userapi/wallet/wxPayNofity", false, false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void testGenerateNonceStr() {
    System.out.println(WXPayUtil.generateNonceStr());
  }
  
  @Test
  public void testMapToXml() throws Exception {
    Map<String, String> data = new HashMap<String, String>();
    data.put("return_code", "SUCCESS");
    data.put("return_msg", "OK");
    String xml = WXPayUtil.mapToXml(data);
    System.out.println(xml);
    System.out.println(WXPayUtil.xmlToMap(xml));
  }
  
  @Test
  public void testGenerateMd5() throws Exception {
    System.out.println(WXPayUtil.MD5("mch_id=1263875401&nonce_str=cjO7ZlNd7MOCP79oVcOzPqrhlaHRGbst&key=c142909d989f2d3a07aa19662a0e759a"));
    BigDecimal fee = BigDecimal.valueOf(1.01);
    System.out.println(fee.multiply(BigDecimal.valueOf(100)).intValue()+"");
    System.out.println(fee.longValue()*100+"")
    ;
  }
  
  @Test
  public void testUnifiedOrder() {
    try {
      Map<String, String> data = new HashMap<String, String>();
      data.put("body", "腾讯充值中心-QQ会员充值");
      data.put("out_trade_no", "201609091059590000001211");
//      data.put("device_info", "WEB");
//      data.put("fee_type", "CNY");
      data.put("total_fee", "1");
      data.put("spbill_create_ip", "47.94.95.155");
//      data.put("notify_url", "http://www.example.com/wxpay/notify");
      data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
//      data.put("product_id", "12");
      data.put("openid", "oTaZz51gBk6HcuHbHwEnV4Spiu-o");
      Map<String, String> resp = wxpay.unifiedOrder(data);
      System.out.println(resp);
      System.out.println(resp.get("prepay_id"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void testOrderQuery() {
    try {
      Map<String, String> data = new HashMap<String, String>();
      data.put("out_trade_no", "20190308150413207545210");
      Map<String, String> resp = wxpay.orderQuery(data);
      System.out.println(resp);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void geneH5payParam() throws Exception {

    System.out.println(WXPayUtil.getCurrentTimestamp()+"");
    System.out.println(WXPayUtil.generateNonceStr());
    Map<String, String> reqData = wxpay.geneJSAPIH5payParam("wx12152541034972a2c9b2f50a1042542593");
    System.out.println(reqData);
  }
}
