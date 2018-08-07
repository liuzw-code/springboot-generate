/**
 * 初始化模板管理详情对话框
 */
var TemplateInfoDlg = {
    TemplateInfoData: {}
};

/**
 * 清除数据
 */
TemplateInfoDlg.clearData = function () {
    this.TemplateInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 */
TemplateInfoDlg.set = function (key) {
    var v = this.get(key);
    if (typeof v !== "undefined") {
        this.TemplateInfoData[key] = v;
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 */
TemplateInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
TemplateInfoDlg.close = function () {
    parent.layer.close(window.parent.Template.layerIndex);
};

/**
 * 收集数据
 */
TemplateInfoDlg.collectData = function () {
    this.set('id').set('templateName').set('templateDesc').set('templateFileName')
        .set('groupId').set('templateType').set('templatePath').set("templateContent");

};

/**
 * 提交添加
 */
TemplateInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Root.ctxPath + "/template/add", function (data) {
        Root.success("添加成功!");
        window.parent.Template.table.refresh();
        TemplateInfoDlg.close();
    }, function (data) {
        Root.error("添加失败!" + data.message + "!");
    });
    ajax.setData(this.TemplateInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
TemplateInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Root.ctxPath + "/template/edit", function (data) {
        Root.success("修改成功!");
        window.parent.Template.table.refresh();
        TemplateInfoDlg.close();
    }, function (data) {
        Root.error("修改失败!" + data.message + "!");
    });
    ajax.setData(this.TemplateInfoData);
    ajax.start();
};

$(function () {
    var type = $("#templateType").val().trim();
    type = type === '' ? 'java' : type;
    $("#codeFile").find('code').html(hljs.highlight(type, $("#templateContent").val()).value);
});

$("#codeFile").click(function () {
    $(this).hide();
    $("#templateContent").show();
});

$("#templateType").change(function () {
    var type = $("#templateType").val().trim();
    type = type === '' ? 'java' : type;
    $("#codeFile").find('code').html(hljs.highlight(type, $("#templateContent").val()).value);
});

$("#templateContent").blur(function () {
    $(this).hide();
    $("#codeFile").find('code').html(hljs.highlight($("#templateType").val(), $(this).val()).value);
    $("#codeFile").show();
});