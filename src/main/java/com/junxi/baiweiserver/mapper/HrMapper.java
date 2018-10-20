package com.junxi.baiweiserver.mapper;

import com.junxi.baiweiserver.model.Hr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HrMapper {

    Hr loadUserByUsername(String username);

    List<Hr> getAllHrWithRoles();

    int updateEnabled(Hr hr);

    int addRoles(@Param("rids") Integer[] rids, @Param("hrid") Integer hrid);

    int deleteRoles(Integer hrid);

    int delHr(Integer hrid);

    int delMsg(Integer hrid);
}
