package com.junxi.baiweiserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junxi.baiweiserver.model.Menu;
import com.junxi.baiweiserver.model.Role;
import com.junxi.baiweiserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * SecurityMetaDataSource用于获取用户请求的地址，根据获取的地址与数据库中所有菜单的url进行匹配，如果匹配成功，
 * 则返回该请求地址需要哪些角色才能请求的角色数组。用SecurityConfig.createList(arr)进行返回。
 * 然后在AccessDecisionManager中，获取用于拥有的角色，与在SecurityMetadataSource中返回的角色数组进行匹配，
 * 如果匹配成功，直接通过，匹配不成功，则抛出AccessDeniedException异常
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
//  可以进行ant风格匹配的匹配器
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) o;
//        获取我们请求的地址,如：  /employee/basic/hello
        String requestUrl = filterInvocation.getRequestUrl();

        List<Menu> menuaAndRoles = menuService.menuAndRoles();
        for (int i = 0; i < menuaAndRoles.size(); i++) {
//            如果地址与某个菜单的url匹配
            if (antPathMatcher.match(menuaAndRoles.get(i).getUrl(),requestUrl)){
                List<Role> roles = menuaAndRoles.get(i).getRoles();
                String[] roleName = new String[roles.size()];
                for (int j = 0; j < roles.size(); j++) {
                    roleName[j] = roles.get(j).getName();
                }
//                返回访问这个地址需要的角色数组
                return SecurityConfig.createList(roleName);
            }
        }

        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

//  直接返回true表示支持
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
