package com.example.demo.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构BO
 *
 * @author lkf
 * @date Created in 2020/7/31 11:14
 */
@Data
public class UnitBO implements Serializable {

    /**
     * 机构编码
     */
    private String unitCode;

    /**
     * 机构名称
     */
    private String unitName;

    /**
     * 机构全称
     */
    private String unitFullName;

    /**
     * 行政区划编码
     */
    private String regionCode;

    /**
     * 机构级别，0501国家级，0502省级，0503市级，0504区县，0505乡
     */
    private String unitLevel;

    /**
     * 上级机构代码
     */
    private String unitParentCode;

    /**
     * 主管机构代码
     */
    private String unitCharge;

    /**
     * 统一社会信用代码
     */
    private String unitUscc;

    /**
     * 机构描述
     */
    private String unitDesc;

    /**
     * 机构地址
     */
    private String unitAddr;

    /**
     * 联系电话
     */
    private String unitTel;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 排序号
     */
    private Integer unitOrder;

    /**
     * 机构经纬度
     */
    private String unitGis;

    /**
     * 机构类型：1虚拟，2计划单列市，3其他，0直辖市
     */
    private String unitType;

    /**
     * 机构代码链
     */
    private String unitCodeLink;

    /**
     * 机构状态:Y：有效,N：无效
     */
    private String unitState;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 数据同步状态
     */
    private String dataSyncState;


}
