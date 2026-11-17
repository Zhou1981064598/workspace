package com.zhou.service;

import com.zhou.domain.Book;
import com.zhou.domain.Record;
import com.zhou.domain.User;
import com.zhou.enetity.PageResult;
import org.springframework.stereotype.Service;


@Service
public interface BookService {
     PageResult selectNewbooks(Integer pageNum,Integer pageSize);
     //根据id查询图书信息
     Book findById(String id);
     Integer borrowBook(Book book);
     //分页查询图书
     PageResult search(Book book,Integer pageNum,Integer pageSize);
     //新增图书
     Integer addBook(Book book);
     Integer editBook(Book book);
     //查询当前借阅图书
     PageResult searchBorrowed(Book book, User user,Integer pageNum,Integer pageSize);
     //归还图书
     boolean returnBook(String id, User user);
     Integer returnConfirm(String id);
}