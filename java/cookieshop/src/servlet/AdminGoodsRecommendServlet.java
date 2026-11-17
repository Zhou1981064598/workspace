//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "admin_goods_recommend",
        urlPatterns = {"/admin/goods_recommend"}
)
public class AdminGoodsRecommendServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();

    public AdminGoodsRecommendServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget = Integer.parseInt(request.getParameter("typeTarget"));
        if (method.equals("add")) {
            this.gService.addRecommend(id, typeTarget);
        } else {
            this.gService.removeRecommend(id, typeTarget);
        }

        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
