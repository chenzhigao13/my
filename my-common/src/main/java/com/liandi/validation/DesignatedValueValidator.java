package com.liandi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * 自定义参数指定值校验器
 * 
 * @author czg
 * @date 2019/9/27 10:08
 */
public class DesignatedValueValidator implements ConstraintValidator<DesignatedValue, String> {

    private DesignatedValue designatedValue;

    @Override
    public void initialize(DesignatedValue designatedValue) {
        this.designatedValue = designatedValue;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }

        String[] values = designatedValue.value();
        if (values.length < 1) {
            return true;
        }

        for (String s : values) {
            if (StringUtils.equals(value, s)) {
                return true;
            }
        }

        return false;
    }

}
