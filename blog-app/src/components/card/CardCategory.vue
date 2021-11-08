<template>

  <el-card :body-style="{ padding: '8px 18px' }">
    <div slot="header" class="me-category-header">
      <span>最新文章</span>
    </div>

    <ul class="me-category-list">
      <li class="me-category-item" v-for="l in 3" :key="l"><a>爲什麼程序員那麼有錢爲什麼程序員那麼有錢爲什麼程序員那麼有錢</a></li>
      <li class="me-category-item" v-for="l in 3" :key="l"><a>搭建element-ui的Vue前端工程操作</a></li>

    </ul>
  </el-card>

</template>

<script>
  export default {
    name: 'CardCategory',
    data() {
      return {}
    },
    methods: {}
  }
</script>

<style scoped>
  .me-category-header {
    font-weight: 600;
  }

  .me-category-list {
    list-style-type: none;
  }

  .me-category-item {
    padding: 4px;
    font-size: 14px;
    color: #5FB878;
  }

  .me-category-item a:hover {
    text-decoration: underline;
  }
</style>
