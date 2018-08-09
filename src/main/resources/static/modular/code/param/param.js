/**
 * 生成参数管理初始化
 */
var Param = {
    id: "GenParamTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Param.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '别名', field: 'name', align: 'center', valign: 'middle'},
        {title: '作者', field: 'author', align: 'center', valign: 'middle'},
        {title: '包名', field: 'packageName', align: 'center', valign: 'middle'},
        {title: '本地路径', field: 'localPath', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'updateTime', align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Param.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length === 0) {
        Root.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Param.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加生成参数
 */
Param.openAddGenParam = function () {
    var index = layer.open({
        type: 2,
        title: '添加生成参数',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Root.ctxPath + '/params/add'
    });
    this.layerIndex = index;
};

/**
 * 修改
 */
Param.openGenParamUpdate = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Root.ctxPath + '/params/edit/' + Param.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除生成参数
 */
Param.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Root.ctxPath + "/params/remove", function (data) {
            Root.success("删除成功!");
            Param.table.refresh();
        }, function (data) {
            Root.error("删除失败!" + data.message + "!");
        });
        ajax.setData({"id": this.seItem.id});
        ajax.start();
    }
};

Param.formParams = function () {
    return {};
};

/**
 * 查询生成参数列表
 */
Param.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    Param.table.refresh({query: queryData});
};

$(function () {
    var table = new BSTable(Param.id, Root.ctxPath + "/params/list", Param.initColumn());
    table.setPaginationType("server");
    table.setQueryParams(Param.formParams());
    Param.table = table.init();
});
