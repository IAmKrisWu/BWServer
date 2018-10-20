package com.junxi.baiweiserver.mapper;

import com.junxi.baiweiserver.model.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> queryRolesByHrId(Integer id);

    List<Role> getAll();

    List<Integer> getRolesMenu(Integer rid);

    int addRole(Role role);

    int delRoleByRid(Integer rid);

}
