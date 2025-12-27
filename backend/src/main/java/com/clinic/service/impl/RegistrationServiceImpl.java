package com.clinic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clinic.entity.Registration;
import com.clinic.mapper.RegistrationMapper;
import com.clinic.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration> implements RegistrationService {
}
