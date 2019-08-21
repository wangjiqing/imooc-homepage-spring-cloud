package com.imooc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程对外服务接口
 *  直接访问：
 *      http://127.0.0.1:7001/homepage-course
 *  Zuul网关访问：
 *      http://127.0.0.1:9000/imooc/homepage-course
 */
@Slf4j
@RestController
public class HomepageCourseController {

    @Resource
    private ICourseService courseService;

    @GetMapping("/get/course")
    public CourseInfo getCourseInfo(Long id) {
        log.info("<homepage-course>: get course -> {}", id);
        return courseService.getCourseInfo(id);
    }

    @PostMapping("/get/courses")
    public List<CourseInfo> getCourseInfos(@RequestBody CourseInfosRequest request) {
        log.info("<homepage-course>: get courses -> {}", JSON.toJSONString(request));
        return courseService.getCourseInfos(request);
    }
}