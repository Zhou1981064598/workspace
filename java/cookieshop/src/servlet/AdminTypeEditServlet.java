//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import model.Type;
import org.apache.commons.beanutils.BeanUtils;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "admin_type_edit",
        urlPatterns = {"/admin/type_edit"}
)
public class AdminTypeEditServlet extends HttpServlet {
    private TypeService tService = new TypeService();

    public AdminTypeEditServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Type t = new Type();

        try {
            BeanUtils.copyProperties(t, request.getParameterMap());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.tService.update(t);
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
