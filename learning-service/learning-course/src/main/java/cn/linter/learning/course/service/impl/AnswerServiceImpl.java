package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.AnswerDao;
import cn.linter.learning.course.dao.QuestionDao;
import cn.linter.learning.course.entity.Answer;
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
    public PageInfo<Answer> listByQuestionId(int pageNum, int pageSize, Long questionId) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(answerDao.listByQuestionId(questionId));
    }

    @Override
    public PageInfo<Answer> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(answerDao.list());
    }

    @Override
    public Answer create(Answer answer, String username) {
        User user = new User();
        user.setUsername(username);
        answer.setAuthor(user);
        LocalDateTime now = LocalDateTime.now();
        answer.setCreateTime(now);
        answer.setUpdateTime(now);
        answerDao.insert(answer);
        questionDao.increaseAnswerCountByQuestionId(answer.getQuestionId());
        return answer;
    }

    @Override
    public Answer update(Answer answer) {
        answer.setUpdateTime(LocalDateTime.now());
        answerDao.update(answer);
        return answerDao.selectById(answer.getId());
    }

    @Override
    public boolean delete(Long id) {
        questionDao.decreaseAnswerCountByAnswerId(id);
        return answerDao.delete(id) > 0;
    }

}

