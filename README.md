# ⛩️ Mission Web-Archive v3.0

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![Patterns](https://img.shields.io/badge/patterns-Factory%20%7C%20Strategy%20%7C%20Decorator-green)

Третья итерация системы анализа магических инцидентов. Мы перешли от локального GUI-приложения к **распределенной веб-архитектуре**, превратив систему в полноценное облачное хранилище (Архивный хаб) для магов-оперативников.

---

## 🌟 Что нового в v3.0?

- **Централизованное хранение**: Больше никакой потери данных при перезапуске! Все миссии запечатаны в **PostgreSQL**.
- **Магия JSONB**: Использование типа данных `JSONB` позволяет эффективно хранить динамические атрибуты (`extraFields`) на уровне базы данных, сохраняя гибкость NoSQL в реляционной среде.
- **RESTful API**: Полное управление архивом через стандартизированные HTTP-эндпоинты.
- **Web UI**: Современный фронтенд на базе **HTML5/JavaScript**, обеспечивающий асинхронную загрузку файлов и моментальное обновление витрины архива.

---

## 🛠 Технологии и паттерны

- **Backend**: Spring Boot 3.2.4 (Data JPA, Web, SpringDoc).
- **Database**: PostgreSQL 15+ (персистентное хранение и JSON-индексация).
- **Паттерны проектирования**:
  - **Factory Method**: Динамический выбор парсера в зависимости от типа свитка (формата файла).
  - **Strategy**: Инкапсулированная логика разбора форматов JSON, XML, YAML, TXT и A5.
  - **Decorator**: Многослойное формирование отчетов — от базовой выжимки до подробного анализа всех дополнительных параметров.

---

## 📁 Структура проекта

```text
├── src/main/java/com/
│   ├── controller/      # API Эндпоинты (Mission, Report)
│   ├── service/         # Бизнес-логика (Import, Archive, Report)
│   ├── repository/      # Доступ к PostgreSQL (Spring Data JPA)
│   ├── entity/          # Сущности БД (Mission, Curse, Sorcerer)
│   ├── parser/          # Реализация парсеров и Фабрики
│   ├── report/          # Система Декораторов для отчетов
│   └── MissionsApplication.java # Точка входа в систему
├── src/main/resources/
│   ├── static/          # Web-интерфейс (index.html, app.js)
│   └── application.yaml # Конфигурация сервера и БД
└── pom.xml              # Зависимости Maven
```

---

## 🚀 Ритуал запуска

### 1. Подготовка PostgreSQL

Перед запуском создайте базу и пользователя (данные из `application.yaml`):

```sql
CREATE DATABASE missions_db;
CREATE USER test WITH PASSWORD 'test';
GRANT ALL PRIVILEGES ON DATABASE missions_db TO test;
```

### 2. Сборка и старт сервера

```bash
# Очистка и установка зависимостей
mvn clean install

# Запуск веб-архива
mvn spring-boot:run
```

### 3. Доступные каналы

| Канал | URL |
|-------|-----|
| Архивный интерфейс | `http://localhost:8080` |
| Документация (Swagger) | `http://localhost:8080/swagger-ui/index.html` |

---

## 📄 Работа с форматами

Система автоматически распознает и импортирует данные из:

| Формат | Описание |
|--------|----------|
| **JSON / XML** | Стандартные протоколы обмена |
| **YAML** | Структурированные записи о проклятиях |
| **TXT** | Текстовые логи боевых групп |
| **A5** | Закрытый формат на базе пайп-разделителей (`\|`) |

---

## 👩‍💻 Автор

**София Егорова** (@esvsophia)  
Студентка группы Б24-902, НИЯУ МИФИ 2026

---

## 📜 Лицензия

Проект распространяется под лицензией **Apache License 2.0**.
