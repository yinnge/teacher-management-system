# 第一阶段：构建
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# 增加 -Dfile.encoding=UTF-8 防止中文乱码
RUN mvn clean package -DskipTests -Dfile.encoding=UTF-8

# 第二阶段：运行
FROM eclipse-temurin:17-jre
WORKDIR /app

# ⚠️ 确认你的 jar 包名是这个
COPY --from=build /app/teacher-management-system-web/target/teacher-management-system-web-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]