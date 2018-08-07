/**
 * 初始化生成参数详情对话框
 */
var GenParamInfoDlg = {
    GenParamInfoData: {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '别名不能为空'
                },
                stringLength: {
                    /*长度提示*/
                    min: 3,
                    max: 20,
                    message: '别名长度必须在3到20之间'
                }
            }
        },
        author: {
            validators: {
                notEmpty: {
                    message: '作者不能为空'
                },
                stringLength: {
                    /*长度提示*/
                    min: 3,
                    max: 20,
                    message: '用户名长度必须在3到20之间'
                }
            }
        },
        packageName: {
            validators: {
                notEmpty: {
                    message: '代码目录不能为空'
                },
                regexp: {
                    /* 只需加此键值对，包含正则表达式，和提示 */
                    regexp: /^[a-zA-Z0-9\.]+$/,
                    message: '只能是数字和字母和.'
                },
                stringLength: {
                    /*长度提示*/
                    min: 1,
                    max: 100,
                    message: '代码目录长度必须在1到100之间'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
GenParamInfoDlg.clearData = function () {
    this.GenParamInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @params key 数据的名称
 */
GenParamInfoDlg.set = function (key) {
    var v = this.get(key);
    if (typeof v !== "undefined") {
        this.GenParamInfoData[key] = v;
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @params key 数据的名称
 */
GenParamInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
GenParamInfoDlg.close = function () {
    parent.layer.close(window.parent.Param.layerIndex);
};
/**
 * 收集数据
 */
GenParamInfoDlg.collectData = function () {
    this.set('id').set("name").set("author").set("packageName")
        .set("moduleName").set("localPath").set("copyright");
};

/**
 * 验证数据
 */
GenParamInfoDlg.validate = function () {
    $('#paramsForm').data("bootstrapValidator").resetForm(true);
    $('#paramsForm').bootstrapValidator('validate');
    return $("#paramsForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
GenParamInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Root.ctxPath + "/params/add", function (data) {
        Root.success("添加成功!");
        window.parent.Param.table.refresh();
        GenParamInfoDlg.close();
    }, function (data) {
        Root.error("添加失败!" + data.message + "!");
    });
    ajax.setData(this.GenParamInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
GenParamInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();
    // if (!this.validate()) {
    //     return;
    // }
    //提交信息
    var ajax = new $ax(Root.ctxPath + "/params/edit", function (data) {
        Root.success("修改成功!");
        window.parent.Param.table.refresh();
        GenParamInfoDlg.close();
    }, function (data) {
        Root.error("修改失败!" + data.message + "!");
    });
    ajax.setData(this.GenParamInfoData);
    ajax.start();
};

$(function () {
    Root.initValidator("paramsForm", GenParamInfoDlg.validateFields);
});
