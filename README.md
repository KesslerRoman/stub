# Заглушка на Java

**Метод GET**

URL запроса:
```
/api/get
```
Пример ответа:

```json
{
    "method": "GetMethod",
    "code": 200
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