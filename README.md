# Spdemo

This project was generated using [spring initializer](https://start.spring.io/) Spring Boot version 3.5.7, Java version 17 with Maven (use mvn in terminal).

## Запуск приложения

Для запуска локального сервера, запустите:

```terminal
mvn spring-boot:run
```

Как только сервер запустится, он доступен по адресу `http://localhost:8080/`. 
Документация Swagger доступна по адресу `http://localhost:8080/swagger-ui/index.html`.

## Создание выполнимого JAR

Вы можете запускать полностью автономный JAR файл.

```terminal
mvn package
```

Если Вы посмотрите в целевой каталог, Вы должны увидеть spdemo-<номер версии в pom>.jar. Для просмотра содержимого арихва используйтеto jar tvf:

```terminal
jar tvf target/spdemo-<номер версии в pom>.jar
```

Для запуска приложения испольуйте команду java -jar:

```terminal
java -jar target/spdemo-<номер версии в pom>.jar
```
