package com.liandi.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 指定值校验
 * 
 * @author czg
 * @date 2019/9/27 10:04
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DesignatedValueValidator.class)
public @interface DesignatedValue {

    String[] value() default {};

    String message() default "{*.validation.constraint.DesignatedValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
