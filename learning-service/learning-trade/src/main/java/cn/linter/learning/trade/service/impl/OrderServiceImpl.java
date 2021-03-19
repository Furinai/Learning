package cn.linter.learning.trade.service.impl;

import cn.linter.learning.trade.client.CourseClient;
import cn.linter.learning.trade.dao.OrderDao;
import cn.linter.learning.trade.entity.Course;
import cn.linter.learning.trade.entity.Order;
import cn.linter.learning.trade.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 订单服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/03/19
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CourseClient courseClient;

    public OrderServiceImpl(OrderDao orderDao, CourseClient courseClient) {
        this.orderDao = orderDao;
        this.courseClient = courseClient;
    }

    @Override
    public Order queryById(Long id) {
        return orderDao.selectById(id);
    }

    @Override
    public PageInfo<Order> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(orderDao.list());
    }

    @Override
    public Order create(Order order) {
        Course course = courseClient.queryCourse(order.getProductId()).getData();
        order.setPrice(course.getPrice());
        order.setProductName(course.getName());
        LocalDateTime now = LocalDateTime.now();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        orderDao.insert(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        order.setUpdateTime(LocalDateTime.now());
        orderDao.update(order);
        return queryById(order.getId());
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id) > 0;
    }

}

