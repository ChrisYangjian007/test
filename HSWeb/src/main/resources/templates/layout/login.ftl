<!doctype html>
<html lang="en">
<head>
<#include "head.ftl">
    <style type="text/css">
        @import url("${request.contextPath}/css/element-ui/index.css");
        @import url("${request.contextPath}/css/element-ui/login.css");
    </style>
    <script src="${request.contextPath}/js/vue/vue.js"></script>
    <script src="${request.contextPath}/js/element-ui/index.js"></script>
    <script src="${request.contextPath}/js/vue/vue-resource.js"></script>
    <title>登录页</title>
</head>
<body>
<div id="app">
    <el-form ref="form" :model="form" :rules="rules2" label-position="left" label-width="0px"
             class="login-container">
        <h3 class="title">系统登录</h3>
        <el-form-item prop="account">
            <el-input type="text" v-model="form.account" auto-complete="off"
                      placeholder="账号" @keyup.enter="submit2"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
            <el-input type="password" v-model="form.checkPass" auto-complete="off"
                      placeholder="密码" @keyup.enter="submit2"></el-input>
        </el-form-item>
        <el-checkbox v-model="checked" checked class="remember" @keyup.enter="submit2">记住密码</el-checkbox>
        <el-form-item style="width:100%;">
            <el-button style="width: 100%" :plain="true" type="primary" @click.native.prevent="submit2"
                       :loading="login">登陆
            </el-button>
        <#--<el-button @click.native.prevent="reset2">重置</el-button>-->
        </el-form-item>
    </el-form>
</div>
</body>
<script>
    Vue.use(ELEMENT);
    Vue.http.options.emulateJSON = true;
    var Main = {
        data() {
            return {
                login: false,
                form: {
                    account: "",
                    checkPass: ""
                },
                rules2: {
                    account: [
                        {required: true, message: '请输入账号', trigger: 'blur'}
                    ],
                    checkPass: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                },
                checked: true
            }
        },
        methods: {
//            reset2() {
//                this.$refs.form.resetFields();
//            },
            submit2() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.login = true;
                        var loginParams = {username: this.form.account, password: this.form.checkPass};
                        this.$http.post("${request.contextPath}/login.json", loginParams).then((response) => {
                            if (response.body.success) {
                                this.$message({
                                    message: "登陆成功！",
                                    type: 'success'
                                });
                                window.location.href = "${request.contextPath}/index.htm";
                            } else {
                                this.login = false;
                                this.$refs.form.resetFields();
                                this.$message({
                                    message: response.body.msg,
                                    type: 'warning'
                                });
                            }
                        }, (response) => {
//                            错误执行位置
                            this.$refs.form.resetFields();
                        });
                    }
                });
            }
        }
    }
    var Ctor = Vue.extend(Main)
    new Ctor().$mount('#app')
</script>
</html>