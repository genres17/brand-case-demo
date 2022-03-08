new Vue({
    el:"#brand_chart",
    mounted(){
        // this.showBrand();
        this.pageShowBrand();
    },
    data(){
        return{
            // 表格数据
            tableData: [{
                brandName:"华为",
                companyName:"华为有效公司",
                order:100,
                status:1
            }, {
                brandName:"华为",
                companyName:"华为有效公司",
                order:100,
                status:1
            },{
                brandName:"华为",
                companyName:"华为有效公司",
                order:100,
                status:1
            },{
                brandName:"华为",
                companyName:"华为有效公司",
                order:100,
                status:1
            }],
            // 复选框选中的数据
            multipleSelection : [],
            // 单行表单数据和搜索临时数据，省事写一起了
            brand: {
                status: '',
                companyName: '',
                brandName:'',
                description:'',
                id:'',
                order:''
            },
            // 对话框里的表单
            dialogFormVisible: false,
            // formLabelWidth: '120px',
            currentPage: 1,
            currentPageSize: 5,
            deleteIds: [],
            totalPage:10,
            startIndex:1,
            dialogFormVisible1:false,
            updateBrand: {
                status: '',
                companyName: '',
                brandName:'',
                description:'',
                id:'',
                order:''
            },




        }

    },
    methods:{
        tableRowClassName({row, rowIndex}) {
            if (rowIndex%4 === 1) {
                return 'warning-row';
            } else if (rowIndex%4 === 3) {
                return 'success-row';
            }
            return '';
        },
        handleSelectionChange(val) {
            // 处理复选框的函数，要在数据区定义multipleSelection
            this.multipleSelection = val;
            console.log(this.multipleSelection);
        },
        onSubmit() {
            console.log(this.brand);
            // 必须重新初始化分页参数，不然sql分页会有问题
            this.currentPage = 1;
            // this.currentPageSize = 5;
            this.pageShowBrand();
        },
        addBrand(){
            const _this = this;
            console.log(_this.brand);
            axios({
                method:"post",
                baseURL: "http://localhost:8080/brand_case_war/",
                url:"/brand/insertBrand",
                data: _this.brand

            }).then(function (response){
                if(response.data ==="succeed"){
                    let msg = "数据添加成功";
                    _this.dialogFormVisible = false;
                    this.currentPage = 1;
                    // this.currentPageSize = 5;
                    // _this.pageShowBrand();
                    _this.openSuccess(msg);
                }else{
                    let msg = '出错咯，请重新添加数据';
                    _this.dialogFormVisible = false;
                    _this.openError(msg);
                }
                // 清空brand，不然会干扰查询的内容
                _this.brand = {
                    status: '',
                    companyName: '',
                    brandName:'',
                    description:'',
                    id:'',
                    order:''
                }
                _this.pageShowBrand();
            })

        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.currentPageSize = val;
            this.pageShowBrand();
            this.startIndex = (this.currentPage-1)*this.currentPageSize+1;

        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentPage = val;
            this.pageShowBrand();
            this.startIndex = (this.currentPage-1)*this.currentPageSize+1;
        },
        showBrand(){
            const _this = this;
            axios({
                method:"post",
                baseURL: "http://localhost:8080/brand_case_war/",
                url:"/brand/selectAllBrand"
            }).then(response=>{
                console.log(response.data);
                _this.tableData = response.data;

            });
        },
        openSuccess(msg){
            this.$message({
                message: msg,
                type: 'success'
            });
        },
        openError(msg){
            this.$message.error(msg);
        },
        deleteBrands(){
            // 批量删除数据
            let brands = this.multipleSelection;
            for (let i = 0; i < brands.length; i++) {
                this.deleteIds[i] = brands[i].id;
            }
            console.log(this.deleteIds);
            axios({
                method:"post",
                baseURL: "http://localhost:8080/brand_case_war/",
                url:"/brand/deleteBrands",
                data: this.deleteIds
            }).then(response=>{
                if(response.data = "success"){
                    let msg = "删除成功！";
                    this.currentPage = 1;
                    // this.currentPageSize = 5;
                    this.pageShowBrand();
                    this.openSuccess(msg);

                }else{
                    let msg = "删除失败哦QAQ";
                    this.openError(msg);
                }

            })

        },
        handleDelete(index, row) {
            // console.log(index, row);
            // 删除单行数据
            let id = row.id;
            this.deleteIds[0] = id;
            this.deleteBrands();
        },
        handleEdit(index, row){
            // 修改当前行数据，row变量能获取当前行的json信息
            this.updateBrand = row;
            console.log(row);
            console.log(this.updateBrand);
            this.dialogFormVisible1 = true;


        },
        doUpdate(){
            axios({
                method:"post",
                baseURL: "http://localhost:8080/brand_case_war/",
                url:"/brand/updateBrand",
                data: this.updateBrand
            }).then(response=>{
                if(response.data = "success"){
                    this.dialogFormVisible1 = false;
                    let msg = "修改成功！";
                    this.pageShowBrand();
                    this.openSuccess(msg);

                }else{
                    let msg = "修改失败哦QAQ";
                    this.openError(msg);
                }
            })

        },
        pageShowBrand(){
            // 分页查询代码
            console.log(this.currentPageSize,this.currentPage);
            axios({
                method:"post",
                baseURL: "http://localhost:8080/brand_case_war/",
                url:"/brand/selectByPageBrands?currentPage="+this.currentPage+"&currentPageSize="+this.currentPageSize,
                data: this.brand
            }).then(response=>{
                this.tableData = response.data.items;
                this.totalPage = response.data.counts;
                this.startIndex = (this.currentPage-1)*this.currentPageSize+1;
            })
        },
        reset(){
            // 清除条件后重新查询
            this.currentPage = 1;
            this.brand = {
                status: '',
                companyName: '',
                brandName:''
            };
            this.pageShowBrand();
        }


    }
})