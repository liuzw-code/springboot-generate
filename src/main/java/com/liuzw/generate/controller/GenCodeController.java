package com.liuzw.generate.controller;

import com.liuzw.generate.aop.TargetDataSource;
import com.liuzw.generate.bean.*;
import com.liuzw.generate.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 代码生成
 *
 * @author liuzw
 * @date 2018/8/1 15:42
 **/
@Controller
@RequestMapping("gen")
public class GenCodeController extends BaseController {

    @Autowired
    private TemplateGroupService templateGroupService;

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private DatabaseInfoService databaseInfoService;

    @Autowired
    private ColumnService columnService;

    @Autowired
    private GenCodeService genCodeService;

    /**
     * 跳转到 首页 页面
     */
    @GetMapping("/info")
    public String gen(Model model) {
        model.addAttribute("databaseList", databaseInfoService.getList());
        return "gen/gen";
    }

    /**
     * 跳转到生成代码页面
     *
     * @param databaseId  数据源 id
     * @param tableNames  表名
     */
    @GetMapping("/gen/{databaseId}/{tableNames}")
    public String genDetail(Model model,
                            @PathVariable("databaseId") Long databaseId,
                            @PathVariable("tableNames") String tableNames) {
        if (StringUtils.isNotEmpty(tableNames)) {
            tableNames = tableNames.substring(0, tableNames.lastIndexOf(","));
            model.addAttribute("tableNames", tableNames);
        }
        model.addAttribute("databaseId", databaseId);
        model.addAttribute("group", templateGroupService.getList());
        model.addAttribute("params", paramsService.getList());

        return "gen/gen_detail";
    }



    @TargetDataSource
    @PostMapping("/list")
    @ResponseBody
    public ResultData<Page<TableBean>> list(@RequestBody GenQueryBean bean) {
        List<TableBean> list = columnService.getList(bean);
        return ResultData.createSelectSuccessResult(createPageInfo(list));
    }


    @TargetDataSource
    @PostMapping("/genCode")
    @ResponseBody
    public ResultData<Boolean> genCode(@RequestBody GenCodeBean bean) {
        Boolean flag = genCodeService.genCode(null, bean);
        if (flag) {
            return ResultData.createSuccessResult(null, "代码生成成功");
        } else {
            return ResultData.createErrorResult("代码生成错误");
        }
    }

    @TargetDataSource
    @GetMapping("/genCode")
    public void genCode(HttpServletResponse response, GenCodeBean bean) {
        genCodeService.genCode(response, bean);
    }

}
