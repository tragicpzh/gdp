# 1 登录
    平台首页为index.html
    用户经过login.html页面登录成功后，会根据用户类型被分配到/administrator/*、/teacher/*、/student/*路径下
    数据库中的role取值为ADM、TEA、STU，分别对应管理员、教师、和学生
# 2 下载服务
    下载服务类DownloadService，网页通过post /download/**请求可以下载**对应的文件，**为文件相对file文件夹的路径