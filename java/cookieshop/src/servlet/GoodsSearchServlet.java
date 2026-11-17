//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import model.Page;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(
        name = "goods_search",
        urlPatterns = {"/goods_search"}
)
public class GoodsSearchServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();

    public GoodsSearchServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取搜索框中输入的关键字
        String keyword = request.getParameter("keyword");
        int pageNumber = 1;
        //默认pageNumber为1，如获取到pageNumber不为空，则给pageNumber赋值
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception var6) {
            }
        }

        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        //根据id获取商品，并对查询到的商品数量进行分页
        Page p = this.gService.getSearchGoodsPage(keyword, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
            //根据查询到的商品数量进行分页
        } else if (pageNumber >= p.getTotalPage() + 1) {
            p = this.gService.getSearchGoodsPage(keyword, pageNumber);
        }

        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword, "utf-8"));
        request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
    }
}
