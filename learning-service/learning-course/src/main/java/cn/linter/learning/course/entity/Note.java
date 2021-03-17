package cn.linter.learning.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 笔记实体类
 *
 * @author wangxiaoyang
 * @since 2021/03/06
 */
@Data
@ToString
@EqualsAndHashCode
public class Note implements Serializable {

    private static final long serialVersionUID = 291345456529582039L;
    /**
     * 笔记ID
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
     * 课程ID
     */
    private Long courseId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
