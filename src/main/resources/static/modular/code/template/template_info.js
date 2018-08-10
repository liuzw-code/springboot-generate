/**
 * 初始化模板管理详情对话框
 */
var TemplateInfoDlg = {
    TemplateInfoData: {},
    validateFields: {
        templateName: {
            validators: {
                notEmpty: {
                    message: '模板名称不能为空'
                },
                stringLength: {
                    /*长度提示*/
                    min: 3,
                    max: 20,
                    message: '模板名称长度必须在3到20之间'
                }
            }
        },
        templateFileName: {
            validators: {
                notEmpty: {
                    message: '模板文件名不能为空'
                },
                regexp: {
                    /* 只需加此键值对，包含正则表达式，和提示 */
                    regexp: /^[a-zA-Z]+[.][a-zA-Z]+$/,
                    message: '必须以字母开头,有且只能有一个. 格式类似于：service.java'
                },
                stringLength: {
                    /*长度提示*/
                    min: 3,
                    max: 20,
                    message: '模板文件名长度必须在3到20之间'
                }
            }
        },
        templatePath: {
            validators: {
                notEmpty: {
                    message: '模板路径不能为空'
                },
                regexp: {
                    /* 只需加此键值对，包含正则表达式，和提示 */
                    regexp: /^([a-zA-Z][a-zA-Z]*[.])*([a-zA-Z][a-zA-Z]*)$/,
                    message: '必须以字母开头并且包含字母或者.'
                },
                stringLength: {
                    /*长度提示*/
                    min: 1,
                    max: 100,
                    message: '模板路径长度必须在1到30之间'
                }
            }
        },
        templateType: {
            validators: {
                notEmpty: {
                    message: '模板类型不能为空'
                }
            }
        },
        groupId: {
            validators: {
                notEmpty: {
                    message: '模板组不能为空'
                }
            }
        },
        templateEngineType: {
            validators: {
                notEmpty: {
                    message: '模板引擎类型不能为空'
                }
            }
        }
    }
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
    this.set('id').set('templateName').set('templateDesc').set('templateFileName').set('templateEngineType')
        .set('groupId').set('templateType').set('templatePath').set("templateContent");

};


/**
 * 验证数据
 */
TemplateInfoDlg.validate = function () {
    $('#templateForm').data("bootstrapValidator").resetForm();
    $('#templateForm').bootstrapValidator('validate');
    return $("#templateForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TemplateInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
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
    var type = $("#templateType").val().trim();
    type = type === '' ? 'java' : type;
    $("#codeFile").find('code').html(hljs.highlight(type, $(this).val()).value);
    $("#codeFile").show();
});

$(function () {
    Root.initValidator("templateForm", TemplateInfoDlg.validateFields);
    var type = $("#templateType").val().trim();
    type = type === '' ? 'java' : type;
    $("#codeFile").find('code').html(hljs.highlight(type, $("#templateContent").val()).value);
});