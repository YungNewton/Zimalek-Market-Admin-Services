package com.zmarket.marketadminservice.annotations;

import com.zmarket.marketadminservice.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {DateValidator.class})
@Retention(RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidDate {
    String message() default "invalid date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
