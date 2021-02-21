package cn.linter.learning.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分类实体类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Data
@ToString
@EqualsAndHashCode
public class Category implements Serializable {

    private static final long serialVersionUID = 586009742825901403L;
    /**
     * 分类ID
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级ID
     */
    private Long parentId;

}
