 🏫 Course Enrollment System (Spring Boot + Keycloak + JPA + JUnit)

 📌 Описание проекта

Это полноценное REST-приложение для управления системой курсов и студентов.  
Система включает:

- Регистрацию студентов на курсы
- Получение всех записей
- Отмену регистрации
- Отметку курса как завершённого

Проект построен на Spring Boot, Keycloak для авторизации, PostgreSQL, JUnit + Mockito для тестирования.

---

 🧩 Сущности

- Student — студент (ID, имя, email)
- Course — курс (ID, название, описание, вместимость)
- Enrollment — запись на курс (ID, студент, курс, дата, статус завершения)

---

 🔐 Роли

- ADMIN: может просматривать всех студентов и курсы, удалять записи
- STUDENT: может записываться на курсы, просматривать свои записи

---

 ⚙️ Используемые технологии

- Java 17+
- Spring Boot 3
- Spring Security + Keycloak
- Spring Data JPA
- PostgreSQL
- JUnit 5, Mockito
- Maven
- Lombok
- MapStruct

---
 
🚀 Как запустить

1. Склонируй проект
   `bash
   git clone 