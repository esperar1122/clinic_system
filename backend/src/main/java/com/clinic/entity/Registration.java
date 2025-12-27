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
@TableName("registration")
public class Registration extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long patientId;

    private String registrationNo;

    private String department;

    private String doctor;

    private BigDecimal registrationFee;

    private LocalDate visitDate;

    private String status;
}
