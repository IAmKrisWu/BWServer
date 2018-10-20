package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.JoblevelMapper;
import com.junxi.baiweiserver.model.Joblevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelService {
    @Autowired
    JoblevelMapper joblevelMapper;

    public List<Joblevel> getAll(){
        return joblevelMapper.getAll();
    }

    public int addJobLevel(Joblevel joblevel){
        joblevel.setCreatedate(new Date());
        joblevel.setEnabled(true);
       return joblevelMapper.addJobLevel(joblevel);
    }

    public int updateJobLevel(Joblevel joblevel){
        return joblevelMapper.updateJobLevel(joblevel);
    }

    public int delJobLevel(Integer id){
        return joblevelMapper.delJobLevel(id);
    }
}
