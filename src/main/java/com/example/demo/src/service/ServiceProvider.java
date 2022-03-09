package com.example.demo.src.service;

import com.example.demo.config.BaseException;
import com.example.demo.src.service.model.response.GetServiceRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ServiceProvider {

    private final ServiceMapper serviceMapper;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ServiceProvider(ServiceMapper serviceMapper) {
        this.serviceMapper = serviceMapper;
    }

    public List<GetServiceRes> getServices() throws BaseException {
        try{
            List<GetServiceRes> getServiceRes = serviceMapper.getServices();
            return getServiceRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
