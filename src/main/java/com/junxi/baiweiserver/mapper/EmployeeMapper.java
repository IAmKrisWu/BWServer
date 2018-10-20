package com.junxi.baiweiserver.mapper;

import com.junxi.baiweiserver.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> getEmployeeByPage(@Param("name") String name);

    boolean deleteByEmpId(@Param("ids") String[] ids);

    List<Employee> getAll();

    int allEmployee(@Param("employees") List<Employee> employees);
}