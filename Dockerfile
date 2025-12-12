# 第一阶段：使用 Maven 构建打包
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# 开始打包
RUN mvn clean package -DskipTests

# 第二阶段：运行环境
FROM openjdk:17-jdk-slim
# 从第一阶段复制生成的 jar 包
COPY --from=build /app/teacher-management-system-web/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]