# TaskManager

## Postup spustenia:<br>
<ol>
  <li>Nastaviť v docker-compose premenné: MYSQL_ROOT_PASSWORD a MYSQL_DATABASE + volumes</li>
  <li>docker-compose up -d</li>
  <li>Nastaviť pripojenie k PostgreSQL</li>
</ol>

## Testovanie API:
### Tasks:
<p>
V Postmanovi vykonaj nalsedujůce kroky:
</p>
<ol>
  <li>POST request na: localhost:8080/api/v1/auth/register , príklad body JSON:</li>
{<br>
    "name": "John",<br>
    "surname": "Doe",<br>
    "nickName": "johndoe",<br>
    "email": "user@email.com",<br>
    "phone": "4654565465",<br>
    "password": "UserPassword*1",<br>
    "dateOfBirth": "1990-01-01",<br>
    "gender": "MALE"<br>
}
  <li>JWT token z odpovede si ulož do cookie pre danú doménu (napr.: localhost:8080/*) vo fomáte: token=*hodnota_tokenu*</li>
  <li>Možeš skúšať jednotlivé endpointy definované v TaskController, napr. JSON na vytvorenie tasku:</li>
{<br>
"priority": "HIGH",<br>
"title": "Task 3",<br>
"description": "Description for Task 3",<br>
"dueDate": "2024-06-03T12:00:00"<br>
}
</ol>
79020340-c715-46cd-9324-19442f529d6e