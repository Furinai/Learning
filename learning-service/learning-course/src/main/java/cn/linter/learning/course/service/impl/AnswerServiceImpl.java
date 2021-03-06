package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.AnswerDao;
import cn.linter.learning.course.dao.QuestionDao;
import cn.linter.learning.course.entity.Answer;
import cn.linter.learning.course.entity.Question;
import cn.linter.learning.course.entity.User;
import cn.linter.learning.course.service.AnswerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 答案服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerDao answerDao;
    private final QuestionDao questionDao;

    public AnswerServiceImpl(AnswerDao answerDao, QuestionDao questionDao) {
        this.answerDao = answerDao;
        this.questionDao = questionDao;
    }

    @Override
    public Answer queryById(Long id) {
        return answerDao.selectById(id);
    }

    @Override
    public PageInfo<Answer> listByQuestionId(int pageNumber, int pageSize, Long questionId) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(answerDao.listByQuestionId(questionId));
    }

    @Override
    public PageInfo<Answer> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(answerDao.list());
    }

    @Override
    public Answer create(Answer answer, String username) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setUsername(username);
        answer.setAuthor(user);
        answer.setCreateTime(now);
        answer.setUpdateTime(now);
        answerDao.insert(answer);
        Question question = questionDao.selectById(answer.getQuestionId());
        question.setAnswerCount(question.getAnswerCount() + 1);
        questionDao.update(question);
        return answer;
    }

    @Override
    public Answer update(Answer answer) {
        answer.setUpdateTime(LocalDateTime.now());
        answerDao.update(answer);
        return queryById(answer.getId());
    }

    @Override
    public boolean delete(Long id) {
        boolean success = answerDao.delete(id) > 0;
        Answer answer = answerDao.selectById(id);
        Question question = questionDao.selectById(answer.getQuestionId());
        question.setAnswerCount(question.getAnswerCount() - 1);
        questionDao.update(question);
        return success;
    }

}

