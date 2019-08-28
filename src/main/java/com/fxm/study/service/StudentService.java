package com.fxm.study.service;

import com.fxm.study.pojo.Student;
public interface StudentService{


    int deleteByPrimaryKey(Integer sno);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sno);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

}
