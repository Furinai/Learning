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
    public PageInfo<Note> listByCourseId(int pageNum, int pageSize, Long courseId) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(noteDao.listByCourseId(courseId));
    }

    @Override
    public PageInfo<Note> listByUsername(int pageNum, int pageSize, String username) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(noteDao.listByUsername(username));
    }

    @Override
    public PageInfo<Note> listByCourseIdAndUsername(int pageNum, int pageSize, Long courseId, String username) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(noteDao.listByCourseIdAndUsername(courseId, username));
    }

    @Override
    public PageInfo<Note> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(noteDao.list());
    }

    @Override
    public Note create(Note note, String username) {
        User user = new User();
        user.setUsername(username);
        note.setAuthor(user);
        LocalDateTime now = LocalDateTime.now();
        note.setCreateTime(now);
        note.setUpdateTime(now);
        noteDao.insert(note);
        return note;
    }

    @Override
    public Note update(Note note) {
        note.setUpdateTime(LocalDateTime.now());
        noteDao.update(note);
        return noteDao.selectById(note.getId());
    }

    @Override
    public boolean delete(Long id) {
        return noteDao.delete(id) > 0;
    }

}

