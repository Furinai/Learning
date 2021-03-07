package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.EvaluationDao;
import cn.linter.learning.course.entity.Evaluation;
import cn.linter.learning.course.entity.User;
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

    public EvaluationServiceImpl(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    @Override
    public Evaluation queryById(Long id) {
        return evaluationDao.selectById(id);
    }

    @Override
    public PageInfo<Evaluation> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(evaluationDao.list());
    }

    @Override
    public PageInfo<Evaluation> listByCourseId(int pageNumber, int pageSize, Long courseId) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(evaluationDao.listByCourseId(courseId));
    }

    @Override
    public Evaluation create(Evaluation evaluation, String username) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setUsername(username);
        evaluation.setAuthor(user);
        evaluation.setCreateTime(now);
        evaluation.setUpdateTime(now);
        evaluationDao.insert(evaluation);
        return evaluation;
    }

    @Override
    public Evaluation update(Evaluation evaluation) {
        evaluation.setUpdateTime(LocalDateTime.now());
        evaluationDao.update(evaluation);
        return queryById(evaluation.getId());
    }

    @Override
    public boolean delete(Long id) {
        return evaluationDao.delete(id) > 0;
    }

}

