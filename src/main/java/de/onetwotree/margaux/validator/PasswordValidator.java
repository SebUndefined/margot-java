package de.onetwotree.margaux.validator;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.Errors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;


/**
 * Created by SebUndefined on 13/12/17.
 */
public class PasswordValidator implements ConstraintValidator<PasswordValidatorInterface,Object> {
    private String firstField;
    private String secondField;

    @Override
    public void initialize(final PasswordValidatorInterface passwordValidatorInterface) {
        firstField = passwordValidatorInterface.first();
        secondField = passwordValidatorInterface.second();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Method methodGetPassword = o.getClass().getMethod("getPassword");
            Method methodGetPasswordConfirm = o.getClass().getMethod("getConfirmPassword");

            if (methodGetPassword.invoke(o) == null && methodGetPasswordConfirm.invoke(o) == null)
                return true;
            else if (methodGetPassword.invoke(o) == null)
                return false;
            return methodGetPassword.invoke(o).equals(methodGetPasswordConfirm.invoke(o));

        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
