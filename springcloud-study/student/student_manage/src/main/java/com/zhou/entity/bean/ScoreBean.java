package com.zhou.entity.bean;

import com.zhou.entity.Score;
import lombok.Data;

@Data
public class ScoreBean extends Score {
    private String stuName;
    private String sbjName;
}
