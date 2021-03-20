package cn.linter.learning.trade.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单实体类
 *
 * @author wangxiaoyang
 * @since 2021/03/19
 */
@Data
@ToString
@EqualsAndHashCode
public class Order implements Delayed, Serializable {

    private static final long serialVersionUID = -21147626328522368L;

    private Long id;
    /**
     * 交易号
     */
    private String tradeNo;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 买家
     */
    private String username;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 关闭时间
     */
    private Long closeMilliseconds;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 状态：0->未支付 1->已支付 2->已关闭
     */
    private Integer status;

    @Override
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(closeMilliseconds - System.currentTimeMillis(), timeUnit);
    }

    @Override
    public int compareTo(Delayed delayed) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - delayed.getDelay(TimeUnit.MILLISECONDS));
    }

}
