<template>
  <el-card>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="8">
        <el-input v-model="searchNo" placeholder="请输入销售单号" clearable @clear="getList">
          <template #append>
            <el-button :icon="Search" @click="getList">搜索</el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" :icon="Plus" @click="handleAdd">新增销售</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="saleNo" label="销售单号" width="150" />
      <el-table-column prop="patientId" label="患者ID" width="100" />
      <el-table-column prop="patientName" label="患者姓名" width="120" />
      <el-table-column prop="totalAmount" label="总金额" width="120" />
      <el-table-column prop="paymentMethod" label="支付方式" width="120" />
      <el-table-column prop="status" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="row.status === '已支付' ? 'success' : 'warning'">{{ row.status }}</el-tag>
        </template>
      </el-table-column>
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
        <el-form-item label="销售单号" prop="saleNo">
          <el-input v-model="form.saleNo" />
        </el-form-item>
        <el-form-item label="患者ID" prop="patientId">
          <el-input-number v-model="form.patientId" :min="1" />
        </el-form-item>
        <el-form-item label="患者姓名" prop="patientName">
          <el-input v-model="form.patientName" />
        </el-form-item>
        <el-form-item label="总金额" prop="totalAmount">
          <el-input-number v-model="form.totalAmount" :min="0" :precision="2" />
        </el-form-item>
        <el-form-item label="支付方式" prop="paymentMethod">
          <el-select v-model="form.paymentMethod">
            <el-option label="现金" value="现金" />
            <el-option label="微信" value="微信" />
            <el-option label="支付宝" value="支付宝" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status">
            <el-option label="已支付" value="已支付" />
            <el-option label="未支付" value="未支付" />
          </el-select>
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
import { medicineSaleApi } from '@/api'

const tableData = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const searchNo = ref('')
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formRef = ref(null)

const form = reactive({
  id: null,
  saleNo: '',
  patientId: null,
  patientName: '',
  totalAmount: null,
  paymentMethod: '现金',
  status: '已支付'
})

const rules = {
  saleNo: [{ required: true, message: '请输入销售单号', trigger: 'blur' }],
  patientId: [{ required: true, message: '请输入患者ID', trigger: 'blur' }],
  totalAmount: [{ required: true, message: '请输入总金额', trigger: 'blur' }]
}

const getList = async () => {
  const res = await medicineSaleApi.getList({
    current: currentPage.value,
    size: pageSize.value,
    saleNo: searchNo.value
  })
  tableData.value = res.data.records
  total.value = res.data.total
}

const handleAdd = () => {
  dialogTitle.value = '新增销售'
  Object.assign(form, {
    id: null,
    saleNo: '',
    patientId: null,
    patientName: '',
    totalAmount: null,
    paymentMethod: '现金',
    status: '已支付'
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑销售'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该销售记录吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await medicineSaleApi.delete(row.id)
    ElMessage.success('删除成功')
    getList()
  })
}

const handleSubmit = async () => {
  await formRef.value.validate()
  if (form.id) {
    await medicineSaleApi.update(form)
    ElMessage.success('更新成功')
  } else {
    await medicineSaleApi.save(form)
    ElMessage.success('保存成功')
  }
  dialogVisible.value = false
  getList()
}

getList()
</script>
