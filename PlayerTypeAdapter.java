/**
 * TypeAdapter for Gson to be able to serialize and deserialize the
 * Pet arraylist that contains Cat objects
 * 
 * @author Jaime Lee
 * Date Created: 11/26/2024
 */
import com.google.gson.TypeAdapter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class PlayerTypeAdapter extends TypeAdapter<PlayerData>
{
    private final TypeAdapter<ArrayList<Pet>> petListAdapter;
    
    public PlayerTypeAdapter(Gson gson)
    {
        TypeToken<ArrayList<Pet>> petList = new TypeToken<>(){};
        this.petListAdapter = gson.getAdapter(petList);
    }
    
    @Override
    public void write(JsonWriter out, PlayerData player) throws IOException
    {
        out.beginObject();
                
        out.name("name").value(player.getName());
        out.name("user_id").value(player.getUserId());
        out.name("love").value(player.getLove());
        out.name("treats").value(player.getTreats());
        out.name("player_logoff").value(player.getPlayerLogoff().toString());
        out.name("petsOwned"); petListAdapter.write(out, player.getPetsOwned());

        out.endObject();
    }
    
    @Override
    public PlayerData read(JsonReader in) throws IOException
    {
        PlayerData player;
        in.beginObject();

        String name = null;
        int user_id =  -1;
        long love = -1;
        int treats = -1;
        LocalDateTime player_logoff = null;
        ArrayList<Pet> petsOwned = null;

        while(in.hasNext())
        {
            String fieldName = in.nextName();
            switch(fieldName)
            {
                case "name":
                    name = in.nextString();
                    break;
                case "user_id":
                    user_id = in.nextInt();
                    break;
                case "love":
                    love = in.nextLong();
                    break;
                case "treats":
                    treats = in.nextInt();
                    break;
                case "player_logoff":
                    player_logoff = LocalDateTime.parse(in.nextString());
                    break;
                case "petsOwned":
                    petsOwned = petListAdapter.read(in);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        player = new PlayerData(name, user_id, love, treats, player_logoff, petsOwned);
        return player;
    }
}