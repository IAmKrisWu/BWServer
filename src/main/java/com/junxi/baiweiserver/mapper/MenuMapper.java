package com.junxi.baiweiserver.mapper;

import com.junxi.baiweiserver.model.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> getMenusByHrId(Integer id);

    List<Menu> menuAndRoles();

    List<Menu> menuTree();
}
