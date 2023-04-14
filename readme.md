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

#### Команды запуска тестов на различных платформах
Mobile тесты (android):\
emulationTest - используется эмулятор, `local.properties`\
realTest - используется реальное устройство, `real.properties`\
browserstackTest - используется сервис browserstack, `browserstack.properties`
```
gradle clean emulationTest
gradle clean realTest
gradle clean browserstackTest
```
Веб тесты:\
localTest - используется эмулятор, `local.properties`\
selenideTest - используется реальное устройство, `selenide.properties`
```
gradle clean localTest
gradle clean selenideTest
```
API тесты:\
apiTest - используется эмулятор, `api.properties`
```
gradle clean apiTest
```

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Jenkins.svg" width="25" height="25"  alt="Jenkins"/><a name="Jenkins">Jenkins</a><a target="_blank" href="https://jenkins.autotests.cloud/job/golem272_alfa/"> job</a>
Параметры сборки в Jenkins:

- <code>TYPETEST</code> выбор типа тестов, из CI доступны:
  - browserstackTest
  - selenideTest
  - apiTest

#### Доп параметры для команды - selenideTest
- <code>BROWSER</code> браузер, по умолчанию chrome
- <code>VERSION</code> версия браузера, по умолчанию 100.0
- <code>RESOLUTION</code> размер окна браузера, по умолчанию 1280x720
- <code>REMOTE_URL</code> адрес удаленного сервера
- <code>REMOTE_URL_VIDEO</code> адрес удаленного сервера

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/report.png" width="25" height="25"  alt="report"/>Allure отчеты:
### <img src="/images/logos/Allure.svg" width="25" height="25"  alt="Allure"/>Отчет в Allure</a> <a target="_blank" href="https://jenkins.autotests.cloud/job/golem272_alfa/10/"> report</a>
#### Основной отчет
<p align="center">
<img title="Allure Overview Dashboard" src="/images/screens/overviewReport.jpg">
</p>

#### Тесты
<p align="center">
<img title="Allure Suites" src="/images/screens/testsReport.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

### <img src="/images/logos/Allure_TO.svg" width="25" height="25"  alt="Allure TestOps"/>Отчет в Allure</a> <a target="_blank" href="https://allure.autotests.cloud/jobrun/20210"> TestOps</a>
#### Основной отчет
<p align="center">
<img title="Allure Overview Dashboard" src="/images/screens/overviewOps.jpg">
</p>

#### Тесты
<p align="center">
<img title="Allure Suites" src="/images/screens/testsOps.jpg">
</p>

#### Пример ручных тестов
<p align="center">
  <img title="Allure TestOps manual tests" src="/images/screens/manual.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Telegram.svg" width="25" height="25"  alt="Telegram"/>Telegram уведомления</a>
После завершения сборки специальный бот, созданный в Telegram, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне.
<p align="center">
<img title="Allure Overview Dashboard" src="/images/screens/telegram.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Jira.svg" width="25" height="25" alt="Jira"/>Jira интеграция</a>
<p align="center">
  <img title="Allure TestOps overview" src="/images/screens/jira.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Selenoid.svg" width="25" height="25" alt="Selenoid"/>Selenoid видеозапись тестов</a>
### Видео запись прохождения тестов
<p align="center">
  <img title="Selenoid video for test4" src="/images/gif/videoWeb.gif">
  <img title="Selenoid video for test4" src="/images/gif/vidoMobile.gif">
</p>

[Вернуться к оглавлению](#Содержание)
