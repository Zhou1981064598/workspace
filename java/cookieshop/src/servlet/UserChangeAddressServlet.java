//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
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

@WebServlet(
        name = "user_changeaddress",
        urlPatterns = {"/user_changeaddress"}
)
public class UserChangeAddressServlet extends HttpServlet {
    private UserService uService = new UserService();

    public UserChangeAddressServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User)request.getSession().getAttribute("user");

        try {
            BeanUtils.copyProperties(loginUser, request.getParameterMap());
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        } catch (InvocationTargetException var6) {
            var6.printStackTrace();
        }

        this.uService.updateUserAddress(loginUser);
        request.setAttribute("msg", "收件信息更新成功！");
        request.getRequestDispatcher("/user_center.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
