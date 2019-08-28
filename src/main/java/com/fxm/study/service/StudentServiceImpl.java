package com.fxm.study.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.fxm.study.pojo.Student;
import com.fxm.study.dao.StudentMapper;
import com.fxm.study.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(Integer sno) {
        return studentMapper.deleteByPrimaryKey(sno);
    }

    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(Integer sno) {
        return studentMapper.selectByPrimaryKey(sno);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

}
