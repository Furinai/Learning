package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.CourseDao;
import cn.linter.learning.course.entity.Category;
import cn.linter.learning.course.entity.Course;
import cn.linter.learning.course.entity.User;
import cn.linter.learning.course.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course queryById(Long id) {
        return courseDao.selectById(id);
    }

    @Override
    public Course queryById(Long id, String username) {
        Course course = courseDao.selectById(id);
        if (username != null) {
            Boolean attended = courseDao.selectRelationByUsernameAndCourseId(username, id);
            course.setAttended(attended);
        }
        return course;
    }

    @Override
    public PageInfo<Course> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(courseDao.list());
    }

    @Override
    public PageInfo<Course> listByCategoryIdOrTeacherNameOrStudentName(int pageNum, int pageSize, Integer categoryId,
                                                                       String teacherName, String studentName,
                                                                       Boolean approved, String orderBy) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(courseDao.listByCategoryIdOrTeacherNameOrStudentName(categoryId,
                teacherName, studentName, approved, orderBy));
    }

    @Override
    public Course create(Course course, String username) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setUsername(username);
        course.setTeacher(user);
        course.setApproved(false);
        course.setAverageScore((short) 0);
        course.setCreateTime(now);
        course.setUpdateTime(now);
        courseDao.insert(course);
        courseDao.insertCategoryCourseRelation(course.getId(), course.getCategories());
        return course;
    }

    @Override
    public Course update(Course course) {
        course.setUpdateTime(LocalDateTime.now());
        courseDao.update(course);
        List<Category> categories = course.getCategories();
        if (categories != null && !categories.isEmpty()) {
            courseDao.deleteCategoryCourseRelation(course.getId());
            courseDao.insertCategoryCourseRelation(course.getId(), course.getCategories());
        }
        return queryById(course.getId());
    }

    @Override
    public boolean delete(Long id) {
        boolean success = courseDao.delete(id) > 0;
        courseDao.deleteCategoryCourseRelation(id);
        return success;
    }

}

