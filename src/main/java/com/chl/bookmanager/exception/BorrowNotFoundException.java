package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/4/004
 */
public class BorrowNotFoundException extends RuntimeException implements Serializable {
    public BorrowNotFoundException() {
        super();
    }

    public BorrowNotFoundException(String message) {
        super(message);
    }
}
