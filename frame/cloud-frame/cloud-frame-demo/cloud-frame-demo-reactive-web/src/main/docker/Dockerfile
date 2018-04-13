FROM java:8
VOLUME /tmp
ADD ./cloud-frame-demo-reactive-web.jar /cloud-frame-demo-reactive-web.jar
RUN bash -c 'touch /cloud-frame-demo-reactive-web.jar'
EXPOSE 9090
ENTRYPOINT ["java","-jar","/cloud-frame-demo-reactive-web.jar"]
