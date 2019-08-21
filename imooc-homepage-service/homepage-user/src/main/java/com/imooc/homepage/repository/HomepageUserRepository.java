package com.imooc.homepage.repository;

import com.imooc.homepage.entity.HomepageUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * HomepageUser访问数据库接口定义
 */
public interface HomepageUserRepository extends JpaRepository<HomepageUser, Long> {

    /**
     * 通过用户名查找数据记录
     * @param username
     * @return
     */
    HomepageUser findByUsername(String username);
}
