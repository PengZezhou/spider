package com.yssj.core.framework.validator;

import com.yssj.core.framework.exception.SysException;
import com.yssj.core.framework.web.RestStatus;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Administrator
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws SysException 校验不通过，则报SysException异常
     */
    public static void validated(Object object, Class<?>... groups)
            throws SysException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                msg.append(constraint.getMessage()).append("<br/>");
            }
            throw new SysException(RestStatus.PARAM_ERROR,msg.toString());
        }
    }
}
