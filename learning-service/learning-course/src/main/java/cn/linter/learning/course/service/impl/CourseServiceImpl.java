package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.CourseDao;
import cn.linter.learning.course.dao.CourseSearchDao;
import cn.linter.learning.course.entity.Category;
import cn.linter.learning.course.entity.Course;
import cn.linter.learning.course.entity.User;
import cn.linter.learning.course.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private final CourseSearchDao courseSearchDao;

    public CourseServiceImpl(CourseDao courseDao, CourseSearchDao courseSearchDao) {
        this.courseDao = courseDao;
        this.courseSearchDao = courseSearchDao;
    }

    @Override
    public Course queryById(Long id) {
        return courseDao.selectById(id);
    }

    @Override
    public Course queryById(Long id, String username) {
        Course course = courseDao.selectById(id);
        course.setRegistered(courseDao.selectRegistration(username, id));
        return course;
    }

    @Override
    public PageInfo<Course> list(int pageNum, int pageSize, Boolean approved, String orderBy) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(courseDao.list(approved, orderBy));
    }

    @Override
    public PageInfo<Course> listByTeacherName(int pageNum, int pageSize, String teacherName) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(courseDao.listByTeacherName(teacherName));
    }

    @Override
    public PageInfo<Course> listByStudentName(int pageNum, int pageSize, String studentName) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(courseDao.listByStudentName(studentName));
    }

    @Override
    public PageInfo<Course> listByCategoryId(int pageNum, int pageSize, Integer categoryId, String orderBy) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(courseDao.listByCategoryId(categoryId, orderBy));
    }

    @Override
    public List<Category> listCategoryById(Long id) {
        return courseDao.listCategoryById(id);
    }

    @Override
    public Course create(Course course, String username) {
        User user = new User();
        user.setUsername(username);
        course.setTeacher(user);
        course.setApproved(false);
        course.setAverageScore(0);
        LocalDateTime now = LocalDateTime.now();
        course.setCreateTime(now);
        course.setUpdateTime(now);
        courseDao.insert(course);
        courseDao.insertCategory(course.getId(), course.getCategories());
        courseSearchDao.save(course);
        return course;
    }

    @Override
    public Course update(Course course) {
        course.setUpdateTime(LocalDateTime.now());
        int rows = courseDao.update(course);
        System.out.println(rows);
        List<Category> categories = course.getCategories();
        if (categories != null && !categories.isEmpty()) {
            courseDao.deleteCategory(course.getId());
            courseDao.insertCategory(course.getId(), categories);
        }
        Course newCourse = courseDao.selectById(course.getId());
        courseSearchDao.deleteById(course.getId());
        courseSearchDao.save(newCourse);
        return newCourse;
    }

    @Override
    public boolean delete(Long id) {
        courseDao.deleteCategory(id);
        boolean success = courseDao.delete(id) > 0;
        if (success) {
            courseSearchDao.deleteById(id);
        }
        return success;
    }

    @Override
    public Course insertRegistration(String username, Long courseId) {
        courseDao.insertRegistration(username, courseId);
        return courseDao.selectById(courseId);
    }

    @Override
    public Page<Course> search(String keyword, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return courseSearchDao.findByNameOrDescription(keyword, keyword, pageable);
    }

    @Override
    public void synchronize() {
        courseSearchDao.deleteAll();
        courseSearchDao.saveAll(courseDao.list(true, "create_time"));
    }

}