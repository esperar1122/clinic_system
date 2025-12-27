import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/Layout.vue'

const routes = [
  {
    path: '/',
    component: Layout,
    redirect: '/patient',
    children: [
      {
        path: 'patient',
        name: 'Patient',
        component: () => import('@/views/Patient.vue'),
        meta: { title: '患者管理' }
      },
      {
        path: 'registration',
        name: 'Registration',
        component: () => import('@/views/Registration.vue'),
        meta: { title: '挂号记录' }
      },
      {
        path: 'medicine',
        name: 'Medicine',
        component: () => import('@/views/Medicine.vue'),
        meta: { title: '药品管理' }
      },
      {
        path: 'supplier',
        name: 'Supplier',
        component: () => import('@/views/Supplier.vue'),
        meta: { title: '供应商管理' }
      },
      {
        path: 'medicineSale',
        name: 'MedicineSale',
        component: () => import('@/views/MedicineSale.vue'),
        meta: { title: '药品零售' }
      },
      {
        path: 'hospitalization',
        name: 'Hospitalization',
        component: () => import('@/views/Hospitalization.vue'),
        meta: { title: '收费管理' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
