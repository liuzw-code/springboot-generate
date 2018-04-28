$(function () {
    $("#jqGrid").jqGrid({
        url: '/list',
        datatype: "json",
        colModel: [			
			{ label: '表名', name: 'tableName', width: 100, key: true },
			{ label: 'Engine', name: 'engine', width: 70},
			{ label: '表备注', name: 'tableComment', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 100 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			tableName: null
		},
		path : ""
	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'tableName': vm.q.tableName},
                page:1 
            }).trigger("reloadGrid");
		},
		generator: function() {
			var tableNames = getSelectedRows();
			if(tableNames == null){
				return ;
			}
            this.path = sessionStorage.getItem('path');
            //$("#myModal").modal('show');
             location.href = "/generate?tables=" + tableNames
		},
        submit: function() {
            var tableNames = getSelectedRows();
            if(!this.path) {
                alert("请输入生成文件路径");
                return;
            }
            console.log(this.path);
            sessionStorage.setItem('path',this.path);
            var path = this.path.replace("\\","/");
            $.ajax({
                url: "/generateCode?tables="+tableNames + "&path="+path,
                type: "get",
                dataType: "text",
                success: function(data){
                  alert("代码生成成功！，请去[" + path + "]下查看");
                    $("#myModal").modal('hide');
                },
                error:function () {
                    alert("error....")
                }
            });
        }
	}
});

