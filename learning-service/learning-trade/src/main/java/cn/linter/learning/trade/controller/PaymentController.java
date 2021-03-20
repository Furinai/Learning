package cn.linter.learning.trade.controller;

import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.exception.BusinessException;
import cn.linter.learning.trade.entity.Order;
import cn.linter.learning.trade.service.OrderService;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付控制器
 *
 * @author wangxiaoyang
 * @since 2021/3/19
 */
@RestController
@RequestMapping("payments")
public class PaymentController {

    @Value("${gateway.protocol}")
    private String gatewayProtocol;
    @Value("${gateway.host}")
    private String gatewayHost;

    private final OrderService orderService;

    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping
    public Result<String> pay(@RequestParam Long orderId) {
        Order order = orderService.queryById(orderId);
        AlipayTradePagePayResponse pagePayResponse;
        try {
            pagePayResponse = Factory.Payment.Page().pay(
                    order.getProductName(), orderId.toString(),
                    order.getPrice().toString(),
                    gatewayProtocol + "://" + gatewayHost + "/api/payments/success"
            );
        } catch (Exception e) {
            throw new BusinessException(ResultStatus.PAYMENT_CREATE_FAILURE);
        }
        return Result.of(ResultStatus.SUCCESS, pagePayResponse.getBody());
    }

}
