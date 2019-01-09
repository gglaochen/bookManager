package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2018/12/27/027
 */
public class UserNotFoundException extends RuntimeException implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public UserNotFoundException(){

    }
    public UserNotFoundException(String message){
        super(message);
    }
}
