//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import model.Type;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "admi_type_list",
        urlPatterns = {"/admin/type_list"}
)
public class AdminTypeListServlet extends HttpServlet {
    private TypeService tService = new TypeService();

    public AdminTypeListServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> list = this.tService.GetAllType();
        request.setAttribute("list", list);
        this.getServletContext().removeAttribute("typeList");
        this.getServletContext().setAttribute("typeList", list);
        request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
