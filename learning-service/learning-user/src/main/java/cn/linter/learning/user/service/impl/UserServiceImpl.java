package cn.linter.learning.user.service.impl;

import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.exception.BusinessException;
import cn.linter.learning.user.dao.UserDao;
import cn.linter.learning.user.entity.User;
import cn.linter.learning.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User queryByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public PageInfo<User> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(userDao.list());
    }

    @Override
    public User create(User user) {
        if (userDao.selectByUsername(user.getUsername()) != null) {
            throw new BusinessException(ResultStatus.USERNAME_ALREADY_EXISTS);
        }
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userDao.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        User oldUser = userDao.selectByUsername(user.getUsername());
        if (oldUser != null && !oldUser.getUsername().equals(user.getUsername())) {
            throw new BusinessException(ResultStatus.USERNAME_ALREADY_EXISTS);
        }
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        user.setUpdateTime(LocalDateTime.now());
        userDao.update(user);
        return queryByUsername(user.getUsername());
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id) > 0;
    }

}