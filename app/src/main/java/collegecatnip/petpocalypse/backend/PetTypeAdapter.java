package collegecatnip.petpocalypse.backend;
/**
 * TypeAdapter for Gson to be able to serialize and deserialize the
 * Pet arraylist that contains Cat objects
 * 
 * @author Jaime Lee
 * Date Created: 11/26/2024
 */
import com.google.gson.TypeAdapter;
import java.io.IOException;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import java.util.ArrayList;

public class PetTypeAdapter extends TypeAdapter<ArrayList<Pet>>
{
    @Override
    public void write(JsonWriter out, ArrayList<Pet> pets) throws IOException
    {
        // begin creating array
        out.beginArray();
        // for each pet in array
        for(Pet pet: pets)
        {
            // start object
            out.beginObject(); 
            // if pet is Cat
            if(pet instanceof Cat)
            {
                // add field for type and label as cat
                // add rest of data fields and data
                out.name("type").value("Cat");
                out.name("petID").value(pet.getPetID());
                out.name("pettionaryID").value(pet.getPettionaryID());
                out.name("breed").value(pet.getBreed());
                out.name("name").value(pet.getName());
                out.name("level").value(pet.getLevel());
                out.name("size").value(pet.getSize());
                out.name("rarity").value(pet.getRarity());
                out.name("duplicates").value(pet.getDuplicates());
                out.name("owned").value(pet.isOwned());
                out.name("flavor").value(pet.getFlavorText());
                out.name("secret").value(pet.getSecret());
            }
            else if(pet instanceof Dog)
            {
                // add field for type and label as Dog
                // add rest of data fields and data
                out.name("type").value("Dog");
                out.name("petID").value(pet.getPetID());
                out.name("pettionaryID").value(pet.getPettionaryID());
                out.name("breed").value(pet.getBreed());
                out.name("name").value(pet.getName());
                out.name("level").value(pet.getLevel());
                out.name("size").value(pet.getSize());
                out.name("rarity").value(pet.getRarity());
                out.name("duplicates").value(pet.getDuplicates());
                out.name("owned").value(pet.isOwned());
                out.name("flavor").value(pet.getFlavorText());
                out.name("secret").value(pet.getSecret());
            }
            else
            {
                // add field for type and label as unknown
                // add rest of data fields and data
                out.name("type").value("Unknown");
                out.name("petID").value(pet.getPetID());
                out.name("pettionaryID").value(pet.getPettionaryID());
                out.name("breed").value(pet.getBreed());
                out.name("name").value(pet.getName());
                out.name("level").value(pet.getLevel());
                out.name("size").value(pet.getSize());
                out.name("rarity").value(pet.getRarity());
                out.name("duplicates").value(pet.getDuplicates());
                out.name("owned").value(pet.isOwned());
                out.name("flavor").value(pet.getFlavorText());
                out.name("secret").value(pet.getSecret());
            }
            out.endObject();
        }
        out.endArray();
    }
    
    @Override
    public ArrayList<Pet> read(JsonReader in) throws IOException
    {
        System.out.println("Using custom Pet reader...");
        // create return ArrayList
        ArrayList<Pet> pets = new ArrayList<>();
        // read beginning of array
        in.beginArray();
        // for each object in array
        while(in.hasNext())
        {
            // read beginning of objecy
            in.beginObject();
            
            // instantiate data fields with filler data to be overwritten
            // as it is read in by Gson
            String type = null;
            int petID = -1;
            int pettionaryID = -1;
            String breed = null;
            String name = null;
            int level = -1;
            int size = -1;
            int rarity = -1;
            int duplicates = -1;
            boolean owned = false;
            String flavor = null;
            String secret = null;
            
            // for each field name
            while(in.hasNext())
            {
                // read in field name and then write data from field to previously
                // instantiated data fields
                String fieldName = in.nextName();
                switch(fieldName)
                {
                    case "type":
                        type = in.nextString();
                        break;
                    case "petID":
                        petID = in.nextInt();
                        break;
                    case "pettionaryID":
                        pettionaryID = in.nextInt();
                        break;
                    case "breed":
                        breed = in.nextString();
                        break;
                    case "name":
                        name = in.nextString();
                        break;
                    case "level":
                        level = in.nextInt();
                        break;
                    case "size":
                        size = in.nextInt();
                        break;
                    case "rarity":
                        rarity = in.nextInt();
                        break;
                    case "duplicates":
                        duplicates = in.nextInt();
                        break;
                    case "owned":
                        owned = in.nextBoolean();
                        break;
                    case "flavor":
                        flavor = in.nextString();
                        break;
                    case "secret":
                        secret = in.nextString();
                        break;
                    default:
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            // read in end of object
            
            // create pet;
            Pet pet = null;
            
            // if pet type is cat, create Cat object
            // else create dog object
            switch(type)
            {
                case "Cat":
                    pet = new Cat(petID,pettionaryID,breed,name,level,size,rarity,duplicates,owned,flavor,secret);
                    break;
                case "Dog":
                    pet = new Dog(petID,pettionaryID,breed,name,level,size,rarity,duplicates,owned,flavor,secret);
                    break;
                default:
                    // catch errors and mark unknown
                    System.out.println("Unknown pet type: " + type);
                    break;
            }
            
            // if pet was properly created, add pet to array
            if(pet != null)
            {
                pets.add(pet);
            }
        }
        // read end of array and return
        in.endArray();
        return pets;
    }
}