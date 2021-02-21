package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.CourseDao;
import cn.linter.learning.course.entity.Course;
import cn.linter.learning.course.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * 课程服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course queryById(Long id) {
        return courseDao.selectById(id);
    }

    @Override
    public PageInfo<Course> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(courseDao.list());
    }

    @Override
    public PageInfo<Course> listByCategoryId(int pageNumber, int pageSize, Integer categoryId, String orderBy) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(courseDao.listByCategoryId(categoryId, orderBy));
    }

    @Override
    public Course create(Course course) {
        courseDao.insert(course);
        return course;
    }

    @Override
    public Course update(Course course) {
        courseDao.update(course);
        return queryById(course.getId());
    }

    @Override
    public boolean delete(Long id) {
        return courseDao.delete(id) > 0;
    }

}

