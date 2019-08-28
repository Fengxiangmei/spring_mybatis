package com.fxm.study;

import static org.junit.Assert.assertTrue;

import com.fxm.study.pojo.Student;
import com.fxm.study.service.StudentService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static StudentService studentService;
    public static ApplicationContext ctx;

    Logger logger = Logger.getLogger(AppTest.class);
    @Before
    public  void before(){
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public  void testMerge(){
        studentService=(StudentService) ctx.getBean("studentServiceImpl");
        Student  student = studentService.selectByPrimaryKey(1);
        logger.info(student.getSname() + ",," + student.getSno());
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
