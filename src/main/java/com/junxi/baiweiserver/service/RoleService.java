package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.RoleMapper;
import com.junxi.baiweiserver.model.HrRole;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    RoleMapper mapper;

    public List<Role> queryRolesByHrId(Integer id){
        return mapper.queryRolesByHrId(id);
    }

    public List<Role> getAll(){
        return mapper.getAll();
    }

    public List<Integer> getRoleMenu(Integer rid){
        return mapper.getRolesMenu(rid);
    }

    public int addRole(Role role){
        return mapper.addRole(role);
    }

    @Autowired
    HrRoleService hrRoleService;
    @Autowired
    MenuRoleService menuRoleService;
    public int delRole(Integer rid){
//        删除角色，要先判断是否有用户正在使用该角色
        List<HrRole> hrRoles = hrRoleService.getByRid(rid);
        if(hrRoles==null||hrRoles.size()==0){
            //先删从表。因为从表有外键
            menuRoleService.delMenuByRid(rid);
//            再删主表
            mapper.delRoleByRid(rid);

            return 1;
        }
        return -1;
    }
}
