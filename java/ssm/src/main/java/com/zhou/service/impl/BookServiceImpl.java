package com.zhou.service.impl;

import com.zhou.dao.BookMapper;
import com.zhou.domain.Book;
import com.zhou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public Book findBookById(Integer id) {
        return bookMapper.findBookById(id);
    }
}
