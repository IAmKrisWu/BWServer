package com.junxi.baiweiserver.controller;

import com.junxi.baiweiserver.model.Menu;
import com.junxi.baiweiserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    MenuService service;

    @GetMapping("/menus")
    public List<Menu> getMenusByHrId(){
//        这里获取用户菜单不能使用传过来的id，因为不安全。使用登录后，SecurityContextHolder存放的用户信息。
        return service.allMenus();
    }


}
