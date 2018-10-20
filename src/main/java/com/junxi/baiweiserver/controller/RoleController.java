package com.junxi.baiweiserver.controller;

import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.model.Role;
import com.junxi.baiweiserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/roles")
public class RoleController {
    @Autowired
    RoleService service;

    @GetMapping("/")
    public List<Role> getAll(){
        return service.getAll();
    }

    @PostMapping("/")
    public RespBean addRole(Role role){
        int i = service.addRole(role);
        if(i==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/")
    public RespBean delRole(Integer rid){
        int i = service.delRole(rid);
        if(i==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("有用户正在使用该角色，无法删除");
    }

}
