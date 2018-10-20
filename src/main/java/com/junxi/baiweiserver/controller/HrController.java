package com.junxi.baiweiserver.controller;

import com.junxi.baiweiserver.model.Hr;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.HrService;
import com.junxi.baiweiserver.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService service;
    @Autowired
    MenuRoleService menuRoleService;
    @GetMapping("/")
    public List<Hr> getAllHr(){
        return service.getAllHrWithRoles();
    }

    @PutMapping("/enabled")
    public RespBean enabledChange(Hr hr){
        int i = service.updateEnabled(hr);
        if(i==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @PutMapping("/roles")
    public RespBean updateRoles(Integer[] rids,Integer hrid){

        int i = service.updateRoles(rids, hrid);
        if(i==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("角色为空");
    }

    @DeleteMapping("/")
    public RespBean delHr(Integer hrid){
        int i = service.delHr(hrid);
        if(i==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
