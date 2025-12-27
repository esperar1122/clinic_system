# 诊所管理系统

一个基于 SpringBoot + Vue3 的诊所管理系统，实现了患者管理、挂号记录、药品管理、供应商管理、药品零售和住院收费等功能。

## 技术栈

### 后端
- SpringBoot 2.7.18
- MyBatis-Plus 3.5.5
- MySQL 8.0
- JDK 1.8
- Maven 3.9.0

### 前端
- Vue 3.3.4
- Vite 5.0.8
- Element Plus 2.4.4
- Axios 1.6.2
- Vue Router 4.2.5

## 功能模块

1. **患者管理** - 管理患者基本信息
2. **挂号记录** - 添加和管理挂号信息
3. **药品管理** - 对药品进行管理，包括库存
4. **供应商管理** - 管理药品供应商信息
5. **药品零售** - 药品出售和结算
6. **收费管理** - 住院信息和收费管理

## 项目结构

```
clinic_system/
├── backend/          # 后端SpringBoot项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/clinic/
│   │   │   │       ├── controller/   # 控制器层
│   │   │   │       ├── service/      # 服务层
│   │   │   │       ├── mapper/       # 数据访问层
│   │   │   │       ├── entity/       # 实体类
│   │   │   │       ├── config/       # 配置类
│   │   │   │       └── common/       # 公共类
│   │   │   └── resources/
│   │   │       ├── application.yml   # 配置文件
│   │   │       └── schema.sql        # 数据库脚本
│   │   └── test/
│   └── pom.xml
└── frontend/         # 前端Vue3项目
    ├── src/
    │   ├── views/          # 页面组件
    │   ├── router/         # 路由配置
    │   ├── api/            # API接口
    │   ├── utils/          # 工具类
    │   ├── App.vue
    │   └── main.js
    ├── package.json
    └── vite.config.js
```

## 快速开始

### 1. 数据库配置

创建MySQL数据库并执行SQL脚本：

```bash
mysql -u root -p
```

```sql
source backend/src/main/resources/schema.sql
```

### 2. 后端启动

修改 `backend/src/main/resources/application.yml` 中的数据库配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/clinic_system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: your_password
```

启动后端服务：

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

后端服务将运行在 http://localhost:8080

### 3. 前端启动

安装依赖并启动前端服务：

```bash
cd frontend
npm install
npm run dev
```

前端服务将运行在 http://localhost:3000

## API接口

### 患者管理
- GET /api/patient/list - 获取患者列表
- GET /api/patient/{id} - 获取患者详情
- POST /api/patient - 新增患者
- PUT /api/patient - 更新患者
- DELETE /api/patient/{id} - 删除患者
- GET /api/patient/all - 获取所有患者

### 挂号记录
- GET /api/registration/list - 获取挂号列表
- GET /api/registration/{id} - 获取挂号详情
- POST /api/registration - 新增挂号
- PUT /api/registration - 更新挂号
- DELETE /api/registration/{id} - 删除挂号

### 药品管理
- GET /api/medicine/list - 获取药品列表
- GET /api/medicine/{id} - 获取药品详情
- POST /api/medicine - 新增药品
- PUT /api/medicine - 更新药品
- DELETE /api/medicine/{id} - 删除药品
- GET /api/medicine/all - 获取所有药品

### 供应商管理
- GET /api/supplier/list - 获取供应商列表
- GET /api/supplier/{id} - 获取供应商详情
- POST /api/supplier - 新增供应商
- PUT /api/supplier - 更新供应商
- DELETE /api/supplier/{id} - 删除供应商
- GET /api/supplier/all - 获取所有供应商

### 药品零售
- GET /api/medicineSale/list - 获取销售列表
- GET /api/medicineSale/{id} - 获取销售详情
- POST /api/medicineSale - 新增销售
- PUT /api/medicineSale - 更新销售
- DELETE /api/medicineSale/{id} - 删除销售

### 住院收费
- GET /api/hospitalizationFee/list - 获取住院收费列表
- GET /api/hospitalizationFee/{id} - 获取住院收费详情
- POST /api/hospitalizationFee - 新增住院收费
- PUT /api/hospitalizationFee - 更新住院收费
- DELETE /api/hospitalizationFee/{id} - 删除住院收费

## 注意事项

1. 确保MySQL服务已启动
2. 确保JDK版本为1.8
3. 确保Maven版本为3.9.0
4. 前端需要先安装依赖 npm install
5. 后端默认端口8080，前端默认端口3000

## 开发说明

- 后端使用MyBatis-Plus进行数据访问，代码生成器可提高开发效率
- 前端使用Element Plus组件库，提供统一的UI风格
- 前端通过axios调用后端RESTful API
- 前后端分离开发，通过代理解决跨域问题

## 许可证

MIT License
