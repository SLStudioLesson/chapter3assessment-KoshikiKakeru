package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        while ((line = reader.readLine()) != null) {
            ArrayList<Ingredient> reaIngredients = new ArrayList<>();
            String[] r = line.split(",");
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
        // 新しいレシピをrecipes.csvに追加します。
        // レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true))) {
            String in = "";
            for (Ingredient ing : recipe.getIngredients()) {
                in += ", " + ing.getName();
            }
            String write = recipe.getName() + in;
            writer.write(write);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: 例外のメッセージ");
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}
