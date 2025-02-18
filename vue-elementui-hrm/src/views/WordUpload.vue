<template>
  <div>
    <h1>Word文件上传</h1>
    <el-upload
      class="upload-demo"
      action="/docs/uploadWord"
      :on-success="handleSuccess"
      :before-upload="beforeUpload"
      :file-list="fileList"
      multiple
      :limit="3"
      :on-preview="handlePreview"
      :on-remove="handleRemove">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传word文件，且不超过5MB</div>
    </el-upload>
    <el-table :data="fileList" style="width: 100%">
      <el-table-column prop="name" label="文件名"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="previewFile(scope.row)">预览</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import file from 'mockjs'

export default {
  data () {
    return {
      fileList: []
    }
  },
  methods: {
    handleSuccess (response, file, fileList) {
      this.fileList = fileList
    },
    beforeUpload (file) {
      const isWord = file.type === 'application/msword' || file.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
      const isLt5M = file.size / 1024 / 1024 < 5
      if (!isWord) {
        this.$message.error('上传文件只能是word文件')
      }
      if (!isLt5M) {
        this.$message.error('上传文件大小不能超过5MB')
      }
      return isWord && isLt5M
    },
    handlePreview (file) {
      this.previewFile(file)
    },
    handleRemove (file, fileList) {
      this.fileList = fileList
    },
    window: open(`/docs/preview/${file.name}`)
  }
}

</script>

<style scoped>
.upload-demo {
  margin-bottom: 20px;
}
</style>
