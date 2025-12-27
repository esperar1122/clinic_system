-- 创建数据库
CREATE DATABASE IF NOT EXISTS clinic_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE clinic_system;

-- 患者表
CREATE TABLE IF NOT EXISTS patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '患者ID',
    name VARCHAR(50) NOT NULL COMMENT '患者姓名',
    gender VARCHAR(10) NOT NULL COMMENT '性别',
    age INT COMMENT '年龄',
    phone VARCHAR(20) NOT NULL COMMENT '联系电话',
    id_card VARCHAR(18) COMMENT '身份证号',
    address VARCHAR(200) COMMENT '家庭住址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='患者表';

-- 供应商表
CREATE TABLE IF NOT EXISTS supplier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '供应商ID',
    name VARCHAR(100) NOT NULL COMMENT '供应商名称',
    contact_person VARCHAR(50) COMMENT '联系人',
    contact_phone VARCHAR(20) COMMENT '联系电话',
    address VARCHAR(200) COMMENT '地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';

-- 药品表
CREATE TABLE IF NOT EXISTS medicine (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '药品ID',
    name VARCHAR(100) NOT NULL COMMENT '药品名称',
    specification VARCHAR(50) COMMENT '规格',
    manufacturer VARCHAR(100) COMMENT '生产厂家',
    supplier_id BIGINT COMMENT '供应商ID',
    unit VARCHAR(20) COMMENT '单位',
    price DECIMAL(10,2) NOT NULL COMMENT '单价',
    stock_quantity INT DEFAULT 0 COMMENT '库存数量',
    expiry_date DATE COMMENT '有效期',
    category VARCHAR(50) COMMENT '药品分类',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品表';

-- 挂号记录表
CREATE TABLE IF NOT EXISTS registration (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '挂号ID',
    patient_id BIGINT NOT NULL COMMENT '患者ID',
    registration_no VARCHAR(50) NOT NULL UNIQUE COMMENT '挂号单号',
    department VARCHAR(50) NOT NULL COMMENT '挂号科室',
    doctor VARCHAR(50) NOT NULL COMMENT '接诊医生',
    registration_fee DECIMAL(10,2) DEFAULT 0.00 COMMENT '挂号费',
    visit_date DATE NOT NULL COMMENT '就诊日期',
    status VARCHAR(20) DEFAULT '待诊' COMMENT '状态: 待诊/就诊中/已完成',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='挂号记录表';

-- 药品零售表
CREATE TABLE IF NOT EXISTS medicine_sale (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '零售ID',
    sale_no VARCHAR(50) NOT NULL UNIQUE COMMENT '销售单号',
    patient_id BIGINT COMMENT '患者ID',
    patient_name VARCHAR(50) COMMENT '患者姓名',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '总金额',
    sale_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '销售时间',
    payment_method VARCHAR(20) COMMENT '支付方式: 现金/微信/支付宝',
    status VARCHAR(20) DEFAULT '已支付' COMMENT '状态',
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
    status VARCHAR(20) DEFAULT '住院中' COMMENT '状态: 住院中/已出院',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='住院收费表';

-- 费用明细表
CREATE TABLE IF NOT EXISTS fee_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '费用明细ID',
    hospitalization_id BIGINT NOT NULL COMMENT '住院ID',
    item_name VARCHAR(100) NOT NULL COMMENT '费用项目',
    item_type VARCHAR(50) COMMENT '费用类型: 药费/检查费/治疗费/床位费',
    amount DECIMAL(10,2) NOT NULL COMMENT '金额',
    item_date DATE COMMENT '费用日期',
    remark VARCHAR(200) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='费用明细表';
