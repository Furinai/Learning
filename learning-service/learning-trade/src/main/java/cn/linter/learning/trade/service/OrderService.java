package cn.linter.learning.trade.service;

import cn.linter.learning.trade.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
     * 通过交易编号查询单个订单
     *
     * @param tradeNo 交易编号
     * @return 单个订单
     */
    Order queryByTradeNo(String tradeNo);

    /**
     * 通过用户名和商品ID查询单个未支付订单
     *
     * @param username  用户名
     * @param productId 商品ID
     * @return 单个订单
     */
    Order queryUnpaidByUsernameAndProductId(String username, Long productId);

    /**
     * 分页查询所有订单
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 订单列表
     */
    PageInfo<Order> list(int pageNumber, int pageSize);

    /**
     * 查询所有未支付订单
     *
     * @return 订单列表
     */
    List<Order> listUnpaid();

    /**
     * 新增订单
     *
     * @param order    订单实例
     * @param username 用户名
     * @return 订单实例
     */
    Order create(Order order, String username);

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
