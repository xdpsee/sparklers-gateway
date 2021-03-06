package com.zhenhui.demo.sparklers.gateway.utils;


import com.zhenhui.demo.sparklers.common.Result;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ErrorHandler extends AbstractErrorController {

    public ErrorHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result handleError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, false);

        Integer status = (Integer) errorAttributes.get("status");
        String error = (String) errorAttributes.get("error");
        String message = (String) errorAttributes.get("message");

        return Result.builder().status(status).error(error).message(message).build();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}


