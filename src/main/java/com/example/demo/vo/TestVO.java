package com.example.demo.vo;

import com.example.demo.entity.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author czy
 * @date 2020/04/23
 */
@Data
@ApiModel
public class TestVO {
    /**
     * id
     */
    @ApiModelProperty
    @NotBlank(message = "ID cannot be empty",groups = ValidGroup.AddGroup.class)
    private String id;
    /**
     * name
     */
    @ApiModelProperty
    @NotBlank(message = "name cannot be empty",groups = {ValidGroup.AddGroup.class,ValidGroup.NameGroup.class})
    private String name;
}
