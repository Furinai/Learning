package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.CategoryDao;
import cn.linter.learning.course.entity.Category;
import cn.linter.learning.course.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category queryById(Integer id) {
        return categoryDao.selectById(id);
    }

    @Override
    public List<Category> list() {
        return categoryDao.list();
    }

    @Override
    public Category create(Category category) {
        LocalDateTime now = LocalDateTime.now();
        category.setCreateTime(now);
        category.setUpdateTime(now);
        categoryDao.insert(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryDao.update(category);
        return categoryDao.selectById(category.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return categoryDao.delete(id) > 0;
    }

}

