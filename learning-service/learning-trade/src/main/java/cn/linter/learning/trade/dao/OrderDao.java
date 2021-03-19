package cn.linter.learning.trade.dao;

import cn.linter.learning.trade.entity.Order;
import org.apache.ibatis.annotations.Mapper;

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
     * 查询所有订单
     *
     * @return 订单列表
     */
    List<Order> list();

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
