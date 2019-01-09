package com.chl.bookmanager.exception;

import java.io.Serializable;

/**
 * @author:Administrator
 * @date:2018/12/27/027
 */
public class UsernameAlreadyExistException extends RuntimeException  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5916370894847140757L;
    public UsernameAlreadyExistException(){

    }
    public UsernameAlreadyExistException(String message){
        super(message);
    }
}
