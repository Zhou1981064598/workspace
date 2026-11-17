//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import model.Page;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "admin_order_list",
        urlPatterns = {"/admin/order_list"}
)
public class AdminOrderListServlet extends HttpServlet {
    private OrderService oService = new OrderService();

    public AdminOrderListServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = 0;
        if (request.getParameter("status") != null) {
            status = Integer.parseInt(request.getParameter("status"));
        }

        request.setAttribute("status", status);
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (Exception var6) {
            }
        }

        if (pageNumber <= 0) {
            pageNumber = 1;
        }

        Page p = this.oService.getOrderPage(status, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else if (pageNumber >= p.getTotalPage() + 1) {
            p = this.oService.getOrderPage(status, pageNumber);
        }

        request.setAttribute("p", p);
        request.getRequestDispatcher("/admin/order_list.jsp").forward(request, response);
    }
}
