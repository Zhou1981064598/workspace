//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package service;

import dao.OrderDao;
import model.Order;
import model.OrderItem;
import model.Page;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class OrderService {
    private OrderDao oDao = new OrderDao();

    public OrderService() {
    }

    public void addOrder(Order order) {
        Connection con = null;

        try {
            con = DataSourceUtils.getConnection();
            con.setAutoCommit(false);
            this.oDao.insertOrder(con, order);
            int id = this.oDao.getLastInsertId(con);
            order.setId(id);
            Iterator var4 = order.getItemMap().values().iterator();

            while(var4.hasNext()) {
                OrderItem item = (OrderItem)var4.next();
                this.oDao.insertOrderItem(con, item);
            }

            con.commit();
        } catch (SQLException var7) {
            var7.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException var6) {
                    var6.printStackTrace();
                }
            }
        }

    }

    public List<Order> selectAll(int userid) {
        List list = null;

        try {
            list = this.oDao.selectAll(userid);
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Order o = (Order)var3.next();
                List<OrderItem> l = this.oDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return list;
    }

    public Page getOrderPage(int status, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 10;
        int totalCount = 0;

        try {
            totalCount = this.oDao.getOrderCount(status);
        } catch (SQLException var10) {
            var10.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list = null;

        try {
            list = this.oDao.selectOrderList(status, pageNumber, pageSize);
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                Order o = (Order)var7.next();
                List<OrderItem> l = this.oDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        }

        p.setList(list);
        return p;
    }

    public void updateStatus(int id, int status) {
        try {
            this.oDao.updateStatus(id, status);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public void delete(int id) {
        Connection con = null;

        try {
            con = DataSourceUtils.getDataSource().getConnection();
            con.setAutoCommit(false);
            this.oDao.deleteOrderItem(con, id);
            this.oDao.deleteOrder(con, id);
            con.commit();
        } catch (SQLException var6) {
            var6.printStackTrace();
            if (con != null) {
                try {
                    ;//事务回滚
                    con.rollback();
                } catch (SQLException var5) {
                    var5.printStackTrace();
                }
            }
        }

    }
}
