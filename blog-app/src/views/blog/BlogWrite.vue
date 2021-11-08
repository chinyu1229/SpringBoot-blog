<template>
  <div id="write" v-title :data-title="title">
    <el-container>
      <base-header :simple=true>
        <el-col :span="4" :offset="2">
          <div class="me-write-info">寫文章</div>
        </el-col>
        <el-col :span="4" :offset="6">
          <div class="me-write-btn">
            <el-button round @click="publishShow">發佈</el-button>
            <el-button round @click="cancel">取消</el-button>
          </div>
        </el-col>
      </base-header>

      <el-container class="me-area me-write-box">
        <el-main class="me-write-main">
          <div class="me-write-title">
            <el-input resize="none"
                      type="textarea"
                      autosize
                      v-model="articleForm.title"
                      placeholder="請輸入標題"
                      class="me-write-input">
            </el-input>

          </div>
          <div id="placeholder" style="visibility: hidden;height: 89px;display: none;"></div>
          <markdown-editor :editor="articleForm.editor" class="me-write-editor"></markdown-editor>
        </el-main>
      </el-container>

      <el-dialog title="摘要 分類 標籤"
                 :visible.sync="publishVisible"
                 :close-on-click-modal=false
                 custom-class="me-dialog">

        <el-form :model="articleForm" ref="articleForm" :rules="rules">
          <el-form-item prop="summary">
            <el-input type="textarea"
                      v-model="articleForm.summary"
                      :rows="6"
                      placeholder="請輸入摘要">
            </el-input>
          </el-form-item>
          <el-form-item label="文章分類" prop="category">
            <el-select v-model="articleForm.category" value-key="id" placeholder="請選擇文章分類">
              <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="文章標籤" prop="tags">
            <el-checkbox-group v-model="articleForm.tags">
              <el-checkbox v-for="t in tags" :key="t.id" :label="t.id" name="tags">{{t.tagName}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="publishVisible = false">取 消</el-button>
          <el-button type="primary" @click="publish('articleForm')">發佈</el-button>
        </div>
      </el-dialog>
    </el-container>
  </div>
</template>

<script>
  import BaseHeader from '@/views/BaseHeader'
  import MarkdownEditor from '@/components/markdown/MarkdownEditor'
  import {publishArticle, getArticleById} from '@/api/article'
  import {getAllCategorys} from '@/api/category'
  import {getAllTags} from '@/api/tag'

  export default {
    name: 'BlogWrite',
    mounted() {

      if(this.$route.params.id){
        this.getArticleById(this.$route.params.id)
      }

      this.getCategorysAndTags()
      this.editorToolBarToFixedWrapper = this.$_.throttle(this.editorToolBarToFixed, 200)

      window.addEventListener('scroll', this.editorToolBarToFixedWrapper, false);
    },
    beforeDestroy() {
      window.removeEventListener('scroll', this.editorToolBarToFixedWrapper, false)
    },
    data() {
      return {
        publishVisible: false,
        categorys: [],
        tags: [],
        articleForm: {
          id: '',
          title: '',
          summary: '',
          category: '',
          tags: [],
          editor: {
            value: '',
            ref: '',//保存mavonEditor實例  實際不該這樣
            default_open: 'edit',
            toolbars: {
              bold: true, // 粗體
              italic: true, // 斜體
              header: true, // 標題
              underline: true, // 下劃線
              strikethrough: true, // 中劃線
              mark: true, // 標記
              superscript: true, // 上角標
              subscript: true, // 下角標
              quote: true, // 引用
              ol: true, // 有序列表
              ul: true, // 無序列表
              imagelink: true, // 圖片鏈接
              code: true, // code
              fullscreen: true, // 全屏編輯
              readmodel: true, // 沉浸式閱讀
              help: true, // 幫助
              undo: true, // 上一步
              redo: true, // 下一步
              trash: true, // 清空
              navigation: true, // 導航目錄
              //subfield: true, // 單雙欄模式
              preview: true, // 預覽
            }
          }
        },
        rules: {
          summary: [
            {required: true, message: '請輸入摘要', trigger: 'blur'},
            {max: 80, message: '不能大於80個字符', trigger: 'blur'}
          ],
          category: [
            {required: true, message: '請選擇文章分類', trigger: 'change'}
          ],
          tags: [
            {type: 'array', required: true, message: '請選擇標籤', trigger: 'change'}
          ]
        }
      }
    },
    computed: {
      title (){
        return '寫文章 - 程式碼戲團'
		}
	},
    methods: {
      getArticleById(id) {
        let that = this
        getArticleById(id).then(data => {

          Object.assign(that.articleForm, data.data)
          that.articleForm.editor.value = data.data.body.content

          let tags = this.articleForm.tags.map(function (item) {
            return item.id;
          })

          this.articleForm.tags = tags


        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章加載失敗', showClose: true})
          }
        })
      },
      publishShow() {
        if (!this.articleForm.title) {
          this.$message({message: '標題不能爲空!', type: 'warning', showClose: true})
          return
        }

        if (this.articleForm.title.length > 30) {
          this.$message({message: '標題不能多於30個字', type: 'warning', showClose: true})
          return
        }

        if (!this.articleForm.editor.ref.d_render) {
          this.$message({message: '內容不能爲空!', type: 'warning', showClose: true})
          return
        }

        this.publishVisible = true;
      },
      publish(articleForm) {

        let that = this

        this.$refs[articleForm].validate((valid) => {
          if (valid) {

            let tags = this.articleForm.tags.map(function (item) {
              return {id: item};
            });

            let article = {
              id: this.articleForm.id,
              title: this.articleForm.title,
              summary: this.articleForm.summary,
              category: this.articleForm.category,
              tags: tags,
              body: {
                content: this.articleForm.editor.value,
                contentHtml: this.articleForm.editor.ref.d_render
              }

            }

            this.publishVisible = false;

            let loading = this.$loading({
              lock: true,
              text: '發佈中，請稍後...'
            })

            publishArticle(article,this.$store.state.token).then((data) => {
              if(data.success){
                loading.close();
                that.$message({message: '發佈成功', type: 'success', showClose: true})
                that.$router.push({path: `/view/${data.data.id}`})
              }else{
                that.$message({message: error, type: '發佈文章失敗:'+data.msg, showClose: true});
              }

            }).catch((error) => {
              loading.close();
              if (error !== 'error') {
                that.$message({message: error, type: 'error', showClose: true});
              }
            })

          } else {
            return false;
          }
        });
      },
      cancel() {
        this.$confirm('文章將不會保存, 是否繼續?', '提示', {
          confirmButtonText: '確定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$router.push('/')
        })
      },
      getCategorysAndTags() {
        let that = this
        getAllCategorys().then(data => {
          if(data.success){
            that.categorys = data.data
          }else{
             that.$message({type: 'error', message: '文章分類加載失敗', showClose: true})
          }

        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章分類加載失敗', showClose: true})
          }
        })

        getAllTags().then(data => {
          if(data.success){
            that.tags = data.data
          }else{
             that.$message({type: 'error', message: '標籤加載失敗', showClose: true})
          }
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '標籤加載失敗', showClose: true})
          }
        })

      },
      editorToolBarToFixed() {

        let toolbar = document.querySelector('.v-note-op');
        let curHeight = document.documentElement.scrollTop || document.body.scrollTop;
        if (curHeight >= 160) {
          document.getElementById("placeholder").style.display = "block"; //bad  用計算屬性較好
          toolbar.classList.add("me-write-toolbar-fixed");
        } else {
          document.getElementById("placeholder").style.display = "none";
          toolbar.classList.remove("me-write-toolbar-fixed");
        }
      }
    },
    components: {
      'base-header': BaseHeader,
      'markdown-editor': MarkdownEditor
    },
    //組件內的守衛 調整body的背景色
    beforeRouteEnter(to, from, next) {
      window.document.body.style.backgroundColor = '#fff';
      next();
    },
    beforeRouteLeave(to, from, next) {
      window.document.body.style.backgroundColor = '#f5f5f5';
      next();
    }
  }
</script>

<style>
  .el-header {
    position: fixed;
    z-index: 1024;
    min-width: 100%;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
  }

  .me-write-info {
    line-height: 60px;
    font-size: 18px;
    font-weight: 600;
  }

  .me-write-btn {
    margin-top: 10px;
  }

  .me-write-box {
    max-width: 700px;
    margin: 80px auto 0;
  }

  .me-write-main {
    padding: 0;
  }

  .me-write-title {
  }

  .me-write-input textarea {
    font-size: 32px;
    font-weight: 600;
    height: 20px;
    border: none;
  }

  .me-write-editor {
    min-height: 650px !important;
  }

  .me-header-left {
    margin-top: 10px;
  }

  .me-title img {
    max-height: 2.4rem;
    max-width: 100%;
  }

  .me-write-toolbar-fixed {
    position: fixed;
    width: 700px !important;
    top: 60px;
  }

  .v-note-op {
    box-shadow: none !important;
  }

  .auto-textarea-input, .auto-textarea-block {
    font-size: 18px !important;
  }
</style>
