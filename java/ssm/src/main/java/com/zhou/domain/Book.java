package com.zhou.domain;

import java.lang.ref.SoftReference;

public class Book {
    //图书ID
    private Integer id;
    //图书名称
    private String name;
    //图书出版社
    private String press;
    //图书作者
    private String  author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
