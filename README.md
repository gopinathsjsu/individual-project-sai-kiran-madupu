# # individual-project-sai-kiran-madupu

## Project Requirements:
The application should maintain an internal, static database (inventory of stock)  (this may be developed using HashMaps and/or other  built-in Java Data structures). This means once we re-run the program, the changes to the data would not persist. We will provide the data that has to be maintained. The data will contain the following tables and fields:

#### Table 1: Items
- Category (Essentials, Luxury, Miscellaneous)
- Item for each category (Essentials - Clothes, soap, milk; Luxury - perfume, chocolates; Misc - Bedsheets, footwear)
- The available Quantity of each item
- Price of each item

#### Table 2: Cards
- Card Numbers

Input CSV file will contain an order including Items, Quantity needed, and the payment card number.

Input file should be processed as follows:
1. Validate if the requested quantity for each item is permissible. For example, if the request is to order 3 soaps, check the database if we have at least 3 soaps in our inventory.
2. There will be a cap on the quantity of each category that can be ordered in one single order. For example, restrict Essentials to a maximum of 3, Luxury to 4, and Misc to 6. (This will be configured beforehand)
3. In case it is an incorrect request, generate and output TXT file with message "Please correct quantities." and include the items with incorrect quantities
4. After this validation, if the cart is valid, calculate prices for the cart.
5. Take the card number of the user and if it is not present in DB add it.
6. Output the CSV list with the total amount paid.
