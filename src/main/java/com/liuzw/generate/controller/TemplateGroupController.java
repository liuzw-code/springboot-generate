package com.liuzw.generate.controller;


import com.liuzw.generate.bean.*;
import com.liuzw.generate.service.TemplateGroupService;
import com.liuzw.generate.utils.CopyDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 模板组管理
 *
 * @author liuzw
 * @version V1.0
 **/

@Controller
@RequestMapping("/templateGroup")
public class TemplateGroupController extends BaseController {

    @Autowired
    private TemplateGroupService templateGroupService;


    /**
     * 跳转到 模板组 页面
     */
    @GetMapping("/info")
    public String templateGroup() {
        return "group/templateGroup";
    }

    /**
     * 跳转到 模板组新增 页面
     */
    @GetMapping("/add")
    public String templateGroupAdd() {
        return "group/templateGroup_add";
    }

    /**
     * 跳转到 模板组修改 页面
     */
    @GetMapping(value = "/edit/{id}")
    public String templateGroupUpdate(@PathVariable("id") Long id, Model model) {
        TemplateGroupBean bean = CopyDataUtil.copyObject(templateGroupService.getById(id), TemplateGroupBean.class);
        model.addAttribute("templateGroup", bean);
        return "group/templateGroup_edit";
    }


    /**
     * 获取所有数据
     *
     * @param dto TemplateGroupBean
     * @return ResultData<Page   <   TemplateGroupBean>>
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public ResultData<Page<TemplateGroupBean>> getList(@RequestBody TemplateGroupQueryBean dto) {
        return ResultData.createSelectSuccessResult(convertPageInfo(templateGroupService.getList(dto), TemplateGroupBean.class));
    }


    /**
     * 添加
     *
     * @param dto TemplateGroupDto
     * @return ResultData<TemplateGroupBean>
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public ResultData<TemplateGroupBean> insert(@RequestBody TemplateGroupBean dto) {
        return ResultData.createInsertResult(templateGroupService.insert(dto));
    }

    /**
     * 更新
     *
     * @param dto TemplateGroupDto
     * @return ResultData<TemplateGroupBean>
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public ResultData<TemplateGroupBean> update( @RequestBody TemplateGroupBean dto) {
        return ResultData.createUpdateResult(templateGroupService.update(dto));
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
        return ResultData.createDeleteResult(templateGroupService.delete(id.getId()));
    }

}
