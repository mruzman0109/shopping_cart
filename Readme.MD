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

## 🛠️ 3. Run with MongoDB

To setup mongo create following (check if brew is installed):
```bash
brew tap mongodb/brew
brew install mongodb-community@6.0
brew services start mongodb-community@6.0
```

Run the Mongo shell:
```bash
mongosh
```
and then prepare dbs:

```bash
test> use admin
switched to db admin
admin> db.getUsers()
{ users: [], ok: 1 }

use shopping_cart

db.createUser({
user: "cartUser",
pwd: "123456",
roles: [
{ role: "readWrite", db: "shopping_cart" }
]
})
```

exit mongodb with double pres Ctrl+C
and then restart mongodb using:
```bash
brew services stop mongodb-community@6.0
```
add if missing in your mongodb configuration:

```yaml
security:
  authorization: enabled
```

Then, restart MongoDB:

```bash
brew services start mongodb-community@6.0
```

Finally, run the application with the MongoDB profile:

```bash
./gradlew bootRun --args='--spring.profiles.active=mongo'
``` 

## 📚 4. API Documentation
Once the application is running, access the Swagger UI:

http://localhost:8080/swagger-ui/index.html#/

This provides an interactive interface for exploring and testing all available endpoints.


## 🛠️ 5. Testing the Application

To run the tests, execute:

```bash
./gradlew test
```