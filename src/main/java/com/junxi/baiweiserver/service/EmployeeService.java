package com.junxi.baiweiserver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junxi.baiweiserver.mapper.EmployeeMapper;
import com.junxi.baiweiserver.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper mapper;

    public PageInfo<Employee> getEmployeeByPage(Integer page,Integer count,String name){
        PageHelper.startPage(page,count);
        List<Employee> employees = mapper.getEmployeeByPage(name);
        PageInfo<Employee> pageInfo = new PageInfo<>(employees);
        return pageInfo;
    }

    public boolean deleteByIds(String[] ids){
        return mapper.deleteByEmpId(ids);
    }

    public List<Employee> getAll() {
        return mapper.getAll();
    }

    public int allEmployee(List<Employee> employees){
        return mapper.allEmployee(employees);
    }
}
