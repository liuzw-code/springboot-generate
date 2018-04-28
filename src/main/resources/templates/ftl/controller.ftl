package ${data.packagePath}.${data.module}.controller;


import ${data.packagePath}.${data.module}.bean.${data.className};
import ${data.packagePath}.${data.module}.service.${data.className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuzw
 * @version V1.0
 **/
<#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
<#assign pkProperty=data.pkColumns[0].propertyName!"">
<#assign pkPropertyType=data.pkColumns[0].propertyType!"">
@RestController
@RequestMapping("/${data.varName}")
@Api(description = "管理")
@Validated
public class ${data.className}Controller extends BaseController {

    @Autowired
    private ${data.className}Service ${data.varName}Service;


    /**
     *  获取所有数据
     *
     * @param dto  ${data.className}ReqDto
     * @return     ResultData<PageInfo<${data.className}ResDto>>
     */
     @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
     @PostMapping(value = "/${data.varName}/list",  produces = MediaType.APPLICATION_JSON_VALUE)
     public ResultData<PageInfo<${data.className}ResDto>> getList(@Valid @RequestBody ${data.className}QueryDto dto) {
         return  ResultData.createSuccessResult(convertListByPageInfo(${data.varName}Service.getList(dto), ${data.className}ResDto.class));
     }

    /**
     *  获取信息
     *
     * @param ${pkProperty}    主键id
     * @return     ResultData<${data.className}ResDto>
     */
     @ApiOperation(value = "查询指定数据", notes = "查询指定数据")
     @ApiImplicitParams({@ApiImplicitParam(name = "${pkProperty}", value = "数据Id", paramType = "query", dataType = "${pkPropertyType}")})
     @GetMapping(value = "/${data.varName}/query",  produces = MediaType.APPLICATION_JSON_VALUE)
     @RequestProcess(popedomCode = "mpys:${data.varName}", popedomType = PopedomType.View)
     public ResultData<${data.className}ResDto> query(Long id) {
        return ResultData.createQuerySuccessResult(CopyDataUtil.copyObject(${data.varName}Service.getById(idDto.getId()), ${data.className}ResDto.class));
     }


    /**
     *  添加
     *
     * @param dto   ${data.className}
     * @return      ResultData<${data.className}ReqDto>
     */
     @ApiOperation(value = "增加数据", notes = "增加数据")
     @PostMapping(value = "/${data.varName}/add",  produces = MediaType.APPLICATION_JSON_VALUE)
     @RequestProcess(popedomCode = "mpys:${data.varName}", popedomType = PopedomType.Insert, saveResult = true)
     public ResultData<${data.className}ReqDto> insert(@Valid @RequestBody ${data.className}ReqDto dto) {
        return ResultData.createAddResult(${data.varName}Service.insert(dto), dto);
     }

    /**
     *  更新
     *
     * @param dto  ${data.className}
     * @return     ResultData<${data.className}ReqDto>
     */
     @ApiOperation(value = "更新数据", notes = "更新数据")
     @PostMapping(value = "/${data.varName}/edit",  produces = MediaType.APPLICATION_JSON_VALUE)
     @RequestProcess(popedomCode = "mpys:${data.varName}", popedomType = PopedomType.Update, saveResult = true)
     public ResultData<${data.className}ReqDto> update(@Validated({MustId.class}) @RequestBody ${data.className}ReqDto dto) {
         return ResultData.createUpdateResult(${data.varName}Service.update(dto), dto);
     }


     /**
      *  删除
      *
      * @param  idDto  主键id
      * @return        ResultData<String>
      */
      @ApiOperation(value = "删除数据", notes = "删除数据")
      @PostMapping(value = "/${data.varName}/remove",  produces = MediaType.APPLICATION_JSON_VALUE)
      @RequestProcess(popedomCode = "mpys:${data.varName}", popedomType = PopedomType.Delete, saveResult = true)
      public ResultData<Void> delete(@RequestBody IdDto idDto) {
         return ResultData.createDeleteResult(${data.varName}Service.delete(idDto.getId()));
      }

}
