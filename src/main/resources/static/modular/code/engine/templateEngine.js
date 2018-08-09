/**
 * 管理初始化
 */
var TemplateEngine = {
    id: "TemplateEngineTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TemplateEngine.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '组名称', field: 'engineName', align: 'center', valign: 'middle'},
        {title: '描述', field: 'engineDesc', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
TemplateEngine.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length === 0) {
        Root.info("请先选中表格中的某一记录！");
        return false;
    } else {
        TemplateEngine.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
TemplateEngine.openAddTemplateEngine = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Root.ctxPath + '/templateEngine/add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
TemplateEngine.openTemplateEngineDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Root.ctxPath + '/templateEngine/edit/'
            + TemplateEngine.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 删除
 */
TemplateEngine.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Root.ctxPath + "/templateEngine/remove", function (data) {
            Root.success("删除成功!");
            TemplateEngine.table.refresh();
        }, function (data) {
            Root.error("删除失败!" + data.message + "!");
        });
        ajax.setData({"id": this.seItem.id});
        ajax.start();
    }
};

TemplateEngine.formParams = function () {
    return {};
};

/**
 * 查询列表
 */
TemplateEngine.search = function () {
    var queryData = {};
    queryData['engineName'] = $("#engineName").val();
    queryData['engineDesc'] = $("#engineDesc").val();
    TemplateEngine.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TemplateEngine.initColumn();
    var table = new BSTable(TemplateEngine.id, "/templateEngine/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(TemplateEngine.formParams());
    TemplateEngine.table = table.init();
});
