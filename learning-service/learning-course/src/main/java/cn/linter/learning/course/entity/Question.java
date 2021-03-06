package cn.linter.learning.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 问题实体类
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
@Data
@ToString
@EqualsAndHashCode
public class Question implements Serializable {

    private static final long serialVersionUID = -60145375148094938L;
    /**
     * 问题ID
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 作者
     */
    private User author;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 答案数量
     */
    private Integer answerCount;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
