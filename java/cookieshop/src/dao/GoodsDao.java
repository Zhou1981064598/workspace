//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dao;

import model.Goods;
import model.Recommend;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsDao {
    public GoodsDao() {
    }
    //根据推荐类型获取推荐商品列表
    public List<Map<String, Object>> getGoodsList(int recommendType) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select g.id,g.name,g.cover,g.price,t.name typename" +
                     " from recommend r,goods g,type t " +
                     "where type=? and r.goods_id=g.id and g.type_id=t.id";
        return (List)r.query(sql, new MapListHandler(), new Object[]{recommendType});
                                  //将多条记录封装到一个Map集合中
    }
    //获取滚动商品列表（所有推荐商品）
    public List<Map<String, Object>> getScrollGood() throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select g.id,g.name,g.cover,g.price  from recommend r,goods g where r.goods_id=g.id";
        return (List)r.query(sql, new MapListHandler());
    }
    //根据商品类型id查询商品，若为0则查询所有商品
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) throws SQLException {
        String sql;
        QueryRunner r;
        if (typeID == 0) {
            sql = "select * from goods limit ? , ?";
            r = new QueryRunner(DataSourceUtils.getDataSource());
            return (List)r.query(sql, new BeanListHandler(Goods.class), new Object[]{(pageNumber - 1) * pageSize, pageSize});
        } else {
            sql = "select * from goods where type_id=? limit ? , ?";
            r = new QueryRunner(DataSourceUtils.getDataSource());
            return (List)r.query(sql, new BeanListHandler(Goods.class), new Object[]{typeID, (pageNumber - 1) * pageSize, pageSize});
        }
    }
    //根据商品类型ID统计商品数量
    public int getCountOfGoodsByTypeID(int typeID) throws SQLException {
        String sql = "";
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        if (typeID == 0) {
            sql = "select count(*) from goods";
            return ((Long)r.query(sql, new ScalarHandler())).intValue();
        } else {
            sql = "select count(*) from goods where type_id=?";
            return ((Long)r.query(sql, new ScalarHandler(), new Object[]{typeID})).intValue();
        }
    }
    //根据推荐栏的信息查询商品
    public List<Goods> selectGoodsbyRecommend(int type, int pageNumber, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql;
        //当不添加推荐类型限制的时候
        if (type == 0) {
            sql = " select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g,type t where g.type_id=t.id order by g.id limit ?,?";
            return (List)r.query(sql, new BeanListHandler(Goods.class), new Object[]{(pageNumber - 1) * pageSize, pageSize});
        }
        //添加推荐类型限制的时候
        else
        {
            sql = " select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g,recommend r,type t where g.id=r.goods_id and g.type_id=t.id and r.type=? order by g.id limit ?,?";
            return (List)r.query(sql, new BeanListHandler(Goods.class), new Object[]{type, (pageNumber - 1) * pageSize, pageSize});
        }
    }
        //根据推荐类型ID对推荐栏的商品信息进行统计。
    public int getRecommendCountOfGoodsByTypeID(int type) throws SQLException {
        if (type == 0) {
            return this.getCountOfGoodsByTypeID(0);
        } else {
            QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
            String sql = "select count(*) from recommend where type=?";
            return ((Long)r.query(sql, new ScalarHandler(), new Object[]{type})).intValue();
        }
    }
        //根据商品ID查询商品
    public Goods getGoodsById(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id = ? and g.type_id=t.id";
        return (Goods)r.query(sql, new BeanHandler(Goods.class), new Object[]{id});
    }
        //根据商品名字进行模糊查询统计
    public int getSearchCount(String keyword) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from goods where name like ?";
        return ((Long)r.query(sql, new ScalarHandler(), new Object[]{"%" + keyword + "%"})).intValue();
    }
        //根据商品名称进行模型查询
    public List<Goods> selectSearchGoods(String keyword, int pageNumber, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from goods where name like ? limit ?,?";
        return (List)r.query(sql, new BeanListHandler(Goods.class), new Object[]{"%" + keyword + "%", (pageNumber - 1) * pageSize, pageSize});
    }
    //判断是否为滚动商品
    public boolean isScroll(Goods g) throws SQLException {
        return this.isRecommend(g, 1);
    }
    //判断是否为热销商品
    public boolean isHot(Goods g) throws SQLException {
        return this.isRecommend(g, 2);
    }
    //判断是否为新品商品
    public boolean isNew(Goods g) throws SQLException {
        return this.isRecommend(g, 3);
    }
    //根据推荐类型和商品id查询是否存在该推荐商品
    private boolean isRecommend(Goods g, int type) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from recommend where type=? and goods_id=?";
        Recommend recommend = (Recommend)r.query(sql, new BeanHandler(Recommend.class), new Object[]{type, g.getId()});
        return recommend != null;
    }

        //往推荐栏添加商品数据
    public void addRecommend(int id, int type) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into recommend(type,goods_id) values(?,?)";
        r.update(sql, new Object[]{type, id});
    }
    //删除推荐栏的信息
    public void removeRecommend(int id, int type) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from recommend where type=? and goods_id=?";
        r.update(sql, new Object[]{type, id});
    }
    //插入商品数据
    public void insert(Goods g) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into goods(name,cover,image1,image2,price,intro,stock,type_id) values(?,?,?,?,?,?,?,?)";
        r.update(sql, new Object[]{g.getName(), g.getCover(), g.getImage1(), g.getImage2(), g.getPrice(), g.getIntro(), g.getStock(), g.getType().getId()});
    }
//更新商品信息
    public void update(Goods g) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update goods set name=?,cover=?,image1=?,image2=?,price=?,intro=?,stock=?,type_id=? where id=?";
        r.update(sql, new Object[]{g.getName(), g.getCover(), g.getImage1(), g.getImage2(), g.getPrice(), g.getIntro(), g.getStock(), g.getType().getId(), g.getId()});
    }
//删除商品信息
    public void delete(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from goods where id = ?";
        r.update(sql, id);
    }
}
