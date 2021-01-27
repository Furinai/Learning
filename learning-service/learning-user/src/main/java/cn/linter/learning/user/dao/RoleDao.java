package cn.linter.learning.user.dao;

import cn.linter.learning.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@Mapper
public interface RoleDao {

    /**
     * 通过ID查询单个角色
     *
     * @param id 角色ID
     * @return 单个角色
     */
    Role selectById(Integer id);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Role> list();

    /**
     * 新增角色
     *
     * @param role 角色实例
     * @return 角色实例
     */
    int insert(Role role);

    /**
     * 更新角色
     *
     * @param role 角色实例
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return 影响行数
     */
    int delete(Integer id);

}