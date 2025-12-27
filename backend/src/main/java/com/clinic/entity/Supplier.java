package com.clinic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("supplier")
public class Supplier extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String contactPerson;

    private String contactPhone;

    private String address;
}
