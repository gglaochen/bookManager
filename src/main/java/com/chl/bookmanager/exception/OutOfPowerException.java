package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/3/003
 */
public class OutOfPowerException extends RuntimeException implements Serializable {

    public OutOfPowerException() {
        super();
    }

    public OutOfPowerException(String message) {
        super(message);
    }
}
