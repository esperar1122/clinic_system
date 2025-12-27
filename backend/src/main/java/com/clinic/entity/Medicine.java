package com.clinic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("medicine")
public class Medicine extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String specification;

    private String manufacturer;

    private Long supplierId;

    private String unit;

    private BigDecimal price;

    private Integer stockQuantity;

    private LocalDate expiryDate;

    private String category;
}
