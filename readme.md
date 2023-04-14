<h1 >Проект автоматизации API для сайта <a href="https://alfabank.ru/ ">alfabank.ru</a></h1>

## <a name="Содержание">Содержание</a>
+ [Описание](#Описание)
+ [Стек технологий](#стек-технологий)
+ [Запуск тестов](#Запуск-тестов)
+ [Сборка в Jenkins](#jenkins-job)
+ [Allure отчеты:](#Allure-отчеты)
+ [Уведомление в Telegram](#telegram-уведомления)
+ [Интеграция с Jira](#Jira-интеграция)
+ [Видео прохождения тестов](#selenoid-видеозапись-тестов)

## <a name="Описание">Описание</a>
Тестовый проект состоит из веб-тестов (UI), тестов API и мобильных тестов (Android).\
Краткий список интересных фактов о проекте:
- [x] `Page Object` проектирование
- [x] Параметризованные тесты
- [x] Различные файлы конфигурации для запуска теста в зависимости от параметров сборки
- [x] Конфигурация с библиотекой `Owner`
- [x] Использование `Lombok` для моделей в API тестах
- [x] Использование request/response спецификаций для API тестов
- [x] Custom Allure listener для API requests/responses логов
- [x] Интеграция с `Allure TestOps`
- [x] Автотесты как тестовая документация
- [x] Интеграция с `Jira`


## Технологии и инструменты

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/IntelliJ_IDEA.png">
<img width="6%" title="Java" src="images/logo/Java_logo.png">
<img width="6%" title="Selenide" src="images/logo/Selenide.png">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.png">
<img width="6%" title="Allure Report" src="images/logo/AllureReports.png">
<img width="6%" title="Gradle" src="images/logo/Gradle.png">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.png">
<img width="6%" title="GitHub" src="images/logo/GitHub.png">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.png">
<img width="6%" title="Allure TestOps" src="images/logo/AllureTestOps.svg">
</p>

## <a name="Запуск тестов">Запуск тестов</a>
#### Про конфигурации
Файлы конфигурации должны находиться по пути:
```
src/test/resources
```
Креды вынесены в отдельный конфиг файл - `credential.properties`\
Ожидаемые конфиги прописаны у соответсвующих команд.

#### Команды запуска тестов 
API тесты
```
gradle clean apiTests
```

[Вернуться к оглавлению](#Содержание)

## Запуск тестов в Jenkins

> Сборка с параметрами позволяет перед запуском изменить параметры для сборки (путем выбора из списка или прямым указанием значения).

Параметры сборки в Jenkins:

<p align="center">
<img src="images/screenshots/JenkinsJob.jpg"/></a>
</p>

<a id="allure"></a>

[Вернуться к оглавлению](#Содержание)

## Отчеты в Allure

### Основное окно

<p align="center">
<img src="images/screenshots/AllureOverview.png">
</p>

### Тесты

<p align="center">
<img src="images/screenshots/AllureBehaviors.png">
</p>

[Вернуться к оглавлению](#Содержание)

## Интеграция с Allure TestOps 

### Тест-кейсы
<p align="center">
<img src="images/screenshots/AllureTestCases.png">
</p>

### Пример мануального тест-кейса
<p align="center">
<img src="images/screenshots/AllureTestOpsManualTest.png">
</p>

### Пример запуска тест-кейсов
<p align="center">
<img src="images/screenshots/AllureTestOpsLaunches.png">
</p>

<a id="jira"></a>
## Интеграция с Jira 
<p align="center">
<img src="images/screenshots/Jira.png">
</p>

<a id="telegram"></a>
## Уведомления в Telegram с использованием бота

<p>
<img src="images/screenshots/TelegramBot.png">
</p>

<a id="video"></a>
## Пример прогона теста в Selenoid

> К каждому тесту в отчете прилагается видео
<p align="center">
  <img src="images/video/VideoGif.gif">
</p>
