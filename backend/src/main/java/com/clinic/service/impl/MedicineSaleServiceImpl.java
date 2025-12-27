package com.clinic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clinic.entity.MedicineSale;
import com.clinic.mapper.MedicineSaleMapper;
import com.clinic.service.MedicineSaleService;
import org.springframework.stereotype.Service;

@Service
public class MedicineSaleServiceImpl extends ServiceImpl<MedicineSaleMapper, MedicineSale> implements MedicineSaleService {
}
