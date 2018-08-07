/**
 * 数据库管理管理初始化
 */
var DbInfo = {
    id: "DbInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DbInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '名称', field: 'name', align: 'center', valign: 'middle'},
        {title: '数据库驱动', field: 'dbDriver', align: 'center', valign: 'middle'},
        {title: '数据库地址', field: 'dbUrl', align: 'center', valign: 'middle'},
        {title: '数据库账户', field: 'dbUsername', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
DbInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length === 0) {
        Root.info("请先选中表格中的某一记录！");
        return false;
    } else {
        DbInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据库管理
 */
DbInfo.openAddDbInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加数据库管理',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: '/databaseInfo/add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据库管理详情
 */
DbInfo.openDbInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '数据库管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: '/databaseInfo/edit/' + DbInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据库管理
 */
DbInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax("/databaseInfo/remove", function (data) {
            Root.success("删除成功!");
            DbInfo.table.refresh();
        }, function (data) {
            Root.error("删除失败!" + data.message + "!");
        });
        ajax.setData({"id": this.seItem.id});
        ajax.start();
    }
};

DbInfo.formParams = function () {
    return {};
};

/**
 * 查询数据库管理列表
 */
DbInfo.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['dbUrl'] = $("#dbUrl").val();
    DbInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DbInfo.initColumn();
    var table = new BSTable(DbInfo.id, "/databaseInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(DbInfo.formParams());
    DbInfo.table = table.init();
});
