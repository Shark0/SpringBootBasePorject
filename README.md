# Spring Boot Base Project
多模組API專案

## 模組介紹
### Base
定義API Service代碼架構

### Application
透過Base實作專案API內容

## 實作範例
### JWT登入範例
實作Json Web Token登入範例

http://www.ruanyifeng.com/blog/2018/07/json_web_token-tutorial.html

### RBAC權限管理
實作以角色為基礎的權限存取控制，透過帳號、角色、與權限關係來設定一個帳號可以存取的API以及可以看到的選單

https://zh.wikipedia.org/wiki/%E4%BB%A5%E8%A7%92%E8%89%B2%E7%82%BA%E5%9F%BA%E7%A4%8E%E7%9A%84%E5%AD%98%E5%8F%96%E6%8E%A7%E5%88%B6

### 開始
#### 先在本機localhost設定好測試用的Postgres連線資訊
1. 帳號：root 
2. 密碼：root 
3. database: base


#### 建制Demo資料
1. 設定開發環境預先執行的profile為dev並執行專案，如果MySql有設定成功，JPA會自動建制專案要用到的Table
2. 執行Application模組理的ApplicationTests來自動新增root帳號、admin角色、基礎權限(permission、role、menu、account)、選單
3. 執行Application模組

#### API文件
啟動Application模組就可觀看Swagger API文件 http://localhost:8081/swagger-ui.html#/ 
可以透過文件來新增帳號，透過root帳號來設定選單、角色、與權限，也可賦予新帳號admin角色。P.S. 登入API跟註冊API不需要在Swagger文件上輸入JWT
