package com.clinic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clinic.entity.Medicine;
import com.clinic.mapper.MedicineMapper;
import com.clinic.service.MedicineService;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine> implements MedicineService {
}
