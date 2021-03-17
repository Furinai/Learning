package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.utils.JwtUtil;
import cn.linter.learning.course.entity.Note;
import cn.linter.learning.course.service.NoteService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 笔记控制器
 *
 * @author wangxiaoyang
 * @since 2021/03/06
 */
@RestController
@RequestMapping("notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("{id}")
    public Result<Note> queryNote(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, noteService.queryById(id));
    }

    @GetMapping
    public Result<Page<Note>> listNote(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                       @RequestParam(required = false) String username) {
        PageInfo<Note> pageInfo;
        if (username == null) {
            pageInfo = noteService.list(pageNum, pageSize);
        } else {
            pageInfo = noteService.listByUsername(pageNum, pageSize, username);
        }
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Note> createNote(@RequestBody Note note, @RequestHeader("Authorization") String token) {
        return Result.of(ResultStatus.SUCCESS, noteService.create(note, JwtUtil.getUsername(token)));
    }

    @PutMapping
    public Result<Note> updateNote(@RequestBody Note note) {
        return Result.of(ResultStatus.SUCCESS, noteService.update(note));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteNote(@PathVariable("id") Long id) {
        noteService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
