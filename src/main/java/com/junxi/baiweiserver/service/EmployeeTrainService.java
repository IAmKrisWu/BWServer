package com.junxi.baiweiserver.service;

import com.junxi.baiweiserver.mapper.EmployeeTrainMapper;
import com.junxi.baiweiserver.model.EmployeeTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeTrainService {
    @Autowired
    EmployeeTrainMapper mapper;

    public List<EmployeeTrain> getAll(){
        return mapper.getAll();
    }

    public int addTrain(EmployeeTrain train) {
        return mapper.addTrain(train);
    }

    public int delTrain(Integer id){
        return mapper.delTrain(id);
    }

    public int updateTrain(EmployeeTrain train){
        return mapper.updateTrain(train);
    }
}
