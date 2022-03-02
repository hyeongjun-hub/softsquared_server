package com.example.demo.src.service;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.service.model.GetServiceRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/services")
public class ServiceController {
    final Logger logger = LoggerFactory.getLogger((this.getClass()));

    @Autowired
    private final ServiceProvider serviceProvider;

    @Autowired
    private final ServiceDao serviceDao;

    public ServiceController(ServiceProvider serviceProvider, ServiceDao serviceDao) {
        this.serviceProvider = serviceProvider;
        this.serviceDao = serviceDao;
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