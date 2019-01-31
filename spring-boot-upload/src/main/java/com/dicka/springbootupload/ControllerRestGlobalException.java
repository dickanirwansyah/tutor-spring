package com.dicka.springbootupload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerRestGlobalException extends ResponseEntityExceptionHandler{

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public ResponseEntity<?> handlerControllerException(HttpServletRequest request,
                                                        Throwable ex){
        HttpStatus status = this.getStatus(request);
        return new ResponseEntity<String>("(Message in rest global exception *): "+
         ex.getMessage(), status);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handlerControllerRootException(HttpServletRequest request,
                                                            Throwable ex){
        HttpStatus status = this.getStatus(request);
        return new ResponseEntity<Object>("(Message in res global exception *): "+
            ex.getMessage(), status);
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
