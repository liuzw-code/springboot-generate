var GenInfo = {
    templateBtn: '<button type="button" class="list-group-item" value="id" onclick="GenInfo.selectTemplates(this.value)">templateName</button>',
    param: {
        params : {}
    },
    validateFields: {
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
                    regexp: /^[a-zA-Z0-9_\-\.]+$/,
                    message: '只能是数字和字母和-_.'
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



GenInfo.getData = function (url) {

    var success = function (data) {
        var html = '';
        $(data.data.data).each(function (i, d) {
            html += GenInfo.templateBtn.replace("id", d.id).replace("templateName", d.templateName);
        });
        $("#templatesList").html(html);
    };
    var ajax = new $ax(Root.ctxPath + url, success);
    ajax.setData(GenInfo.param);
    ajax.start();
};



GenInfo.selectTemplates = function (id) {
    if (!GenInfo.param.templateIds) {
        GenInfo.param.templateIds = [];
    }
    if ($.inArray(id, GenInfo.param.templateIds) > -1) {
        GenInfo.param.templateIds.splice($.inArray(id, GenInfo.param.templateIds), 1);
        $("#templatesList").find("button").each(function (i, d) {
            if ($(this).val() === id) {
                $(this).removeClass('active');
            }
        });
    } else {
        $("#templatesList").find("button").each(function (i, d) {
            if ($(this).val() === id) {
                $(this).addClass('active');
            }
        });
        GenInfo.param.templateIds.push(id);
    }
    GenInfo.genBtnAble();

};

GenInfo.selectAll = function () {
    $("#templatesList").find('button').each(function () {
        GenInfo.selectTemplates($(this).attr('value'));
    })
};
/**
 * 验证数据
 */
GenInfo.validate = function () {
    $('#genForm').data("bootstrapValidator").resetForm();
    $('#genForm').bootstrapValidator('validate');
    return $("#genForm").data('bootstrapValidator').isValid();
};

GenInfo.selectChange = function (data, eleName) {
    $("#" + eleName).find("button").each(function (i, d) {
        if ($(this).val() === data) {
            $(this).addClass('active');
        } else {
            $(this).removeClass('active');
        }
    });
};

GenInfo.genBtnAble = function () {
    if (GenInfo.param.templateIds && GenInfo.param.templateIds.length > 0) {
        $("#genBtn").removeAttr("disabled");
    } else {
        $("#genBtn").attr("disabled", "disabled");
    }
};


GenInfo.genCode = function () {
    if (!GenInfo.param.templateIds) {
        Root.error("请选择模板!");
    }
    if (!this.validate()) {
        return;
    }

    //表id
    GenInfo.param.tableNames = [];
    var tableNames = $("#tableNames").val().split(",");
    for (var index in tableNames) {
        GenInfo.param.tableNames.push(tableNames[index]);
    }
    //基本参数
    GenInfo.param.params = {
        "author": $("#author").val(),
        "name": $("#name").val(),
        "packageName": $("#packageName").val(),
        "localPath": $("#localPath").val(),
        "copyright": $("#copyright").val()
    };

    //数据库id
    GenInfo.param.databaseId = $("#databaseId").val();
    //本地生产就ajax访问后台就可以了
    if (GenInfo.param.params.localPath) {
        var ajax = new $ax("/gen/genCode", function (data) {
            if (data.code === 1) {
                Root.success("代码生成成功!");
            }
        }, function (data) {
            Root.error("代码生成失败!");
        });
        ajax.setData(GenInfo.param);
        ajax.start();
    } else {
        window.location.href = '/gen/genCode?' + $.param(GenInfo.param);
    }

};

// 页面初始化
$(function () {
    GenInfo.param = {
        "pageNum" : 1,
        "pageSize" : 1000
    };
    GenInfo.getData('/template/list');
    Root.initValidator("genForm", GenInfo.validateFields);
});

$("#params").change(function () {
    var ajax = new $ax(Root.ctxPath + '/params/getById/' + $(this).val(), function (data) {
        $("#author").val(data.data.author);
        $("#packageName").val(data.data.packageName);
        $("#localPath").val(data.data.localPath);
        $("#copyright").val(data.data.copyright);
    });
    ajax.setData({'id': $("#params").val()});
    ajax.start();
});

$("#groupId").change(function () {
    GenInfo.param = {
        "pageNum" : 1,
        "pageSize" : 1000,
        "groupId": $(this).val()
    };
    GenInfo.getData('/template/list');
});