package com.junxi.baiweiserver.controller;

import com.junxi.baiweiserver.model.Menu;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.MenuRoleService;
import com.junxi.baiweiserver.service.MenuService;
import com.junxi.baiweiserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permis")
public class PermisController {

    @Autowired
    MenuService menuService;

    @Autowired
    RoleService roleService;

    @Autowired
    MenuRoleService menuRoleService;

    @GetMapping("/menutree")
    public List<Menu> getMenuTree(){
        return menuService.menuTree();
    }

    @GetMapping("/menu")
    public List<Integer> getRolesMenu(Integer rid){
        return roleService.getRoleMenu(rid);
    }

    @PutMapping("/")
    public RespBean updateMenu(Integer rid,Integer[] mids){
        return menuRoleService.updateMenu(rid,mids);
    }
}
