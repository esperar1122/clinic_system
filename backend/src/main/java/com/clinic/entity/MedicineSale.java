package com.clinic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("medicine_sale")
public class MedicineSale extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String saleNo;

    private Long patientId;

    private String patientName;

    private BigDecimal totalAmount;

    private String paymentMethod;

    private String status;
}
