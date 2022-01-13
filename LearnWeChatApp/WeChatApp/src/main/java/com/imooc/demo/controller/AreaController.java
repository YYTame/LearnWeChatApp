package com.imooc.demo.controller;

import com.imooc.demo.entity.Area;
import com.imooc.demo.service.AreaService;
import com.imooc.demo.util.ResultUtil;
import com.imooc.demo.util.objUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping(value = "/getAreas")
    public Result getAreas(){
        return ResultUtil.success(areaService.queryArea());
    }

    @GetMapping(value="/getAreabyAreaId/{id}")
    public Result getAreabyAreaId(@PathVariable(value = "id")Integer areaId){
        return ResultUtil.success(areaService.queryAreaById(areaId));
    }

    @PostMapping(value="/createArea")
    public Result createArea(@RequestBody Area area){
        areaService.insertArea(area);
        return ResultUtil.success();
    }

    @PutMapping(value="/updateArea")
    public Result updateArea(@RequestBody Area area){
        areaService.updateArea(area);
        return ResultUtil.success();
    }

    @DeleteMapping(value="/deleteArea/{areaId}")
    public Result deleteArea(@PathVariable(value="areaId")Integer id){
        areaService.deleteArea(id);
        return ResultUtil.success();
    }
}
