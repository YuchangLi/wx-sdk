/**
 * 
 */
package com.youseniu.sdk.wx.dto;

/**
 * 返回错误码对象
 * @author liyuchang
 * @date 2018-01-12 16:13:17
 */
public class Error {
  /**
   * 错误码： 48001等
   */
  private String errcode;
  /**
   * 错误描述： api unauthorized等
   */
  private String errmsg;
  /**
   * @return the errcode
   */
  public String getErrcode() {
    return errcode;
  }
  /**
   * @param errcode the errcode to set
   */
  public void setErrcode(String errcode) {
    this.errcode = errcode;
  }
  /**
   * @return the errmsg
   */
  public String getErrmsg() {
    return errmsg;
  }
  /**
   * @param errmsg the errmsg to set
   */
  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
  
}
