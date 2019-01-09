package com.chl.bookmanager.interceptor;

import com.chl.bookmanager.domain.ReaderInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:Administrator
 * @date:2018/12/20/020
 */
public class AdminInterceptor implements HandlerInterceptor {

    //  数据验证拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (request.getSession().getAttribute("user")!=null) {
            ReaderInfo readerInfo = (ReaderInfo) request.getSession().getAttribute("user");
            if (readerInfo.getTypeId() == 7) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
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
