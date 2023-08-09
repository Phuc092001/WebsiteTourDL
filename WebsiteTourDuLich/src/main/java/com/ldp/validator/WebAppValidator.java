/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.validator;

import com.ldp.pojos.NguoiDung;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author ACER
 */
@Component
public class WebAppValidator implements Validator{

    @Autowired
    private javax.validation.Validator beanValidator;

    private  Set<Validator> springValidators;
    
    @Override
    public boolean supports(Class<?> type) {
        return NguoiDung.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        //nho bean validate nhung nho spring bao loi
        Set<ConstraintViolation<Object>> beans = this.beanValidator.validate(o);
        
        for (ConstraintViolation<Object> obj: beans)
            errors.rejectValue(obj.getPropertyPath().toString(), 
                    obj.getMessageTemplate(), obj.getMessage());
        
        for(Validator v: this.springValidators)
            v.validate(o, errors);
    }
    
    /**
     * @param springValidators the springValidators to set
     */
    public void setSpringValidators(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }
}
