package com.clinic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clinic.common.Result;
import com.clinic.entity.HospitalizationFee;
import com.clinic.service.HospitalizationFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitalizationFee")
@CrossOrigin
public class HospitalizationFeeController {

    @Autowired
    private HospitalizationFeeService hospitalizationFeeService;

    @GetMapping("/list")
    public Result<Page<HospitalizationFee>> list(@RequestParam(defaultValue = "1") Integer current,
                                                  @RequestParam(defaultValue = "10") Integer size,
                                                  @RequestParam(required = false) String admissionNo) {
        Page<HospitalizationFee> page = new Page<>(current, size);
        QueryWrapper<HospitalizationFee> queryWrapper = new QueryWrapper<>();
        if (admissionNo != null && !admissionNo.isEmpty()) {
            queryWrapper.like("admission_no", admissionNo);
        }
        queryWrapper.orderByDesc("create_time");
        Page<HospitalizationFee> result = hospitalizationFeeService.page(page, queryWrapper);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<HospitalizationFee> getById(@PathVariable Long id) {
        HospitalizationFee hospitalizationFee = hospitalizationFeeService.getById(id);
        return Result.success(hospitalizationFee);
    }

    @PostMapping
    public Result<String> save(@RequestBody HospitalizationFee hospitalizationFee) {
        boolean save = hospitalizationFeeService.save(hospitalizationFee);
        return save ? Result.success("保存成功") : Result.error("保存失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody HospitalizationFee hospitalizationFee) {
        boolean update = hospitalizationFeeService.updateById(hospitalizationFee);
        return update ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = hospitalizationFeeService.removeById(id);
        return remove ? Result.success("删除成功") : Result.error("删除失败");
    }
}
