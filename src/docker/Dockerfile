FROM amazoncorretto:11

EXPOSE 8080

LABEL project="local" component="test" app="bookstore-app"

RUN yum install shadow-utils-4.1.5.1 -y && \
    yum clean all -y && \
    groupadd -r bookstore -f -g 1001 && \
    useradd -u 1002 -r -g bookstore -m -d /bookstore -s /sbin/nologin bookstore && \
    mkdir -p /dumps/bookstore/tmp && \
    mkdir -p /tmps

USER bookstore
COPY --chown=bookstore:bookstore application.properties /bookstore/application.properties
COPY --chown=bookstore:bookstore bookstore-app.jar /bookstore/bookstore-app.jar


CMD ["java", "-Xlog:gc", "-server", "-Xmn700m", "-Xms2048M", "-Xmx2048M", "-Dserver.tomcat.basedir=/tmps", "-Djava.io.tmpdir=/tmps",\
"-XX:+UseG1GC", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/dumps/bookstore",\
"-jar", "/bookstore/bookstore-app.jar", "--spring.config.location=file:/bookstore/application.properties"]