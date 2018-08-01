package com.Thorn.service;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录过滤
 */
public class UserSessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 不拦截的url
        String[] notFilter = new String[]{"/BBS/", "/Login/"};

        // 请求的url
        String url = request.getRequestURI();

        Object obj = request.getSession().getAttribute("user");
        if (null == obj) {
            boolean doFilter = chek(notFilter, url);
            if (doFilter) {
                // 如果session中不存在登录者实体，则弹出框提示重新登录
                PrintWriter out = response.getWriter();
                //String loginPage = request.getContextPath()+"/index.jsp";
                String loginPage = "/Login/index";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                builder.append("window.top.location.href='");
                builder.append(loginPage);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            //已登录不能返回这个页面
            if (url.indexOf("/Login") == -1)
                filterChain.doFilter(request, response);
            else {
                //返回BBS主页面
                PrintWriter out = response.getWriter();
                //String loginPage = request.getContextPath()+"/index.jsp";
                String index = "/BBS/index";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                builder.append("window.top.location.href='");
                builder.append(index);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
            }
        }

    }

    /**
     * @param notFilter 不拦截的url
     * @param url       ：请求的url
     * @return false：不拦截
     * true：拦截
     */
    public boolean chek(String[] notFilter, String url) {
        //url以css和js结尾的不进行拦截
        if (url.endsWith(".css") || url.endsWith(".js")) {
            return false;
        }
        //含有notFilter中的任何一个则不进行拦截
        for (String s : notFilter) {
            if (url.indexOf(s) != -1) {
                return false;
            }
        }
        return true;
    }

}

