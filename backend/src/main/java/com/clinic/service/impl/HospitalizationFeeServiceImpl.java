package com.clinic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clinic.entity.HospitalizationFee;
import com.clinic.mapper.HospitalizationFeeMapper;
import com.clinic.service.HospitalizationFeeService;
import org.springframework.stereotype.Service;

@Service
public class HospitalizationFeeServiceImpl extends ServiceImpl<HospitalizationFeeMapper, HospitalizationFee> implements HospitalizationFeeService {
}
