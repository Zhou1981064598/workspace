//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package service;

import dao.GoodsDao;
import model.Goods;
import model.Page;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class GoodsService {
    private GoodsDao gDao = new GoodsDao();

    public GoodsService() {
    }
    //根据推荐类型获取推荐商品列表
    public List<Map<String, Object>> getGoodsList(int recommendType) {
        List list = null;
        try {
            list = this.gDao.getGoodsList(recommendType);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return list;
    }
    //获取滚动商品列表（所有推荐商品）
    public List<Map<String, Object>> getScrollGood() {
        List list = null;

        try {
            list = this.gDao.getScrollGood();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

        return list;
    }
    //根据商品类型id查询商品，若为0则查询所有商品
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) {
        List list = null;

        try {
            list = this.gDao.selectGoodsByTypeID(typeID, pageNumber, pageSize);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return list;
    }
    //根据商品类型id分页查询商品，若为0则查询所有商品
    public Page selectPageByTypeID(int typeID, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;

        try {
            totalCount = this.gDao.getCountOfGoodsByTypeID(typeID);
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;

        try {
            list = this.gDao.selectGoodsByTypeID(typeID, pageNumber, 8);
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        p.setList(list);
        return p;
    }
    //根据推荐栏的信息分页查询商品
    public Page getGoodsRecommendPage(int type, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;

        try {
            totalCount = this.gDao.getRecommendCountOfGoodsByTypeID(type);
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;

        try {
            list = this.gDao.selectGoodsbyRecommend(type, pageNumber, 8);
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                Goods g = (Goods)var6.next();
                g.setScroll(this.gDao.isScroll(g));
                g.setHot(this.gDao.isHot(g));
                g.setNew(this.gDao.isNew(g));
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        p.setList(list);
        return p;
    }
    //根据商品ID查询商品
    public Goods getGoodsById(int id) {
        Goods g = null;

        try {
            g = this.gDao.getGoodsById(id);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return g;
    }

    public Page getSearchGoodsPage(String keyword, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;

        try {
            totalCount = this.gDao.getSearchCount(keyword);
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(8, totalCount);
        List list = null;

        try {
            list = this.gDao.selectSearchGoods(keyword, pageNumber, 8);
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        p.setList(list);
        return p;
    }

    public void addRecommend(int id, int type) {
        try {
            this.gDao.addRecommend(id, type);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public void removeRecommend(int id, int type) {
        try {
            this.gDao.removeRecommend(id, type);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public void insert(Goods goods) {
        try {
            this.gDao.insert(goods);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void update(Goods goods) {
        try {
            this.gDao.update(goods);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            this.gDao.delete(id);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
}
