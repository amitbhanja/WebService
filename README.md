# WebService
Webservices implemented using SOAP and REST (both independently)

Webservice follows the fundamental functionality of a shopping cart
system.

The server provides the following functionality:
- The server has to manage the shopping cart for every client. Each shopping cart is
stored using a uniqueID for each client. No complex session management is needed
for the clients.
- The server has to provide Information about at least three different products
(“Name”, “Price”, “Available Amount”)
- The server has to manage the available amounts for each product

Each client provides the following functionality:
- The client has to provide a user interface, which shows the available products with
prices and the available amount for each product
- The user is able to buy products. The user can send his order using his shopping cart.
- The user gets a return message, if a product is not available, because another order
might be received beforehand.

Both Restful and SOAP webservices have been implemented with client and server in both the cases.

The instructions for running the code are mentioned in the Readme.txt file.
