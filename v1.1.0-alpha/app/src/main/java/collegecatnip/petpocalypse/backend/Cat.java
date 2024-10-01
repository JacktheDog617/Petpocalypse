package collegecatnip.petpocalypse.backend;
/**
 * Class to hold the Pet
 *
 * @author Kendall Devich
 * Date Created: 9/23/2024
 *
 * @author Jaime Lee
 * Last Modified: 9/30/2024
 * Patch Notes:
 *		Added owned boolean
 */

public class Cat implements Pet
{
    // Attributes
    private String nickName;
    private String breed;
    private int level;
    private int rarity;
    private boolean owned;

    // Constructor
    public Cat(String nickname, String breed, int level, int rarity, int id, boolean owned_status)
    {
        nickName = nickname;
        this.breed = breed;
        this.level = level;
        this.rarity = rarity;
        owned = owned_status;
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

    public int getLevel(){
        return level;
    }

    public int getRarity(){
        return rarity;
    }

    public boolean isOwned()
    {
        return owned;
    }

    public void changedOwned(boolean isOwned) {
        owned = isOwned;
    }
}
