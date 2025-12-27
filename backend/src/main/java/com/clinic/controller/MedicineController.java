package com.clinic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clinic.common.Result;
import com.clinic.entity.Medicine;
import com.clinic.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
@CrossOrigin
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/list")
    public Result<Page<Medicine>> list(@RequestParam(defaultValue = "1") Integer current,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestParam(required = false) String name) {
        Page<Medicine> page = new Page<>(current, size);
        QueryWrapper<Medicine> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        Page<Medicine> result = medicineService.page(page, queryWrapper);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Medicine> getById(@PathVariable Long id) {
        Medicine medicine = medicineService.getById(id);
        return Result.success(medicine);
    }

    @PostMapping
    public Result<String> save(@RequestBody Medicine medicine) {
        boolean save = medicineService.save(medicine);
        return save ? Result.success("保存成功") : Result.error("保存失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody Medicine medicine) {
        boolean update = medicineService.updateById(medicine);
        return update ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = medicineService.removeById(id);
        return remove ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/all")
    public Result<List<Medicine>> all() {
        List<Medicine> list = medicineService.list();
        return Result.success(list);
    }
}
