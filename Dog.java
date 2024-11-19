/**
 * Class to hold the dogs data
 *
 * @author Jaime Lee
 * Date Created: 11/1/2024
 */

public class Dog implements Pet, Comparable<Dog>
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
    public int compareTo(Dog other)
    {
        return this.PetID - other.PetID;
    }
    
    // Constructor for existing cat
    public Dog(String nickname, String breed, int level, int duplicates, int rarity, int PetID, int PettionaryID, boolean owned_status)
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
    public Dog(String breed, int rarity, int PetID, int PettionaryID)
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