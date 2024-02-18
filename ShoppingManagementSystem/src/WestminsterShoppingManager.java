import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WestminsterShoppingManager implements ShoppingManager {
    private String[] productList; // only the ID
    private Product[] productdetails; //Details of the products
    private String products ; // save file variable

//Constructor
    public WestminsterShoppingManager(String[] productList) {
        this.productList = productList;
        this.productdetails = new Product[500];
        this.products = "products.txt";
    }


    @Override
    public void mainMenu() {
        Scanner myobj = new Scanner(System.in);
        while (true) {
            System.out.println("--------- Westminster Shopping Manager ---------");
            System.out.println("1. Add a product");
            System.out.println("2. Remove a product");
            System.out.println("3. Print the list of products");
            System.out.println("4. Save in a file");
            System.out.println("5. Open The Application");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            // Check if the input is an integer
            if (myobj.hasNextInt()) {
                int choice = myobj.nextInt();

                // Check if the integer is within the valid range
                if (choice >= 1 && choice <= 6) {
                    switch (choice) {
                        case 1:
                            addProductSubMenu();
                            break;
                        case 2:
                            removeProductSubmenu();
                            break;
                        case 3:
                            printListOfProducts();
                            break;
                        case 4:
                            saveInFile();
                            break;
                        case 5:
                            displayGUI();
                            break;
                        case 6:
                            System.out.println("Exiting Westminster Shopping Manager. Goodbye!");
                            System.exit(0);
                        default:
                            // This case should not be reachable if your checks are correct
                            System.out.println("Invalid option. Please enter a number between 1 and 6.");
                    }
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
                    myobj.nextLine(); // Clear the buffer
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                myobj.next(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }

    @Override
    public void addProductSubMenu() {
        Scanner myobj= new Scanner(System.in);

        while (true) {
            System.out.println("--------- Add Product ---------");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");
            System.out.println("3. Main menu");
            System.out.print("Select an option (1-3): ");

            try {
                int productChoice = myobj.nextInt();

                switch (productChoice) {
                    case 1:
                        addElectronics();
                        break;
                    case 2:
                        addClothing();
                        break;
                    case 3:
                        return;  // Go back to the main menu
                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 3.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                myobj.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    //This method retrieves all non-null products from the productdetails array.
    public List<Product> getAllProducts() {
        return Arrays.stream(productdetails)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void displayGUI() {
        new ShopGUI(this);
    }

    @Override
    // Method to remove a product from the productdetails array based on its ID
    public void removeProductSubmenu() {
        // Create a Scanner object for reading user input
        Scanner myobj = new Scanner(System.in);

        System.out.print("Enter the product ID to remove: ");
        String productIdToRemove = myobj.next();

        // Call the method to find the index of the product with the given ID in the productdetails array
        int index = findProductIndexById(productIdToRemove);

        // Check if the product was found (i.e., index is not -1)
        if (index != -1) {
            // Retrieve the product to be removed from the productdetails array
            Product removedProduct = productdetails[index];
            // Set the product at the found index to null, effectively removing it from the array
            productdetails[index] = null;
            // Print a confirmation message, indicating the type of product (Electronics or Clothing) and its ID
            System.out.println((removedProduct instanceof Electronics ? "Electronics" : "Clothing") + " Product '" + removedProduct.getProductId() + "' removed successfully:");
            // Print the number of remaining products in the system
            System.out.println("Remaining products in the system: " + getRemainingProductCount());
        } else {
            // If the product is not found (i.e., index is -1), inform the user that the product ID was not found
            System.out.println("Product with ID " + productIdToRemove + " not found.");
        }
    }

    private int findProductIndexById(String productId) {
        // Iterate over the productdetails array
        for (int i = 0; i < productdetails.length; i++) {
            // Check if the current element is not null and its ID matches the given productId
            if (productdetails[i] != null && productdetails[i].getProductId().equals(productId)) {
                // Return the index where the matching product is found
                return i;
            }
        }
        // Return -1 if the product is not found in the array after completing the loop
        return -1;
    }

    private int getRemainingProductCount() {
        // Initialize a counter to keep track of the number of products
        int count = 0;

        // Iterate over each product in the productdetails array
        for (Product product : productdetails) {
            // Check if the current product in the array is not null
            if (product != null) {
                // Increment the count for each non-null product found
                count++;
            }
        }

        // Return the total count of non-null products
        return count;
    }


    @Override
    // Method to add an electronics product
    public void addElectronics() {
        // Initialize a Scanner object for reading user input
        Scanner myobj = new Scanner(System.in);
        // Prompting the user to enter details of the electronic item
        System.out.println("Enter the details of Electronic item");

        // Requesting the user to enter the product ID
        System.out.print("Product ID (IntegerID): ");
        String productId;
        // Validating the product ID input to ensure it's an integer
        while (!myobj.hasNextInt()) {
            // Display an error message for invalid input
            System.out.println("Invalid input. Product ID should be an integer. Please try again:");
            myobj.next(); // discard the invalid input
        }
        // Read the product ID from user input
        productId = myobj.next();
        // Insert the product ID into the product list
        insertProductId(productId);
        myobj.nextLine(); // consume the leftover newline character after reading integer

        // Requesting the user to enter the product name
        System.out.print("Enter product name: ");
        String productName = myobj.nextLine(); // Read the product name

        // Requesting the user to enter the number of available items
        System.out.print("Enter the number of available items: ");
        int availableItems;
        // Validating the input for the number of available items
        while (!myobj.hasNextInt()) {
            System.out.println("Invalid input. Number of available items should be an integer. Please try again:");
            myobj.next(); // discard the invalid input
        }
        availableItems = myobj.nextInt(); // Read the number of available items
        myobj.nextLine(); // consume the leftover newline character

        // Requesting the user to enter the price
        System.out.print("Enter the price: ");
        double price;
        // Validating the input for the price
        while (!myobj.hasNextDouble()) {
            System.out.println("Invalid input. Price should be a number. Please try again:");
            myobj.next(); // discard the invalid input
        }
        price = myobj.nextDouble(); // Read the price
        myobj.nextLine(); // consume the leftover newline character

        // Requesting the user to enter the brand of the electronic item
        System.out.print("Enter the brand: ");
        String brand = myobj.nextLine(); // Read the brand name

        // Requesting the user to enter the warranty period
        System.out.print("Enter the warranty period (in months): ");
        double warrantyPeriod;
        // Validating the input for the warranty period
        while (!myobj.hasNextInt()) {
            System.out.println("Invalid input. Warranty period should be an integer representing months. Please try again:");
            myobj.next(); // discard the invalid input
        }
        warrantyPeriod = myobj.nextDouble(); // Read the warranty period
        // Checking for valid warranty period
        if (warrantyPeriod < 0) {
            System.out.println("Invalid Warranty Period. It cannot be negative.");
            return; // Exit the method if the warranty period is negative
        }

        // Creating a new Electronics object with the provided details
        Electronics newElectronics = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
        // Adding the new Electronics object to the productdetails array
        for (int i = 0; i < productdetails.length; i++) {
            if (productdetails[i] == null) {
                productdetails[i] = newElectronics;
                System.out.println("Successfully added electronics to the electronicsList array.");
                return; // Exit the loop once the insertion is done
            }
        }
    }


    // Method to add a clothing product
    public void addClothing() {
        // Initialize a Scanner object for reading user input
        Scanner myobj = new Scanner(System.in);
        // Prompting the user to enter details of the clothing item
        System.out.println("Enter the details of Clothing");

        // Requesting the user to enter the product ID
        System.out.print("Product ID: ");
        String productId;
        // Validating the product ID input to ensure it's an integer
        while (!myobj.hasNextInt()) {
            // Display an error message for invalid input
            System.out.println("Invalid input. Product ID should be an integer. Please try again:");
            myobj.next(); // discard when the invalid input
        }
        productId = myobj.next(); // Read the product ID from user input
        insertProductId(productId); // Insert the product ID into the product list
        myobj.nextLine(); // consumes the leftover newline character after reading integer

        // Requesting the user to enter the product name
        System.out.print("Enter product name: ");
        String productName = myobj.nextLine(); // Read the product name

        // Requesting the user to enter the number of available items
        System.out.print("Enter the number of available items: ");
        int availableItems;
        // Validating the input for the number of available items
        while (!myobj.hasNextInt()) {
            System.out.println("Invalid input. Number of available items should be an integer. Please try again:");
            myobj.next(); // discard the invalid input
        }
        availableItems = myobj.nextInt(); // Read the number of available items
        myobj.nextLine(); // consume the leftover newline character

        // Requesting the user to enter the price
        System.out.print("Enter the price: ");
        double price;
        // Validating the input for the price
        while (!myobj.hasNextDouble()) {
            System.out.println("Invalid input. Price should be a number. Please try again:");
            myobj.next(); // discard the invalid input
        }
        price = myobj.nextDouble(); // Read the price
        myobj.nextLine(); // consume the leftover newline character

        // Requesting the user to enter the size of the clothing
        System.out.print("Enter the size (S, M, L, XL, XXL, XXXL): ");
        String size = myobj.nextLine().toUpperCase(); // Read and convert the size input to uppercase
        // Check if the entered size is one of the valid sizes
        if (!Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL").contains(size)) {
            System.out.println("Invalid size. Please enter one of S, M, L, XL, XXL, XXXL.");
            return; // Exit the method if the entered size is not valid
        }

        // Requesting the user to enter the color of the clothing
        System.out.print("Enter the color: ");
        String color = myobj.nextLine(); // Read the color

        // Creating a new Clothing object with the provided details
        Clothing newClothing = new Clothing(productId, productName, availableItems, price, size, color);
        // Adding the new Clothing object to the productdetails array
        for (int i = 0; i < productdetails.length; i++) {
            if (productdetails[i] == null) {
                productdetails[i] = newClothing;
                System.out.println("Successfully added clothing to the clothingList array.");
                break; // Exit the loop once the insertion is done
            }
        }
    }


    private void insertProductId(String productId) {
        // Find the first empty slot in productList and insert the productId
        for (int i = 0; i < productList.length; i++) {
            if (productList[i] == null) {
                productList[i] = productId;
                //System.out.println("Successfully added productId to the productList array.");
                return;  // Exit the loop once the insertion is done
            }
        }
    }


    @Override
    // Method to print the list of all products in the productdetails array
    public void printListOfProducts() {
        // Iterate over each product in the productdetails array
        for (Product product : this.productdetails) {
            // Check if the current product in the array is not null (i.e., a valid product exists at this index)
            if (product != null) {
                // Print the details of the non-null product
                // The getDetails method of the Product class (or its subclasses) is assumed to return a string of product details
                System.out.println(product.getDetails());
            }
        }
    }

    // Method to save the product details into a file
    public void saveInFile() {
        // Using try-with-resources to automatically manage resource closing
        // FileWriter is initialized to write data to a file named "products.txt"
        try (FileWriter writer = new FileWriter("products.txt");
             // BufferedWriter is wrapped around FileWriter for efficient writing
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            // Iterate over each product in the productdetails array
            for (Product product : productdetails) {
                // Check if the current product in the array is not null
                if (product != null) {
                    // Write the details of the product to the file
                    // The getDetails method of Product class is assumed to return a string of product details
                    bufferedWriter.write(product.getDetails());
                    // Write a new line in the file after each product's details
                    bufferedWriter.newLine();
                }
            }
            // Print a confirmation message indicating successful saving of the product list
            System.out.println("Product list saved successfully.");
        } catch (IOException e) {
            // Catch any IOExceptions and print an error message
            System.out.println("Error saving product list to file: " + e.getMessage());
            // Print the stack trace for the exception
            e.printStackTrace();
        }
    }



    // Load the list of products from a file
    @Override
    // Method to load product details from a file
    public void loadFromFile() {
        // Using try-with-resources to automatically manage resource closing
        // FileReader reads data from the file specified by 'products'
        try (FileReader reader = new FileReader(products);
             // BufferedReader is wrapped around FileReader for efficient reading
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            // Read each line from the file until there are no more lines
            while ((line = bufferedReader.readLine()) != null) {
                // Process the line to create a Product object and add it to the productdetails array
                // The specific processing will depend on how the product data is formatted in the file
            }
            System.out.println("Products loaded from the file successfully.");
        } catch (IOException e) {
            // Catch any IOExceptions and print an error message
            System.out.println("An error occurred while loading products from the file: " + e.getMessage());
            // Print the stack trace for the exception
            e.printStackTrace();
        }
    }


    //Read info from the text file to the console when application starts.
    // Method to read and display information from a text file
    //error occurred not working
    public void readInfoText () {
        // FileReader is initialized to read data from the file specified by 'products'
        try (FileReader fileReader = new FileReader(products);
             // BufferedReader is wrapped around FileReader for efficient reading
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            // Read each line from the file until there are no more lines (i.e., readLine() returns null)
            while ((line = bufferedReader.readLine()) != null) {
                // Print the line read from the file to the console
                System.out.println(line);
            }
        } catch (IOException e) {
            // Catch any IOExceptions and print an error message
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            // Print the stack trace for the exception
            e.printStackTrace();
        }
    }

    @Override
    //Read info from the text file to the console when application starts.
    public void readInfomationText() {
        try (FileReader fileReader = new FileReader(products);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addToProductDetails(Product product) {
        for (int i = 0; i < productdetails.length; i++) {
            if (productdetails[i] == null) {
                productdetails[i] = product;
                break;
            }
        }
    }

    public static void main (String[]args){
        String[] productList = new String[50];
        String[] productdetails = new String[500];

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager(productList);

        // Load existing products from file (if any)

        //shoppingManager.loadFromFile();

        // Call enterProductList to allow users to input values into the productList array

        shoppingManager.mainMenu();
        //shoppingManager.openGUI(productdetails);

        // Save the product list to file before exiting
        shoppingManager.saveInFile();
    }

}