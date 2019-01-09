package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/6/006
 */
public class CashNotPaidException extends RuntimeException implements Serializable {
    public CashNotPaidException() {
        super();
    }

    public CashNotPaidException(String message) {
        super(message);
    }
}
