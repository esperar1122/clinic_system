package com.clinic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clinic.common.Result;
import com.clinic.entity.MedicineSale;
import com.clinic.service.MedicineSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicineSale")
@CrossOrigin
public class MedicineSaleController {

    @Autowired
    private MedicineSaleService medicineSaleService;

    @GetMapping("/list")
    public Result<Page<MedicineSale>> list(@RequestParam(defaultValue = "1") Integer current,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(required = false) String saleNo) {
        Page<MedicineSale> page = new Page<>(current, size);
        QueryWrapper<MedicineSale> queryWrapper = new QueryWrapper<>();
        if (saleNo != null && !saleNo.isEmpty()) {
            queryWrapper.like("sale_no", saleNo);
        }
        queryWrapper.orderByDesc("create_time");
        Page<MedicineSale> result = medicineSaleService.page(page, queryWrapper);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<MedicineSale> getById(@PathVariable Long id) {
        MedicineSale medicineSale = medicineSaleService.getById(id);
        return Result.success(medicineSale);
    }

    @PostMapping
    public Result<String> save(@RequestBody MedicineSale medicineSale) {
        boolean save = medicineSaleService.save(medicineSale);
        return save ? Result.success("销售成功") : Result.error("销售失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody MedicineSale medicineSale) {
        boolean update = medicineSaleService.updateById(medicineSale);
        return update ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = medicineSaleService.removeById(id);
        return remove ? Result.success("删除成功") : Result.error("删除失败");
    }
}
