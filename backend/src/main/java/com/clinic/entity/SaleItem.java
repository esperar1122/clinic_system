package com.clinic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("sale_item")
public class SaleItem {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long saleId;

    private Long medicineId;

    private String medicineName;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal subtotal;
}
