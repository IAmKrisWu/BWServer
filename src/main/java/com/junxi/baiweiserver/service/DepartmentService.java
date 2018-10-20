package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.DepartmentMapper;
import com.junxi.baiweiserver.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper mapper;

    public List<Department> getAllDeps(Integer pid){
        return mapper.getAllDeps(pid);
    }

    public List<Department> getDepsForS() {
        return mapper.getDepsForS();
    }

    public void addDep(Department department){
        mapper.addDep(department);
    }

    public void delDep(Department department) {
        mapper.delDep(department);
    }
}
