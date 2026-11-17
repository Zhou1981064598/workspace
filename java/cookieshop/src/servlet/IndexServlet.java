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
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "IndexServlet",
        urlPatterns = {"/index"}
)
public class IndexServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();

    public IndexServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> ScrollGood = this.gService.getScrollGood();
        request.setAttribute("scroll", ScrollGood);
        List<Map<String, Object>> newList = this.gService.getGoodsList(3);
        request.setAttribute("newList", newList);
        List<Map<String, Object>> hotList = this.gService.getGoodsList(2);
        request.setAttribute("hotList", hotList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
