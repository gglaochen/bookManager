package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/4/004
 */
public class HasBorrowedAgainException extends RuntimeException implements Serializable {

    public HasBorrowedAgainException() {
        super();
    }

    public HasBorrowedAgainException(String message) {
        super(message);
    }
}
