package collegecatnip.petpocalypse;
/**
 * Class to hold the Pet
 *
 * @author Kendall Devich
 * Date Created: 9/23/2024
 *
 * @author Jaime Lee
 * Last Modified: 9/27/2024
 * Patch Notes:
 *		Added constructor
 */

public class Cat implements Pet
{
    // Attributes
    private String nickName;
    private String breed;
    private String flavor_text;
    private int level;
    private int rarity;
    private int pettionary_id;

    // Constructor
    public Cat(String nickname, String breed, String pet_entry, int level, int rarity, int id)
    {
        nickName = nickname;
        this.breed = breed;
        flavor_text = pet_entry;
        this.level = level;
        this.rarity = rarity;
        pettionary_id = id;
    }
    
    // Getters and Setters
    public String getNickName(){
        return nickName;
    }

    public void setNickName(String name){
        nickName = name;
    }

    public String getBreed(){
        return breed;
    }

    public String getFlavorText(){
        return flavor_text;
    }

    public int getLevel(){
        return level;
    }

    public int getRarity(){
        return rarity;
    }

    public int getPettionaryID() {
        return pettionary_id;
    }

}
