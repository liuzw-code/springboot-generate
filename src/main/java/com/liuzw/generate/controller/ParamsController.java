package com.liuzw.generate.controller;


import com.liuzw.generate.bean.*;
import com.liuzw.generate.service.ParamsService;
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
 * 参数管理
 *
 * @author liuzw
 * @version V1.0
 **/
@Validated
@Controller
@RequestMapping("/params")
public class ParamsController extends BaseController {

    @Autowired
    private ParamsService paramsService;


    /**
     * 跳转到 参数管理 页面
     */
    @GetMapping("/info")
    public String param() {
        return "param/param";
    }

    /**
     * 跳转到 参数新增 页面
     */
    @GetMapping("/add")
    public String paramAdd() {
        return "param/param_add";
    }

    /**
     * 跳转到 参数管理编辑 页面
     *
     * @param id 主键id
     */
    @GetMapping(value = "/edit/{id}")
    public String paramUpdate(@NotNull(message = "id不能为空") @PathVariable("id") Long id, Model model) {
        ParamsBean bean = CopyDataUtil.copyObject(paramsService.getById(id), ParamsBean.class);
        model.addAttribute("params", bean);
        return "param/param_edit";
    }


    /**
     * 获取所有数据
     *
     * @param dto ParamsBean
     * @return ResultData<Page   <   ParamsBean>>
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public ResultData<Page<ParamsBean>> getList(@RequestBody ParamsQueryBean dto) {
        return ResultData.createSelectSuccessResult(convertPageInfo(paramsService.getList(dto), ParamsBean.class));
    }


    /**
     * 获取详细信息
     *
     * @param id 主键id
     */
    @PostMapping(value = "/getById/{id}")
    @ResponseBody
    public  ResultData<ParamsBean> getById(@NotNull(message = "id不能为空") @PathVariable("id") Long id) {
        ParamsBean bean = CopyDataUtil.copyObject(paramsService.getById(id), ParamsBean.class);
        return ResultData.createSelectSuccessResult(bean);
    }

    /**
     * 添加
     *
     * @param dto ParamsDto
     * @return ResultData<ParamsBean>
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public ResultData<ParamsBean> insert(@Validated(Insert.class) @RequestBody ParamsBean dto) {
        return ResultData.createInsertResult(paramsService.insert(dto));
    }

    /**
     * 更新
     *
     * @param dto ParamsDto
     * @return ResultData<ParamsBean>
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public ResultData<ParamsBean> update(@Validated(Update.class)@RequestBody ParamsBean dto) {
        return ResultData.createUpdateResult(paramsService.update(dto));
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
        return ResultData.createDeleteResult(paramsService.delete(id.getId()));
    }

}
