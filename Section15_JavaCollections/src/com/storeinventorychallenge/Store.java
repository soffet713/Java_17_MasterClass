package com.storeinventorychallenge;

import java.time.LocalDate;
import java.util.*;

public class Store {

    private static Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> aisleInventory;
    private static String separator = "-".repeat(75);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Store myStore = new Store();
        myStore.stockStore();
        myStore.listInventory();

        myStore.stockAisles();
        myStore.listProductsByCategory();

        System.out.println(separator);
        myStore.manageStoreCarts();
        System.out.println(separator);
        sc.close();
        //myStore.listProductsByCategory(false,true);

        //myStore.carts.forEach(System.out::println);
        //myStore.abandonCarts();
        //myStore.listProductsByCategory(false,true);
        //myStore.carts.forEach(System.out::println);
    }

    private void manageStoreCarts() {
        /* Code from course
        Cart cart1 = new Cart(Cart.CartType.PHYSICAL,1);
        carts.add(cart1);

        InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
        cart1.addItem(item, 6);
        cart1.addItem(aisleInventory.get(Category.PRODUCE).get("japanese sweet potato"), 5);
        cart1.addItem(aisleInventory.get(Category.BEVERAGE).get("coffee"),10);
        cart1.addItem(aisleInventory.get(Category.CONDIMENTS).get("sriracha"),2);
        cart1.addItem(aisleInventory.get(Category.FROZEN).get("black olive & mushroom pizza"),2);
        System.out.println(cart1);

        cart1.removeItem(aisleInventory.get(Category.BEVERAGE).get("coffee"),5);
        cart1.removeItem(aisleInventory.get(Category.PRODUCE).get("japanese sweet potato"),2);
        System.out.println(cart1);

        Cart cart2 = new Cart(Cart.CartType.VIRTUAL,1);
        carts.add(cart2);
        cart2.addItem(inventory.get("L103"),20);
        cart2.addItem(inventory.get("LCS1"),24);
        cart2.addItem(inventory.get("B100"),10);
        cart2.addItem(inventory.get("DL04"),12);
        System.out.println(cart2);

        Cart cart3 = new Cart(Cart.CartType.VIRTUAL, 0);
        carts.add(cart3);
        cart3.addItem(inventory.get("C777"),998);
        System.out.println(cart3);
        if(!checkOutCart(cart3)) {
            System.out.println("Something went wrong, could not check out.");
        }

        Cart cart4 = new Cart(Cart.CartType.PHYSICAL, 0);
        carts.add(cart4);
        cart4.addItem(aisleInventory.get(Category.BEVERAGE).get("tea"),1);
        System.out.println(cart4);
         */

        //My code
        System.out.println("Welcome! Please enter the type of cart you would like to use: VIRTUAL/PHYSICAL?");
        Cart cart = null;
        while(cart==null) {
            String cartType = sc.nextLine().toUpperCase();
            if (cartType.substring(0, 1).equalsIgnoreCase("V")) {
                cart = new Cart(Cart.CartType.VIRTUAL, 0);
                carts.add(cart);
                addVirtualItems(cart);
            } else if (cartType.substring(0, 1).equalsIgnoreCase("P")) {
                cart = new Cart(Cart.CartType.PHYSICAL, 0);
                carts.add(cart);
                addPhysicalItems(cart);
            } else {
                System.out.println("Invalid cart type. Please choose Physical or Virtual.");
            }
        }

        while(!carts.isEmpty()) {
            System.out.println("Would you like to checkout? Y/N");

            String nextChoice = sc.nextLine();

            if (nextChoice.startsWith("Y") || nextChoice.startsWith("y")) {
                if (!checkOutCart(cart)) {
                    System.out.println("Something went wrong, could not check out.");
                } else {
                    System.out.println("Have a wonderful day!");
                }
            } else if (nextChoice.startsWith("N") || nextChoice.startsWith("n")) {
                System.out.println("What would you like to do? \nS: Continue Shopping \nC: Checkout \n" +
                        "Q/A: Quit and Abandon Cart");
                System.out.println(separator);
                nextChoice = sc.nextLine();
                String firstLetter = nextChoice.substring(0,1).toUpperCase();
                switch(firstLetter) {
                    case "S" :
                        if(cart.getType()==Cart.CartType.PHYSICAL) { addPhysicalItems(cart); }
                        else { addVirtualItems(cart); }
                        break;
                    case "C" :
                        if (!checkOutCart(cart)) {
                            System.out.println("Something went wrong, could not check out.");
                        } else {
                            System.out.println("Have a wonderful day!");
                        }
                        break;
                    case "Q" : case "A" :
                        abandonCart(cart);
                        break;
                    default : System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }

    private void addPhysicalItems(Cart cart) {
        boolean doneShopping = false;
        System.out.println("Please select one of the following categories:");
        aisleInventory.keySet().forEach(System.out::println);
        while(!doneShopping) {
            String currOption = sc.nextLine();
            Category currCategory = Category.valueOf(currOption.toUpperCase());
            if(aisleInventory.containsKey(currCategory)) {
                aisleInventory.get(currCategory).keySet().forEach(System.out::println);
            }
            System.out.println("Please select one of the items above to add to cart. Select Quit to stop " +
                    "shopping. Select Checkout to checkout.");
            String currProduct = sc.nextLine();
            System.out.printf("Please enter an amount of %s to add to your cart.%n", currProduct);
            int qty = sc.nextInt();
            sc.nextLine();
            cart.addItem(aisleInventory.get(currCategory).get(currProduct),qty);
            System.out.printf("%d %s have been added to the cart.%n", qty,currProduct);
            System.out.println(separator);
            System.out.println("What would you like to do? \nS: Shop \nQ: Quit");
            System.out.println(separator);
            currOption = sc.next();
            sc.nextLine();
            char firstLetter = currOption.charAt(0);
            switch(firstLetter) {
                case 's' : case 'S' :
                    System.out.println("Please select one of the following categories:");
                    aisleInventory.keySet().forEach(System.out::println);
                    break;
                case 'q' : case 'Q' : doneShopping = true;
                    break;
                default : System.out.println("Error - invalid option. Please select Shop, Checkout, or Quit.");
                    break;
            }
        }
    }

    private void addVirtualItems(Cart cart) {
        boolean doneShopping = false;
        System.out.println("Please select one of the following categories:");
        aisleInventory.keySet().forEach(System.out::println);
        while(!doneShopping) {
            String currOption = sc.nextLine();
            Category currCategory = Category.valueOf(currOption.toUpperCase());
            Map<String, String> categoryProducts = new HashMap<>();
            if(aisleInventory.containsKey(currCategory)) {
                for(InventoryItem i : inventory.values()) {
                    if(i.getProduct().category()==currCategory) {
                        System.out.printf(("Name: %s | Sku: %s%n"), i.getProduct().name(),i.getProduct().sku());
                        categoryProducts.put(i.getProduct().name(), i.getProduct().sku());
                    }
                }
            }
            System.out.println("Please select one of the items above to add to cart. Select Quit to stop " +
                    "shopping. Select Checkout to checkout.");
            String currProduct = sc.nextLine();
            String currSku = categoryProducts.get(currProduct);
            System.out.printf("Please enter an amount of %s to add to your cart.%n", currSku);
            int qty = sc.nextInt();
            sc.nextLine();
            cart.addItem(inventory.get(currSku),qty);
            System.out.printf("%d %s have been added to the cart.%n", qty,currProduct);
            System.out.println(separator);
            System.out.println("What would you like to do? \nS: Shop \nQ: Quit");
            System.out.println(separator);
            currOption = sc.next();
            sc.nextLine();
            char firstLetter = currOption.charAt(0);
            switch(firstLetter) {
                case 's' : case 'S' :
                    System.out.println("Please select one of the following categories:");
                    aisleInventory.keySet().forEach(System.out::println);
                    break;
                case 'q' : case 'Q' : doneShopping = true;
                    break;
                default : System.out.println("Error - invalid option. Please select Shop, Checkout, or Quit.");
                    break;
            }
        }
    }

    private boolean checkOutCart(Cart cart) {
        for(var cartItem : cart.getProducts().entrySet()) {
            var item = inventory.get(cartItem.getKey());
            int qty = cartItem.getValue();
            if(!item.sellItem(qty)) {
                return false;
            }
        }
        cart.printSalesSlip(inventory);
        carts.remove(cart);
        return true;
    }

    private void abandonCart(Cart cart) {
        System.out.println(separator);
        System.out.println("Releasing items for abandoned cart: " + cart);
        for(String sku : cart.getProducts().keySet()) {
            InventoryItem item = inventory.get(sku);
            item.releaseItem(cart.getProducts().get(sku));
        }
        System.out.println(separator);
    }

    private void abandonCarts() {
        System.out.println(separator);
        int dayOfYear = LocalDate.now().getDayOfYear();
        Cart lastCart = null;
        for(Cart cart : carts) {
            if(cart.getCartDate().getDayOfYear() == dayOfYear) {
                break;
            }
            lastCart = cart;
        }

        var oldCarts = carts.headSet(lastCart, true);
        Cart abandonedCart = null;
        while((abandonedCart = oldCarts.pollFirst()) != null) {
            System.out.println("Releasing items for abandoned cart: " + abandonedCart);
            for(String sku : abandonedCart.getProducts().keySet()) {
                InventoryItem item = inventory.get(sku);
                item.releaseItem(abandonedCart.getProducts().get(sku));
            }
        }
        System.out.println(separator);
    }

    private void listProductsByCategory() {
        listProductsByCategory(true, false);
    }

    private void listProductsByCategory(boolean includeHeader, boolean includeDetail) {
        aisleInventory.keySet().forEach(k -> {
            if(includeHeader) System.out.println("--------\n" + k + "\n--------");
            if(!includeDetail) {
                aisleInventory.get(k).keySet().forEach(System.out::println);
            } else {
                aisleInventory.get(k).values().forEach(System.out::println);
            }
        });
    }

    private void stockStore() {

        inventory = new HashMap<>();
        List<Product> products = new ArrayList<>(List.of(
                new Product("A100","apple","local",Category.PRODUCE),
                new Product("B100","banana","local",Category.PRODUCE),
                new Product("P100","pear","local",Category.PRODUCE),
                new Product("L103","lemon","local",Category.PRODUCE),
                new Product("SP07","japanese sweet potato","import",Category.PRODUCE),
                new Product("BB99","blueberries","local",Category.PRODUCE),
                new Product("AM01","almond milk","farm",Category.DAIRY),
                new Product("Y001","yogurt","farm",Category.DAIRY),
                new Product("C333","cheese","farm",Category.DAIRY),
                new Product("C777","honey nut cheerios","General Mills",Category.CEREAL),
                new Product("HBO7","honey bunches of oats","Post",Category.CEREAL),
                new Product("G111","granola","Nat Valley",Category.CEREAL),
                new Product("BB11","ground beef","butcher",Category.MEAT),
                new Product("CC11","chicken","butcher",Category.MEAT),
                new Product("BC11","bacon","butcher",Category.MEAT),
                new Product("BC77","coke","Coca Cola",Category.BEVERAGE),
                new Product("BC88","coffee","value",Category.BEVERAGE),
                new Product("BC99","tea","herbal",Category.BEVERAGE),
                new Product("LCS1","lemon seltzer","La Croix",Category.BEVERAGE),
                new Product("ME01","energy drink","Monster energy",Category.BEVERAGE),
                new Product("FZ01","drumsticks","Nestle",Category.FROZEN),
                new Product("FZ02","vanilla ice cream","Blue Bunny",Category.FROZEN),
                new Product("FZ03","frozen waffles","Eggo",Category.FROZEN),
                new Product("FZ04","black olive & mushroom pizza","Amy's Kitchen",Category.FROZEN),
                new Product("BK01","cupcakes","local",Category.BAKERY),
                new Product("BK02","chocolate cake","local",Category.BAKERY),
                new Product("BK03","muffins","local",Category.BAKERY),
                new Product("DL01","biscuits","local",Category.DELI),
                new Product("DL02","corn","local",Category.DELI),
                new Product("DL03","mashed potatoes","local",Category.DELI),
                new Product("DL04","wings","local",Category.DELI),
                new Product("FL01","roses","local",Category.FLORAL),
                new Product("FL02","tulips","local",Category.FLORAL),
                new Product("SF01","crab","ocean",Category.SEAFOOD),
                new Product("SF02","grouper","ocean",Category.SEAFOOD),
                new Product("SF03","salmon","ocean",Category.SEAFOOD),
                new Product("SF04","shrimp","ocean",Category.SEAFOOD),
                new Product("GY01","greek yogurt","Oikos",Category.DAIRY),
                new Product("PTZ1","pretzels","Snyder of Hanover",Category.SNACKS),
                new Product("PN01","peanuts","Planters",Category.SNACKS),
                new Product("FHD1","flamin' hot chips","Doritos",Category.SNACKS),
                new Product("SP01","spaghetti pasta","Barilla",Category.PASTA),
                new Product("PP01","penne pasta","Barilla",Category.PASTA),
                new Product("RN22","ramen noodles","Maruchan",Category.PASTA),
                new Product("HK01","ketchup","Heinz",Category.CONDIMENTS),
                new Product("HM01","mustard","Heinz",Category.CONDIMENTS),
                new Product("SR01","sriracha","Huy Fong",Category.CONDIMENTS),
                new Product("PD01","diapers","Pampers",Category.BABY),
                new Product("BP01","baby powder","Johnsons's",Category.BABY),
                new Product("BBF1","banana baby food","Gerber",Category.BABY),
                new Product("PS01","shampoo","Pantene",Category.BEAUTY),
                new Product("DBW1","body wash","Dove",Category.BEAUTY),
                new Product("OSD1","deodorant","Old Spice",Category.BEAUTY),
                new Product("PL01","paper plates","Dixie",Category.HOUSEWARES),
                new Product("LD01","laundry detergent","Tide",Category.HOUSEWARES),
                new Product("DS01","dish soap","Dawn",Category.HOUSEWARES),
                new Product("DF01","dog food","Pedigree",Category.PETS),
                new Product("CF01","cat food","Purina",Category.PETS)
        ));

        products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p, random.nextDouble(0.50, 2.75),
                1000,5)));
    }

    private void stockAisles() {
        aisleInventory = new EnumMap<>(Category.class);
        for(InventoryItem item : inventory.values()) {
            Category aisle = item.getProduct().category();

            Map<String, InventoryItem> productMap = aisleInventory.get(aisle);
            if(productMap == null) {
                productMap = new TreeMap<>();
            }
            productMap.put(item.getProduct().name(), item);
            aisleInventory.putIfAbsent(aisle, productMap);
        }
    }

    private void listInventory() {
        System.out.println(separator);
        inventory.values().forEach(System.out::println);
    }
}
