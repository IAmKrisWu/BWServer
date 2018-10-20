package com.junxi.baiweiserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junxi.baiweiserver.model.Department;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/dep")
public class DepController {

    @Autowired
    DepartmentService service;

    @GetMapping("/")
    public List<Department> getDeps(@RequestParam(defaultValue = "-1") Integer pid){
        List<Department> allDeps = service.getAllDeps(pid);

        return allDeps;
    }

    @GetMapping("/depsforselect")
    public List<Department> getDepsForS(){

        return service.getDepsForS();
    }

    @PostMapping("/")
    public RespBean addDep(Department department){
        department.setEnabled(true);
//        因为调用的是存储过程，因此不需要返回值，直接修改的是对象
        service.addDep(department);
        if(department.getResult()==1){
            return RespBean.ok("添加成功",department);
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/")
    public RespBean delDep(Department department){
        service.delDep(department);
        if(department.getResult()==1){
            return RespBean.ok("删除成功");
        }else if(department.getResult()==-1){
            return RespBean.error("该部门下有员工，无法删除");
        }
        return RespBean.error("删除失败");
    }
}
