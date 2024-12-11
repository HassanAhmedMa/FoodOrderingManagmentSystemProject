package com.example.demo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files {
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
