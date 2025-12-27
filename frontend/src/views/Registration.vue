<template>
  <el-card>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="8">
        <el-input v-model="searchNo" placeholder="请输入挂号单号" clearable @clear="getList">
          <template #append>
            <el-button :icon="Search" @click="getList">搜索</el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" :icon="Plus" @click="handleAdd">新增挂号</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="registrationNo" label="挂号单号" width="150" />
      <el-table-column prop="patientId" label="患者ID" width="100" />
      <el-table-column prop="department" label="科室" width="120" />
      <el-table-column prop="doctor" label="医生" width="100" />
      <el-table-column prop="registrationFee" label="挂号费" width="100" />
      <el-table-column prop="visitDate" label="就诊日期" width="120" />
      <el-table-column prop="status" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="getStatusType(row.status)">{{ row.status }}</el-tag>
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
        <el-form-item label="患者ID" prop="patientId">
          <el-input-number v-model="form.patientId" :min="1" />
        </el-form-item>
        <el-form-item label="挂号单号" prop="registrationNo">
          <el-input v-model="form.registrationNo" />
        </el-form-item>
        <el-form-item label="科室" prop="department">
          <el-input v-model="form.department" />
        </el-form-item>
        <el-form-item label="医生" prop="doctor">
          <el-input v-model="form.doctor" />
        </el-form-item>
        <el-form-item label="挂号费" prop="registrationFee">
          <el-input-number v-model="form.registrationFee" :min="0" :precision="2" />
        </el-form-item>
        <el-form-item label="就诊日期" prop="visitDate">
          <el-date-picker v-model="form.visitDate" type="date" value-format="YYYY-MM-DD" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status">
            <el-option label="待诊" value="待诊" />
            <el-option label="就诊中" value="就诊中" />
            <el-option label="已完成" value="已完成" />
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
import { registrationApi } from '@/api'

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
  registrationNo: '',
  department: '',
  doctor: '',
  registrationFee: 10,
  visitDate: '',
  status: '待诊'
})

const rules = {
  patientId: [{ required: true, message: '请输入患者ID', trigger: 'blur' }],
  registrationNo: [{ required: true, message: '请输入挂号单号', trigger: 'blur' }],
  department: [{ required: true, message: '请输入科室', trigger: 'blur' }],
  doctor: [{ required: true, message: '请输入医生', trigger: 'blur' }],
  visitDate: [{ required: true, message: '请选择就诊日期', trigger: 'change' }]
}

const getStatusType = (status) => {
  const map = {
    '待诊': 'info',
    '就诊中': 'warning',
    '已完成': 'success'
  }
  return map[status] || 'info'
}

const getList = async () => {
  const res = await registrationApi.getList({
    current: currentPage.value,
    size: pageSize.value,
    registrationNo: searchNo.value
  })
  tableData.value = res.data.records
  total.value = res.data.total
}

const handleAdd = () => {
  dialogTitle.value = '新增挂号'
  Object.assign(form, {
    id: null,
    patientId: null,
    registrationNo: '',
    department: '',
    doctor: '',
    registrationFee: 10,
    visitDate: '',
    status: '待诊'
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑挂号'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定要删除该挂号记录吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await registrationApi.delete(row.id)
    ElMessage.success('删除成功')
    getList()
  })
}

const handleSubmit = async () => {
  await formRef.value.validate()
  if (form.id) {
    await registrationApi.update(form)
    ElMessage.success('更新成功')
  } else {
    await registrationApi.save(form)
    ElMessage.success('保存成功')
  }
  dialogVisible.value = false
  getList()
}

getList()
</script>
