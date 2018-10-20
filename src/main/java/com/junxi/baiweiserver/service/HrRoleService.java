package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.HrRoleMapper;
import com.junxi.baiweiserver.model.HrRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HrRoleService {
    @Autowired
    HrRoleMapper mapper;

    public List<HrRole> getByRid(Integer rid){
        return mapper.getByRid(rid);
    }
}
