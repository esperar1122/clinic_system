<template>
  <el-card>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="8">
        <el-input v-model="searchName" placeholder="请输入患者姓名" clearable @clear="getList">
          <template #append>
            <el-button :icon="Search" @click="getList">搜索</el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" :icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="姓名" width="120" />
      <el-table-column prop="gender" label="性别" width="80" />
      <el-table-column prop="age" label="年龄" width="80" />
      <el-table-column prop="phone" label="联系电话" width="150" />
      <el-table-column prop="idCard" label="身份证号" width="180" />
      <el-table-column prop="address" label="家庭住址" />
      <el-table-column label="操作" width="180" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :total="total"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      style="margin-top: 20px; justify-content: flex-end"
      @size-change="getList"
      @current-change="getList"
    />

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="form.gender">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="form.age" :min="0" :max="150" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" />
        </el-form-item>
        <el-form-item label="家庭住址" prop="address">
          <el-input v-model="form.address" type="textarea" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </el-card>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus } from '@element-plus/icons-vue'
import { patientApi } from '@/api'

const tableData = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const searchName = ref('')
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formRef = ref(null)

const form = reactive({
  id: null,
  name: '',
  gender: '男',
  age: null,
  phone: '',
  idCard: '',
  address: ''
})

const rules = {
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
  phone: [{ required: true, message: '请输入联系电话', trigger: 'blur' }]
}

const getList = async () => {
  const res = await patientApi.getList({
    current: currentPage.value,
    size: pageSize.value,
    name: searchName.value
  })
  tableData.value = res.data.records
  total.value = res.data.total
}

const handleAdd = () => {
  dialogTitle.value = '新增患者'
  Object.assign(form, {
    id: null,
    name: '',
    gender: '男',
    age: null,
    phone: '',
    idCard: '',
    address: ''
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑患者'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该患者吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await patientApi.delete(row.id)
    ElMessage.success('删除成功')
    getList()
  })
}

const handleSubmit = async () => {
  await formRef.value.validate()
  if (form.id) {
    await patientApi.update(form)
    ElMessage.success('更新成功')
  } else {
    await patientApi.save(form)
    ElMessage.success('保存成功')
  }
  dialogVisible.value = false
  getList()
}

getList()
</script>
