# ReqresInAPITests

Тесты для API https://reqres.in/
GET: Single <resource> /api/unknown/{id} - информация о цвете
GET: List <resource> /api/unknown - список ресурсов
GET: Single <resource> NOT FOUND /api/unknown/23 - информация о цвете, id которого не существует

# Описание проекта

Проект представляет из себя набор тестов для тестовых API.
В тестах используются различные приемы создания тестов, которые могут пригодиться в работе над реальным проектом.

# Frameworks

В тестах использованы следующие фреймворки:

1. TestNG
2. RestAssured
3. Lombok

# Структура проекта

1. Пакет DTO - объекты JSON
2. Пакет DataProviders - источники данных для тестов.
   В настоящий момент содержат следующие примеры:

- Object[][]
- Iterator<Object[]>
  Планируется:
- подключить чтение из файла
- подключить чтение из БД (Oracle XE)

3. Пакет Specification - спецификации для request/response
3. Пакет ColorTests - тесты для API:

- SingleColorTest - тесты на GET: Single <resource> /api/unknown/{id}
- ColorListTests - тесты на GET: List <resource> /api/unknown - список ресурсов
- ExamplesTest - общие примеры тестов, которые могут понадобиться при тестировании
Тесты будут дорабатываться по мере изучения новых технологий :))
