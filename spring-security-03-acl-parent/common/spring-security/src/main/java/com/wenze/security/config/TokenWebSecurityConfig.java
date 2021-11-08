package com.wenze.security.config;

import com.wenze.security.filter.TokenAuthFilter;
import com.wenze.security.filter.TokenLoginFilter;
import com.wenze.security.security.DefaultPasswordEncoder;
import com.wenze.security.security.TokenLogoutHandler;
import com.wenze.security.security.TokenManager;
import com.wenze.security.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
  private TokenManager tokenManager;
  private RedisTemplate redisTemplate;
  private DefaultPasswordEncoder defaultPasswordEncoder;
  private UserDetailsService userDetailsService;

  @Autowired
  public TokenWebSecurityConfig(TokenManager tokenManager, RedisTemplate redisTemplate, DefaultPasswordEncoder defaultPasswordEncoder, UserDetailsService userDetailsService) {
    this.tokenManager = tokenManager;
    this.redisTemplate = redisTemplate;
    this.defaultPasswordEncoder = defaultPasswordEncoder;
    this.userDetailsService = userDetailsService;
  }

  /**
   * 配置设置，设置退出的地址和token，redis操作地址
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
        // 没有权限访问
        .authenticationEntryPoint(new UnauthorizedEntryPoint())
        .and().csrf().disable()
        .authorizeRequests()
        .anyRequest().authenticated()
        // 设置退出路径
        .and().logout().logoutUrl("/admin/cal/index/logout")
        .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate))
        .and().addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
        .addFilter(new TokenAuthFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
  }

  /**
   * 调用userDetailsService和密码处理
   * @param auth
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
  }

  /**
   * 不进行认证的路径，可以直接访问
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/api/**");
  }
}
