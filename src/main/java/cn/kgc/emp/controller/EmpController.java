package cn.kgc.emp.controller;


import cn.kgc.common.vo.Result;
import cn.kgc.emp.entity.Emp;
import cn.kgc.emp.service.IEmpService;
import cn.kgc.emp.vo.EmpQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kgc
 * @since 2022-07-19
 */
@Api(tags = {"员工模块接口"})
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private IEmpService empService;
    @ApiOperation("分页查询员工列表")
    @GetMapping("")
    public Result<Map<String,Object>> getEmpList(EmpQuery param){

        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        if(param.getBirthday() != null) {
            wrapper.eq("birthday", param.getBirthday());
        }
        if(StringUtils.hasLength(param.getName())){
            wrapper.like("name",param.getName());
        }
        Page<Emp> page = empService.page(new Page<>(param.getPageNo(), param.getPageSize()), wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("rows",page.getRecords());
        data.put("total",page.getTotal());

        return Result.success(data);
    }

    @ApiOperation("新增员工")
    @PostMapping("")
    public Result<Emp> addEmp(@RequestBody Emp emp){
        empService.save(emp);
        return Result.success("新增员工成功",emp);
    }

    // 修改
    @ApiOperation("修改员工")
    @PutMapping("")
    public Result<Emp> updateEmp(@RequestBody Emp emp){
        empService.updateById(emp);
        return Result.success("修改员工成功",emp);
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Result<Object> deleteEmpById(@PathVariable("id") Integer id){
        empService.removeById(id);
        return Result.success("删除员工成功");
    }


    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public Result<Emp> getEmpById(@PathVariable("id") Integer id){
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }
}
