# Charles7c API

[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/Charles7c/charles7c-api/blob/main/LICENSE)
![Release](https://img.shields.io/badge/RELEASE-v1.0.0-%23ff3f59.svg)

### 简介

提供个人所需 API。

### 开始

```bash
# 1.克隆本项目
git clone https://github.com/Charles7c/charles7c-api.git

# 2.在 IDE（IntelliJ IDEA/Eclipse）中打开本项目

# 3.创建数据库

# 4.修改配置文件中的数据源信息
# [4.也可以在 IntelliJ IDEA 中直接配置程序启动环境变量（DB_HOST、DB_PORT、DB_USER、DB_PWD）]

# 5.启动程序
# 5.1 启动成功：访问 http://localhost:8000/，页面输出：Charles7c API started successfully.
# 5.2 接口文档：http://localhost:8000/doc.html

# 6.部署
# 6.1 通过 GitHub Actions 部署
#   6.1.1 服务器安装好 docker 及 docker-compose（参考：https://blog.charles7c.top/categories/fragments/2022/10/31/CentOS%E5%AE%89%E8%A3%85Docker）
#   6.1.2 将 docker 目录上传到 / 目录下，授权（chmod -R 777 /docker）
#   6.1.3 修改 docker-compose.yml 中的数据库配置、API服务配置、Nginx配置
#   6.1.4 上传 HTTPS 证书到 /docker/nginx/cert 目录下，并修改 /docker/nginx/conf/nginx.conf 配置中的证书名
#   6.1.5 执行 docker-compose up mariadb nginx -d 创建并后台运行容器
#   6.1.6 根据个人需要修改 .github/workflows/deploy.yml 文件
#   6.1.7 在 GitHub 仓库配置相应 secrets（SERVER_HOST、SERVER_PORT、SERVER_USERNAME、SERVER_PASSWORD）
#   6.1.8 push 到 GitHub 仓库，触发 GitHub Actions
# 6.2 其他方式部署
```

### 已提供 API

- [x] 查询文章阅读数
- [x] 记录页面浏览量

### License

- 遵循 [MIT](https://github.com/Charles7c/charles7c-api/blob/main/LICENSE) 开源许可协议
- Copyright © 2022-present Charles7c