package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        // recipes.csvからレシピデータを読み込み、それをリスト形式で返します。
        String line;
        ArrayList<Recipe> readRecipes = new ArrayList<>();
        ArrayList<Ingredient> reaIngredients = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        while ((line = reader.readLine()) != null) {
            String[] r = line.split(",");
            reaIngredients.clear();
            for (int i = 1; i < r.length; i++) {
                Ingredient ing = new Ingredient(r[i]);
                reaIngredients.add(ing);
            }
            Recipe recipes = new Recipe(r[0], reaIngredients);
            readRecipes.add(recipes);
        }
        return readRecipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {

    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}
