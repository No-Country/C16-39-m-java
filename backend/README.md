[![MIT License][license-shield]][license-url]

# About the backend project 📝

[![Product Name Screen Shot][product-screenshot]](https://movies-apirest-c77e9f5e2ba2.herokuapp.com/swagger-ui/index.html)

This project is an API REST developed in Spring Boot 3.2 that provides endpoints to manage all requests
for `Movie Pulse` web application. It allows a user register and login to review a movie, add a comment
and rate it. The application uses a MySQL database for data storage and is hosted on the Heroku platform.

<b>Main features: </b>📌

<ul>
    <li>Sing-up users and login with JWT token security.</li>
    <li>Make a review from a movie.</li>
    <li>Add a comment and rate.</li>
    <li>Secure connection to the database in the cloud.</li>
    <li>Deployment and execution on Heroku.</li>
</ul>

## Built With 🔐

The stack use to develop and deploy this project is:

* Spring Boot 3
* Maven
* Spring Web
* Spring Security
* Spring Data JPA
* MySql Driver
* MySQL 8+
* Auth0
* Lombok
* MapStruct 1.5
* OpenAPI 2 (Swagger)
* Heroku

## Getting Started 🚀

Clone the repo:
```bash
git clone https://github.com/No-Country/C16-39-m-java.git
```
And go to the backend project directory:
```bash
cd backend
```

### Pre-requisites 📋
* Environment variables required (example values) to run the application locally in the file `application.properties`:
  ```properties
  DB_URL=jdbc:mysql://localhost:3306/
  DB_NAME=mi_db
  DB_USERNAME=admin
  DB_PASSWORD=my_pa$$word
  JWT_ISSUER=com.mypage.myapp
  JWT_SECRET=top_$ecret
  ```

### Usage ⏯
1. Create a database in MySQL with the name you choose.
2. Run the application from the IDE.
3. Open the browser and go to the URL `http://localhost:8080/swagger-ui/index.html` or use the demo on Heroku shown below on section Demo 👨‍💻.
4. Use the Swagger UI to test the API.
5. Create a user with the endpoint `POST /users` and the following body:
    ```json
    {
      "name": "John Doe",
      "email": "john.doe@gmail.com",
      "password": "johnDoe123"
    }
    ```
6. Login with the endpoint `POST /users/auth` and the following body:
    ```json
    {
      "email": "myemail@example.com",
      "password": "my_email_pa$$word"
    }
    ```
7. Copy the token from the response and click on the button `Authorize` in the Swagger UI.
8. Paste the token in the field `Value` and click on the button `Authorize`.
9. Now you can test others endpoints from the API.

## Demo 👨‍💻

- **Application Demo** on Heroku: [movies-plus-api](https://movies-apirest-c77e9f5e2ba2.herokuapp.com/swagger-ui/index.html)

## Frontend 🖥️

- [movie-pulse](https://c16-39-m-java.vercel.app)

## Authors ✒️

* **Bernardo Royo** - [Bernardo2193](https://github.com/Bernardo2193)
* **Hiram Chávez** - [JustLearningMX](https://github.com/JustLearningMX)
* **Julián Tassone** - [JuliT160](https://github.com/JuliT160)
* **Melina Martinez** - [MelinaMartinez124](https://github.com/MelinaMartinez124)

---

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://choosealicense.com/licenses/mit/
[product-screenshot]: /src/main/resources/static/img/caratula-movies-back.png
