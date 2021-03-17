package cn.linter.learning.user.service;

import cn.linter.learning.user.entity.User;
import com.github.pagehelper.PageInfo;

/**
 * 用户服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public interface UserService {

    /**
     * 通过用户名查询单个用户
     *
     * @param username 用户名
     * @return 单个用户
     */
    User queryByUsername(String username);

    /**
     * 分页查询所有用户
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 用户列表
     */
    PageInfo<User> list(int pageNum, int pageSize);

    /**
     * 新增用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    User create(User user);

    /**
     * 更新用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    User update(User user);

    /**
     * 删除用户
     *
     * @param username 用户名
     * @return 是否成功
     */
    boolean delete(String username);

}