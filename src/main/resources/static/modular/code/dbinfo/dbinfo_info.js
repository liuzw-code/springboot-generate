/**
 * 初始化数据库管理详情对话框
 */
var DbInfoInfoDlg = {
    DbInfoInfoData: {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: "名称不能为空"
                }
            }
        },
        dbUrl: {
            validators: {
                notEmpty: {
                    message: "链接不能为空"
                }
            }
        },
        dbPassword: {
            validators: {
                notEmpty: {
                    message: "密码不能为空"
                }
            }
        },
        dbUsername: {
            validators: {
                notEmpty: {
                    message: "用户名名称不能为空"
                }
            }
        }
    }
};

/**
 * 清除数据
 */
DbInfoInfoDlg.clearData = function () {
    this.DbInfoInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 */
DbInfoInfoDlg.set = function (key) {
    var v = this.get(key);
    if (typeof v !== "undefined") {
        this.DbInfoInfoData[key] = v;
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 */
DbInfoInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
DbInfoInfoDlg.close = function () {
    parent.layer.close(window.parent.DbInfo.layerIndex);
};

/**
 * 收集数据
 */
DbInfoInfoDlg.collectData = function () {
    this.set("id").set("name").set("dbType").set("dbUrl").set("dbUsername").set("dbPassword");
};

/**
 * 验证数据是否为空
 */
DbInfoInfoDlg.validate = function () {
    $("#dbinfoForm").data("bootstrapValidator").resetForm();
    $("#dbinfoForm").bootstrapValidator("validate");
    return $("#dbinfoForm").data("bootstrapValidator").isValid();
};
/**
 * 提交添加
 */
DbInfoInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Root.ctxPath + "/databaseInfo/add", function (data) {
        Root.success("添加成功!");
        window.parent.DbInfo.table.refresh();
        DbInfoInfoDlg.close();
    }, function (data) {
        Root.error("添加失败!" + data.message + "!");
    });
    ajax.clear();
    ajax.setData(this.DbInfoInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
DbInfoInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Root.ctxPath + "/databaseInfo/edit", function (data) {
        Root.success("修改成功!");
        window.parent.DbInfo.table.refresh();
        DbInfoInfoDlg.close();
    }, function (data) {
        Root.error("修改失败!" + data.message + "!");
    });
    ajax.setData(this.DbInfoInfoData);
    ajax.start();
};

$(function () {
    Root.initValidator("dbinfoForm", DbInfoInfoDlg.validateFields);
});
