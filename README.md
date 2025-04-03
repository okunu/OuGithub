# OuGithub
android github客户端，主要有如下功能：

- 可作为游客浏览github上的热门内容
- 用户可浏览GitHub仓库信息（信息内容自定），且不跳转外部浏览器
- 可搜索github上的内容，能按编程语言搜索仓库并按星标排序
- 用户可登录GitHub账户并查看个人资料下的仓库列表。认证状态在应用重启后保持
- 用户可注销返回匿名状态
- 应用支持横竖屏模式

# 构建指南：

下载代码，编译即可，如果不想自己构建，可以下载已经构建好的release版本apk

# 运行指南：

- 首页有search和user两个table，不登录可浏览search内容，上方有搜索栏以及语言选择和star排序方式选择
- 点击user按钮，若没有登录则会跳转到登录页面，目前支持github授权登录方式（即会通过url跳转到github网站，登录授权），登录成功后，user页面将会显示用户的仓库情况
- 在user面板点击 repos，即可查看自己的仓库情况，点击任意一个仓库，可查看详情
- 在自己的repo下有设置issue功能，但目前接口异常，返回404，还未解决
- 回到user面板，可以点击 logout 实现注销登录功能，若想再次登录，则重新点击user跳转到登录页面即可。

![使用1](https://github.com/user-attachments/assets/e983ec2c-b514-4040-bb73-4aa24b5d9ce3)
![使用2](https://github.com/user-attachments/assets/66ed6c11-97db-493c-b5fb-3e2d55e869e3)

# 框架设计：
整体上有这么些模块：

- 功能模块：主页面 登录  repos模块 搜索模块 user模块
- 基础模块：基础工具 基础UI/vm 网络组件 组件通信桥


整体采用组件化结合MVVM方式。使用组件化开发方式，能够让代码彻底解耦，健壮性提升。而MVVM可以分离业务以及ui，结合android的jetpack，还是非常高效和优雅的。

一般来说，组件化都会有这么两个问题：

- 组件间如何通信
- 如何跳转页面

页面的跳转较简单，一般采用arouter即可。本工程中组件的通信示意如下：

![image](https://github.com/user-attachments/assets/38913870-783d-4d36-b3db-bb2a0e11d4bb)

![image](https://github.com/user-attachments/assets/a42c097c-092f-4ab8-a489-a68b4b4eea7a)

另外本工程还使用MVVM架构来实现相关业务，mvvm能够帮助解耦业务和ui逻辑，代码也会更优雅。

以login模块为例：当用户授权成功后，login模块将会去获取用户信息，然后再上层界面跳转到主页面。

![image](https://github.com/user-attachments/assets/d540aa2a-fa33-41c4-b5cb-34a94a5ad525)

# 测试用例：
暂时只做了1个，后面继续完善


