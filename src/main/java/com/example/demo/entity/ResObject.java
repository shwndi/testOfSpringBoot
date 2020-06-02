package com.example.demo.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * RESTFul接口协议对象
 */
@Data
public class ResObject<T> implements Serializable {

    public static final int SUCCESS = 1;
    public static final int FAILED = 0;

    private int code = SUCCESS;
    private String message;
    private T data;

    public ResObject() {
        this(true);
    }

    public ResObject(boolean success) {
        this(success, "");
    }

    public ResObject(boolean success, String message) {
        this.code = success ? SUCCESS : FAILED;
        this.message = message;
    }

    public ResObject(boolean success, String message, T data) {
        this.code = success ? SUCCESS : FAILED;
        this.message = message;
        this.data = data;
    }

    public ResObject(T data) {
        this();
        this.data = data;
    }


    public static ResObject create() {
        return new ResObject();
    }

    public static ResObject create(Object data) {
        return new ResObject(data);
    }

    /**
     * 根据data和httpStatus创建ResponseEntity
     * @param data
     * @param httpStatus
     * @return
     */
    public static ResponseEntity create(Object data, HttpStatus httpStatus) {
        ResObject result = new ResObject(data);
        return new ResponseEntity(result, httpStatus);
    }

    /**
     * 返回成功
     * @param data
     * @return
     */
    public static ResponseEntity success(Object data) {
        return create(data, HttpStatus.OK);
    }

    /**
     * 返回失败
     * @param reason    失败原因
     * @param data      数据
     * @return
     */
    public static ResponseEntity failed(String reason, Object data) {
        ResObject result = new ResObject(false, reason);
        result.setData(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
