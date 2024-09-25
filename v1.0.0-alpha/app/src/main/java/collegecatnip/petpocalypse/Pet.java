package collegecatnip.petpocalypse;
/**
 * Pet interface to sync cat and dog classes
 *
 * @author Kendall Devich
 * Date Created: 9/23/2024
 *
 * @author Kendall Devich
 * Last Modified: 9/23/2024
 * Patch Notes:
 *		The base of the pet class.
 */

public interface Pet
{
    // Getters and Setters
    public String getNickName();

    public Void setNickName(String name);

    public String getBreed();

    public String getFlavorText();

    public int getAction();

    public int getRarity();

    public int getPettionaryID();

}
