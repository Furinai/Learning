package cn.linter.learning.trade.dao;

import cn.linter.learning.trade.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/03/19
 */
@Mapper
public interface OrderDao {

    /**
     * 通过ID查询单个订单
     *
     * @param id 订单ID
     * @return 单个订单
     */
    Order selectById(Long id);

    /**
     * 通过交易编号查询单个订单
     *
     * @param tradeNo 交易编号
     * @return 单个订单
     */
    Order selectByTradeNo(String tradeNo);

    /**
     * 通过用户名和商品ID查询单个未支付订单
     *
     * @param username  用户名
     * @param productId 商品ID
     * @return 单个订单
     */
    Order queryUnpaidByUsernameAndProductId(@Param("username") String username, @Param("productId") Long productId);

    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    List<Order> list();

    /**
     * 查询所有未支付订单
     *
     * @return 订单列表
     */
    List<Order> listUnpaid();

    /**
     * 新增订单
     *
     * @param order 订单实例
     * @return 订单实例
     */
    int insert(Order order);

    /**
     * 更新订单
     *
     * @param order 订单实例
     * @return 影响行数
     */
    int update(Order order);

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return 影响行数
     */
    int delete(Long id);

}
