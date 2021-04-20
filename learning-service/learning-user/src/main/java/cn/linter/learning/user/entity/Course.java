package cn.linter.learning.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 课程实体类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Data
@ToString
@EqualsAndHashCode
public class Course implements Serializable {

    private static final long serialVersionUID = -1643655146233502915L;
    /**
     * 课程ID
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 简介
     */
    private String description;
    /**
     * 是否参与
     */
    private Boolean registered;
    /**
     * 教师ID
     */
    private User teacher;
    /**
     * 平均评分
     */
    private Integer averageScore;
    /**
     * 封面图片地址
     */
    private String coverPicture;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}