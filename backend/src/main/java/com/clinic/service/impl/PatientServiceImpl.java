package com.clinic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clinic.entity.Patient;
import com.clinic.mapper.PatientMapper;
import com.clinic.service.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {
}
