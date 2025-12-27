<template>
  <el-card>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="8">
        <el-input v-model="searchName" placeholder="请输入供应商名称" clearable @clear="getList">
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
      <el-table-column prop="name" label="供应商名称" width="200" />
      <el-table-column prop="contactPerson" label="联系人" width="120" />
      <el-table-column prop="contactPhone" label="联系电话" width="150" />
      <el-table-column prop="address" label="地址" />
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
        <el-form-item label="供应商名称" prop="name">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactPerson">
          <el-input v-model="form.contactPerson" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
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
import { supplierApi } from '@/api'

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
  contactPerson: '',
  contactPhone: '',
  address: ''
})

const rules = {
  name: [{ required: true, message: '请输入供应商名称', trigger: 'blur' }]
}

const getList = async () => {
  const res = await supplierApi.getList({
    current: currentPage.value,
    size: pageSize.value,
    name: searchName.value
  })
  tableData.value = res.data.records
  total.value = res.data.total
}

const handleAdd = () => {
  dialogTitle.value = '新增供应商'
  Object.assign(form, {
    id: null,
    name: '',
    contactPerson: '',
    contactPhone: '',
    address: ''
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑供应商'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该供应商吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await supplierApi.delete(row.id)
    ElMessage.success('删除成功')
    getList()
  })
}

const handleSubmit = async () => {
  await formRef.value.validate()
  if (form.id) {
    await supplierApi.update(form)
    ElMessage.success('更新成功')
  } else {
    await supplierApi.save(form)
    ElMessage.success('保存成功')
  }
  dialogVisible.value = false
  getList()
}

getList()
</script>
