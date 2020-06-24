<template>
    <div>
        <Header></Header>
        <div class="detail">
            <h2>{{blog.title}}</h2>
            <div class="right" v-if="ownBlog">
                <router-link class="pad" :to="{name: 'BlogEdit', params: {blogId: blog.id}}" >
                    <el-button type="primary" icon="el-icon-edit" plain></el-button>
                </router-link>
                <el-button type="primary" icon="el-icon-delete" plain @click="deleteBlog"></el-button>
            </div>
<!--            <el-link icon="el-icon-edit" v-if="ownBlog">-->
<!--            <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}" >-->
<!--                编辑-->
<!--            </router-link>-->
<!--            </el-link>-->
            <el-divider></el-divider>
            <div class="markdown-body" v-html="blog.content"></div>
        </div>
    </div>
    </template>

    <script>
    import Header from "../components/Header";
    import 'github-markdown-css'
    export default {
        name: "BlogDetail",
        components: {Header},
        data() {
            return {
                blog: {
                    id: "",
                    title: "",
                    content: ""
                },
                ownBlog: false,
            }
        },
        methods:{
            deleteBlog(){
                const blogId = this.$route.params.blogId
                const that = this
                that.$axios.get('/blog/'+blogId+'/delete',
                    {
                        headers: {"Authorization": localStorage.getItem("token")}
                    }
                ).then(res => {
                    console.log("删除成功");
                    that.$router.push("/blogs")

                }).catch(error=>{
                    console.log(error)
                })
            },
        },
        created() {
            const blogId = this.$route.params.blogId
            // console.log(blogId)
            const that = this
            if(blogId) {
                this.$axios.get('/blog/' + blogId).then(res => {
                    const blog = res.data.data
                    that.blog.id = blog.id
                    that.blog.title = blog.title
                    var MardownIt = require("markdown-it")
                    var md = new MardownIt()
                    var result = md.render(blog.content)
                    that.blog.description = blog.description
                    that.blog.content = result
                    that.ownBlog = (blog.userId === that.$store.getters.getUser.id)

                })
            }
        }
    }
</script>

<style scoped>
    .detail{
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        width: 100%;
        min-height: 700px;
        padding: 30px 30px;
    }
    .detail h2{
        text-align: center;
    }
    .right{
        padding-left: 800px;
    }
    .pad{
        padding:20px;
    }
</style>