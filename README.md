
# buyer
A backend shopping card project with Spring Boot
Swagger ui link: http://localhost:8080/swagger-ui/index.html

# Used Technologies, Designs and Principles
Spring Core <br>
Spring Web <br>
Spring Data JPA <br>
Spring Security<br>
Jwt-Login<br>
Hibernate<br>
Lombok <br>
Postgresql <br>
Domain Driven Design<br>
Clean Code Principles <br>

# Api Endpoints
<p> /api/v1/categories                         -    GET Request    -   Get all categories</p>
<p> /api/v1/categories/{id}                    -    GET Request    -   Get category by id</p>
<p> /api/v1/categories                         -    POST Request   -   Create a new category</p>
<p> /api/v1/categories/{id}                    -    PUT Request    -   Update category by id</p>
<p> /api/v1/categories/{id}                    -    DELETE Request -   Delete category by id</p>
<br>
<p> /api/v1/products                           -    GET Request    -   Get all products</p>
<p> /api/v1/products/{id}                      -    GET Request    -   Get product by id</p>
<p> /api/v1/products                           -    POST Request   -   Create a new product</p>
<p> /api/v1/products/{id}                      -    PUT Request    -   Update product by id</p>
<p> /api/v1/products/{id}                      -    DELETE Request -   Delete product by id</p>
<br>
<p> /api/v1/images/{id}                        -    GET Request    -   Download image</p>
<p> /api/v1/images                             -    POST Request   -   Create a new image</p>
<p> /api/v1/images/{id}                        -    PUT Request    -   Update image by id</p>
<p> /api/v1/images/{id}                        -    DELETE Request -   Delete image by id</p>
<br> 
<p> /api/v1/cart/{id}/my-cart                  -    GET Request    -   Get cart items</p>
<p> /api/v1/cart/1/total-price                 -    GET Request    -   Get total price of cart</p>
<p> /api/v1/cart/{id}                          -    DELETE Request -   Clear cart</p>
<br>
<p> /api/v1/cart-item                          -    PATCH Request  -   Update Cart Item Quantity</p>
<p> /api/v1/cart-item                          -    POST  Request  -   Add Cart Item Quantity</p>
<p> /api/v1/cart-item{cartId}/{productId}      -    Delete Request -   Remove Cart Item</p>
<br>
<p> /api/v1/orders/{userId}/all                -    GET Request    -   Get all orders of spesific user</p>
<p> /api/v1/orders/{orderId}/all               -    GET Request    -   Get spesific order</p>
<p> /api/v1/orders/{userId}                    -    POST  Request  -   Place order</p>
<p> /api/v1/orders/{orderId}                   -    Delete Request -   Cancel the order</p>
<br>
<p> /api/v1/users                              -    GET Request    -   Get all users info</p>
<p> /api/v1/users/{userId}                     -    GET Request    -   Get user with id</p>
<p> /api/v1/users/{userId}                     -    POST  Request  -   Create a new user</p>
<p> /api/v1/users/{userId}                     -    PUT  Request   -   Update the user</p>
<p> /api/v1/users/{orderId}                    -    Delete Request -   Delete the user</p>
<br>
<p> /api/v1/auth/login                         -    POST Request   -   Login</p>
