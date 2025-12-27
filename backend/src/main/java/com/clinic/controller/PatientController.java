package com.clinic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clinic.common.Result;
import com.clinic.entity.Patient;
import com.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public Result<Page<Patient>> list(@RequestParam(defaultValue = "1") Integer current,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam(required = false) String name) {
        Page<Patient> page = new Page<>(current, size);
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        Page<Patient> result = patientService.page(page, queryWrapper);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Patient> getById(@PathVariable Long id) {
        Patient patient = patientService.getById(id);
        return Result.success(patient);
    }

    @PostMapping
    public Result<String> save(@RequestBody Patient patient) {
        boolean save = patientService.save(patient);
        return save ? Result.success("保存成功") : Result.error("保存失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody Patient patient) {
        boolean update = patientService.updateById(patient);
        return update ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = patientService.removeById(id);
        return remove ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/all")
    public Result<List<Patient>> all() {
        List<Patient> list = patientService.list();
        return Result.success(list);
    }
}
