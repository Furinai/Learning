package cn.linter.learning.course.service.impl;

import cn.linter.learning.course.dao.NoteDao;
import cn.linter.learning.course.entity.Note;
import cn.linter.learning.course.entity.User;
import cn.linter.learning.course.service.NoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 笔记服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/03/06
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDao;

    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Note queryById(Long id) {
        return noteDao.selectById(id);
    }

    @Override
    public PageInfo<Note> listByCourseIdOrUsername(int pageNumber, int pageSize, Long courseId, String username) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(noteDao.listByCourseIdOrUsername(courseId, username));
    }

    @Override
    public PageInfo<Note> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(noteDao.list());
    }

    @Override
    public Note create(Note note, String username) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setUsername(username);
        note.setAuthor(user);
        note.setCreateTime(now);
        note.setUpdateTime(now);
        noteDao.insert(note);
        return note;
    }

    @Override
    public Note update(Note note) {
        note.setUpdateTime(LocalDateTime.now());
        noteDao.update(note);
        return queryById(note.getId());
    }

    @Override
    public boolean delete(Long id) {
        return noteDao.delete(id) > 0;
    }

}
