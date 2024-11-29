import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveTester
{
    public static void main(String[] args)
    {
        PlayerSave save = new PlayerSave();
        
        save.loadPlayerData();
        
        PlayerData player = new PlayerData();
        
        player.setName("Failed");
        player.setLove(-1);
        player.setTreats(-1);
        player.setUserId(-1);
        
        player.printInfo();
        
        save.loadPlayerData();
        
        player.printInfo();
    }
}