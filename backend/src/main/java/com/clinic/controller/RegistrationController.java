package com.clinic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clinic.common.Result;
import com.clinic.entity.Registration;
import com.clinic.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/list")
    public Result<Page<Registration>> list(@RequestParam(defaultValue = "1") Integer current,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(required = false) String registrationNo) {
        Page<Registration> page = new Page<>(current, size);
        QueryWrapper<Registration> queryWrapper = new QueryWrapper<>();
        if (registrationNo != null && !registrationNo.isEmpty()) {
            queryWrapper.like("registration_no", registrationNo);
        }
        queryWrapper.orderByDesc("create_time");
        Page<Registration> result = registrationService.page(page, queryWrapper);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Registration> getById(@PathVariable Long id) {
        Registration registration = registrationService.getById(id);
        return Result.success(registration);
    }

    @PostMapping
    public Result<String> save(@RequestBody Registration registration) {
        boolean save = registrationService.save(registration);
        return save ? Result.success("挂号成功") : Result.error("挂号失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody Registration registration) {
        boolean update = registrationService.updateById(registration);
        return update ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = registrationService.removeById(id);
        return remove ? Result.success("删除成功") : Result.error("删除失败");
    }
}
