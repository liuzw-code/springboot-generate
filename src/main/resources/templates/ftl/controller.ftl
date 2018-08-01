package ${data.packagePath}.${data.module}.controller;




import ${data.packagePath}.${data.module}.bean.Id;
import ${data.packagePath}.${data.module}.bean.Page;
import ${data.packagePath}.${data.module}.bean.ResultData;
import ${data.packagePath}.${data.module}.utils.CopyDataUtil;
import ${data.packagePath}.${data.module}.bean.${data.className};
import ${data.packagePath}.${data.module}.service.${data.className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuzw
 **/
<#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
<#assign pkProperty=data.pkColumns[0].propertyName!"">
<#assign pkPropertyType=data.pkColumns[0].propertyType!"">
@RestController
@RequestMapping("/${data.varName}")
@Api(description = "管理")
public class ${data.className}Controller extends BaseController {

    @Autowired
    private ${data.className}Service ${data.varName}Service;


    /**
     *  获取所有数据
     *
     * @param bean  ${data.className}Bean
     * @return     ResultData<Page<${data.className}Bean>>
     */
     @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
     @PostMapping(value = "/${data.varName}/list")
     public ResultData<Page<${data.className}Bean>> getList(@RequestBody ${data.className}QueryBean bean) {
         return  ResultData.createSelectSuccessResult(convertPageInfo(${data.varName}Service.getList(bean), ${data.className}Bean.class));
     }

    /**
     *  获取信息
     *
     * @param ${pkProperty}    主键id
     * @return     ResultData<${data.className}Bean>
     */
     @ApiOperation(value = "查询指定数据", notes = "查询指定数据")
     @PostMapping(value = "/${data.varName}/getById")
     public ResultData<${data.className}Bean> getById(@RequestBody Id id) {
        if (id.getId() == null) {
            return ResultData.createErrorResult("id 不能为空");
        }
        return ResultData.createSelectSuccessResult(CopyDataUtil.copyObject(${data.varName}Service.getById(id.getId()), ${data.className}Bean.class));
     }


    /**
     *  添加
     *
     * @param bean   ${data.className}
     * @return      ResultData<${data.className}Bean>
     */
     @ApiOperation(value = "增加数据", notes = "增加数据")
     @PostMapping(value = "/${data.varName}/add")
     public ResultData<${data.className}Bean> insert(@RequestBody ${data.className}Bean bean) {
        return ResultData.createInsertResult(${data.varName}Service.insert(bean));
     }

    /**
     *  更新
     *
     * @param bean  ${data.className}
     * @return     ResultData<${data.className}Bean>
     */
     @ApiOperation(value = "更新数据", notes = "更新数据")
     @PostMapping(value = "/${data.varName}/edit")
     public ResultData<${data.className}Bean> update(@RequestBody ${data.className}Bean bean) {
         return ResultData.createUpdateResult(${data.varName}Service.update(bean));
     }


     /**
      *  删除
      *
      * @param  idDto  主键id
      * @return        ResultData<String>
      */
      @ApiOperation(value = "删除数据", notes = "删除数据")
      @PostMapping(value = "/${data.varName}/remove")
      public ResultData<Void> delete(@RequestBody Id id) {
         if (id.getId() != null) {
             return ResultData.createErrorResult("id 不能为空");
         }
         return ResultData.createDeleteResult(${data.varName}Service.delete(id.getId()));
      }

       /**
       *  批量删除
       *
       * @param  id     主键id
       * @return        ResultData<Void>
       */
       @ApiOperation(value = "删除数据", notes = "删除数据")
       @PostMapping(value = "/${data.varName}/batchRemove")
       public ResultData<Void> batchRemove(@RequestBody Id ids) {
          if (StringUtils.isEmpty(ids.getIds())) {
              return ResultData.createErrorResult("id 不能为空");
          }
          return ResultData.createDeleteResult(${data.varName}Service.batchRemove(id.getIds()));
       }
}
