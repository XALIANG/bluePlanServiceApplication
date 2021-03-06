package com.blue.blueplanserviceapplicationpc.config;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.Service.UserServiceImp;
import com.blue.blueplanserviceapplicationpc.common.Result;
import com.blue.blueplanserviceapplicationpc.utils.DateUtils;
import com.blue.blueplanserviceapplicationpc.utils.TokenUtil;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 拦截器
 */
@Configuration
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    UserServiceImp userServiceImp;

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；
     * 　　　*       false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        response.setContentType("application/json;charset=utf-8");
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("Authorization");
        System.out.println("token-----------------" + token);


        //执行认证
        if (token == null) {
            try {
                response.getWriter().println(404);
                System.out.println("非法请求");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }
        String[] userTokenSplit = token.split(" ");
        String userId = JWT.decode(userTokenSplit[1]).getAudience().get(0);
        User user = userServiceImp.findUserById(userId);
        //获取过期时间
        TokenUtil tokenUtil = new TokenUtil();
        DateUtils dateUtils = new DateUtils();
        Date currentJwtOverdue = tokenUtil.obtainJwtDate(userTokenSplit[1]);
        System.out.println("currentJwtOverduecurrentJwtOverdue" + currentJwtOverdue + "current Date" + new Date());

        Long currentTime = DateUtils.timeToStamp(dateUtils.dateToTime(new Date()));
        Long curentOverdueTime = DateUtils.timeToStamp(dateUtils.dateToTime(currentJwtOverdue));
        System.out.println(currentTime > curentOverdueTime);
        if(currentTime > curentOverdueTime){
            try {
                response.getWriter().println(401);
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //从解密的token中获取userId

        if (user == null) {
            try {
                response.getWriter().print("未找到该用户!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        //暂时不动
//         验证上传的token私钥部分是否与用户的密码一致
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword())).build();
//        System.out.println(jwtVerifier);
//        try {
//            jwtVerifier.verify(token);
//        } catch (JWTVerificationException e) {
//            try {
//                response.getWriter().print("token异常!请重新登陆");
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object
            handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finall
     * 　　 * 但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object
            handler, Exception ex) throws Exception {

    }

}
