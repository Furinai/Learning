package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.entity.Category;
import cn.linter.learning.course.dao.CategoryDao;
import cn.linter.learning.course.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category queryById(Long id) {
        return categoryDao.selectById(id);
    }

    @Override
    public PageInfo<Category> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(categoryDao.list());
    }

    @Override
    public Category create(Category category) {
        categoryDao.insert(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        categoryDao.update(category);
        return queryById(category.getId());
    }

    @Override
    public boolean delete(Long id) {
        return categoryDao.delete(id) > 0;
    }

}

