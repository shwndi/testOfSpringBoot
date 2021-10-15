package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czy
 * @date 2021/4/8
 */
@RestController
@RequestMapping("/es")
public class TestEsController {

public ResponseEntity getObject(){
    ResponseEntity entity = new ResponseEntity<>(HttpStatus.OK);
    Object o = new Object();
    System.out.println("method: getObject");
    return entity;
}
}
