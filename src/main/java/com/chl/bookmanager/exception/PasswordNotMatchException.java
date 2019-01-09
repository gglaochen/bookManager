package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2018/12/27/027
 */
public class PasswordNotMatchException extends RuntimeException implements Serializable {
    public PasswordNotMatchException(){

    }
    public PasswordNotMatchException(String message){
        super(message);
    }
}
