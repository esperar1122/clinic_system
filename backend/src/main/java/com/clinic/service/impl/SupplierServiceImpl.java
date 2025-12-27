package com.clinic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clinic.entity.Supplier;
import com.clinic.mapper.SupplierMapper;
import com.clinic.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {
}
