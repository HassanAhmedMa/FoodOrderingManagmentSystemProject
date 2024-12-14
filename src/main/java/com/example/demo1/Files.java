package com.example.demo1;

import Entities.FoodItem;
import Entities.Order;
import Entities.Restaurant;
import Personchild.Customer;
import Personchild.DeliveryStaff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files {
    public static List<DeliveryStaff> deliveryStaffList = new ArrayList<>();
    public static List<Restaurant> restaurants = new ArrayList<>();
    public static List<String> RestaurantnamesList = new ArrayList<>();
    public static List<Float> RatingList = new ArrayList<>();
   // public static List<String> RestaurantnamesList = new ArrayList<>();
    public static void loadNames(String fileName) throws FileNotFoundException {





        Scanner sc = new Scanner(new File(fileName));

        ArrayList<String> names = new ArrayList<String>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim(); // Trim whitespace from the line

            if (!line.isEmpty()) { // Check if the line is not empty
                names.add(line);           // Add the line to the list
            }
        }
        RestaurantnamesList.addAll(names);

    }

    public static void setDeliveryStaffList(String fileName) throws FileNotFoundException{

        DeliveryStaff currentStaff = null;

        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();

                if (line.startsWith("*")) {
                    currentStaff = new DeliveryStaff(line.substring(1).split(" "));
                    deliveryStaffList.add(currentStaff);
                }
                else if (line.startsWith("=")) {
                    String[] parts = line.substring(1).split("\\s", 5);
                    //System.out.println(parts[1]);
                    int orderId = Integer.parseInt(parts[0]);
                    String foodData = parts[1].substring(1, parts[1].length() - 1); // Remove parentheses
                    List<FoodItem> foodItems = new ArrayList<>();

                    //System.out.println("Order ID: " + orderId);
                    //System.out.println("Food Data: " + foodData);
                    //System.out.println("Customer Name: " + parts[2]);
                    //System.out.println("Order Status: " + parts[3]);
//
// Parse food data//
                    //System.out.println(foodData);
                    for (String item : foodData.split(",")) {
                        item = item.trim(); // Clean up whitespace around each food item
                        System.out.println("Raw Item: " + item); // Debugging

                        // Use a regex to split the name, price, and type
                        String[] foodParts = item.split("\\|"); // Split on space before a number


                        String name = foodParts[0].trim(); // First part is the name
                        float price = Float.parseFloat(foodParts[1].replace("f", ""));
                        String type = foodParts[2].trim();
                        int quantity = Integer.parseInt(foodParts[3].trim());
                        FoodItem foodItem = new FoodItem(name, price, type, "NoImageAvailable");
                        foodItem.setQuantityInCart(quantity);
                        foodItems.add(foodItem);

                        //System.out.println("Parsed Food Item: " + name + ", " + price + ", " + type);

                    }
                    Order TempOrder = new Order(orderId,Integer.parseInt(parts[3]) ,foodItems,parts[2]);
                    TempOrder.setOrderPrice(Float.parseFloat(parts[4]));
                    currentStaff.addOrder(TempOrder);

                }

        }
            System.out.println(deliveryStaffList);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


    public static DeliveryStaff getDeliveryStaff(String deliveryStaffName ) {
        for(DeliveryStaff deliveryStaff : deliveryStaffList){
            if(deliveryStaff.getFirstName().equals(deliveryStaffName)){
                return deliveryStaff;
            }
        }
        return null;
    }

    public static void setFoodItems(String fileName) throws FileNotFoundException
    {
        Restaurant RestaurantToAddFoodItemsIn = new Restaurant("","",List.of("",""),List.of("",""));
        FoodItem FoodItemToAdd = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                if(!line.isEmpty() && line.startsWith("#"))
                {
                    RestaurantToAddFoodItemsIn  = returnByName(line.substring(1).toLowerCase());

                    //System.out.println(line.substring(1));


                }
                else if(line.startsWith("*") && !line.isEmpty())
                {
                    String[] parts = line.substring(1).split(" ");
                    if (parts.length >= 4) {
                        String FoodName = parts[0];
                        Float Price = Float.parseFloat(parts[1].replace("f", ""));
                        String FoodType = parts[2];
                        String ImgSrc;
                        if(parts[3].equalsIgnoreCase("+"))
                        {
                            ImgSrc = "NoImageAvailable.png";
                        }
                        else{
                            ImgSrc = parts[3];
                            System.out.println(ImgSrc);
                        }

                        FoodItemToAdd = new FoodItem(FoodName, Price, FoodType , ImgSrc);
                        //System.out.println("  Food Item: " + FoodName + ", Price: " + Price + ", Type: " + FoodType + ", ImgSrc: " + ImgSrc);
                    }
                }
                if(FoodItemToAdd != null)
                {
                    RestaurantToAddFoodItemsIn.addFoodItem(FoodItemToAdd);
                    FoodItemToAdd = null;
                }



            }


        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    public static Customer returnCustomerByName(String customerName)
    {
        for(Customer customer : listOfCustomers)
        {
            if(customer.getUsername().equals(customerName))
            {
                return customer;
            }
        }
        return null;

    }
    public static List<Customer> listOfCustomers = new ArrayList<>();

    public static void loadListOfCustomers(String fileName) throws FileNotFoundException {
        Customer customerToAdd = null;
        String TempUserName = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                if(!line.isEmpty() && line.startsWith("#"))
                {

                    TempUserName = line.substring(1).toLowerCase();
                    //System.out.println(line.substring(1));


                }
                else if(line.startsWith("*") && !line.isEmpty())
                {
                    String[] parts = line.substring(1).split(" ");
                    if (parts.length >= 5) {
                        String Password = parts[0];
                        String firstName = parts[1];
                        String LastName = parts[2];
                        String email = parts[3];
                        String phoneNumber = parts[4];

                        customerToAdd = new Customer(TempUserName, Password, firstName, LastName, email, phoneNumber);
                        TempUserName = "";


                        //System.out.println("  Food Item: " + FoodName + ", Price: " + Price + ", Type: " + FoodType + ", ImgSrc: " + ImgSrc);
                    }


                    listOfCustomers.add(customerToAdd);
                    SignupController.users.put(customerToAdd.getUsername(), customerToAdd.getPassword());

                }




            }
            } catch (IOException e) {
            throw new RuntimeException(e);
        }}

        public static void printCustomersData()
        {
            for(Customer customer : listOfCustomers)
            {
                customer.displayInfo();
            }
        }


        public static void GetMenuItemsForEachRestaurant() {
        for(Restaurant tempRestaurant : restaurants)
        {
            for(FoodItem foodItem : tempRestaurant.getMenuItems())
            {
                System.out.println(foodItem.getName());
            }
        }
    }

    protected static Restaurant returnByName(String RestaurantToFindByName)
    {
        List<String> lowerCaseRestaurantNamesList = new ArrayList<>();
        for(String restaurant : RestaurantnamesList)
        {
            lowerCaseRestaurantNamesList.add(restaurant.toLowerCase());
        }
        if(lowerCaseRestaurantNamesList.contains(RestaurantToFindByName.toLowerCase()))
        {
            //System.out.println("Required Restaurant : " + RestaurantToFindByName);
            for(Restaurant r : restaurants)
            {
                //System.out.println("Restraunt : " + r.getName());
                if(r.getName().equalsIgnoreCase(RestaurantToFindByName))
                {
                    return r;
                }
            }
        }
        else
        {
            System.out.println("RESTAURANT NOT FOUND 5555555555!!");
        }
        return null;
    }






    public static void setRestaurantNamesList() {
        System.out.println(Files.RestaurantnamesList);
        Restaurant restaurant;
        int i = 0;
        for(String name : Files.RestaurantnamesList)
        {

            restaurant = new Restaurant(name,Files.listOfGovernorate.get(i),Files.listOfAreas.get(i), Files.CategoriesList.get(i));
            restaurant.setImgLocation(Files.listOfImagesPath.get(i));
            restaurants.add(restaurant);
            i++;

        }
    }
    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }



    public static void loadRating(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));


        while (sc.hasNextLine()) {
            RatingList.add(sc.nextFloat());
        }


    }

    public static List<List<String>>  CategoriesList = new ArrayList<>();
    public static void loadCategories(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));

        while (sc.hasNextLine()) {
            String[] words = sc.nextLine().split("\\s+");
            List<String> oneTimeList = new ArrayList<>(List.of(words));
            CategoriesList.add(oneTimeList);
        }

    }
    public static List<String> listOfGovernorate = new ArrayList<>();
    public static void loadGovernorate(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));

        ArrayList<String> governorateNames = new ArrayList<String>();
        while (sc.hasNextLine()) {
            governorateNames.add(sc.nextLine());
        }
        listOfGovernorate.addAll(governorateNames);

    }

    public static List<List<String>> listOfAreas = new ArrayList<>();
    public static void loadAreas(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));

        while (sc.hasNextLine()) {
            String[] words = sc.nextLine().split("\\s+");
            List<String> oneTimeList = new ArrayList<>(List.of(words));
            listOfAreas.add(oneTimeList);
        }

    }
    public static List<String> listOfImagesPath = new ArrayList<>();
    public static void loadImages(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));


        while (sc.hasNextLine()) {
            listOfImagesPath.add(sc.nextLine());
        }


    }

    public static void loadAll(String namesLocation,String CategoriesLocation,String GovernorateLocation,String AreaLocation,String ImagesLocation) throws FileNotFoundException {
        loadNames(namesLocation);
        loadCategories(CategoriesLocation);
        loadGovernorate(GovernorateLocation);
        loadAreas(AreaLocation);
        loadImages(ImagesLocation);
        setRestaurantNamesList();



    }










   public static void printALlData() {
       printNamesList();
       printCategories();
       printAreas();
      printGovernorateList();


   }




    public static void printGovernorateList() {
        int i = 0;
        for (String governorate : listOfGovernorate) {
            System.out.println(governorate);
        }
    }

    public static void printNamesList() {
        for (String name : RestaurantnamesList) {
            System.out.println(name);
        }
    }
    public static void printImagePath() {
        for (String location : listOfImagesPath) {
            System.out.println(location);
        }
    }
    public static void printRatingList() {
        for (Float rating : RatingList) {
            System.out.println(rating);
        }
    }

    public static void printCategories() {
        for (List<String> category : CategoriesList) {
            for (String name : category) {
                System.out.println(name);
            }
        }
    }
    public static void printAreas() {
        for (List<String> Area : listOfAreas) {
            System.out.println(Area);
        }
    }
}
