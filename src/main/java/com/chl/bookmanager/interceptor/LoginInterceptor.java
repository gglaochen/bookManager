package com.chl.bookmanager.interceptor;

import com.chl.bookmanager.domain.ReaderInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:Administrator
 * @date:2019/1/3/003
 */
public class LoginInterceptor implements HandlerInterceptor {
    //  数据验证拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (request.getSession().getAttribute("user")==null) {
            return false;
        }
        return true;
    }

    // 视图渲染，视图处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler");
    }

    // 清理缓存，处理异常，释放缓存中的数据，输出错误日志
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterHandler");
    }
}
