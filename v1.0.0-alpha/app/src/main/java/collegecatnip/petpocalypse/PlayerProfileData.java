package collegecatnip.petpocalypse;

/*
 * Class to manage the players profile data for the player class
 *
 * @author Madison Ridore
 * Date Created: 9/26/2024
 *
 * @author Jaime Lee
 * Last modified: 9/27/2024
 * Patch Notes:
 *      added a pet_data editor
 */
import java.util.ArrayList;

public class PlayerData {

    // player variables
    private String playerName;
    private int user_id;
    private int love;
    private int treats;
    private long player_logoff;
    private ArrayList<Pet> pet_data;


    public PlayerData() {
        pet_data = new ArrayList<>();
        love = 0;
        treats = 0;
    }

    // getters and setters

    public void addPet(Pet new_pet)
    {
        pet_data.add(new_pet);
    }
    
    public ArrayList<Pet> getPetList()
    {
        return pet_data;
    }
    
    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getTreats() {
        return treats;
    }

    public void setTreats(int treats) {
        this.treats = treats;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerNickname(String playerName) {
        this.playerName = playerName;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public long getPlayerLogoff() {
        return player_logoff;
    }

    public void setPlayerLogoff(long player_logoff) {
        this.player_logoff = player_logoff;
    }

    // method to update monies
    public void updateMonies(long additionalMonies) {
        this.love += additionalMonies;
    }
}
