<template>
  <el-card>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="8">
        <el-input v-model="searchName" placeholder="请输入药品名称" clearable @clear="getList">
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
      <el-table-column prop="name" label="药品名称" width="150" />
      <el-table-column prop="specification" label="规格" width="100" />
      <el-table-column prop="manufacturer" label="生产厂家" width="150" />
      <el-table-column prop="supplierId" label="供应商ID" width="100" />
      <el-table-column prop="unit" label="单位" width="80" />
      <el-table-column prop="price" label="单价" width="100" />
      <el-table-column prop="stockQuantity" label="库存" width="100" />
      <el-table-column prop="expiryDate" label="有效期" width="120" />
      <el-table-column prop="category" label="分类" width="120" />
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="700px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="药品名称" prop="name">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="规格" prop="specification">
          <el-input v-model="form.specification" />
        </el-form-item>
        <el-form-item label="生产厂家" prop="manufacturer">
          <el-input v-model="form.manufacturer" />
        </el-form-item>
        <el-form-item label="供应商ID" prop="supplierId">
          <el-input-number v-model="form.supplierId" :min="1" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" />
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input-number v-model="form.price" :min="0" :precision="2" />
        </el-form-item>
        <el-form-item label="库存数量" prop="stockQuantity">
          <el-input-number v-model="form.stockQuantity" :min="0" />
        </el-form-item>
        <el-form-item label="有效期" prop="expiryDate">
          <el-date-picker v-model="form.expiryDate" type="date" value-format="YYYY-MM-DD" />
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-input v-model="form.category" />
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
import { medicineApi } from '@/api'

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
  specification: '',
  manufacturer: '',
  supplierId: null,
  unit: '',
  price: null,
  stockQuantity: 0,
  expiryDate: '',
  category: ''
})

const rules = {
  name: [{ required: true, message: '请输入药品名称', trigger: 'blur' }],
  unit: [{ required: true, message: '请输入单位', trigger: 'blur' }],
  price: [{ required: true, message: '请输入单价', trigger: 'blur' }]
}

const getList = async () => {
  const res = await medicineApi.getList({
    current: currentPage.value,
    size: pageSize.value,
    name: searchName.value
  })
  tableData.value = res.data.records
  total.value = res.data.total
}

const handleAdd = () => {
  dialogTitle.value = '新增药品'
  Object.assign(form, {
    id: null,
    name: '',
    specification: '',
    manufacturer: '',
    supplierId: null,
    unit: '',
    price: null,
    stockQuantity: 0,
    expiryDate: '',
    category: ''
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑药品'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该药品吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await medicineApi.delete(row.id)
    ElMessage.success('删除成功')
    getList()
  })
}

const handleSubmit = async () => {
  await formRef.value.validate()
  if (form.id) {
    await medicineApi.update(form)
    ElMessage.success('更新成功')
  } else {
    await medicineApi.save(form)
    ElMessage.success('保存成功')
  }
  dialogVisible.value = false
  getList()
}

getList()
</script>
