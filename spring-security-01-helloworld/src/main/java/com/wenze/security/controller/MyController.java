package com.wenze.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyController {

  @GetMapping("/hello")
  public String hello(){
    return "hello Spring Security";
  }

  @GetMapping("/index")
  public String index(){
    return "hello index";
  }

  @GetMapping("/update")
  // @Secured({"ROLE_sale", "ROLE_manager"})
  // @PreAuthorize("hasAnyAuthority('admins')")
  @PostAuthorize("hasAnyAuthority('admins')")
  public String update(){
    return "hello update";
  }
}
