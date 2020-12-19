# ilex-coding-challenge

### Steps to run the application

1. pull source code to your local
2. navigate to the project DIR in command prompt
3. execute programe with Gradle command `gradle bootrun`

program will be ran on port 8085, please ensure port availability


### Trying the APIs:

1. Add Product
```
   curl --location --request POST 'http://localhost:8085/v1/products' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "productId": 3,
   "productName": "coconut",
   "productDescription": "fresh",
   "price": 3.20,
   "categories": [
   "food",
   "drinks"
   ]
   }'
```

2. List Products
```
curl --location --request GET 'http://localhost:8085/v1/products'
```

3. Place Order
```
curl --location --request POST 'http://localhost:8085/v1/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId": 1,
    "products": [
        {
            "productId": 2,
            "price": 1.20,
            "quantity": 5
        }
    ]
}'
```

4. List Orders
```
curl --location --request GET 'http://localhost:8085/v1/orders/users/1?fromDate=2020-12-19T18:27:34Z&toDate=2020-12-30T22:27:34Z'
```