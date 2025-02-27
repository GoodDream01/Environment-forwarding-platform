package com.demo.shirodemo.common;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author YL
 * @date 2019/4/10 12:32
 * @apiNote
 */
@RestController
public class GlobalFilterExceptionHandler extends BasicErrorController {

    public GlobalFilterExceptionHandler() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));

        HttpStatus status = getStatus(request);
        String message = body.get("message").toString();
        String exception = body.get("exception").toString();
        //单独处理用户realm中抛出的权限不足和认证失败异常，抛出交给GlobalExceptionHandler处理
        if (exception.equals(AuthenticationException.class.getName())) {
            throw new AuthenticationException();
        } else if (exception.equals(AuthorizationException.class.getName())) {
            throw new AuthorizationException();
        }
        //其他500异常直接返回给前端
        return new ResponseEntity<>(R.failure(status.value(), message), HttpStatus.OK);
    }

    @Override
    public String getErrorPath() {
        return "error/error";
    }
}
