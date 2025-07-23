To start the application, run the following command:
The application has two profiles: `postgres` and `mongo`. You can choose one of them by passing the appropriate argument.
```bash

./gradlew bootRun --args='--spring.profiles.active=postgres'
or
./gradlew bootRun --args='--spring.profiles.active=mongo'
```
If you want to use postgres, you have in resources PSQLCommands to start the database.


After everything is up and running you can access the API documentation at:
http://localhost:8080/swagger-ui/index.html#/