package collegecatnip.petpocalypse.backend;
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

public class Shop {
    private PlayerData playerData;

    private static final int BASIC_PET_COST = 100; // cost of a basic pet

    public Shop(PlayerData playerData) {
        this.playerData = playerData; //loading the players data
    }

    // method to buy a basic pet
    public void buyBasicPet() {

        if (playerData.getLove() >= BASIC_PET_COST) {
            playerData.setLove(playerData.getLove() - BASIC_PET_COST);

            Pet basicPet = new Cat("Fuz", "Cat", 1, 1, 11010, false);
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
        System.out.println("Pet Breed: " + pet.getBreed());
        System.out.println("Rarity: " + pet.getRarity());
    }
}