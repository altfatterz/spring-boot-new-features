## Spring Boot 2.3 New Features

1. [Graceful shutdown](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-graceful-shutdown)

```yaml
server:
  shutdown: graceful
spring:
  lifecycle:
    # default
    timeout-per-shutdown-phase: 30s
````    

```bash
2020-05-22 16:51:34.621  INFO 5839 --- [extShutdownHook] o.s.b.w.e.tomcat.GracefulShutdown        : Commencing graceful shutdown. Waiting for active requests to complete
2020-05-22 16:51:37.860  INFO 5839 --- [tomcat-shutdown] o.s.b.w.e.tomcat.GracefulShutdown        : Graceful shutdown complete
```

2. [Buildpacks](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#buildpacks)

```bash
$ ./mvnw clean package spring-boot:build-image
```

```bash
$ docker images
REPOSITORY                         TAG                     IMAGE ID            CREATED             SIZE
spring-boot-new-features           0.0.1-SNAPSHOT          ae1c6cd5bde5        40 years ago        230MB
```

Customise the name:

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <image>
            <name>docker.io/altfatterz/${project.artifactId}:latest</name>
        </image>
    </configuration>
</plugin>
```

```bash
$ docker images
REPOSITORY                            TAG                     IMAGE ID            CREATED             SIZE
spring-boot-new-features              0.0.1-SNAPSHOT          ae1c6cd5bde5        40 years ago        230MB
altfatterz/spring-boot-new-features   latest                  ad563b91e642        40 years ago        232MB
```

```bash
$ docker push altfatterz/spring-boot-new-features:latest
```

Start the container

```bash
$ docker container run -p 8080:8080 altfatterz/spring-boot-new-features:latest
```

```
$ kubectl apply -f k8s.yaml
$ kubectl get all
$ kubectl port-forward svc/demo 8080:8080
```

3. [Liveness and Readiness probes](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-kubernetes-probes)

```yaml
        livenessProbe:
          httpGet:
            path: /actuator/health/liveness
            port: 8080
          # wait 5 seconds before performing the first probe
          initialDelaySeconds: 5
          # probe every 3 seconds
          periodSeconds: 3
        readinessProbe:
          httpGet:
            path: /actuator/health/readiness
            port: 8080
          initialDelaySeconds: 5
          periodSeconds: 3
```

Resources

1. kind tool: https://kind.sigs.k8s.io/