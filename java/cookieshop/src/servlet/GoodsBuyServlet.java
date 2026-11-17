package servlet;

import model.Goods;
import model.Order;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "goods_buy",urlPatterns = "/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();

    public GoodsBuyServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order o = null;
        //若购物车不为空，则获取购物车对象，若为空，则创建一个购物车空对象
        if (request.getSession().getAttribute("order") != null) {
            o = (Order)request.getSession().getAttribute("order");
        } else {
            o = new Order();
            request.getSession().setAttribute("order", o);
        }
        //这里主要用于获取商品id，并根据商品id判断商品是否还有库存，如果有库存，则调用Order类中的addGoods方法将商品添加到购物车的订单中。

        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        Goods goods = this.gService.getGoodsById(goodsid);
        if (goods.getStock() > 0) {
            o.addGoods(goods);
            response.getWriter().print("ok");
        } else {
            response.getWriter().print("fail");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
