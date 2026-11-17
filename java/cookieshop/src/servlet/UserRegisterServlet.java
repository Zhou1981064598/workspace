package servlet;

import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 该类主要用于完成注册功能
 */
@WebServlet(name = "user_register",urlPatterns = "/user_register")
public class UserRegisterServlet extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        try {
            BeanUtils.copyProperties(user,request.getParameterMap());
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }if(userService.register(user)){
            request.setAttribute("msg","注册成功！");
            request.getRequestDispatcher("user_login.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","用户名或者邮箱重复，请重新填写！");
            request.getRequestDispatcher("user_register.jsp").forward(request,response);
        }
    }

}
