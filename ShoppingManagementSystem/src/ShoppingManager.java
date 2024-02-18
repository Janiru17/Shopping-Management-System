public interface ShoppingManager {

    void mainMenu();

    void addProductSubMenu();
    void removeProductSubmenu();
    void addElectronics();
    void addClothing();

    void printListOfProducts();

    // Load the list of products from a file
    void loadFromFile();

    //Read info from the text file to the console when application starts.
    void readInfomationText();
}
