package com.example.springbootstudy.AOP;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Page {
    private int pageNum;
    private int pageSize;
    private int total;
}
