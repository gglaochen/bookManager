package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/3/003
 */
public class AlreadyPredeterException extends RuntimeException implements Serializable {

    public AlreadyPredeterException() {
        super();
    }

    public AlreadyPredeterException(String message) {
        super(message);
    }
}
