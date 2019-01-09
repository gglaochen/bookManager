package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/4/004
 */
public class NotAllowedBorrowAgainException extends RuntimeException implements Serializable {
    public NotAllowedBorrowAgainException() {
        super();
    }

    public NotAllowedBorrowAgainException(String message) {
        super(message);
    }
}
