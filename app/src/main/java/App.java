import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();
            
            // ユーザーの選択に応じて、CSVDataHandlerまたはJSONDataHandlerのインスタンスを生成する
            // 選択されたデータハンドラーをcom/recipeappパッケージのRecipeUIに渡し、
            // displayMenuメソッドを呼び出してメインメニューを表示
            switch(choice){
            // 「1」を選択した場合、CSVDataHandlerインスタンスを生成する
            // 引数が0個のコンストラクタを実行するものとする
                case "1":
                DataHandler csvDataHandler = new CSVDataHandler();
                RecipeUI recipeUI = new RecipeUI(csvDataHandler);
                recipeUI.displayMenu();
                break;
            // 「2」を選択した場合、JSONDataHandlerインスタンスを生成する
                case "2":
                DataHandler jsonDataHandler = new JSONDataHandler();
                RecipeUI recipeUI2 = new RecipeUI(jsonDataHandler);
                recipeUI2.displayMenu();
                break;
            //不正な入力（「1」「2」以外）が与えられた場合、CSVDataHandlerインスタンスを生成する
                default:
                DataHandler csvDataHandler2 = new CSVDataHandler();
                RecipeUI recipeUI3 = new RecipeUI(csvDataHandler2);
                recipeUI3.displayMenu();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}