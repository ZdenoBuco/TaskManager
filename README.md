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
<p>
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
</p>
  <li>JWT token z odpovede si ulož do cookie pre danú doménu (napr.: localhost:8080/*) vo fomáte: token=*hodnota_tokenu*</li>
  <li>Možeš skúšať jednotlivé endpointy definované v TaskController</li>
</ol>