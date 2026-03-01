# Dress Shop Application - API Testing Guide

## Application Status
✅ **Application is running successfully on port 8080**

## Database
- **Type**: H2 (In-Memory Database)
- **URL**: `jdbc:h2:mem:dress_shop_db`
- **H2 Console**: http://localhost:8080/h2-console (optional)

## API Endpoints

### 1. Create a Dress
```
POST http://localhost:8080/api/v1/dresses
Content-Type: application/json

{
  "name": "Summer Dress",
  "description": "Beautiful summer dress",
  "price": 49.99,
  "quantity": 10,
  "size": "M",
  "color": "Blue",
  "material": "Cotton"
}
```

### 2. Get All Dresses
```
GET http://localhost:8080/api/v1/dresses
```

### 3. Get Dress by ID
```
GET http://localhost:8080/api/v1/dresses/1
```

### 4. Update Dress
```
PUT http://localhost:8080/api/v1/dresses/1
Content-Type: application/json

{
  "name": "Updated Summer Dress",
  "description": "Updated description",
  "price": 59.99,
  "quantity": 15,
  "size": "L",
  "color": "Red",
  "material": "Silk"
}
```

### 5. Delete Dress
```
DELETE http://localhost:8080/api/v1/dresses/1
```

### 6. Filter by Color
```
GET http://localhost:8080/api/v1/dresses/filter/color/Blue
```

### 7. Filter by Size
```
GET http://localhost:8080/api/v1/dresses/filter/size/M
```

### 8. Filter by Material
```
GET http://localhost:8080/api/v1/dresses/filter/material/Cotton
```

## Testing with cURL

### Create Dress
```bash
curl -X POST http://localhost:8080/api/v1/dresses \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Summer Dress",
    "description": "Beautiful summer dress",
    "price": 49.99,
    "quantity": 10,
    "size": "M",
    "color": "Blue",
    "material": "Cotton"
  }'
```

### Get All Dresses
```bash
curl http://localhost:8080/api/v1/dresses
```

### Get Dress by ID
```bash
curl http://localhost:8080/api/v1/dresses/1
```

### Update Dress
```bash
curl -X PUT http://localhost:8080/api/v1/dresses/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Dress",
    "description": "Updated description",
    "price": 59.99,
    "quantity": 15,
    "size": "L",
    "color": "Red",
    "material": "Silk"
  }'
```

### Delete Dress
```bash
curl -X DELETE http://localhost:8080/api/v1/dresses/1
```

### Filter by Color
```bash
curl http://localhost:8080/api/v1/dresses/filter/color/Blue
```

## Testing with Postman

1. Import the endpoints above into Postman
2. Set request method (GET, POST, PUT, DELETE)
3. Add JSON body for POST/PUT requests
4. Send requests and view responses

## Response Examples

### Success Response (201 Created)
```json
{
  "id": 1,
  "name": "Summer Dress",
  "description": "Beautiful summer dress",
  "price": 49.99,
  "quantity": 10,
  "size": "M",
  "color": "Blue",
  "material": "Cotton"
}
```

### Error Response (404 Not Found)
```json
null
```

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=DressRepositoryTest

# Run specific test method
mvn test -Dtest=DressRepositoryTest#testSaveDress
```

## Switching to MySQL

To use MySQL instead of H2:

1. Start MySQL server
2. Create database: `CREATE DATABASE dress_shop_db;`
3. Run with MySQL profile:
   ```bash
   java -jar target/DressShopApplication-0.0.1-SNAPSHOT.jar --spring.profiles.active=mysql
   ```

## Project Structure

```
src/
├── main/
│   ├── java/com/ss/practices/dsa/
│   │   ├── DressShopApplication.java (Main class)
│   │   ├── controller/DressController.java (REST endpoints)
│   │   ├── service/DressService.java (Business logic)
│   │   ├── repository/DressRepository.java (Data access)
│   │   ├── model/Dress.java (Entity)
│   │   └── dto/DressDTO.java (Data transfer object)
│   └── resources/
│       ├── application.properties (H2 config)
│       └── application-mysql.properties (MySQL config)
└── test/
    └── java/com/ss/practices/dsa/
        ├── DressRepositoryTest.java
        ├── DressControllerTest.java
        └── DressShopApplicationTests.java
```

## Java 17 Features Used

- `.toList()` - Stream API enhancement
- Records (alternative to DTOs)
- Jakarta EE imports
- Sealed classes (potential)
- Pattern matching
- Text blocks (potential)

See `JAVA17_FEATURES.md` for detailed information.
