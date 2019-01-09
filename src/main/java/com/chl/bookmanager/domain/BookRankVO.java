package com.chl.bookmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:Administrator
 * @date:2019/1/7/007
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRankVO {
    private String bookName;
    private String author;
    private Integer borrowNum;
}
