package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2019/1/2/002
 */
public class RestNumNotNullException extends RuntimeException  implements Serializable {

    public RestNumNotNullException() {
        super();
    }

    public RestNumNotNullException(String message) {
        super(message);
    }
}
