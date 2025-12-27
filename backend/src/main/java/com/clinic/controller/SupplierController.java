package com.clinic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clinic.common.Result;
import com.clinic.entity.Supplier;
import com.clinic.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/list")
    public Result<Page<Supplier>> list(@RequestParam(defaultValue = "1") Integer current,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestParam(required = false) String name) {
        Page<Supplier> page = new Page<>(current, size);
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        Page<Supplier> result = supplierService.page(page, queryWrapper);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Supplier> getById(@PathVariable Long id) {
        Supplier supplier = supplierService.getById(id);
        return Result.success(supplier);
    }

    @PostMapping
    public Result<String> save(@RequestBody Supplier supplier) {
        boolean save = supplierService.save(supplier);
        return save ? Result.success("保存成功") : Result.error("保存失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody Supplier supplier) {
        boolean update = supplierService.updateById(supplier);
        return update ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = supplierService.removeById(id);
        return remove ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/all")
    public Result<List<Supplier>> all() {
        List<Supplier> list = supplierService.list();
        return Result.success(list);
    }
}
