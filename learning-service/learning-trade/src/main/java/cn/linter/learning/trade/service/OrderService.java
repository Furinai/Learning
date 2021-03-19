package cn.linter.learning.trade.service;

import cn.linter.learning.trade.entity.Order;
import com.github.pagehelper.PageInfo;

/**
 * 订单服务接口
 *
 * @author wangxiaoyang
 * @since 2021/03/19
 */
public interface OrderService {

    /**
     * 通过ID查询单个订单
     *
     * @param id 订单ID
     * @return 单个订单
     */
    Order queryById(Long id);

    /**
     * 分页查询所有订单
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 订单列表
     */
    PageInfo<Order> list(int pageNumber, int pageSize);

    /**
     * 新增订单
     *
     * @param order 订单实例
     * @return 订单实例
     */
    Order create(Order order);

    /**
     * 更新订单
     *
     * @param order 订单实例
     * @return 订单实例
     */
    Order update(Order order);

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
