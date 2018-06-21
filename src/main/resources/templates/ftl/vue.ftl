<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">

            <el-form align="right" :inline="true"  class="demo-form-inline">
                <el-form-item label="">
                    <el-input v-model="filters.xxxxx" placeholder="查询条件没写，自己写....."></el-input>
                </el-form-item>
                <el-form-item label="">
                    <el-button type="primary" v-on:click="searchData">查询</el-button>
                </el-form-item>
                <el-form-item label="">
                    <el-button v-if="isAuth('mpys:${data.varName}:insert')" type="primary" @click="handleEditOrAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="list"  height="500" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <el-table-column type="selection" ></el-table-column>
           <#list data.columns as c>
             <el-table-column prop="${c.propertyName}" label="${c.propertyCname?if_exists}"   sortable></el-table-column>
           </#list>

            <el-table-column v-if="isAuth('mpys:${data.varName}:update') || isAuth('mpys:${data.varName}:delete')" fixed="right" label="操作" width="150">
                <template slot-scope="scope">
                    <el-button v-if="isAuth('mpys:${data.varName}:update')" size="small" @click="handleEditOrAdd(scope.$index, scope.row)">编辑</el-button>
                    <el-button v-if="isAuth('mpys:${data.varName}:delete')" type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" style="margin-top: 10px">
            <el-button v-if="isAuth('mpys:${data.varName}:delete')" type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[10, 20, 30, 40]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total" style="float: right">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog :title="title" v-model="formVisible" :visible.sync="formVisible">
            <el-form :model="form" label-width="80px" :rules="formRules" ref="form">
            <#list data.columns as c>
               <el-form-item label="${c.propertyCname?if_exists}" prop="${c.propertyName}">
                  <el-input v-model="form.${c.propertyName}" auto-complete="off"></el-input>
               </el-form-item>
            </#list>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="formVisible = false">取消</el-button>
                <el-button type="primary" @click.native="submit" :loading="loading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import {get${data.className}List, remove${data.className}, batchRemove${data.className}, edit${data.className}, add${data.className}} from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    name: '',
                },
                list: [],
                total: 0,
                title:"新增",//弹出框标题
                pageNum: 1, //当前页码
                pageSize: 10,//页数
                listLoading: false,
                sels: [],//列表选中列
                formVisible: false,//编辑界面是否显示
                loading: false,
                formRules: {
                    name: [{required: true, message: '请输入姓名', trigger: 'blur'}]
                },
                //编辑界面数据
                form: {
                  <#list data.columns as c>
                      ${c.propertyName}:'' <#if c_has_next>,</#if>
                  </#list>
                },

            }
        },
        methods: {
            //切换页数
            handleCurrentChange(val) {
                this.pageNum = val;
                this.getData();
            },
            //处理分页条数
            handleSizeChange(val) {
                this.pageSize = val;
                this.getData();
            },
            searchData(){
                this.pageNum = 1;
                this.getData();
            },
            //获取用户列表
            getData() {
                let para = {
                   // name: this.filters.name,
                   // userName: this.filters.userName,
                    pageInfo: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                    }
                };
                this.listLoading = true;
                get${data.className}List(para).then((res) => {
                    this.listLoading = false;
                    if (this.GLOBAL.isResonseSuccess(res)) {
                        this.total = res.data.paginationInfo.total;
                        this.list = res.data.list;
                    }
                }, (res) => { this.listLoading = false; });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    let para = {id: row.id};
                    remove${data.className}(para).then((res) => {
                        this.listLoading = false;
                        if (this.GLOBAL.isResonseSuccess(res)) {
                            this.success();
                            this.getData();
                        }
                    });
                }).catch(() => {});
            },
            //显示编辑/新增界面
            handleEditOrAdd: function (index, row) {
                if (row) {
                    this.title= "编辑";
                    this.form = Object.assign({}, row);
                } else {
                    this.title= "新增";
                    this.form = {};
                }
                this.formVisible = true;
            },
            //编辑
            submit: function () {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.loading = true;
                            if(para.id) {
                                let para = this.form;
                                edit${data.className}(para).then((res) = > {
                                    this.loading = false;
                                    if (this.GLOBAL.isResonseSuccess(res)){
                                        this.success();
                                        this.$refs['form'].resetFields();
                                        this.formVisible = false;
                                        this.getData();
                                     }
                                })
                            } else {
                                let para = Object.assign({}, this.form);
                                add${data.className}(para).then((res) => {
                                    this.addLoading = false;
                                    if (this.GLOBAL.isResonseSuccess(res)) {
                                        this.success();
                                        this.$refs['form'].resetFields();
                                        this.addFormVisible = false;
                                        this.getData();
                                    }
                                });
                            }
                        });
                    }
                });
            },

            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    let para = {ids: ids};
                    batchRemove${data.className}(para).then((res) => {
                        this.listLoading = false;
                        if (this.GLOBAL.isResonseSuccess(res)) {
                            this.success();
                            this.getData();
                        }
                    });
                });
            },

            success: function(){
                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
            }
        },
        mounted() {
            this.getData();
        }
    }

</script>

<style scoped>

</style>