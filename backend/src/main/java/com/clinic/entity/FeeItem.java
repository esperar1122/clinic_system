package com.clinic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@TableName("fee_item")
public class FeeItem {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long hospitalizationId;

    private String itemName;

    private String itemType;

    private BigDecimal amount;

    private LocalDate itemDate;

    private String remark;
}
