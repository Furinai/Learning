package cn.linter.learning.user.dao;

import cn.linter.learning.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Mapper
public interface UserDao {

    /**
     * 通过用户名查询单个用户
     *
     * @param username 用户名
     * @return 单个用户
     */
    User selectByUsername(String username);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<User> list();

    /**
     * 新增用户
     *
     * @param user 用户实例
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 更新用户
     *
     * @param user 用户实例
     * @return 影响行数
     */
    int update(User user);

    /**
     * 删除用户
     *
     * @param username 用户名
     * @return 影响行数
     */
    int delete(String username);

}