package com.clinic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clinic.entity.Registration;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegistrationMapper extends BaseMapper<Registration> {
}
