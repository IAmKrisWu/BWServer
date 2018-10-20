package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.MenuRoleMapper;
import com.junxi.baiweiserver.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuRoleService {
    @Autowired
    MenuRoleMapper mapper;

    public RespBean updateMenu(Integer rid,Integer[] mids){
//        先删除后添加
        mapper.deleteMenuByRid(rid);
//        如果是清空菜单，则直接返回
        if(mids.length==0||mids==null){
            return RespBean.ok("修改成功");
        }

        Integer i = mapper.addMenusByRid(rid, mids);
        if(i>0){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    public int delMenuByRid(Integer rid){
        return mapper.deleteMenuByRid(rid);
    }
}
