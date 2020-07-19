### docker命令(先看书，之后再整理)
-   docker images是查看镜像的命令，docker images -a是查看全部镜像命令，通过此命令会出现好多none的镜像，怎么回事？
-   service docker start 启动docker服务
-   docker中容器是最小运行单元，容器之间是隔离的，pod包含一个或多个容器，pod之前是隔离的，pod是k8s的最小单元，pod的容器共享存储卷，pod的目的不是同一个应用的多个实例，而是运行一个应用多个紧密联系的程序，每个程序一个容器，
pod中所有的的人容器网络是共享的，所有容器的网络是一致的。他们可以通过localhost访问其他容器的端口