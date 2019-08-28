package com.fxm.study.dao;

import com.fxm.study.pojo.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer sno);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sno);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}