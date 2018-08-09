/**
 * 生成参数管理初始化
 */
var Gen = {
    id: "GenTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Gen.initColumn = function () {
    return [
        {field: 'selectItem', checkBox: true},
        {title: '表名', field: 'tableName', align: 'center', valign: 'middle'},
        {title: 'Engine', field: 'engine', align: 'center', valign: 'middle'},
        {title: '表备注', field: 'tableComment', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
Gen.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length === 0) {
        Root.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Gen.seItem = selected;
        return true;
    }
};


/**
 * 修改
 */
Gen.openGen = function () {
    if (this.check()) {
        var tableNames = '';
        for (var index in Gen.seItem)  {
            tableNames += Gen.seItem[index].tableName + ',';
        }

        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Root.ctxPath + "/gen/gen/" + $("#genId").val() + "/" + tableNames
        });
        this.layerIndex = index;
    }
};


Gen.formParams = function () {
    return {};
};

/**
 * 查询生成参数列表
 */
Gen.search = function () {
    var queryData = {};
    queryData['tableName'] = $("#tableName").val();
    queryData['id'] = $("#genId").val();
    Gen.table.refresh({query: queryData});
};

$(function () {
    var table = new BSTable(Gen.id, Root.ctxPath + "/gen/list", Gen.initColumn());
    table.setPaginationType("server");
    table.setQueryParams({"id":$("#genId").val()});
    Gen.table = table.init();
});


$("#genId").change(function () {
    Gen.search();
});