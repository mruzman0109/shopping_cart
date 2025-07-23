# 🚀 Starting the Application

This application supports two primary profiles: `h2` (in-memory) and `postgres`. You can run the application using either by specifying the active profile.

## 🔧 1. Run with In-Memory H2 Database (Default)

To quickly get started with no database setup:

```bash
./gradlew bootRun --args='--spring.profiles.active=h2'
```

## 🔧 2. Run with PostgreSQL Database
✅ Step 1: Set up the database

Ensure you have PostgreSQL running and a database created. Then, run the application with the PostgreSQL profile:

```bash
CREATE DATABASE shoppingcart;
CREATE USER shoppinguser WITH PASSWORD '123456';
GRANT ALL PRIVILEGES ON DATABASE shoppingcart TO shoppinguser;
```

#### 🚀 Step 2: Start the application with the postgres profile

```bash
./gradlew bootRun --args='--spring.profiles.active=postgres'
```

## 📚 3. API Documentation
Once the application is running, access the Swagger UI:

http://localhost:8080/swagger-ui/index.html#/

This provides an interactive interface for exploring and testing all available endpoints.


## 🛠️ 4. Testing the Application

To run the tests, execute:

```bash
./gradlew test
```


### PS. Additional Notes

🍃 MongoDB Support (Optional)
If you'd like to use MongoDB as the persistence layer, check out the dedicated feature branch:
```bash
feature/add-mongo-db-posibility
```
Instructions for Mongo setup and usage are included in that branch's documentation.
