import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TemporarySave
{
    private PlayerData save1 = new PlayerData();
    
    private String cat_fname = "cats.txt";
    private String dog_fname = "dogs.txt";
    
    public TemporarySave(int team)
    {
        loadPetData(team);
        loadDefaultPlayerData(team);
    }
    
    private void loadPetData(int team)
    {
        switch(team)
        {
            case 0:
                // load cat data
                String[] cat;
                String[] cat_data = (readIn(cat_fname)).split("=");
                for(String data:cat_data)
                {
                    cat = data.split(",");
                    save1.addPet(new Cat(cat[0], Integer.parseInt(cat[1]), Integer.parseInt(cat[2]), Integer.parseInt(cat[3])));
                }
                break;
            case 1:
                //load dog data
                String[] dog;
                String[] dog_data = (readIn(dog_fname)).split("=");
                for(String data:dog_data)
                {
                    dog = data.split(",");
                    save1.addPet(new Dog(dog[0], Integer.parseInt(dog[1]), Integer.parseInt(dog[2]), Integer.parseInt(dog[3])));
                }
                break;
            default:
                System.out.println("Pet data could not be loaded...\nExiting...");
                System.exit(0);
        }
    }
    
    private void loadPettionary()
    {
        
    }
    
    private void loadDefaultPlayerData(int team)
    {
        // make it so that it toggles a random cat on;
        save1.setLove(500);
    }
    
    private String readIn(String file_name)
    {
        String out = "";
        try
        {
            File toRead = new File(file_name);
            Scanner fileReader = new Scanner(toRead);
            while(fileReader.hasNextLine())
            {
                out = out + fileReader.nextLine();
            }
            fileReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }
        return out;
    }
}