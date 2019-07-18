package com.lin.exception;

/**
 * @auther : lin
 * @date : 2019/6/27 13:48
 * @desc :
 */
public class SysException extends Exception{
    // 存储提示信息的
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SysException(String message) {
        this.message = message;
    }


}
