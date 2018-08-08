package com.liuzw.generate.controller;


import com.liuzw.generate.bean.*;
import com.liuzw.generate.service.DatabaseInfoService;
import com.liuzw.generate.utils.CopyDataUtil;
import com.liuzw.generate.valid.Insert;
import com.liuzw.generate.valid.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 数据库管理
 *
 * @author liuzw
 * @version V1.0
 **/
@Controller
@RequestMapping("/databaseInfo")
public class DatabaseInfoController extends BaseController {

    @Autowired
    private DatabaseInfoService codeDatabaseInfoService;


    /**
     * 跳转到 数据库管理 页面
     */
    @GetMapping("/info")
    public String dbinfo() {
        return "dbinfo/dbinfo";
    }

    /**
     * 跳转到 数据库管理新增 页面
     */
    @GetMapping("/add")
    public String dbinfoAdd() {
        return "dbinfo/dbinfo_add";
    }


    /**
     * 跳转到 数据库管理编辑 页面
     *
     * @param id 主键id
     */
    @GetMapping(value = "/edit/{id}")
    public String query(@NotNull(message = "id不能为空") @PathVariable("id") Long id, Model model) {
        DatabaseInfoBean bean = CopyDataUtil.copyObject(codeDatabaseInfoService.getById(id), DatabaseInfoBean.class);
        model.addAttribute("dbinfo", bean);
        return "dbinfo/dbinfo_edit";
    }


    /**
     * 获取所有数据
     *
     * @param dto DatabaseInfoBean
     * @return ResultData<Page   <   DatabaseInfoBean>>
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public ResultData<Page<DatabaseInfoBean>> getList(@RequestBody DatabaseInfoQueryBean dto) {
        return ResultData.createSelectSuccessResult(convertPageInfo(codeDatabaseInfoService.getList(dto), DatabaseInfoBean.class));
    }


    /**
     * 添加
     *
     * @param dto DatabaseInfoDto
     * @return ResultData<DatabaseInfoBean>
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public ResultData<DatabaseInfoBean> insert(@Validated @RequestBody DatabaseInfoBean dto) {
        return ResultData.createInsertResult(codeDatabaseInfoService.insert(dto));
    }

    /**
     * 更新
     *
     * @param dto DatabaseInfoDto
     * @return ResultData<DatabaseInfoBean>
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public ResultData<DatabaseInfoBean> update(@Validated(Update.class) @RequestBody DatabaseInfoBean dto) {
        return ResultData.createUpdateResult(codeDatabaseInfoService.update(dto));
    }


    /**
     * 删除
     *
     * @param id 主键id
     * @return ResultData<String>
     */
    @PostMapping(value = "/remove")
    @ResponseBody
    public ResultData<Void> delete(@NotNull(message = "id不能为空") @RequestBody IdBean id) {
        return ResultData.createDeleteResult(codeDatabaseInfoService.delete(id.getId()));
    }

}
