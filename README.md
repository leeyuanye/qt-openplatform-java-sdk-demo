# 项目说明
* 本项目是轻推开放平台javaSDK的示例项目，用于帮助开发者快速搭建轻应用。

# 项目功能点
* 定时刷新access_token示例
* 获取用户名称、头像
* 发送单人纯文本消息
* 群发纯文本消息
* 发送待办消息
* 完成待办消息
* 获取企业成员信息

# 使用说明
1. 请首先在轻推后台管理系统中为轻应用授予`只读通讯录权限`
2. 请在轻应用编辑页设置安全域名为127.0.0.1，并填写轻应用入口：`https://open.qingtui.cn/v1/oauth2/authorize?appid=APP_ID&redirect_uri=http%3a%2f%2f127.0.0.1%3a8080%2findex`,注意替换APP_ID
3. 检出项目后，请在qingtui.properties里填写好APP_ID和APP_SECRET
4. 运行JavaSdkDemoApplication的main方法
5. 在轻推网页版客户端中点击轻应用进行体验
6. 若需要在轻推手机客户端中体验，需要将安全域名设置手机可以访问的开发服务器ip地址（如：192.168.166.1），并将轻应用入口改为：
`https://open.qingtui.cn/v1/oauth2/authorize?appid=APP_ID&redirect_uri=http%3a%2f%2f192.168.166.1%3a8080%2findex`