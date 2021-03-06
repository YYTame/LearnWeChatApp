package com.imooc.demo.util.exception.dao;

import com.imooc.demo.util.enums.ResultEnum;

public class AreaException extends RuntimeException {
    private Integer code;

    public AreaException(String message){
        super(message);

    }
    public AreaException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
