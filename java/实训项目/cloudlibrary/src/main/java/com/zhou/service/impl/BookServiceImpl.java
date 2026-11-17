package com.zhou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhou.dao.BookMapper;
import com.zhou.domain.Book;
import com.zhou.domain.Record;
import com.zhou.domain.User;
import com.zhou.enetity.PageResult;
import com.zhou.service.BookService;
import com.zhou.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;


    @Override
    public PageResult selectNewbooks(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page=bookMapper.selectNewbooks();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Book findById(String id) {
        return bookMapper.findById(id);
    }

    @Override
    public Integer borrowBook(Book book) {
        Book b=this.findById(book.getId()+"");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //设置当天为借阅时间
        book.setBorrowTime((dateFormat.format(new Date())));
        //设置所借阅图书状态为借阅中
        book.setStatus("1");
        //将图书的价格设置在book对象中
        book.setPrice(b.getPrice());
        //将图书的上架时间设置在book对象中
        book.setUploadTime(b.getUploadTime());
        return bookMapper.borrowBook(book);
    }
    /**
     *这个实现类中新增的内容:定义了findById方法，用于根据图书的id查询对应的图书信息,
     *又定义了borrowBook方法，用于执行图书借阅操作，图书借阅需要将当前图书的信息更新
     *更新的信息包括借阅人、借阅状态、借阅时间。由于图书借阅时，页面传递到Service层的图
     *书信息不完整的，所以，在更新图书信息前需要根据id将该图书的完整信息查询出来，
     */
    @Override
    public PageResult search(Book book, Integer pageNum, Integer pageSize) {
        //设置分页查询参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page=bookMapper.searchBooks(book);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Integer addBook(Book book) {
        //设置新增图书的上架时间
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        book.setUploadTime(dateFormat.format(new Date()));
        return bookMapper.addBook(book);
    }

    @Override
    public Integer editBook(Book book) {
        return bookMapper.editBook(book);
    }

    @Override
    public PageResult searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        if ("admin".equals(user.getRole()))
        {  book.setBorrower(user.getName());
            Page<Book> page=bookMapper.selectBorrowed(book);;
            return new PageResult(page.getTotal(),page.getResult());
        }
         else{
             book.setBorrower(user.getName());
             Page<Book> page=bookMapper.selectMyBorrowed(book);;
             return new PageResult(page.getTotal(),page.getResult());
        }}

    @Override
    public boolean returnBook(String id, User user) {
        //根据图书id查询图书的完整信息
        Book book=this.findById(id);
        //再次校验当前登录人员和图书信息是不是同一个人
        boolean rb=book.getBorrower().equals(user.getName());
        if (rb){
            //将图书借阅状态修改为归还中
            book.setStatus("2");
            bookMapper.editBook(book);
        }
        return rb;
    }

    /**
     * 归还确认
     * @parm id 待归还确认的图书id
     */
    @Autowired
    private RecordService recordService;
    @Override
    public Integer returnConfirm(String id){
        //根据图书id查询图书完整信息
        Book book =this.findById(id);
        Record record=this.setRecord(book);
        //将图书的状态改为可借阅
        book.setStatus("0");
        //清除当前图书的借阅人
        book.setBorrower("");
        //清除当前图书的借阅时间信息
        book.setBorrowTime("");
        //清除当前图书的预计归还时间信息
        book.setReturnTime("");
        Integer count=bookMapper.editBook(book);
        if (count==1){
            return recordService.addRecord(record);
        }return 0;
    }
    private Record setRecord(Book book){
        Record record=new Record();
        //设置借阅记录的图书名称
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        record.setBookname(book.getName());
        record.setBookisbn(book.getIsbn());
        record.setBorrower(book.getBorrower());
        record.setBorrowTime(book.getBorrowTime());
        record.setRemandTime(dateFormat.format(new Date()));
        return record;
    }
}