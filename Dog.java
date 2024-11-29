/**
 * Class to hold the dogs data
 *
 * @author Jaime Lee
 * Date Created: 11/1/2024
 * 
 * @author Jaime Lee
 * Date Modified: 11/26/2024
 * Modifications
 *      updated to match changes in cat class
 */

public class Dog implements Pet, Comparable<Dog>
{
    // Attributes
    private int petID;
    private int pettionaryID;
    private String breed;
    private String name;
    private int level;
    private int size;
    private int rarity;
    private int duplicates;
    private boolean owned;
    private String flavor;
    private String secret;

    // Comparable
    @Override
    public int compareTo(Dog other)
    {
        return this.petID - other.petID;
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', breed='" + breed + ", owned=" + owned + '}';
    }
    
    // for json reader
    public Dog(){}

    // for TypeAdapter
    public Dog(int petID,
            int pettionaryID,
            String breed,
            String name,
            int level,
            int size,
            int rarity,
            int duplicates,
            boolean owned,
            String flavor,
            String secret)
    {
        this.petID = petID;
        this.pettionaryID = pettionaryID;
        this.breed = breed;
        this.name = name;
        this.level = level;
        this.size = size;
        this.rarity = rarity;
        this.duplicates = duplicates;
        this.owned = owned;
        this.flavor = flavor;
        this.secret = secret;
    }
    
    // Getters and Setters
    
    public int getPetID()
    {
        return petID;
    }
    
    public int getPettionaryID()
    {
        return pettionaryID;
    }
    
    public String getBreed()
    {
        return breed;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLevel(){
        return level;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public int getRarity(){
        return rarity;
    }

    public int getDuplicates(){
        return duplicates;
    }

    public void addDuplicate() {
        duplicates++;
    }

    public boolean isOwned()
    {
        return owned;
    }

    public void changedOwned(boolean isOwned) {
        this.owned = isOwned;
    }
    
    public String getFlavorText()
    {
        return flavor;
    }
    
    public String getSecret()
    {
        return secret;
    }
}