package com.imooc.homepage.client;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 通过 Feign 访问课程微服务
 * 包括课程接口调用，以及接口异常时的熔断机制
 */
@FeignClient(value = "eureka-client-homepage-course", fallback = CourseClientHystrix.class)
public interface CourseClient {

    @RequestMapping(value = "/homepage-course/get/course", method = RequestMethod.GET)
    CourseInfo getCourseInfo(Long id);

    @RequestMapping(value = "/homepage-course/get/courses", method = RequestMethod.POST)
    List<CourseInfo> getCourseInfos(@RequestBody CourseInfosRequest request);
}
