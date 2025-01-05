 # Базовый образ - openjdk
FROM openjdk:22-jdk-slim

  # Устанавливаем переменную для пути к приложению в контейнере
ARG JAR_FILE=target/*.jar
ARG APP_HOME=/app

  # Создаем директорию для приложения
RUN mkdir -p ${APP_HOME}

  # Копируем JAR файл
COPY ${JAR_FILE} ${APP_HOME}/app.jar

  # Задаем рабочую директорию
WORKDIR ${APP_HOME}

  # Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]