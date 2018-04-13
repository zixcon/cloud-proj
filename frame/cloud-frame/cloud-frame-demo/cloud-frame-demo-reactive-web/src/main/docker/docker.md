* Q
    
        Error response from daemon: conflict: unable to remove repository reference "cloud-frame-demo/cloud-frame-demo-reactive-web" (must force) - container a3fe6f2da487 is using its referenced image 17cbd9721a31
        原因:Docker无法删除images,由于是依赖container。 
        
        停止所有的container,这样才能够删除其中的images:
        docker stop $(docker ps -a -q)

* Q

        docker run -p 9090:9090 -v /data:/data -d cloud-frame-demo/cloud-frame-demo-reactive-web:latest

        