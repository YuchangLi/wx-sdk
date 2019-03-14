package com.github.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WXPayConfigDefault extends WXPayConfig{
  
  private String appid;
  private String mchID;
  private String key;
  private String certPath;
  private String domain;
  private InputStream certStream;
  private IWXPayDomain iWXPayDomain;
  
  public WXPayConfigDefault(String appid, String mchID, String key, String certPath, String domain) {
    super();
    this.appid = appid;
    this.mchID = mchID;
    this.key = key;
    this.certPath = certPath;
    this.domain = domain;
  }

  @Override
  String getAppID() {
    return this.appid;
  }

  @Override
  String getMchID() {
    return this.mchID;
  }

  @Override
  String getKey() {
    return this.key;
  }

  @Override
  InputStream getCertStream() {
    if(this.certStream == null) {
      File file = new File(certPath);
      byte[] certData = new byte[(int) file.length()];
      InputStream certStream = null;
      try {
        certStream = new FileInputStream(file);
        certStream.read(certData);
        this.certStream = new ByteArrayInputStream(certData);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if(certStream != null) {
          try {
            certStream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return certStream;
  }

  @Override
  IWXPayDomain getWXPayDomain() {
    if(this.iWXPayDomain == null) {
      this.iWXPayDomain = new IWXPayDomain() {

        @Override
        public void report(String domain, long elapsedTimeMillis, Exception ex) {
          
        }
  
        @Override
        public DomainInfo getDomain(WXPayConfig config) {
          return new DomainInfo(domain, true);
        }
        
      };
    }
    return this.iWXPayDomain;
  }

}
