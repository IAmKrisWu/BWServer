package com.junxi.baiweiserver.controller;

import com.junxi.baiweiserver.model.EmployeeTrain;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.EmployeeTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnel/train")
public class EmployeeTrainController {
    @Autowired
    EmployeeTrainService service;

    @GetMapping("/")
    public List<EmployeeTrain> getAll() {
        return service.getAll();
    }

    @PostMapping("/")
    public RespBean addTrain(EmployeeTrain train) {
        int i = service.addTrain(train);
        if (i == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/")
    public RespBean delTrain(Integer id){
        int i = service.delTrain(id);
        if(i==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PutMapping("/")
    public RespBean updateTrain(EmployeeTrain train){
        int i = service.updateTrain(train);
        if(i==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
