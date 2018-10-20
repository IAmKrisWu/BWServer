package com.junxi.baiweiserver.mapper;

import com.junxi.baiweiserver.model.Joblevel;

import java.util.List;

public interface JoblevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Joblevel record);

    int insertSelective(Joblevel record);

    Joblevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Joblevel record);

    int updateByPrimaryKey(Joblevel record);

    List<Joblevel> getAll();

    int addJobLevel(Joblevel joblevel);

    int updateJobLevel(Joblevel joblevel);

    int delJobLevel(Integer id);
}