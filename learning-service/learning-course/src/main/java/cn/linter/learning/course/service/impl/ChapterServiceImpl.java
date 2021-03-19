package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.ChapterDao;
import cn.linter.learning.course.entity.Chapter;
import cn.linter.learning.course.service.ChapterService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

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
    public List<Chapter> listInfoByCourseId(Long courseId) {
        return chapterDao.listInfoByCourseId(courseId);
    }

    @Override
    public Chapter create(Chapter chapter) {
        if (chapter.getVideoTime() != null && chapter.getVideoTime().indexOf(':') == -1) {
            chapter.setVideoTime(transformDuration(chapter.getVideoTime()));
        }
        LocalDateTime now = LocalDateTime.now();
        chapter.setCreateTime(now);
        chapter.setUpdateTime(now);
        chapterDao.insert(chapter);
        return chapter;
    }

    @Override
    public Chapter update(Chapter chapter) {
        if (chapter.getVideoTime() != null && chapter.getVideoTime().indexOf(':') == -1) {
            chapter.setVideoTime(transformDuration(chapter.getVideoTime()));
        }
        chapter.setUpdateTime(LocalDateTime.now());
        chapterDao.update(chapter);
        return queryById(chapter.getId());
    }

    @Override
    public boolean delete(Long id) {
        return chapterDao.delete(id) > 0;
    }

    private String transformDuration(String milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(Long.parseLong(milliseconds));
    }

}

