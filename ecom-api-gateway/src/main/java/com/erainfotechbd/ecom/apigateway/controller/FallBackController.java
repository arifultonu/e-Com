package com.erainfotechbd.ecom.apigateway.controller;

import com.erainfotechbd.ecom.apigateway.entity.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1/fallback")
public class FallBackController {

    @GetMapping("/userFallback")
    public ErrorResponse userFallback(){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResponse_code("400");
        errorResponse.setError_code("1");
        errorResponse.setError_message("User Service is Down!!");
        return errorResponse;
    }

}
