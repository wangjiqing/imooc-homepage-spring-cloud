package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.HomepageUserTestApplication;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.repository.HomepageUserCourseRepository;
import com.imooc.homepage.vo.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 用户服务测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HomepageUserTestApplication.class})
public class IUserServiceTest {

    @Resource
    private IUserService userService;
    @Resource
    private HomepageUserCourseRepository homepageUserCourseRepository;

    @Test
//    @Transactional
    public void testCreateUser() {
        System.out.println(JSON.toJSONString(userService.createUser(
                new CreateUserRequest("zhangsan", "zhangsan@123.com"))));
    }

    @Test
    public void testGetUserInfo() {
        // {"email":"zhangsan","id":9,"username":"zhangsan"}
        System.out.println(JSON.toJSONString(userService.getUserInfo(9L)));
    }

    @Test
    public void testCreateHompageUserCourse() {
        HomepageUserCourse course1 = new HomepageUserCourse();
        course1.setUserId(9L);
        course1.setCourseId(8L);

        HomepageUserCourse course2 = new HomepageUserCourse();
        course2.setUserId(9L);
        course2.setCourseId(9L);

        System.out.println(homepageUserCourseRepository.saveAll(Arrays.asList(course1, course2)).size());
    }

    @Test
    public void getUserCourseInfo() {
        // TODO: 这里需要调用组件接口
    }
}