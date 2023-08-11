# Microservice-Spring-Demo
Микросервисы. Пэт проект.

## В сервисе CarService:
# B базу данных сохраняется нформация об автомобилях и деталях.
Об автомобилях известно: VIN, гос. номер, производитель, марка, год выпуска.<br>
Автомобили укомплектованы произвольным набором деталей.
Есть возможность устанавливать владение водителем автомобиля.<br>
Есть возможность установки и замены деталей в автомобиле.<br>
1. CRUD методы для автомобилей
2. Методы для реализации бизнес-требований

## В сервисе DriverService:
# B базу данных сохраняется нформация о  водителях.
О водителях известно: ФИО, паспорт, категория прав, дата рождения, стаж.<br>
  1. CRUD методы для водителей
  2. Методы для реализации бизнес-требований

### Примененные технологии:
- Spring Boot
- JPA
- Hibernate
- PostgreSQL
- REST
- Gradle
