package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.client.CourseClient;
import com.imooc.homepage.entity.HomepageUser;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.repository.HomepageUserCourseRepository;
import com.imooc.homepage.repository.HomepageUserRepository;
import com.imooc.homepage.service.IUserService;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户接口相关实现
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private HomepageUserRepository homepageUserRepository;
    @Resource
    private HomepageUserCourseRepository homepageUserCourseRepository;
    @Resource
    private CourseClient courseClient;

    @Transactional
    @Override
    public UserInfo createUser(CreateUserRequest request) {
        if (!request.validate()) {
            return UserInfo.invalid();
        }

        HomepageUser oldUser = homepageUserRepository.findByUsername(request.getUsername());
        if (null != oldUser) {
            return UserInfo.invalid();
        }

        HomepageUser newUser = homepageUserRepository.save(new HomepageUser(
                request.getUsername(), request.getUsername()));
        return new UserInfo(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    @Override
    public UserInfo getUserInfo(Long id) {
        Optional<HomepageUser> user = homepageUserRepository.findById(id);
        if (!user.isPresent()) {
            return UserInfo.invalid();
        }

        HomepageUser curUser = user.get();
        return new UserInfo(curUser.getId(), curUser.getUsername(), curUser.getEmail());
    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {
        Optional<HomepageUser> user = homepageUserRepository.findById(id);
        if (!user.isPresent()) {
            return UserCourseInfo.invalid();
        }

        HomepageUser homepageUser = user.get();
        UserInfo userInfo = new UserInfo(homepageUser.getId(), homepageUser.getUsername(), homepageUser.getEmail());

        List<HomepageUserCourse> userCourses = homepageUserCourseRepository.findAllByUserId(homepageUser.getId());
        if (CollectionUtils.isEmpty(userCourses)) {
            return new UserCourseInfo(userInfo, Collections.EMPTY_LIST);
        }

        List<CourseInfo> courseInfos = courseClient.getCourseInfos(new CourseInfosRequest(userCourses.stream()
                .map(HomepageUserCourse::getCourseId).collect(Collectors.toList())));

        return new UserCourseInfo(userInfo, courseInfos);
    }
}
