<template>
    <div>
        <Header></Header>
        <div class="content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm contentSize">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>

                <el-form-item label="摘要" prop="description">
                    <el-input type="textarea" v-model="ruleForm.description"></el-input>
                </el-form-item>
                <el-form-item  label="内容" prop="content">
                    <mavon-editor class="size" v-model="ruleForm.content"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">发布</el-button>
                    <el-button @click="resetForm('ruleForm')">清除</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import Header from "../components/Header";
    export default {
        name: "BlogEdit",
        components:{Header},
        data() {
            return {
                ruleForm: {
                    title: '',
                    description: '',
                    content:''
                },
                rules: {
                    title: [
                        { required: true, message: '请输入博客标题', trigger: 'blur' },
                        { min: 1, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    description: [
                        { required: true, message: '请输入博客描述', trigger: 'blur' }
                    ],
                    content: [
                        { required: true, message: '请输入博客内容', trigger: 'blur' }
                    ],

                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const that = this
                        that.$axios.post("/blog/edit",this.ruleForm,
                            {
                            headers: {"Authorization": localStorage.getItem("token")}
                        }
                        ).then(res=>{
                            that.$alert('操作成功', '提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.$router.push("/blogs")
                                }
                            });
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
                const blogId = this.$route.params.blogId
                console.log(blogId)
                const that = this
                if(blogId) {
                    this.$axios.get('/blog/' + blogId).then(res => {
                        const blog = res.data.data
                        that.ruleForm.id = blog.id
                        that.ruleForm.title = blog.title
                        that.ruleForm.description = blog.description
                        that.ruleForm.content = blog.content
                    })
                }
        }
    }
</script>

<style scoped>
    .content{
        text-align: center;
    }
    .contentSize{
        width: 960px;
    }
    .size{
        height: 600px;
    }
</style>