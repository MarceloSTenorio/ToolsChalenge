package br.com.toolschallenge.domain.command.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = LuhnValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Luhn {
    String message() default "Número do cartão inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
