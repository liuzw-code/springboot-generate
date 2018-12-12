# SpringBoot-generate

### 简介
    注意此项目只用于内部服务器！！！！
    因为涉及到数据库账号密码信息.

### 简介
    为了在开发过程中不在重复的编写CRUD,为了提高开发效率,
    于是就开发了一套基于springboot的代码生成器。

### SpringBoot-generate 相关介绍
    1. 底层框架使用的是SpringBoot.
    2. ORM框架选择的是Mybaits + 通用Mapper.
    3. 前端页面选择的是Thymeleaf模板.
    4. 配置动态数据源,支持动态的添加数据源和切换数据源,不需要重启服务.
    5. 目前代码生成器支持的模板为Freemarker.
    6. 代码生成的时候支持浏览器下载和生成到本地.

### 使用

    下载项目SpringBoot-generate(https://github.com/liu1235/springboot-generate.git)
    然后运行GenerateApplication.运行成功之后访问：http://localhost:8888/

   * 进入主页之后，首先配置数据库连接。

   ![数据库连接](https://github.com/liu1235/springboot-generate/raw/master/image/databaseList.png)

    然后点击添加按钮，进行添加提交

   ![数据库连接](https://github.com/liu1235/springboot-generate/raw/master/image/database_add.png)


   * 接着配置模板组。

   ![模板组](https://github.com/liu1235/springboot-generate/raw/master/image/group_list.png)

    然后点击添加按钮，进行添加提交

   ![模板组](https://github.com/liu1235/springboot-generate/raw/master/image/group_add.png)


   * 接着配置模板。

   ![模板](https://github.com/liu1235/springboot-generate/raw/master/image/template_list.png)

    最下面得框是填写生成代码的模板, 目前只支持Freemarker。
    然后点击添加按钮，进行添加提交

   ![模板](https://github.com/liu1235/springboot-generate/raw/master/image/template_add.png)

   * 接着配置基本参数。

   ![基本参数](https://github.com/liu1235/springboot-generate/raw/master/image/params_list.png)

   然后点击添加按钮，进行添加提交

   ![基本参数](https://github.com/liu1235/springboot-generate/raw/master/image/params_add.png)

   * 最后选择生成代码菜单。

   ![生成代码](https://github.com/liu1235/springboot-generate/raw/master/image/gen_list.png)

    上图可以看到数据源的下拉菜单, 当选者不同的数据源时系统会自动切换数据源,显示相应的列表信息.
    然后在勾选要生成的表之后点击详情,进行一些基本的参数设置.

   ![生成代码](https://github.com/liu1235/springboot-generate/raw/master/image/gen.png)

    选择参数后,其他的参数会自动带出. 参数是不能为空的.除了本地路径
    这里说明一下本地路径:
       1. 如果你的系统是windows, 当本地路径没有填写时,此时生成的代码会通过浏览器下载,反之则生成到你填写的路径下.
       2. 如果你的系统不是windows, 无论本地路径有没有填写,生成的代码都会通过浏览器下载



### 模板配置说明

    1. 模板目前只支持Freemarker模板。
    2. 模板中的参数有哪些.
    3. 如何扩展如何扩展（待定）


* 模板中的参数

   生成模板的所有参数的类`BasicDataBean`, 在模板中使用的时候参数为 `data`
   如：获取表名, Freemarker写法: `${data.tableName}`
      获取第一个字段的数据库字段名, Freemarker写法: `${data.columns[0].columnName}`


    |     参数      |   类型  |  说明           |
    |  -------------| -------   | ------------   |
    | `tableName`   |  `String`   |  表名           |
    | `className`   |  `String`   |  类名           |
    | `classVarName`|  `String`   |  类变量名      |
    | `packageName` |  `String`   |  包名          |
    | `author`      |  `String`   |  作者            |
    | `copyright`   |  `String`   |  版权信息        |
    | `columns`     |  `List<ColumnBean>`   |  字段列(包含主键) |
    | `pkColumns`   |  `List<ColumnBean>`   |  主键列表        |


    其中`ColumnBean` 参数为

    |        参数           |   类型    |  说明             |
    |  --------------      | -------   | ------------     |
    | `columnName`         |  `String`   |  数据库字段名称    |
    | `columnType`         |  `String`   |  数据库字段类型    |
    | `columnComment`      |  `String`   |  字段描述内容      |
    | `javaFieldName`      |  `String`   |  java属性名称      |
    | `javaType`           |  `String`   |  java类型          |
    | `javaFieldComment`   |  `String`   |  java属性注释内容  |
    | `jdbcType`           |  `String`   |  数据库字段类型    |
    | `isNullable`         |  `String`   |  是否必填          |
    
    
    
    
