package com.prueba.movies.util;

import com.prueba.movies.dto.base.BaseResponse;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Utils {
    public static <T> BaseResponse<T> getResponseFailed(BindingResult result) {
        BaseResponse<T> response = new BaseResponse<>();
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            String fieldName = error.getField();
            String errorDetail = error.getDefaultMessage();
            errorMessage.append(fieldName).append(" ").append(errorDetail).append(".");
        }
        response.setEstado("Error");
        response.setDescripcionError(ErrorMessages.INVALID_MOVIE_DATA + ": " + errorMessage.toString());
        return response;
    }
}
