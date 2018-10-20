package com.junxi.baiweiserver.mapper;

import com.junxi.baiweiserver.model.EmployeeTrain;

import java.util.List;

public interface EmployeeTrainMapper {
    List<EmployeeTrain> getAll();

    int addTrain(EmployeeTrain train);

    int delTrain(Integer id);

    int updateTrain(EmployeeTrain train);
}
