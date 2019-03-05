package com.youseniu.sdk.wx.dto;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 微信用户信息
 * @author liyuchang
 * @date 2018-01-12 23:59:24
 */
public class UserInfo extends Error {
  private String openid;// 用户的唯一标识
  private String unionid;// 同一用户，对同一个微信开放平台帐号下的不同应用，UnionID是相同的
  private String nickname;// 用户昵称
  private Byte sex;// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
  private String province;// 用户个人资料填写的省份
  private String city;// 普通用户个人资料填写的城市
  private String country;// 国家，如中国为CN
  private List<String> privilege; // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
  private String language;// 用户的语言，简体中文为zh_CN
  private String headimgurl;// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
  private Boolean subscribe;// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
  @JSONField(name="subscribe_time")
  private Date subscribeTime;// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
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
   * @return the unionid
   */
  public String getUnionid() {
    return unionid;
  }
  /**
   * @param unionid the unionid to set
   */
  public void setUnionid(String unionid) {
    this.unionid = unionid;
  }
  /**
   * @return the nickname
   */
  public String getNickname() {
    return nickname;
  }
  /**
   * @param nickname the nickname to set
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  /**
   * @return the sex
   */
  public Byte getSex() {
    return sex;
  }
  /**
   * @param sex the sex to set
   */
  public void setSex(Byte sex) {
    this.sex = sex;
  }
  /**
   * @return the province
   */
  public String getProvince() {
    return province;
  }
  /**
   * @param province the province to set
   */
  public void setProvince(String province) {
    this.province = province;
  }
  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }
  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }
  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }
  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }
  /**
   * @return the privilege
   */
  public List<String> getPrivilege() {
    return privilege;
  }
  /**
   * @param privilege the privilege to set
   */
  public void setPrivilege(List<String> privilege) {
    this.privilege = privilege;
  }
  /**
   * @return the language
   */
  public String getLanguage() {
    return language;
  }
  /**
   * @param language the language to set
   */
  public void setLanguage(String language) {
    this.language = language;
  }
  /**
   * @return the headimgurl
   */
  public String getHeadimgurl() {
    return headimgurl;
  }
  /**
   * @param headimgurl the headimgurl to set
   */
  public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
  }
  /**
   * @return the subscribe
   */
  public Boolean getSubscribe() {
    return subscribe;
  }
  /**
   * @param subscribe the subscribe to set
   */
  public void setSubscribe(Boolean subscribe) {
    this.subscribe = subscribe;
  }
  /**
   * @return the subscribeTime
   */
  public Date getSubscribeTime() {
    return subscribeTime;
  }
  /**
   * @param subscribeTime the subscribeTime to set
   */
  public void setSubscribeTime(Date subscribeTime) {
    this.subscribeTime = subscribeTime;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "UserInfo [openid=" + openid + ", unionid=" + unionid + ", nickname=" + nickname
        + ", sex=" + sex + ", province=" + province + ", city=" + city + ", country=" + country
        + ", privilege=" + privilege + ", language=" + language + ", headimgurl=" + headimgurl
        + ", subscribe=" + subscribe + ", subscribeTime=" + subscribeTime + ", errcode="
        + getErrcode() + ", errmsg=" + getErrmsg() + "]";
  }
//  public static void main(String[] args) {
//    System.out.println(JsonUtil.parse("{\"openid\":\"ob8F-wFENFWw7_FR6Gx8KbPacFEw\",\"nickname\":\"李玉长\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"怀化\",\"province\":\"湖南\",\"country\":\"中国\",\"headimgurl\":\"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ6xHJunSibZQaQV3SvLfJtluniatYJ1s0IvPpLq06frM6X8MNbgicPMdKoL2jiaehmnOp8aPwG0Io6pQ/132\",\"privilege\":[],\"unionid\":\"oTpR9xAIiebFOc6p04tMTaLhviCQ\"}", UserInfo.class));
//  }
  
}
