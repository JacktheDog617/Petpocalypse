/**
 * Class to hold the cats data
 *
 * @author Kendall Devich
 * Date Created: 9/23/2024
 *
 * @author Jaime Lee
 * Last Modified: 10/13/2024
 * Patch Notes:
 *	added constructor for pets with no nickname
 */

public class Cat implements Pet, Comparable<Cat>
{
    // Attributes
    private int PettionaryID;
    private int PetID;
    private String nickName;
    private String breed;
    private int level;
    private int duplicates;
    private int rarity;
    private boolean owned;

    // Comparable
    @Override
    public int compareTo(Cat other)
    {
        return this.PetID - other.PetID;
    }
    
    // Constructor for existing cat
    public Cat(String nickname, String breed, int level, int duplicates, int rarity, int PetID, int PettionaryID, boolean owned_status)
    {
        this.PetID = PetID;
        this.PetID = PettionaryID;
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
    public Cat(String breed, int rarity, int PetID, int PettionaryID)
    {
        this.PetID = PetID;
        this.PettionaryID = PettionaryID;
        this.breed = breed;
        this.level = 0;
        this.duplicates = 0;
        this.rarity = rarity;
        owned = false;
    }

    // Getters and Setters
    
    public int getPetID()
    {
        return PetID;
    }
    
    public int getPettionaryID()
    {
        return PettionaryID;
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