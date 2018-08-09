/**
 * 初始化详情对话框
 */
var TemplateEngineInfoDlg = {
    TemplateEngineInfoData: {},
    validateFields: {
        engineName: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        engineDesc: {
            validators: {
                notEmpty: {
                    message: '描述不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
TemplateEngineInfoDlg.clearData = function () {
    this.TemplateEngineInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TemplateEngineInfoDlg.set = function (key) {
    var v = this.get(key);
    if (typeof v !== "undefined") {
        this.TemplateEngineInfoData[key] = v;
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 */
TemplateEngineInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
TemplateEngineInfoDlg.close = function () {
    parent.layer.close(window.parent.TemplateEngine.layerIndex);
};

/**
 * 收集数据
 */
TemplateEngineInfoDlg.collectData = function () {
    this.set('id').set("engineName").set("engineDesc");
};

/**
 * 验证数据是否为空
 */
TemplateEngineInfoDlg.validate = function () {
    $('#templateEngineForm').data("bootstrapValidator").resetForm();
    $('#templateEngineForm').bootstrapValidator('validate');
    return $("#templateEngineForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TemplateEngineInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Root.ctxPath + "/templateEngine/add", function (data) {
        Root.success("添加成功!");
        window.parent.TemplateEngine.table.refresh();
        TemplateEngineInfoDlg.close();
    }, function (data) {
        Root.error("添加失败!" + data.message + "!");
    });
    ajax.setData(this.TemplateEngineInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TemplateEngineInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Root.ctxPath + "/templateEngine/edit", function (data) {
        Root.success("修改成功!");
        window.parent.TemplateEngine.table.refresh();
        TemplateEngineInfoDlg.close();
    }, function (data) {
        Root.error("修改失败!" + data.message + "!");
    });
    ajax.setData(this.TemplateEngineInfoData);
    ajax.start();
}

$(function () {
    Root.initValidator("templateEngineForm", TemplateEngineInfoDlg.validateFields);
});
