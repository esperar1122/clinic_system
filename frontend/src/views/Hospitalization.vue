<template>
  <el-card>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="8">
        <el-input v-model="searchNo" placeholder="请输入住院号" clearable @clear="getList">
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
      <el-table-column prop="admissionNo" label="住院号" width="120" />
      <el-table-column prop="patientId" label="患者ID" width="100" />
      <el-table-column prop="patientName" label="患者姓名" width="120" />
      <el-table-column prop="department" label="科室" width="120" />
      <el-table-column prop="bedNo" label="床位号" width="100" />
      <el-table-column prop="admissionDate" label="入院日期" width="120" />
      <el-table-column prop="deposit" label="预交金" width="120" />
      <el-table-column prop="totalAmount" label="总费用" width="120" />
      <el-table-column prop="paidAmount" label="已付金额" width="120" />
      <el-table-column prop="status" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="row.status === '已出院' ? 'success' : 'warning'">{{ row.status }}</el-tag>
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="700px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="患者ID" prop="patientId">
          <el-input-number v-model="form.patientId" :min="1" />
        </el-form-item>
        <el-form-item label="患者姓名" prop="patientName">
          <el-input v-model="form.patientName" />
        </el-form-item>
        <el-form-item label="住院号" prop="admissionNo">
          <el-input v-model="form.admissionNo" />
        </el-form-item>
        <el-form-item label="科室" prop="department">
          <el-input v-model="form.department" />
        </el-form-item>
        <el-form-item label="床位号" prop="bedNo">
          <el-input v-model="form.bedNo" />
        </el-form-item>
        <el-form-item label="入院日期" prop="admissionDate">
          <el-date-picker v-model="form.admissionDate" type="date" value-format="YYYY-MM-DD" />
        </el-form-item>
        <el-form-item label="出院日期" prop="dischargeDate">
          <el-date-picker v-model="form.dischargeDate" type="date" value-format="YYYY-MM-DD" />
        </el-form-item>
        <el-form-item label="预交金" prop="deposit">
          <el-input-number v-model="form.deposit" :min="0" :precision="2" />
        </el-form-item>
        <el-form-item label="总费用" prop="totalAmount">
          <el-input-number v-model="form.totalAmount" :min="0" :precision="2" />
        </el-form-item>
        <el-form-item label="已付金额" prop="paidAmount">
          <el-input-number v-model="form.paidAmount" :min="0" :precision="2" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status">
            <el-option label="住院中" value="住院中" />
            <el-option label="已出院" value="已出院" />
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
import { hospitalizationApi } from '@/api'

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
  patientId: null,
  patientName: '',
  admissionNo: '',
  department: '',
  bedNo: '',
  admissionDate: '',
  dischargeDate: '',
  deposit: 0,
  totalAmount: 0,
  paidAmount: 0,
  status: '住院中'
})

const rules = {
  patientId: [{ required: true, message: '请输入患者ID', trigger: 'blur' }],
  admissionNo: [{ required: true, message: '请输入住院号', trigger: 'blur' }],
  department: [{ required: true, message: '请输入科室', trigger: 'blur' }],
  bedNo: [{ required: true, message: '请输入床位号', trigger: 'blur' }]
}

const getList = async () => {
  const res = await hospitalizationApi.getList({
    current: currentPage.value,
    size: pageSize.value,
    admissionNo: searchNo.value
  })
  tableData.value = res.data.records
  total.value = res.data.total
}

const handleAdd = () => {
  dialogTitle.value = '新增住院收费'
  Object.assign(form, {
    id: null,
    patientId: null,
    patientName: '',
    admissionNo: '',
    department: '',
    bedNo: '',
    admissionDate: '',
    dischargeDate: '',
    deposit: 0,
    totalAmount: 0,
    paidAmount: 0,
    status: '住院中'
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑住院收费'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该住院收费记录吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await hospitalizationApi.delete(row.id)
    ElMessage.success('删除成功')
    getList()
  })
}

const handleSubmit = async () => {
  await formRef.value.validate()
  if (form.id) {
    await hospitalizationApi.update(form)
    ElMessage.success('更新成功')
  } else {
    await hospitalizationApi.save(form)
    ElMessage.success('保存成功')
  }
  dialogVisible.value = false
  getList()
}

getList()
</script>
