package collegecatnip.petpocalypse.backend;
/**
 * Class to hold the Pet
 *
 * @author Kendall Devich
 * Date Created: 9/23/2024
 *
 * @author Jaime Lee
 * Last Modified: 10/13/2024
 * Patch Notes:
 *	added constructor for pets with no nickname
 */

public class Cat implements Pet
{
    // Attributes
    private int id;
    private String nickName;
    private String breed;
    private int level;
    private int duplicates;
    private int rarity;
    private boolean owned;

    // Constructor for existing cat
    public Cat(String nickname, String breed, int level, int duplicates, int rarity, int id, boolean owned_status)
    {
        this.id = id;
        nickName = nickname;
        this.breed = breed;
        this.level = level;
        this.duplicates = duplicates;
        /**
         * 1 - Common
         * 2 - Rare
         * 3 - Legendary
         * 4 - Mystery
         * 5 - Dev
         */
        this.rarity = rarity;
        owned = owned_status;
    }

    // Constructor new cat
    public Cat(String breed, int rarity, int id)
    {
        this.id = id;
        this.breed = breed;
        this.level = 0;
        this.duplicates = 0;
        this.rarity = rarity;
        owned = false;
    }

    // Getters and Setters

    public int getID()
    {
        return id;
    }


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

    public int getDuplicates(){
        return duplicates;
    }

    public void addDuplicate() {
        duplicates++;
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