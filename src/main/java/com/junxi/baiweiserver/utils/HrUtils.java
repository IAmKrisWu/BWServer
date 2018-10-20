package com.junxi.baiweiserver.utils;

import com.junxi.baiweiserver.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {
    public static Hr getCurrentUser() {
//        获取当前登录的对象
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}