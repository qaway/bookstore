FROM amazoncorretto:11

EXPOSE 8080

LABEL project="local" component="test" app="bookstore-app"

RUN yum install shadow-utils.x86_64 -y && \
    yum clean all -y && \
    groupadd -r bookstore -f -g 1001 && \
    useradd -u 1002 -r -g bookstore -m -d /bookstore -s /sbin/nologin bookstore && \
    mkdir -p /dumps/bookstore/tmp && \
    mkdir -p /tmps && \
    chown -R bookstore:bookstore /bookstore && \
    chown -R bookstore:bookstore /tmps && \
    chown -R bookstore:bookstore /dumps
USER bookstore
COPY application.properties /bookstore/application.properties
#COPY logback.xml /bookstore/logback.xml
COPY bookstore-app.jar /bookstore/bookstore-app.jar


CMD ["java", "-Xlog:gc", "-server", "-Xmn700m", "-Xms2048M", "-Xmx2048M", "-Dserver.tomcat.basedir=/tmps", "-Djava.io.tmpdir=/tmps",\
"-XX:+UseG1GC", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/dumps/bookstore",\
"-jar", "/bookstore/bookstore-app.jar", "--spring.config.location=file:/bookstore/application.properties"]