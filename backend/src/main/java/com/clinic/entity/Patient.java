package com.clinic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("patient")
public class Patient extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String gender;

    private Integer age;

    private String phone;

    private String idCard;

    private String address;
}
