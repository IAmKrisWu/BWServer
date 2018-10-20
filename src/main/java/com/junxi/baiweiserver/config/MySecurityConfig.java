package com.junxi.baiweiserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Autowired
    CustomAccessDecisionManager customAccessDecisionManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        设置hrservice，并且设置对密码进行加密，使用BCryptPasswordEncoder即使使用相同的密码，加密后也是不一样的。
//        security会帮我们自动进行匹配
        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }
//  默认所有的请求都拦截，在这里可以设置对某些如静态文件不进行拦截。
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login_page");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        登录的地址设置为login,用户名参数名为username,密码参数名为password
        http.authorizeRequests()
//                .anyRequest().authenticated()
//                将SecurityMetadataSource和AccessDecisionManager设置进来。
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(customAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login_page")
                .successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                httpServletResponse.setContentType("text/html;charset=utf-8");
//                httpServletResponse.getWriter().write("登录成功");
//              注意：这个编码格式必须放在最上面，否则不生效
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter writer = resp.getWriter();
//              将对象转为json字符串
                ObjectMapper objectMapper = new ObjectMapper();
                String success = objectMapper.writeValueAsString(RespBean.ok("登录成功",authentication.getPrincipal()));
                writer.write(success);
                writer.flush();
                writer.close();


            }
        }).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                httpServletResponse.setContentType("text/html;charset=utf-8");
//                httpServletResponse.getWriter().write("登录失败");

                resp.setContentType("application/json;charset=utf-8");
                resp.setStatus(200);
                RespBean respBean = null;
                if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                    respBean = RespBean.error("账户名或者密码输入错误!");
                } else if (e instanceof LockedException) {
                    respBean = RespBean.error("账户被锁定，请联系管理员!");
                } else if (e instanceof CredentialsExpiredException) {
                    respBean = RespBean.error("密码过期，请联系管理员!");
                } else if (e instanceof AccountExpiredException) {
                    respBean = RespBean.error("账户过期，请联系管理员!");
                } else if (e instanceof DisabledException) {
                    respBean = RespBean.error("账户被禁用，请联系管理员!");
                } else {
                    respBean = RespBean.error("登录失败!");
                }

                //    将对象转为json字符串
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = resp.getWriter();
                out.write(om.writeValueAsString(respBean));
                out.flush();
                out.close();

            }
        }).permitAll().and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                RespBean respBean = RespBean.ok("注销成功");
                ObjectMapper om = new ObjectMapper();
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(om.writeValueAsString(respBean));
                writer.flush();
                writer.close();
            }
        }).permitAll().and().csrf().disable();
//        permitAll当前的配置允许访问
//        不对登录进行拦截。关闭spring security自带的csrf攻击检测（因为要用postman测试，所以先关闭）
//        logout默认的路径为/logout
    }
}
