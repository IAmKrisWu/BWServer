package com.junxi.baiweiserver.mapper;

import com.junxi.baiweiserver.model.HrRole;

import java.util.List;

public interface HrRoleMapper {
    List<HrRole> getByRid(Integer rid);
}
