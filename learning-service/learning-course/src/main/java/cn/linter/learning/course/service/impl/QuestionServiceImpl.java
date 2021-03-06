package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.QuestionDao;
import cn.linter.learning.course.entity.Question;
import cn.linter.learning.course.entity.User;
import cn.linter.learning.course.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 问题服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Question queryById(Long id) {
        return questionDao.selectById(id);
    }

    @Override
    public PageInfo<Question> listByCourseId(int pageNumber, int pageSize, Long courseId, String orderBy) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(questionDao.listByCourseId(courseId, orderBy));
    }

    @Override
    public PageInfo<Question> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(questionDao.list());
    }

    @Override
    public Question create(Question question, String username) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setUsername(username);
        question.setAuthor(user);
        question.setAnswerCount(0);
        question.setCreateTime(now);
        question.setUpdateTime(now);
        questionDao.insert(question);
        return question;
    }

    @Override
    public Question update(Question question) {
        question.setUpdateTime(LocalDateTime.now());
        questionDao.update(question);
        return queryById(question.getId());
    }

    @Override
    public boolean delete(Long id) {
        return questionDao.delete(id) > 0;
    }

}

