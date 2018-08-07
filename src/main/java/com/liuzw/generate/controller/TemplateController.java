package com.liuzw.generate.controller;


import com.liuzw.generate.bean.*;
import com.liuzw.generate.service.TemplateGroupService;
import com.liuzw.generate.service.TemplateService;
import com.liuzw.generate.utils.CopyDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 模板管理
 *
 * @author liuzw
 * @version V1.0
 **/
@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateGroupService templateGroupService;


    /**
     * 跳转到 模板管理 页面
     */
    @GetMapping("/info")
    public String template(Model model) {
        model.addAttribute("group", templateGroupService.getList());
        return "template/template";
    }


    /**
     * 跳转到 模板新增 页面
     */
    @GetMapping("/add")
    public String templateAdd(Model model) {
        model.addAttribute("group", templateGroupService.getList(new TemplateGroupQueryBean()));
        return "template/template_add";
    }


    /**
     * 跳转到 模板修改 页面
     */
    @GetMapping("/edit/{id}")
    public String templateUpdate(@PathVariable("id") Long id, Model model) {
        TemplateBean bean = CopyDataUtil.copyObject(templateService.getById(id), TemplateBean.class);
        model.addAttribute("group", templateGroupService.getList());
        model.addAttribute("template", bean);
        return "template/template_edit";
    }


    /**
     * 获取所有数据
     *
     * @param dto TemplateBean
     * @return ResultData<Page<TemplateBean>>
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public ResultData<Page<TemplateBean>> getList(@RequestBody TemplateQueryBean dto) {
        return ResultData.createSelectSuccessResult(createPageInfo(templateService.getList(dto)));
    }


    /**
     * 添加
     *
     * @param dto TemplateDto
     * @return ResultData<TemplateBean>
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public ResultData<TemplateBean> insert(@RequestBody TemplateBean dto) {
        return ResultData.createInsertResult(templateService.insert(dto));
    }

    /**
     * 更新
     *
     * @param dto TemplateDto
     * @return ResultData<TemplateBean>
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public ResultData<TemplateBean> update(@RequestBody TemplateBean dto) {
        return ResultData.createUpdateResult(templateService.update(dto));
    }


    /**
     * 删除
     *
     * @param id 主键id
     * @return ResultData<String>
     */
    @PostMapping(value = "/remove")
    @ResponseBody
    public ResultData<Void> delete(@RequestBody IdBean id) {
        return ResultData.createDeleteResult(templateService.delete(id.getId()));
    }

}
