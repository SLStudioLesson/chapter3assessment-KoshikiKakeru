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
                        addNewRecipe();
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
        try {
            ArrayList<Recipe> recipies = new ArrayList<>();
            CSVDataHandler handler = new CSVDataHandler();
            recipies = handler.readData();
            // ファイルが空だった場合
            if (recipies.size() == 0) {
                System.out.println("No recipes available.");
            } else {
                System.out.println("\nRecipies:");
                System.out.println("-----------------------------------");

                for (int i = 0; i < recipies.size(); i++) {
                    Recipe recipe = recipies.get(i);
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.print("Main Ingredients: ");

                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    ingredients = recipe.getIngredients();
                    for (Ingredient ing : ingredients) {
                        System.out.print(ing.getName());
                    }
                    System.out.println("\n-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: 例外のメッセージ");
        }
    }

    private void addNewRecipe() {
        // ユーザーからレシピ名と主な材料を入力させ、DataHandlerを使用してrecipes.csvに新しいレシピを追加
        // IOExceptionを受け取った場合はFailed to add new recipe: 例外のメッセージとコンソールに表示
        // 材料の入力はdoneと入力するまで入力を受け付ける
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ingredient = null;
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        try {
            System.out.println("\nAdding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            System.out.println("Enter ingredients (type 'done' when finished):");
            while (true) {
                System.out.print("Ingredient: ");
                ingredient = reader.readLine();
                if (ingredient.equals("done")) {
                    break;
                } else {
                    Ingredient ing = new Ingredient(ingredient);
                    ingredients.add(ing);
                }
            }

            Recipe recipe = new Recipe(recipeName, ingredients);
            CSVDataHandler handler = new CSVDataHandler();
            handler.writeData(recipe);
            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: 例外のメッセージ");
        }
    }
}
