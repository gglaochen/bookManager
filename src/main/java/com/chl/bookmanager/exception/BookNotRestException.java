package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/3/003
 */
public class BookNotRestException extends RuntimeException implements Serializable {
    public BookNotRestException() {
        super();
    }

    public BookNotRestException(String message) {
        super(message);
    }
}
