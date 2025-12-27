-- 药品零售表
CREATE TABLE IF NOT EXISTS medicine_sale (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '零售ID',
    sale_no VARCHAR(50) NOT NULL UNIQUE COMMENT '销售单号',
    patient_id BIGINT COMMENT '患者ID',
    patient_name VARCHAR(50) COMMENT '患者姓名',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '总金额',
    sale_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '销售时间',
    payment_method VARCHAR(20) COMMENT '支付方式: 现金/微信/支付宝',
    status ENUM('已支付', '未支付', '已退款') DEFAULT '已支付' COMMENT '状态',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品零售表';

-- 药品销售明细表
CREATE TABLE IF NOT EXISTS sale_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    sale_id BIGINT NOT NULL COMMENT '销售ID',
    medicine_id BIGINT NOT NULL COMMENT '药品ID',
    medicine_name VARCHAR(100) COMMENT '药品名称',
    quantity INT NOT NULL COMMENT '数量',
    price DECIMAL(10,2) NOT NULL COMMENT '单价',
    subtotal DECIMAL(10,2) NOT NULL COMMENT '小计',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品销售明细表';

-- 住院收费表
CREATE TABLE IF NOT EXISTS hospitalization_fee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '收费ID',
    patient_id BIGINT NOT NULL COMMENT '患者ID',
    patient_name VARCHAR(50) COMMENT '患者姓名',
    admission_no VARCHAR(50) NOT NULL UNIQUE COMMENT '住院号',
    department VARCHAR(50) COMMENT '科室',
    bed_no VARCHAR(20) COMMENT '床位号',
    admission_date DATE COMMENT '入院日期',
    discharge_date DATE COMMENT '出院日期',
    deposit DECIMAL(10,2) DEFAULT 0.00 COMMENT '预交金',
    total_amount DECIMAL(10,2) DEFAULT 0.00 COMMENT '总费用',
    paid_amount DECIMAL(10,2) DEFAULT 0.00 COMMENT '已付金额',
    status ENUM('住院中', '已出院', '已结算') DEFAULT '住院中' COMMENT '状态: 住院中/已出院',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='住院收费表';

-- 费用明细表
CREATE TABLE IF NOT EXISTS fee_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '费用明细ID',
    hospitalization_id BIGINT NOT NULL COMMENT '住院ID',
    item_name VARCHAR(100) NOT NULL COMMENT '费用项目',
    item_type ENUM('药费', '检查费', '治疗费', '床位费', '其他') COMMENT '费用类型',
    amount DECIMAL(10,2) NOT NULL COMMENT '金额',
    item_date DATE COMMENT '费用日期',
    remark VARCHAR(200) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='费用明细表';
