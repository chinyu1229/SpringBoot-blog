<template>
  <div v-title :data-title="title">
    <el-container class="me-area">
      <el-main class="me-main">
        <!--<el-steps class="me-log-box" :space="100"  direction="vertical">
                 <el-step title="步驟 1" status="process"  icon="el-icon-time" description="步驟 1步驟 1步驟 1步驟 1步驟 1"></el-step>
                 <el-step title="步驟 2" status="process" icon="el-icon-time"></el-step>
                 <el-step title="步驟 3" status="process" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                   <el-step title="步驟 3" status="process" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>

                 <el-step title="步驟 3" status="process" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" status="process" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" status="process" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" status="process" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>
                 <el-step title="步驟 3" icon="el-icon-time" description="這是一段很長很長很長的描述性文字"></el-step>

              </el-steps>-->
        <pre>

      ```
      # 無

      ```
			 </pre>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'Log',
    data() {
      return {}
    },
    computed: {
      title (){
        return '日誌 - 碼神之路'
      }
    }
  }
</script>

<style scoped>
  .el-container {
    width: 700px;
  }

  .me-main {
    overflow: hidden;
  }

  .me-log-box {
    margin-left: 30%;
    margin-top: 20px;
  }

</style>
