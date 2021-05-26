package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.EvaluationDao;
import cn.linter.learning.course.entity.Course;
import cn.linter.learning.course.entity.Evaluation;
import cn.linter.learning.course.entity.User;
import cn.linter.learning.course.service.CourseService;
import cn.linter.learning.course.service.EvaluationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 评价服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/03/07
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationDao evaluationDao;
    private final CourseService courseService;

    public EvaluationServiceImpl(EvaluationDao evaluationDao, CourseService courseService) {
        this.evaluationDao = evaluationDao;
        this.courseService = courseService;
    }

    @Override
    public Evaluation queryById(Long id) {
        return evaluationDao.selectById(id);
    }

    @Override
    public PageInfo<Evaluation> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(evaluationDao.list());
    }

    @Override
    public PageInfo<Evaluation> listByCourseId(int pageNum, int pageSize, Long courseId) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(evaluationDao.listByCourseId(courseId));
    }

    @Override
    public Evaluation create(Evaluation evaluation, String username) {
        LocalDateTime now = LocalDateTime.now();
        evaluation.setCreateTime(now);
        evaluation.setUpdateTime(now);
        Course course = new Course();
        User user = new User();
        user.setUsername(username);
        evaluation.setAuthor(user);
        evaluationDao.insert(evaluation);
        Long courseId = evaluation.getCourseId();
        course.setId(courseId);
        Integer averageScore = evaluationDao.selectAverageScoreByCourseId(courseId);
        course.setAverageScore(averageScore);
        courseService.update(course);
        return evaluation;
    }

    @Override
    public Evaluation update(Evaluation evaluation) {
        evaluation.setUpdateTime(LocalDateTime.now());
        evaluationDao.update(evaluation);
        return evaluationDao.selectById(evaluation.getId());
    }

    @Override
    public boolean delete(Long id) {
        return evaluationDao.delete(id) > 0;
    }

}