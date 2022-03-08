package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 这个自定义的servlet的目的其实是为了根据路径调用对应名称的方法
        * 注意：servlet执行的原理始终都是tomcat web服务器调用service方法
        * 因此，在定义具体业务逻辑的子类servlet中，实际上是继承了本类的service方法
        * 该service方法起到了路径分发的作用。这个思想是源自HttpServlet的*/
        // 1. 首先要获取请求头中的路径
        String uri = req.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        if(lastIndex+1==uri.length()){
            resp.setStatus(404);
            return;
        }
        String methodName = uri.substring(lastIndex+1);

        // 2. 反射
        // 2.1 本方法是子类直接继承的，tomcat执行时是调用子类的该方法，此时的this就是子类对象
        Class<? extends BaseServlet> clz = this.getClass();
        try {
            Method method = clz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            resp.setStatus(404);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
