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
@TableName("hospitalization_fee")
public class HospitalizationFee extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long patientId;

    private String patientName;

    private String admissionNo;

    private String department;

    private String bedNo;

    private LocalDate admissionDate;

    private LocalDate dischargeDate;

    private BigDecimal deposit;

    private BigDecimal totalAmount;

    private BigDecimal paidAmount;

    private String status;
}
