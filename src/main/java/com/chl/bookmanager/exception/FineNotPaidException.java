package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/3/003
 */
public class FineNotPaidException extends RuntimeException implements Serializable {
    public FineNotPaidException() {
        super();
    }

    public FineNotPaidException(String message) {
        super(message);
    }
}
