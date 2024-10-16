package com.recipeapp.datahandler;

import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

public interface DataHandler {
    public abstract String getMode();

    public abstract ArrayList<Recipe> readData() throws IOException;

    public abstract void writeData(Recipe recipe) throws IOException;

    public abstract ArrayList<Recipe> searchData(String keyword) throws IOException;

}
