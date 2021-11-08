package com.mszlu.blog.dao.dos;

import lombok.Data;
// 查詢的對象不需要持久化

@Data
public class Archives {

    private Integer year;

    private Integer month;

    private Long count;
}
