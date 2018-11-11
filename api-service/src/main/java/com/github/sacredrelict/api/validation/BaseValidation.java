package com.github.sacredrelict.api.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base validation for incoming request.
 */
@Component
public class BaseValidation {

    /**
     * Base implementation for validation body in incoming requests.
     * @param bindingResult - Default validate result depends on entity annotations.
     * @return map of errors if they found.
     */
    public Map<String, String> validateBase(BindingResult bindingResult) {
        Map<String, String> fieldErrorsMap = new HashMap();
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrorList) {
                fieldErrorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        return fieldErrorsMap;
    }

}
