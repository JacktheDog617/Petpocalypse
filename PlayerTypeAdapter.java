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
import java.util.Arrays;
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
        out.name("multipliers").value(Arrays.deepToString(player.getMultipliers()));
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
        double[][] multipliers = null;

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
                case "multipliers":
                    multipliers = parseMultipliers(in.nextString());
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        player = new PlayerData(name, user_id, love, treats, player_logoff, petsOwned, multipliers);
        return player;
    }
    
    /**
     * Takes in a string, using predetermined unchanging distances between values
     * grab double [i][3] which is the only changing value in the array
     * @param multipliers_string
     * @return 
     */
    private double[][] parseMultipliers(String multipliers_string)
    {
        // create multidimensional array
        double[][] multipliers = new double[6][3];
        // needed to grab the correct char position
        int gap = 0;
        for(int i = 0; i < 6; i++)
        {
            // if it is the first element, skip to double[0][3]
            if(i == 0)
            {
                gap += 12;
            }
            else // skip to double[i][3]
            {
                gap += 17;
            }
            multipliers[i][0] = i;      // set ID
            multipliers[i][1] = 0.5;    // set multiplier amount
            // set owned based on the int part of the double in multipliers_string[i][3]
            multipliers[i][2] = Character.getNumericValue(multipliers_string.charAt(gap));
        }
        return multipliers;
    }
}