package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.ChapterDao;
import cn.linter.learning.course.entity.Chapter;
import cn.linter.learning.course.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 章节服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/03/02
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterDao chapterDao;

    public ChapterServiceImpl(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    @Override
    public Chapter queryById(Long id) {
        return chapterDao.selectById(id);
    }

    @Override
    public List<Chapter> list() {
        return chapterDao.list();
    }

    @Override
    public List<Chapter> listByCourseId(Long courseId) {
        return chapterDao.listByCourseId(courseId);
    }

    @Override
    public Chapter create(Chapter chapter) {
        chapterDao.insert(chapter);
        return chapter;
    }

    @Override
    public Chapter update(Chapter chapter) {
        chapterDao.update(chapter);
        return queryById(chapter.getId());
    }

    @Override
    public boolean delete(Long id) {
        return chapterDao.delete(id) > 0;
    }

}

