package cn.linter.learning.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 答案实体类
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
@Data
@ToString
@EqualsAndHashCode
public class Answer implements Serializable {

    private static final long serialVersionUID = -37350213301905410L;
    /**
     * 答案ID
     */
    private Long id;
    /**
     * 内容
     */
    private String content;
    /**
     * 作者
     */
    private User author;
    /**
     * 问题ID
     */
    private Long questionId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
