package com.example.demo.controller;

import com.example.demo.entity.ResObject;
import com.example.demo.entity.ValidGroup;
import com.example.demo.vo.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * post请求下请求体的分类验证
 *
 * @author czy
 * @date 2020/04/23
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试使用")
public class GroupController {
    /**
     * 只校验AddGroup组中的属性
     *
     * @param vo
     * @return
     */
    @PostMapping(path = "/saveDict", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "AddGroup")
    public ResponseEntity saveDict(@RequestBody @Validated(ValidGroup.AddGroup.class) TestVO vo) {
        return ResObject.success("请求成功");
    }

    /**
     * 只校验NameGroup组中的属性
     *
     * @param vo
     * @return
     */
    @PostMapping(path = "/updateDict", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "NameGroup")
    public ResponseEntity updateDict(@RequestBody @Validated(ValidGroup.NameGroup.class) TestVO vo) {
        return ResObject.success("请求成功");
    }

    /*-----------------------------------------------------------------*/
    /*不分组和分空组的情况下效果是一样的，不会对对象属性校验*/

    /**
     * 不分组
     *
     * @param vo
     * @return
     */
    @PostMapping(path = "/notNull", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "notNull")
    public ResponseEntity notNull(@RequestBody TestVO vo) {
        return ResObject.success("请求成功");
    }

    /**
     * vo中，分组属性为空
     *
     * @param vo
     * @return
     */
    @PostMapping(path = "/allNull", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "allNull")
    public ResponseEntity allNUll(@RequestBody @Validated(ValidGroup.EmptyGroup.class) TestVO vo) {
        return ResObject.success("请求成功");
    }
    /*------------------------------------------------------------------------------*/
}
