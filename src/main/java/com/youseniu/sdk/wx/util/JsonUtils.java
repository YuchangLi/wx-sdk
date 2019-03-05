package com.youseniu.sdk.wx.util;

import com.alibaba.fastjson.JSON;

/**
 * json转换工具类
 * alibaba.fastjson实现
 * @author liyuchang
 * @date 2018-01-12 16:32:17
 */
public class JsonUtils {
  /**
   * obj2jsonStr
   * @param obj
   * @return
   */
  public static String toString(Object obj) {
    return JSON.toJSONString(obj);
  }
  
  /**
   * jsonStr2obj
   * @param jsonStr
   * @param _class
   * @return
   */
  public static <E> E parse(String jsonStr, Class<E> classType){
    return JSON.parseObject(jsonStr, classType);
  }
  
}
