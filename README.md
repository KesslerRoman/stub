# Заглушка на Java

## Запуск

```shell
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-javaagent:jolokia-agent-jvm-2.0.3-javaagent.jar=port=7777,host=localhost"
```

## Описание

**Метод GET**

URL запроса:
```
/api/get
```
Пример ответа:

```json
{
    "method": "default_user",
    "code": "default_password",
    "date": "14-06-2024 14:56:19"
}
```

**Метод POST**

URL запроса:
```
/api/post
```

Пример данных для запроса:
```json
{
    "login": "user",
    "password": "1234"
}
```

Пример ответа:
```json
{
    "login": "user",
    "password": "1234",
    "date": "13-06-2024 14:39:15"
}
```

При неверных данных в запросе, возвращается ошибка ```400```. Пример ответа:
```json
{
    "msg": "Bad request",
    "code": 400
}
```