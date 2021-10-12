## KickSharing

Данное приложение представляет собой сервис по аренде самокатов.
Использованные технологии: Spring Boot 2, Java 16, Hibernate ORM, Gradle.

Базовые характеристики сущностей:
1)  Пользователь: имя, фамилия, возраст, ID.
2)  Самокат: наименование, статус, ID.

Арендовать самокаты могут только пользователи старше 18 лет.  
Каждый пользователь может арендовать до 5 самокатов включительно.  
Одновременно самокат может арендовать только один пользователь.

Поддерживаются следующие методы API:
+ Добавление/поиск/удаление пользователя
+	Изменение возраста пользователя
+	Добавление/удаление самоката
+	Аренда пользователем самокатов
+	Завершение пользователем аренды самокатов
+	Отображение всех свободных самокатов
+	Отображение последних 5 добавленных пользователей и список арендованных ими самокатов

Работоспособность методов подтверждается тестами.  
В корне проекта содержится конфиг `KickSharing.postman_collection.json` для Postman.
