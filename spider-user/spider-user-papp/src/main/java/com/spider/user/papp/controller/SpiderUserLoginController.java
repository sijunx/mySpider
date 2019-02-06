package com.spider.user.papp.controller;

import com.spider.base.redis.SpiderRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class SpiderUserLoginController {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUserLoginController.class);

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String login(HttpServletRequest request,HttpServletResponse response, String phone, String password){
        logger.info("phone:{} password:{}", phone, password);
        String sessionId = request.getSession().getId();

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("userId", "12");
        httpSession.setAttribute("token", "23");

        //  设置userId
        Cookie cookie = new Cookie("userId","12");
        cookie.setMaxAge(60*60*10);
        cookie.setPath("/");
        response.addCookie(cookie);
        //  设置token
        String tocken = UUID.randomUUID().toString().replace("-","");
        Cookie cookieObject = new Cookie("token", tocken);
        cookieObject.setMaxAge(60*60*10);
        cookieObject.setPath("/");
        response.addCookie(cookieObject);
        //  将sessionId存入redis
        SpiderRedisClient.set(sessionId, tocken,30);
        StringBuilder stringBuilder = new StringBuilder().append("sessionId:").append(sessionId).append("       tocken:").append(tocken);
        return stringBuilder.toString();
    }
}
