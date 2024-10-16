package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipies();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    public void displayRecipies() {
        // DataHandlerから読み込んだレシピデータを整形してコンソールに表示します。
        // IOExceptionを受け取った場合はError reading file: 例外のメッセージとコンソールに表示します
        // レシピの表示
        System.out.println("");
        System.out.println("Recipies:");
        System.out.println("-----------------------------------");

        try {
            ArrayList<Recipe> recipies = new ArrayList<>();
            CSVDataHandler handler = new CSVDataHandler();
            recipies = handler.readData();
            // ファイルが空だった場合
            if (recipies.size() == 0) {
                System.out.println("No recipes available.");
            } else {
                for (int i = 0; i < recipies.size(); i++) {
                    Recipe recipe = recipies.get(i);
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.print("Main Ingredients: ");

                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    ingredients = recipe.getIngredients();
                    for (Ingredient ing : ingredients) {
                        System.out.print(ing);
                    }
                    System.out.println("-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: 例外のメッセージ");
        }
    }
}
