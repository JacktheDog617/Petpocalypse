package collegecatnip.petpocalypse.backend;
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

    public void setNickName(String nickname);

    public String getBreed();

    public int getLevel();

    public int getRarity();

    public boolean isOwned();

    public void changedOwned(boolean isOwned);
}