/**
 * a shop class that buys pets and adds them to their pettionary 
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 *
 * @author Madison Ridore
 * Last Modified: 9/27/2024
 * Patch Notes:
 *		prints out the pet details and verification that the process did or didn't go through
 */
import java.util.ArrayList;


public class ShopActivity {
    private PlayerProfileData playerData;

    private static final int BASIC_PET_COST = 100; // cost of a basic pet

    public ShopActivity(PlayerProfileData playerData) {
       this.playerData = playerData; //loading the players data
    }

    // method to buy a basic pet
    public void buyBasicPet() {

        if (playerData.getLove() >= BASIC_PET_COST) {
            playerData.setLove(playerData.getLove() - BASIC_PET_COST);

            Pet basicPet = new Cat("Fuz", "Cat", "A menace", 1, 1, 11010);
            playerData.addPet(basicPet);

            displayPetDetails(basicPet);
            System.out.println("You bought a Basic Pet!");
        } else {
            System.out.println("Not enough Love to buy this pet!");

            int remaining = BASIC_PET_COST - playerData.getLove();
            
            System.out.println(remaining + " more love is needed to buy this pet");
        }
    }

    private void displayPetDetails(Pet pet) {
        System.out.println("Pet Nickname: " + pet.getNickName());
        System.out.println("Pet Breed: " + pet.getBreed());
        System.out.println("Flavor Text: " + pet.getFlavorText());
        System.out.println("Level: " + pet.getLevel());
        System.out.println("Rarity: " + pet.getRarity());
        System.out.println("Pettionary ID: " + pet.getPettionaryID());
    }


    public static void main(String[] args) {

        PlayerProfileData playerData = new PlayerProfileData();
        playerData.setLove(10); //play around with ths number for testing
        playerData.setTreats(50); //even though it doesnt matter

        ShopActivity profile = new ShopActivity(playerData);

        //simulate buying a pet
        profile.buyBasicPet();

        // display current profile data
        System.out.println("Current Love: " + playerData.getLove());
        System.out.println("Current Treats: " + playerData.getTreats());
        //System.out.println("Pets Owned: " + playerData.getPetsOwned()); will need a ToString in the cat class or pet interface to print something more than random numbers.
    }
}
