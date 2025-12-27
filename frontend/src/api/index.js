import request from '@/utils/request'

export const patientApi = {
  getList: (params) => request.get('/patient/list', { params }),
  getById: (id) => request.get(`/patient/${id}`),
  save: (data) => request.post('/patient', data),
  update: (data) => request.put('/patient', data),
  delete: (id) => request.delete(`/patient/${id}`),
  getAll: () => request.get('/patient/all')
}

export const registrationApi = {
  getList: (params) => request.get('/registration/list', { params }),
  getById: (id) => request.get(`/registration/${id}`),
  save: (data) => request.post('/registration', data),
  update: (data) => request.put('/registration', data),
  delete: (id) => request.delete(`/registration/${id}`)
}

export const supplierApi = {
  getList: (params) => request.get('/supplier/list', { params }),
  getById: (id) => request.get(`/supplier/${id}`),
  save: (data) => request.post('/supplier', data),
  update: (data) => request.put('/supplier', data),
  delete: (id) => request.delete(`/supplier/${id}`),
  getAll: () => request.get('/supplier/all')
}

export const medicineApi = {
  getList: (params) => request.get('/medicine/list', { params }),
  getById: (id) => request.get(`/medicine/${id}`),
  save: (data) => request.post('/medicine', data),
  update: (data) => request.put('/medicine', data),
  delete: (id) => request.delete(`/medicine/${id}`),
  getAll: () => request.get('/medicine/all')
}

export const medicineSaleApi = {
  getList: (params) => request.get('/medicineSale/list', { params }),
  getById: (id) => request.get(`/medicineSale/${id}`),
  save: (data) => request.post('/medicineSale', data),
  update: (data) => request.put('/medicineSale', data),
  delete: (id) => request.delete(`/medicineSale/${id}`)
}

export const hospitalizationApi = {
  getList: (params) => request.get('/hospitalizationFee/list', { params }),
  getById: (id) => request.get(`/hospitalizationFee/${id}`),
  save: (data) => request.post('/hospitalizationFee', data),
  update: (data) => request.put('/hospitalizationFee', data),
  delete: (id) => request.delete(`/hospitalizationFee/${id}`)
}
