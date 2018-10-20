package com.junxi.baiweiserver.mapper;

import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    int deleteMenuByRid(Integer rid);

    int addMenusByRid(@Param("rid") Integer rid, @Param("mids") Integer[] mids);

}
