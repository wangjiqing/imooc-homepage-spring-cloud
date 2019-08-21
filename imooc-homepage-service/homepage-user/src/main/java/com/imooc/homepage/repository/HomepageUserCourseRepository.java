package com.imooc.homepage.repository;

        import com.imooc.homepage.entity.HomepageUserCourse;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

/**
 * HomepageUserCourse 访问数据接口定义
 */
public interface HomepageUserCourseRepository extends JpaRepository<HomepageUserCourse, Long> {

    /**
     * 通过用户id寻找课程记录
     * @param userId
     * @return
     */
    List<HomepageUserCourse> findAllByUserId(Long userId);
}
