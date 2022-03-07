package com.example.demo.src.service;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.service.model.response.GetServiceRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    final Logger logger = LoggerFactory.getLogger((this.getClass()));

    @Autowired
    private final ServiceProvider serviceProvider;

    public ServiceController(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @GetMapping("")
    public BaseResponse<List<GetServiceRes>> getServices(){
        try{
            List<GetServiceRes> getServiceRes = serviceProvider.getServices();
            return new BaseResponse<>(getServiceRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
