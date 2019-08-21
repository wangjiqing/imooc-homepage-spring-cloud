package com.imooc.homepage;

import lombok.*;

/**
 * 课程信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseInfo {

    private Long id;
    private String courseName;
    private String courseType;
    private String courseIcon;
    private String courseIntro;

    public static CourseInfo invalid() {
        return new CourseInfo(-1L, "", "", "", "");
    }
}
