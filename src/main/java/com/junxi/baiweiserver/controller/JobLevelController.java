package com.junxi.baiweiserver.controller;

import com.junxi.baiweiserver.model.Joblevel;
import com.junxi.baiweiserver.model.RespBean;
import com.junxi.baiweiserver.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService service;

    @GetMapping("/")
    public List<Joblevel> getAll(){
        List<Joblevel> all = service.getAll();
        if(all!=null){
            return all;
        }
        return null;
    }

    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        int i = service.addJobLevel(joblevel);
        if(i==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateJobLevel(Joblevel joblevel){
        int i = service.updateJobLevel(joblevel);
        if(i==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @DeleteMapping("/")
    public RespBean delJobLevel(Integer id){
        int i = service.delJobLevel(id);
        if(i==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
