package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.MenuMapper;
import com.junxi.baiweiserver.model.Menu;
import com.junxi.baiweiserver.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "c1")
public class MenuService {
    @Autowired
    MenuMapper mapper;

    public List<Menu> allMenus(){
        return mapper.getMenusByHrId(HrUtils.getCurrentUser().getId());
    }

//    表示使用方法名作为缓存的key
    @Cacheable(key = "#root.method.name")
    public List<Menu> menuAndRoles() {
        return mapper.menuAndRoles();
    }

    public List<Menu> menuTree(){
        return mapper.menuTree();
    }
}
