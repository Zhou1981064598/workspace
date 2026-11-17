//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "admin_user_editshow",
        urlPatterns = {"/admin/user_editshow"}
)
public class AdminUserEditshowServlet extends HttpServlet {
    private UserService uService = new UserService();

    public AdminUserEditshowServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = this.uService.selectById(id);
        request.setAttribute("u", user);
        request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);
    }
}
