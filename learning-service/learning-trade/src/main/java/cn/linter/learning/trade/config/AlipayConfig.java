package cn.linter.learning.trade.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 支付配置类
 *
 * @author wangxiaoyang
 * @since 2021/3/19
 */
@Component
public class AlipayConfig implements ApplicationRunner {

    @Value("${alipay.protocol}")
    private String protocol;
    @Value("${alipay.gatewayHost}")
    private String gatewayHost;
    @Value("${alipay.appId}")
    private String appId;
    @Value("${alipay.signType}")
    private String signType;
    @Value("${alipay.merchantPrivateKey}")
    private String merchantPrivateKey;
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;

    @Override
    public void run(ApplicationArguments args) {
        Config config = new Config();
        config.protocol = protocol;
        config.gatewayHost = gatewayHost;
        config.appId = appId;
        config.signType = signType;
        config.merchantPrivateKey = merchantPrivateKey;
        config.alipayPublicKey = alipayPublicKey;
        Factory.setOptions(config);
    }

}
