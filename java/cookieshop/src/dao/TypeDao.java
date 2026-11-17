//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dao;

import model.Type;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class TypeDao {
    public TypeDao() {
    }
    //查询所有类型
    public List<Type> GetAllType() throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from type";
        return (List)r.query(sql, new BeanListHandler(Type.class));
    }
    //根据类型id查询类型名
    public Type selectTypeNameByID(int typeid) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from type where id=?";
        return (Type)r.query(sql, new BeanHandler(Type.class), new Object[]{typeid});
    }
    //根据类型id查询类型
    public Type select(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from type where id = ?";
        return (Type)r.query(sql, new BeanHandler(Type.class), new Object[]{id});
    }
    //插入类型
    public void insert(Type t) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into type(name) values(?)";
        r.update(sql, t.getName());
    }
    //修改类型
    public void update(Type t) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update type set name=? where id = ?";
        r.update(sql, new Object[]{t.getName(), t.getId()});
    }
    //删除类型
    public void delete(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from type where id = ?";
        r.update(sql, id);
    }
}
