package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.HomepageCourseTestApplication;
import com.imooc.homepage.entity.HomepageCourse;
import com.imooc.homepage.repository.HomepageCourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 课程服务接口测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HomepageCourseTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ICourseServiceTest {

    @Resource
    private ICourseService courseService;

    @Autowired
    private HomepageCourseRepository homepageCourseRepository;

//    @Test
    public void testCreateCourseInfo() {
        HomepageCourse course1 = new HomepageCourse(
                "JDK11&12新特性解读", 0, "https://www.imooc.com", "解读JDK11&12新版本特性");
        HomepageCourse course2 = new HomepageCourse(
                "微服务SpringCloud实战", 1, "https://www.imooc.com", "实战SpringCloud");
        int size = homepageCourseRepository.saveAll(Arrays.asList(course1, course2)).size();
        System.out.println(size);
    }

    @Test
    public void testGetCourseInfo() {
        System.out.println(JSON.toJSONString(courseService.getCourseInfo(8L)));
        System.out.println(JSON.toJSONString(courseService.getCourseInfo(1L)));
    }

    @Test
    public void testGetCourseInfos() {
        System.out.println(JSON.toJSONString(courseService.getCourseInfos(new CourseInfosRequest(Arrays.asList(8L, 9L)))));
    }


//    @MockBean
//    private HomepageCourseRepository homepageCourseRepository;
//
//    @Test
//    public void getCourseInfo() {
//        HomepageCourse homepageCourse = new HomepageCourse("课程名称", 0, "课程图标", "课程介绍");
//        Optional<HomepageCourse> optional = Optional.of(homepageCourse);
//        given(this.homepageCourseRepository.findById(1L)).willReturn(optional);
//        CourseInfo courseInfo = courseService.getCourseInfo(1L);
//        System.out.println(courseInfo);
//    }
}