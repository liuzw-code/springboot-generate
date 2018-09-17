/**
 * 初始化详情对话框
 */
var TemplateGroupInfoDlg = {
    TemplateGroupInfoData: {},
    validateFields: {
        groupName: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        groupDesc: {
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
TemplateGroupInfoDlg.clearData = function () {
    this.TemplateGroupInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TemplateGroupInfoDlg.set = function (key) {
    var v = this.get(key);
    if (typeof v !== "undefined") {
        this.TemplateGroupInfoData[key] = v;
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 */
TemplateGroupInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
TemplateGroupInfoDlg.close = function () {
    parent.layer.close(window.parent.TemplateGroup.layerIndex);
};

/**
 * 收集数据
 */
TemplateGroupInfoDlg.collectData = function () {
    this.set('id').set("groupName").set("groupDesc");
};

/**
 * 验证数据是否为空
 */
TemplateGroupInfoDlg.validate = function () {
    $('#templateGroupForm').data("bootstrapValidator").resetForm();
    $('#templateGroupForm').bootstrapValidator('validate');
    return $("#templateGroupForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TemplateGroupInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Root.ctxPath + "/templateGroup/add", function (data) {
        Root.success("添加成功!");
        window.parent.TemplateGroup.table.refresh();
        TemplateGroupInfoDlg.close();
    }, function (data) {
        Root.error("添加失败!" + data.message + "!");
    });
    ajax.setData(this.TemplateGroupInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TemplateGroupInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Root.ctxPath + "/templateGroup/edit", function (data) {
        Root.success("修改成功!");
        window.parent.TemplateGroup.table.refresh();
        TemplateGroupInfoDlg.close();
    }, function (data) {
        Root.error("修改失败!" + data.message + "!");
    });
    ajax.setData(this.TemplateGroupInfoData);
    ajax.start();
}

$(function () {
    Root.initValidator("templateGroupForm", TemplateGroupInfoDlg.validateFields);
});
