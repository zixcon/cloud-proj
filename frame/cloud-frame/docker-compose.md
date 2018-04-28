* docker compose容器编排一般有三个步骤：
    1. 编写Dockerfile，定义镜像的构建参数。
    2. 编写docker-compose.yml文件，定义应用的挂载，环境变量，启动参数等。
    3. 执行 docker-compose up 命令，自动执行构建镜像并启动容器和应用。(docker-compose up -d)

* 通过docker-compose编排出的镜像，应该还通过docker-compose rm来删除


