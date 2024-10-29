package collegecatnip.petpocalypse.backend;
/**
 * Class to outline Entry objects that will be managed in PettionaryManager
 *
 * @author Jaime Lee
 * Date Created: 9/30/2024
 */
public class PettionaryEntry
{
    private final String breed;
    private final int ID;
    private final int rarity;
    private final String size;
    private final String flavor_text;
    private final String secret_text;
    private boolean showEntry;
    private boolean showSecret;

    /**
     * default constructor
     */
    public PettionaryEntry()
    {
        breed = "Example Entry";
        ID = 0;
        rarity = 0;
        size = "small/medium/large/extra large";
        flavor_text = "1-2 sentences about the personality";
        secret_text = "this will say a fun fact or a where it's from";
        showEntry = true;
        showSecret = true;
    }

    /**
     * constructor with parameters
     * @param breed
     * @param ID
     * @param rarity
     * @param size
     * @param flavor_text
     * @param secret_text
     * @param showEntry
     * @param showSecret
     */
    public PettionaryEntry(String breed, int ID, int rarity, String size, String flavor_text, String secret_text, boolean showEntry, boolean showSecret)
    {
        this.breed = breed;
        this.ID = ID;
        this.rarity = rarity;
        this.size = size;
        this.flavor_text = flavor_text;
        this.secret_text = secret_text;
        this.showEntry = showEntry;
        this.showSecret = showSecret;
    }

    /**
     * getters
     * @return
     */
    public String getBreed()
    {
        return breed;
    }

    public int getID()
    {
        return ID;
    }

    public int getRarity()
    {
        return rarity;
    }

    public String getSize()
    {
        return size;
    }

    public String getFlavorText()
    {
        return flavor_text;
    }

    public String getSecretText()
    {
        return secret_text;
    }

    /**
     * set visibility of entry
     * @return
     */

    public boolean shouldShowEntry()
    {
        return showEntry;
    }

    public boolean shouldShowSecret()
    {
        return showSecret;
    }

    public void changeVisibility(String which_entry, boolean new_visibility)
    {
        if(which_entry.toUpperCase().equals("FLAVOR_TEXT"))
        {
            showEntry = new_visibility;
        }
        else if(which_entry.toUpperCase().equals("SECRET_ENTRY"))
        {
            showSecret = new_visibility;
        }
    }
}