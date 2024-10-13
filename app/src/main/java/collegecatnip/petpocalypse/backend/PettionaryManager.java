package collegecatnip.petpocalypse.backend;
/**
 * Class to manage all pet-tionary entries, whether they should be active, and what is in them
 *
 * @author Jaime Lee
 * Date Created: 9/20/2024
 */
import java.util.ArrayList;
public class PettionaryManager
{
    /* Holds all Pettionary entries */
    private static ArrayList<PettionaryEntry> Pettionary;

    /* default constructor */
    public PettionaryManager()
    {
        Pettionary = new ArrayList<PettionaryEntry>();
    }

    /* add an entry that has already been created */
    public void addEntry(PettionaryEntry new_entry)
    {
        Pettionary.add(new_entry);
    }

    /* create a new entry and add to Pettionary, requires same parameters as PettionaryEntry constructor */
    public void addEntry(String breed, int ID, int rarity, String size, String flavor_text, String secret_text, boolean showEntry, boolean showSecret)
    {
        PettionaryEntry new_entry = new PettionaryEntry(breed, ID, rarity, size, flavor_text, secret_text, showEntry, showSecret);
    }

    /* find the index of an entry, given the pet's ID and return */
    public int findEntry(int ID)
    {
        for(int i = 0; i < Pettionary.size(); i++)
        {
            if(Pettionary.get(i).getID() == ID)
            {
                return i;
            }
        }
        return -1;
    }

    /* get entry given the index */
    public PettionaryEntry getEntry(int index)
    {
        return Pettionary.get(index);
    }
}