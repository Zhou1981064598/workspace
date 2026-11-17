//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import model.Order;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@WebServlet(
        name = "order_confirm",
        urlPatterns = {"/order_confirm"}
)
public class OrderConfirmServlet extends HttpServlet {
    private OrderService oService = new OrderService();

    public OrderConfirmServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order o = (Order)request.getSession().getAttribute("order");

        try {
            BeanUtils.copyProperties(o, request.getParameterMap());
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        } catch (InvocationTargetException var6) {
            var6.printStackTrace();
        }
        int paytype=o.getPaytype();
        System.out.println(paytype);
        o.setDatetime(new Date());
        if (paytype==3){
            o.setStatus(1);}
        else {
            o.setStatus(2);
        }
        o.setUser((User)request.getSession().getAttribute("user"));
        this.oService.addOrder(o);
        request.getSession().removeAttribute("order");
        request.setAttribute("msg", "订单支付成功！");
        request.getRequestDispatcher("/order_success.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
