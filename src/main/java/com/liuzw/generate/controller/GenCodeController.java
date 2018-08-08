package com.liuzw.generate.controller;

import com.liuzw.generate.aop.TargetDataSource;
import com.liuzw.generate.bean.*;
import com.liuzw.generate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param databaseId 数据源 id
     * @param tableNames 表名
     */
    @GetMapping("/gen/{databaseId}/{tableNames}")
    public String genDetail(Model model,
                            @PathVariable("databaseId") Long databaseId,
                            @PathVariable("tableNames") String tableNames) {
        tableNames = tableNames.substring(0, tableNames.lastIndexOf(","));
        model.addAttribute("tableNames", tableNames);
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


    /**
     * 生成代码到本地
     *
     * @param bean 基本参数
     * @return ResultData<Boolean>
     */

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

    /**
     * 下载代码
     *
     */
    @TargetDataSource
    @GetMapping("/downloadCode")
    public void downloadCode(HttpServletRequest request, HttpServletResponse response) {

        String tableName = request.getParameter("tableNames");
        String templateIds = request.getParameter("templateIds");

        ParamsBean param = ParamsBean.builder()
                .author(request.getParameter("author"))
                .copyright(request.getParameter("copyright"))
                .packageName(request.getParameter("packageName"))
                .build();

        GenCodeBean bean = GenCodeBean.builder()
                .databaseId(Long.valueOf(request.getParameter("databaseId")))
                .params(param)
                .tableNames(Arrays.stream(tableName.split(",")).collect(Collectors.toList()))
                .templateIds(Arrays.stream(templateIds.split(",")).map(Long::valueOf).collect(Collectors.toList()))
                .build();

        genCodeService.genCode(response, bean);
    }

}
