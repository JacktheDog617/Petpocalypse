package collegecatnip.petpocalypse.backend;
/**
 * a shop class that buys pets and adds them to their pettionary
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 *
 * @author Jaime Lee
 * Last Modified: 10/23/2024
 * Patch Notes:
 *		added purchasing of pet carrier (lootbox)
 */

public class Shop {
    LootBox petCarrier = new LootBox(PlayerData.getPetsOwned());
    IncomeCalculator multiplier = new IncomeCalculator();
    int tempID = 0;

    private PlayerData playerData;

    private static final int BASIC_PET_COST = 100; // cost of a basic pet
    private static final int BASIC_PET_CARRIER_COST = 100; // cost of a basic pet carrier
    private static final int SILVER_PET_CARRIER_COST = 200; // cost of a basic pet carrier
    private static final int GOLD_PET_CARRIER_COST = 300; // cost of a basic pet carrier

    private static final int ROOM_ITEM_COST = 100;

    public Shop(PlayerData playerData) {
        this.playerData = playerData; //loading the players data
    }

    // method to buy a basic pet
    public void buyBasicPet() {

        if (playerData.getLove() >= BASIC_PET_COST) {
            playerData.setLove(playerData.getLove() - BASIC_PET_COST);

            Pet basicPet = new Cat("Fuz", "Cat", 1,0,1, 11010, false);
            playerData.addPet(basicPet);

            displayPetDetails(basicPet);
            System.out.println("You bought a Basic Pet!");
        } else {
            System.out.println("Not enough Love to buy this pet!");

            long remaining = BASIC_PET_COST - playerData.getLove();

            System.out.println(remaining + " more love is needed to buy this pet");
        }
    }

    // method to buy a basic pet carrier
    public void buyBasicPetCarrier() {

        if (playerData.getLove() >= BASIC_PET_CARRIER_COST) {
            playerData.setLove(playerData.getLove() - BASIC_PET_CARRIER_COST);

            Pet new_pet = petCarrier.openBox(1);

            playerData.addPet(new_pet);

            displayPetDetails(new_pet);
            System.out.println("You opened a Basic Pet Carrier!");
        } else {
            System.out.println("Not enough Love to buy this pet carrier!");

            long remaining = BASIC_PET_CARRIER_COST - playerData.getLove();

            System.out.println(remaining + " more love is needed to buy this pet carrier");
        }
    }

    // method to buy a silver pet carrier
    public void buySilverPetCarrier() {

        if (playerData.getLove() >= SILVER_PET_CARRIER_COST) {
            playerData.setLove(playerData.getLove() - SILVER_PET_CARRIER_COST);

            Pet new_pet = petCarrier.openBox(2);

            playerData.addPet(new_pet);

            displayPetDetails(new_pet);
            System.out.println("You opened a Silver Pet Carrier!");
        } else {
            System.out.println("Not enough Love to buy this pet carrier!");

            long remaining = SILVER_PET_CARRIER_COST - playerData.getLove();

            System.out.println(remaining + " more love is needed to buy this pet carrier");
        }
    }

    // method to buy a golden pet carrier
    public void buyGoldPetCarrier() {

        if (playerData.getLove() >= GOLD_PET_CARRIER_COST) {
            playerData.setLove(playerData.getLove() - GOLD_PET_CARRIER_COST);

            Pet new_pet = petCarrier.openBox(3);

            playerData.addPet(new_pet);

            displayPetDetails(new_pet);
            System.out.println("You opened a Golden Pet Carrier!");
        } else {
            System.out.println("Not enough Love to buy this pet carrier!");

            long remaining = GOLD_PET_CARRIER_COST - playerData.getLove();

            System.out.println(remaining + " more love is needed to buy this pet carrier");
        }
    }

    public void buyRoomItem(int itemID)
    {
        switch(itemID)
        {
            case 1:
                if (playerData.getLove() >= ROOM_ITEM_COST) {
                    playerData.setLove(playerData.getLove() - ROOM_ITEM_COST);

                    multiplier.toggleMultiplier(tempID, 1);
                    tempID++;

                    System.out.println("You bought a room item!");
                } else {
                    System.out.println("Not enough Love to buy a room item!");

                    long remaining = ROOM_ITEM_COST - playerData.getLove();

                    System.out.println(remaining + " more love is needed to buy a room item.");
                }
        }
    }

    private void displayPetDetails(Pet pet) {
        System.out.println("Pet Breed: " + pet.getBreed());
        System.out.println("Rarity: " + pet.getRarity());
    }
}