package com.spider.user.service.api;

import com.spider.user.service.dto.UserInfoDTO;

public interface IUserInfoService {

    UserInfoDTO findById(Long id);
}
