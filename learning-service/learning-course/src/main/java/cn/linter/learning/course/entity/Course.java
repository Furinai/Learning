package cn.linter.learning.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = -38543796891982620L;
    /**
     * 课程ID
     */
    @Id
    private Long id;
    /**
     * 名称
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;
    /**
     * 价格
     */
    @Field(type = FieldType.Text)
    private BigDecimal price;
    /**
     * 简介
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String description;
    /**
     * 是否报名
     */
    @Transient
    private Boolean registered;
    /**
     * 教师ID
     */
    @Transient
    private User teacher;
    /**
     * 平均评分
     */
    @Transient
    private Integer averageScore;
    /**
     * 封面图片地址
     */
    @Field(name = "cover_picture", type = FieldType.Text)
    private String coverPicture;
    /**
     * 审核通过
     */
    @Transient
    private Boolean approved;
    /**
     * 创建时间
     */
    @Transient
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @Transient
    private LocalDateTime updateTime;
    /**
     * 分类列表
     */
    @Transient
    private List<Category> categories;

}