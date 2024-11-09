package com.example.test2_springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.test2_springboot.common.utils.R;
import com.example.test2_springboot.model.SpeciesEntity;
import com.example.test2_springboot.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * @author wx
 * @email wx@gmail.com
 * @date 2023-04-21 17:48:27
 */
@RestController
@RequestMapping("/species")
public class SpeciesController {
    @Autowired
    private SpeciesService speciesService;
        // curl 127.0.0.1:8080/species/tissue?speciesname=cattle
    @RequestMapping("/tissue")// post
    JSONObject selectTissuebyspaciesname(@RequestParam("speciesname") String speciesname) {
        JSONObject result = new JSONObject();
        try {
            result.put("status", 1);
            result.put("data", speciesService.selectBySpecies(speciesname));
        } catch (Exception e) {
            result.put("status", 0);
            result.put("data", null);
        } finally {
            return result;
        }


    }

//    @RequestMapping("/selectTissueBySpecies/{speciesname}")//restful
//    JSONObject selectTissueBySpecies(@PathVariable("speciesname") String speciesname) {
//        JSONObject result = new JSONObject();
//        try {
//            result.put("status", 1);
//            result.put("data", speciesService.selectTissueBySpecies(speciesname));
//        } catch (Exception e) {
//            result.put("status", 0);
//            result.put("data", null);
//        } finally {
//            return result;
//        }
//
//
//    }
//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    //@RequiresPermissions("test2_springboot:species:list")
//    public R list() {
//        return R.ok().put("list", speciesService.list());
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    //@RequiresPermissions("test2_springboot:species:info")
//    public R info(@PathVariable("id") Integer id) {
//        SpeciesEntity species = speciesService.getById(id);
//
//        return R.ok().put("species", species);
//    }
//
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    //@RequiresPermissions("test2_springboot:species:save")
//    public R save(@RequestBody SpeciesEntity species) {
//        speciesService.save(species);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    //@RequiresPermissions("test2_springboot:species:update")
//    public R update(@RequestBody SpeciesEntity species) {
//        //ValidatorUtils.validateEntity(species);
//        speciesService.updateById(species);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    //@RequiresPermissions("test2_springboot:species:delete")
//    public R delete(@RequestBody Integer[] ids) {
//        speciesService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
