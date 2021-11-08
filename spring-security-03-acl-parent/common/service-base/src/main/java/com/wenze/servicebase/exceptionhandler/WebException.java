package com.wenze.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class WebException extends RuntimeException{
  private Integer code;//状态码
  private String msg;//异常信息

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
