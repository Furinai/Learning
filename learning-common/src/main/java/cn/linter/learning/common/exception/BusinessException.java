package cn.linter.learning.common.exception;

import cn.linter.learning.common.entity.ResultStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 业务异常类
 *
 * @author wangxiaoyang
 * @date 2020/12/19
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4494482667405812688L;

    /**
     * 状态
     */
    private ResultStatus status;

    public BusinessException(ResultStatus status) {
        this.status = status;
    }

}
