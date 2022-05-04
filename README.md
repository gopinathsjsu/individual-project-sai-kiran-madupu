# Individual Project - Sai Kiran Madupu - 015505243

## Project Requirements:
The application should maintain an internal, static database (inventory of stock)  (this may be developed using HashMaps and/or other  built-in Java Data structures). This means once we re-run the program, the changes to the data would not persist. We will provide the data that has to be maintained. The data will contain the following tables and fields:

### Table 1: Items
- Category (Essentials, Luxury, Miscellaneous)
- Item for each category (Essentials - Clothes, soap, milk; Luxury - perfume, chocolates; Misc - Bedsheets, footwear)
- The available Quantity of each item
- Price of each item

### Table 2: Cards
- Card Numbers

Input CSV file will contain an order including Items, Quantity needed, and the payment card number.

Input file should be processed as follows:
1. Validate if the requested quantity for each item is permissible. For example, if the request is to order 3 soaps, check the database if we have at least 3 soaps in our inventory.
2. There will be a cap on the quantity of each category that can be ordered in one single order. For example, restrict Essentials to a maximum of 3, Luxury to 4, and Misc to 6. (This will be configured beforehand)
3. In case it is an incorrect request, generate and output TXT file with message "Please correct quantities." and include the items with incorrect quantities
4. After this validation, if the cart is valid, calculate prices for the cart.
5. Take the card number of the user and if it is not present in DB add it.
6. Output the CSV list with the total amount paid.

## Prerequisites
- OpenJDK 16
- Eclipse IDE

## Design Patterns
- Composite Pattern: The composite pattern describes a group of objects that are treated the same way as a single instance of the same type of object. Order contains a List of OrderItems, where we can access individual OrderItems.

- Singleton Pattern: The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance. The Inventory List is single object that follows singleton design pattern and it acts as the in-memory data store.

- Adapter Pattern: The FileUtility class reads input files into Inventory and Order

## Run the Project
1. Navigate to the root folder of the project
2. Execute the below command in the terminal
`java -cp bin main.Billing "Dataset - Sheet1.csv" "Cards - Sheet1.csv" "Input1 - Sheet1.csv"`

The application expects three command line arguments in the same order:
1. Dataset CSV
2. Cards CSV
3. Input CSV


## UML Class Diagram
![image](https://user-images.githubusercontent.com/18122083/166617019-cc3e5f52-4e12-4020-bcf8-7e72090e4426.png)


## Success Output
<img width="324" alt="image" src="https://user-images.githubusercontent.com/18122083/166613645-5d0b0477-d2a4-4235-a6ee-1d62902315b8.png">

## Error Output
<img width="531" alt="image" src="https://user-images.githubusercontent.com/18122083/166613678-8b859f65-aad3-494e-a66c-4e4f7fa64a44.png">





