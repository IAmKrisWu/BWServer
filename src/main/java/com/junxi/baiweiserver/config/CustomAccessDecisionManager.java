package com.junxi.baiweiserver.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
//    在这个方法决定用户是否有对应的权限
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
//      获取用户所有的权限，也就是在hr类中方法getAuthorities
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


//        collection就是我们在FilterInvocationSecurityManager中返回的请求地址所需要的角色集合
        for (ConfigAttribute configAttribute : collection) {
//            角色的名字
            String attribute = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(attribute)) {
//                判断是否已经登录
                if(authentication instanceof UsernamePasswordAuthenticationToken){
                    return;
                }
            }

//            用户拥有的角色。不要忘记用autority.getAutority()方法获取用户拥有的角色名喔～
            for (GrantedAuthority authority : authorities) {
                if(attribute.equals(authority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
