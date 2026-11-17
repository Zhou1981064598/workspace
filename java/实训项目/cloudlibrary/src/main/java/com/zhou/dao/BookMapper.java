package com.zhou.dao;

import com.github.pagehelper.Page;
import com.zhou.domain.Book;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;



public interface BookMapper {
    @Select("select * from book where book_status!='3' order by book_uploadtime "+"desc")
    @Results(id="bookMap",value = {
            @Result(id = true,column = "book_id",property = "id"),
            @Result(column = "book_name",property = "name"),
            @Result(column = "book_isbn",property = "isbn"),
            @Result(column = "book_press",property = "press"),
            @Result(column = "book_author",property = "author"),
            @Result(column = "book_pagination",property = "pagination"),
            @Result(column = "book_price",property = "price"),
            @Result(column = "book_uploadtime",property = "uploadTime"),
            @Result(column = "book_status",property = "status"),
            @Result(column = "book_borrower",property = "borrower"),
            @Result(column = "book_borrowtime",property = "borrowTime"),
            @Result(column = "book_returntime",property = "returnTime")
    })
    Page<Book> selectNewbooks();
    @Select(("select * from book where book_id=#{id}"))
    @ResultMap("bookMap")
    Book findById(String id);
    Integer borrowBook(Book book);
    /**
     * 借阅图书模块根据id查询图书信息的方法，因为图书持久化类的属性和图书表的字段不一致
     * 所以需要通过设置映射关系来完成结果集的映射，在这之前，BookMapeper接口中的selectNewBooks
     * 方法已经写好，已经通过注解配置了图书持久化类的属性何图书表字段的映射，在BookMapper类接口文件中
     *如果其他方法需要使用这种映射关系，只需要在上方添加@ResultMap注解即可，并在@ResultMap中添加@ResultMap注解
     * 的id即可
     */
    @Select({"<script>" +
            "SELECT * FROM book " +
            "where book_status !='3'" +
            "<if test=\"name != null\"> AND  book_name  like  CONCAT('%',#{name},'%')</if>" +
            "<if test=\"press != null\"> AND book_press like  CONCAT('%', #{press},'%') </if>" +
            "<if test=\"author != null\"> AND book_author like  CONCAT('%', #{author},'%')</if>" +
            "order by book_borrowtime" +
            "</script>"
    })
    @ResultMap("bookMap")
    Page<Book> searchBooks(Book book);
    /**
     * 动态查询图书时，分别根据name、press、author的值是否为空进行语句的动态拼接，并按最终的查询结果
     * 以图书状态升序返回，如果值属性值为空，则查询体条件不拼接该属性
     */
    Integer addBook(Book book);
    //编辑图书
    Integer editBook(Book book);
    @Select(
            {"<script>" +
                    "SELECT * FROM book " +
                    "where book_borrower=#{borrower}" +
                    "AND book_status ='1'"+
                    "<if test=\"name != null\"> AND  book_name  like  CONCAT('%',#{name},'%')</if>" +
                    "<if test=\"press != null\"> AND book_press like  CONCAT('%', #{press},'%') </if>" +
                    "<if test=\"author != null\"> AND book_author like  CONCAT('%', #{author},'%')</if>" +
                    "or book_status ='2'"+
                    "<if test=\"name != null\"> AND  book_name  like  CONCAT('%',#{name},'%')</if>" +
                    "<if test=\"press != null\"> AND book_press like  CONCAT('%', #{press},'%') </if>" +
                    "<if test=\"author != null\"> AND book_author like  CONCAT('%', #{author},'%')</if>" +
                    "order by book_borrowtime" +
                    "</script>"})
    @ResultMap("bookMap")
//查询借阅但未归还的图书和待归还确认的图书
    Page<Book> selectBorrowed(Book book);
    @Select({"<script>"  +
            "SELECT * FROM book " +
            "where book_borrower=#{borrower}" +
            "AND book_status in('1','2')"+
            "<if test=\"name != null\"> AND  book_name  like  CONCAT('%',#{name},'%')</if>" +
            "<if test=\"press != null\"> AND book_press like  CONCAT('%', #{press},'%') </if>" +
            "<if test=\"author != null\"> AND book_author like  CONCAT('%', #{author},'%')</if>" +
            "order by book_borrowtime" +
            "</script>"})
    @ResultMap("bookMap")
//查询借阅但未归还的图书.普通用户会调用以上方法
    Page<Book> selectMyBorrowed(Book book);

}
