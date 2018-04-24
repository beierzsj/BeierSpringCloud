# 使用Docker构建微服务
```shell
docker  version #查看版本
```
```shell
docker  search centos#搜索可用docker镜像
```
```shell
docker  images 查看当前docker所有镜像
```
```shell
docker  pull  centos #下载镜像
```
```shell
cat   centos.tar | docker import  -  centos6  #Docker导入镜像
```
```shell
docker  export  id  > cenos6.tar  #Docker导出镜像
```
```shell
docker  run   centos echo "hello word"#在docker容器中运行hello world!
```
```shell
docker  run  centos yum install ntpdate#在容器中安装ntpdate的程序
```
```shell
docker  ps -l 命令获得最后一个容器的id，docker   ps  -a查看所有的容器。
```

>运行docker commit 提交刚修改的容器，例如：

```shell
docker  commit  2313132  centos:v1
```
```shell
docker run -i -t centos /bin/bash 在容器里启动一个/bin/bash shell环境，可以登录进入操作，其中-t 表示打开一个终端的意思，-i表示可以交互输入。
```
```shell
docker run -d -i -t centos /bin/bash 在后台启动
```
```shell
docker attach CONTAINER ID 进入在后台启动后的这个容器
```
```shell
docker  run  -d  centos:v1  /bin/bash  ,-d表示在后台启动，以daemon方式启动。
```
```shell
docker run -d -p 80:80 -p 8022:22 centos:latest /usr/bin/sshd -D
```
```shell
docker stop  id 关闭容器
```
```shell
docker start  id 启动某个容器
```
```shell
docker  rm  id 删除容器，docker  rmi  images删除镜像
```

>进入容器:
>如果 docker 版本已经在 1.3 以上了, 那么可以用 docker exec 这个命令:

```shell
docker exec -it <CONTAINER ID> /bin/bash             /这样你就进到这个container 里面了，这个bash退出也不会影响之前 docker run 启动的 bash/
```
```shell
docker  -p 80:80 centos 代表映射 
```

参考：

> Docker常用命令：http://www.infoq.com/cn/articles/docker-command-line-quest/
