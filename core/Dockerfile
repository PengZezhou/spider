FROM estv/openjdk-8-alpine:1
#构建参数
ARG WORK_PATH="/opt/app"
# 环境变量
COPY core/target/*.jar $WORK_PATH/
WORKDIR $WORK_PATH
RUN apk add --update font-adobe-100dpi ttf-dejavu fontconfig
ENTRYPOINT exec java $JAVA_OPTS -jar *.jar