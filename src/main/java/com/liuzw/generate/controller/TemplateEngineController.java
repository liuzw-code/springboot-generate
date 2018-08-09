package com.liuzw.generate.controller;


import com.liuzw.generate.bean.*;
import com.liuzw.generate.service.TemplateEngineService;
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
@RequestMapping("/templateEngine")
public class TemplateEngineController extends BaseController {

    @Autowired
    private TemplateEngineService templateEngineService;


    /**
     * 跳转到 模板组 页面
     */
    @GetMapping("/info")
    public String templateEngine() {
        return "engine/templateEngine";
    }

    /**
     * 跳转到 模板组新增 页面
     */
    @GetMapping("/add")
    public String templateEngineAdd() {
        return "engine/templateEngine_add";
    }

    /**
     * 跳转到 模板组修改 页面
     */
    @GetMapping(value = "/edit/{id}")
    public String templateEngineUpdate(@PathVariable("id") Long id, Model model) {
        TemplateEngineBean bean = CopyDataUtil.copyObject(templateEngineService.getById(id), TemplateEngineBean.class);
        model.addAttribute("templateEngine", bean);
        return "engine/templateEngine_edit";
    }


    /**
     * 获取所有数据
     *
     * @param dto TemplateEngineBean
     * @return ResultData<Page<TemplateEngineBean>>
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public ResultData<Page<TemplateEngineBean>> getList(@RequestBody TemplateEngineQueryBean dto) {
        return ResultData.createSelectSuccessResult(convertPageInfo(templateEngineService.getList(dto), TemplateEngineBean.class));
    }


    /**
     * 添加
     *
     * @param dto TemplateEngineDto
     * @return ResultData<TemplateEngineBean>
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public ResultData<TemplateEngineBean> insert(@RequestBody TemplateEngineBean dto) {
        return ResultData.createInsertResult(templateEngineService.insert(dto));
    }

    /**
     * 更新
     *
     * @param dto TemplateEngineDto
     * @return ResultData<TemplateEngineBean>
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public ResultData<TemplateEngineBean> update( @RequestBody TemplateEngineBean dto) {
        return ResultData.createUpdateResult(templateEngineService.update(dto));
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
        return ResultData.createDeleteResult(templateEngineService.delete(id.getId()));
    }

}
