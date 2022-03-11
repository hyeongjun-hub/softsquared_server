package com.example.demo.src.service;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.service.model.response.GetServiceRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServiceController {
    private final ServiceProvider serviceProvider;

    /**
     * 전체서비스 조회
     * @return BaseResponse<List<GetServiceRes>>
     * @throws BaseException
     */
    @GetMapping("")
    public BaseResponse<List<GetServiceRes>> getServices() throws BaseException {
            List<GetServiceRes> getServiceRes = serviceProvider.getServices();
            return new BaseResponse<>(getServiceRes);
    }

}
