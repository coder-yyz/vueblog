<template>
    <div>
        <Header></Header>
        <div class="block">
            <el-timeline>
                <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs">
                    <el-card>
                        <h3>
                            <router-link  :to="{name:'BlogDetail',params:{blogId:blog.id}}" >
                                {{blog.title}}
                            </router-link>
                        </h3>
                        <p>{{blog.description}}</p>

                    </el-card>
                </el-timeline-item>
            </el-timeline>
            <el-pagination class="medium_page"
                    background
                    layout="prev, pager, next"
                           :page-size="size"
                           :current-page="current"
                           :total="total"
                           @current-change="page"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import Header from "../components/Header";
    export default {
        name: "Blogs",
        components: {Header},
        // data:{
        //     total:0,
        //     size:5,
        //     current: 1,
        // }
        data(){
            return{
                blogs:{},
                total:0,
                size:5,
                current: 1,
            }
        },
        methods:{
            page(currentPage){
                const that = this;
                that.$axios.get("/blogs?currentPage="+currentPage).then(response=>{
                    // console.log(response.data.data)
                    that.total = response.data.data.total;
                    that.curren = response.data.data.current;
                    that.size = response.data.data.size;
                    that.blogs = response.data.data.records;

                })

            }
        },
        created() {
            this.page(1)
        }
    }
</script>

<style scoped>
    .medium_page{
        margin: 0 auto;
        text-align: center;
    }
    a {
        text-decoration: none;
    }

</style>