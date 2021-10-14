# Java_Webshop_Basics

Simple REST service that can be used as a webshop backend.

# Technology
- Java 11
- Maven
- Spring Boot + Spring Web MVC
- Spring Data JPA
- Database (Postgres in Docker container)

# Installation:
- Run CMD command to create Postgres DB
```
docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres
```
- Open folder in Java IDE or run Zadatak.jar in CMD (from cloned folder)
```
java -jar targetZadatak-0.0.1-SNAPSHOT.jar
```
# Darabase structure

# Product
- id
- code
- name
- price_hrk
- description
- is_available

# Customer
- id
- first_name
- last_name
- email

# Order
- id
- customer_id
- status
- total_price_hrk
- total_price_eur

# Order item
- id
- order_id
- product_id
- quantity

# POSTMAN REQUESTS
- Get all products
```
GET : localhost:8080/product
```

- Get product by id
```
GET : localhost:8080/product/{id}
```

- Add new product
```
POST : localhost:8080/product

Body raw JSON data:
{  
   "code"  : "3216549842",  
   "name"  : "TEST2",  
   "price_hrk" : "16",  
   "description"  : "Ovo je test",  
   "is_available"        : "true"  
 } 
```

- Update product by id
```
PUT : localhost:8080/product/{id}

Body raw JSON data:
{  
   "code"  : "5082177594",  
   "name"  : "UPDATE",  
   "price_hrk" : "20",  
   "description"  : "UPDATEEEE",  
   "is_available"        : "true"  
 }
```

- Delete product by id
```
DELETE : localhost:8080/product/{id}
```

- Get all customers
```
GET : localhost:8080/customer
```

- Get customer by id
```
GET : localhost:8080/customer/{id}
```

- Add new customer
```
POST : localhost:8080/customer

Body raw JSON data:
{  
   "first_name"  : "Test",  
   "last_name"  : "Treci",  
   "email" : "mail@gmail.com"
 }  
```

- Update customer by id
```
PUT : localhost:8080/customer/{id}

Body raw JSON data:
{  
   "first_name"  : "Janko",  
   "last_name"  : "Pop",  
   "email" : "jp@gmail.com"
 }  
```

- Delete customer by id
```
DELETE : localhost:8080/customer/{id}
```

- Get all orders
```
GET : localhost:8080/order
```

- Get order by id
```
GET : localhost:8080/order/{id}
```

- Add new order
```
POST : localhost:8080/order

Params:
Key = customerID
Value = 2 (Pick random created cutomer id)

Body raw JSON data:
{  

}  
```

- Update order by id (Finalize order)
```
PUT : localhost:8080/order/{id}
```

- Delete order by id
```
DELETE : localhost:8080/order/{id}
```

- Get all order items
```
GET : localhost:8080/orderitem
```

- Get order item by id
```
GET : localhost:8080/orderitem/{id}
```

- Get order item by order id
```
GET : localhost:8080/orderitem/order/{id}
```

- Add new order item
```
POST : localhost:8080/orderitem

Params:
Key = customerID
Value = 2 (Pick random created cutomer id)
Key = orderID
Value = 2 (Pick random created order id)
Key = productID
Value = 2 (Pick random created product id)

Body raw JSON data:
{
    "quantity" : 10
} 
```

- Update order item by id (Finalize order)
```
PUT : localhost:8080/orderitem/{id}

Body raw JSON data:
{
    "quantity" : 20
}
```

- Delete order item by id
```
DELETE : localhost:8080/orderitem/{id}
```
