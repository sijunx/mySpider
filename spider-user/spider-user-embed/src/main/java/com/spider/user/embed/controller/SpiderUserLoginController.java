package com.spider.user.embed.controller;

import com.spider.base.redis.SpiderRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class SpiderUserLoginController {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUserLoginController.class);

    @RequestMapping(value = "/login000", method = {RequestMethod.POST, RequestMethod.GET})
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
        StringBuilder stringBuilder = new StringBuilder().append("sessionId:").append(sessionId).append("       tocken:").append(tocken);
        return stringBuilder.toString();
    }

    @GetMapping("login")
    public String login(HttpSession session) {
        if (session.getAttribute("userName") != null) {
            return "index";
        }
        return "login";
    }

    @PostMapping("userLogin")
    public String userLogin(HttpSession session, HttpServletRequest request, String userName, String password) {
        if (!"admin".equals(userName) || !"admin".equals(password)) {
            request.setAttribute("errMsg", "用户名密码错误，登录失败");
            return "login";
        }
        session.setAttribute("userName", userName);
        session.setAttribute("sessionPort", request.getLocalPort());
        session.setAttribute("sessionId", session.getId());
//        spring:session:spiderPC:sessions:719a3688-5829-434e-8eac-e79182b96732
        String key = new StringBuilder().append("spring:session:spiderPC:sessions:").append(session.getId()).toString();
        Map<String, String> sessionMap = SpiderRedisClient.getHashMap(key);
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry entry:sessionMap.entrySet()){
            stringBuilder.append(entry.getKey()).append(entry.getValue()).append("\r\n");
        }
        session.setAttribute("spring_redis_session", stringBuilder.toString());
        return "index";
    }

    @GetMapping({"/", "index"})
    public String index(HttpSession session) {
        if (session.getAttribute("userName") != null) {
            return "index";
        }
        return "login";
    }
}
