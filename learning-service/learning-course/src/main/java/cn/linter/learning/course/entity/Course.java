package cn.linter.learning.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    private static final long serialVersionUID = -38543796891982620L;
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
    private Boolean attended;
    /**
     * 教师ID
     */
    private User teacher;
    /**
     * 平均评分
     */
    private Short averageScore;
    /**
     * 封面图片地址
     */
    private String coverPicture;
    /**
     * 审核通过
     */
    private Boolean approved;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 分类列表
     */
    private List<Category> categories;

}
