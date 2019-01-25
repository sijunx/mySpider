package com.spider.user.provider.service;

import com.spider.user.service.api.IUserInfoService;
import com.spider.user.service.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceProvider implements IUserInfoService{

//    @Autowired
//    private IUserInfoService userInfoService;

    @Override
    public UserInfoDTO findById(Long id){
//        return userInfoService.findById(id);
        return new UserInfoDTO();
    }
}
