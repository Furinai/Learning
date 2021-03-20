package cn.linter.learning.trade.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.utils.JwtUtil;
import cn.linter.learning.trade.entity.Order;
import cn.linter.learning.trade.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 订单控制器
 *
 * @author wangxiaoyang
 * @since 2021/03/19
 */
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{id}")
    public Result<Order> queryOrder(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, orderService.queryById(id));
    }

    @GetMapping
    public Result<Page<Order>> listOrder(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Order> pageInfo = orderService.list(pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Order> createOrder(@RequestBody Order order, @RequestHeader("Authorization") String token) {
        String username = JwtUtil.getUsername(token);
        Order existingOrder = orderService.queryUnpaidByUsernameAndProductId(username, order.getProductId());
        if (existingOrder != null) {
            return Result.of(ResultStatus.SUCCESS, existingOrder);
        }
        return Result.of(ResultStatus.SUCCESS, orderService.create(order, username));
    }

    @PutMapping
    public Result<Order> updateOrder(@RequestBody Order order) {
        return Result.of(ResultStatus.SUCCESS, orderService.update(order));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
