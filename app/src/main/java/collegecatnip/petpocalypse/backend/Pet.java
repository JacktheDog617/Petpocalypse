package collegecatnip.petpocalypse.backend;
/**
 * Pet interface to sync cat and dog classes
 *
 * @author Kendall Devich
 * Date Created: 9/23/2024
 *
 * @author Jaime Lee
 * Last Modified: 11/25/2024
 * Patch Notes:
 *      merging pettionary entry and
 *      pettionary manager into cat
 */

public interface Pet
{    
    // Getters and Setters
    public int getPetID();
    
    public int getPettionaryID();
    
    public String getBreed();
    
    public String getName();

    public void setName(String name);

    public int getLevel();
    
    public int getSize();
    
    public int getRarity();

    public int getDuplicates();

    public void addDuplicate();

    public boolean isOwned();

    public void changedOwned(boolean isOwned);
    
    public String getFlavorText();
    
    public String getSecret();
}