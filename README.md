# Дипломный проект по профессии «Тестировщик»
Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
### Окружение:
- IDE: IntelliJ IDEA Community Edition 2022.1;
- Java: OpenJDK 11;
- Docker Desktop 4.11.1 (84025).
### Развертывание SUT:
1. Запустить Docker Desktop;
2. Запустить IntelliJ IDEA;
3. Склонировать репозиторий с GitHub [`git clone`](https://github.com/Er1kus/Diplom_Marrakesh.git)
4. Открыть терминал в IDE, запустить контейнеры командой: `docker-compose up -d --force-recreate --build`
5. В новом терминале запустить приложение **aqa-shop.jar** командой:

- для подключения к БД MySQL: 

`java -jar .\artifacts\aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`

- для подключения к БД PostgreSQL: 

`java -jar .\artifacts\aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`
### Запуск тестов и формирование отчетов:
Открыть новый терминал и запустить тесты командой:
+ Для MySQL`.\gradlew test -Durl=jdbc:mysql://localhost:3306/app`

+ Для PostgreSQL `.\gradlew test -Durl=jdbc:postgresql://localhost:5432/app`

Сформировать отчет Allure по итогам прогона тестов командой: `.\gradlew allureServe`;
### Завершение работы
- После ознакомления с визуальным отчетом Allure в браузере, закрыть отчет в терминале командой:
**CTRL+C, y, Enter**;
- В терминале, в котором было запущено приложение **aqa-shop.jar**, остановить его работу командой: **CTRL+C**;
- В терминале, в котором были запущены контейнеры, "потушить" их командой: `docker-compose down`
