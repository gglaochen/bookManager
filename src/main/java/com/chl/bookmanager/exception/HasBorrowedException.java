package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/4/004
 */
public class HasBorrowedException extends RuntimeException implements Serializable {
    public HasBorrowedException() {
        super();
    }

    public HasBorrowedException(String message) {
        super(message);
    }
}
