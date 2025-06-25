#!/bin/bash

# Очистка и сборка проекта
echo "Building project..."
mvn clean package -DskipTests

# Создание Docker образов
echo "Building Docker images..."
docker-compose build

# Запуск контейнеров
echo "Starting containers..."
docker-compose up -d

# Ожидание запуска сервисов
echo "Waiting for services to start..."
sleep 30

# Проверка статуса сервисов
echo "Checking services status..."
curl -s http://localhost:8084/actuator/health
curl -s http://localhost:8085/actuator/health

echo "Build and deployment completed!" 