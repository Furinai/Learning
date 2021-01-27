package cn.linter.learning.user.controller;

import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.user.entity.Role;
import cn.linter.learning.user.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public Result<List<Role>> listRole() {
        return Result.of(ResultStatus.SUCCESS, roleService.list());
    }

    @PostMapping
    public Result<Role> createRole(@RequestBody @Validated({Role.Create.class}) Role role) {
        return Result.of(ResultStatus.SUCCESS, roleService.create(role));
    }

    @PutMapping
    public Result<Role> updateRole(@RequestBody @Validated({Role.Update.class}) Role role) {
        return Result.of(ResultStatus.SUCCESS, roleService.update(role));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteRole(@PathVariable Integer id) {
        roleService.delete(id);
        return ResultStatus.SUCCESS;
    }

}