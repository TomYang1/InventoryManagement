# InventoryManagement

Mr. X owns a store that sells almost everything you think about. Now he wants a inventory management system to manage his inventory. Mr. X feels that controlling his inventory through SMS from his mobile will be revolutionary. So as a prequel, he decides that he wants a system that accepts one line commands and performs the respective operation.

Below is the list of commands he needs in the system:
 
a) create itemName costPrice sellingPrice
       	Whenever Mr. X wants to add a new item to his store he issues a create command. This command creates a new item in the inventory with the given cost price and selling price. The prices are rounded off to two decimal places.
 
b) delete itemName
      	If Mr. X decides not to sell an item anymore, then he simply issues a delete command. This command will remove the item from the inventory.
 
c) updateBuy itemName quantity
      	Whenever Mr. X purchases additional quantity of the mentioned item, then he issues a updateBuy command. This command should increase the quantity of the mentioned item.
 
d) updateSell itemName quantity
      	Whenever Mr. X sells some item, then he issues a updateSell command. This command should deduct the quantity of the mentioned item.
 
e) report
      	Whenever Mr. X wants to view his inventory list he issues the report command. This command should print the current inventory details in the specified format sorted by alphabetical order. Apart from printing the inventory it has to report on the profit made by Mr. X since last report generation.
Where profit is calculated by:  ∑ (sellingPrice-costPrice) of the sold items multiplied by no. of items sold- costPrice of the deleted items.
 
 
In-Office Extension:
 
f) updateSellPrice itemName newSellPrice
      	Mr. X is now happy with the current system, however at times some goods are not selling out faster than expected. In this case he wants to change the initial selling price of that item. So he wants a new command. This command will update the sellingPrice of the specified item.
 
report:
      	Now while calculating the report profit, the system should take care of 2 different selling prices for a same item. Profits should be calculated with old selling price for the items sold before updating and with new selling price for the items bought after updating.
