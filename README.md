# 1 登录
    平台首页为index.html
    用户经过login.html页面登录成功后，会根据用户类型被分配到/administrator/*、/teacher/*、/student/*路径下
    数据库中的role取值为ADM、TEA、STU，分别对应管理员、教师、和学生
# 2 下载服务
    下载服务类DownloadService，网页通过post /download/**请求可以下载**对应的文件，**为文件相对file文件夹的路径包括文件名
# 3 上传服务
    上传服务类UploadService，MultipartFile file为上传的文件，String subPath为上传文件相对于file文件夹的相对路径，不包括文件名
# 4 时间轴对象
    对象名为TimeAxis，其中的所有参数和方法都是静态的，具体使用方法可以查看TimeAxis中的注释
# 5 管理员用户管理
    测试数据已上传，注意输入的学院Id以及专业Id必须存在数据库内（college和major两张表），否则无法录入数据