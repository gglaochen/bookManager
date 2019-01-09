package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/4/004
 */
public class HasPredeterException extends RuntimeException implements Serializable {

    public HasPredeterException() {
        super();
    }

    public HasPredeterException(String message) {
        super(message);
    }
}
