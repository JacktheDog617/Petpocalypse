import java.util.ArrayList;

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
    IncomeCalculator multiplier = new IncomeCalculator();
    int tempID = 0;
    private LootBox petCarrier;

    private PlayerData playerData;
    private ArrayList<Pet> availablePets;

    private static final int BASIC_PET_COST = 100; // cost of a basic pet
    private static final int BASIC_PET_CARRIER_COST = 100; // cost of a basic pet carrier
    private static final int SILVER_PET_CARRIER_COST = 200; // cost of a basic pet carrier
    private static final int GOLD_PET_CARRIER_COST = 300; // cost of a basic pet carrier
    
    private static final int ROOM_ITEM_COST = 100;

    public Shop(PlayerData playerData)
    {
        this.playerData = playerData; //loading the players data
        petCarrier = new LootBox();
        //availablePets = PlayerSave.loadCatsFromJson("cats.json");

//        if (availablePets != null && !availablePets.isEmpty()) {
//            giveBasicPetAtStart();
//        } else {
//            System.out.println("No cats available in the JSON file to give as a basic pet.");
//        }
    }

    private void giveBasicPetAtStart() {
        Pet basicPet = availablePets.get(0); // assuming the first pet is the basic pet
        playerData.addPet(basicPet);
        System.out.println("You received the basic pet: " + basicPet.getName());
    }

//public void buyCat(int catIndex) {
//    ArrayList<Pet> availableCats = PlayerSave.loadCatsFromJson("cats.json");
//
//    if (availableCats != null && catIndex >= 0 && catIndex < availableCats.size()) {
//        Pet catToBuy = availableCats.get(catIndex);
//
//        int catCost = getCatCostByRarity(catToBuy.getRarity());
//
//        if (playerData.getLove() >= catCost) {
//            // deduct love and add the cat to petsOwned
//            playerData.setLove(playerData.getLove() - catCost);
//            playerData.addPet(catToBuy);
//            System.out.println("You bought a Cat!");
//            displayCatDetails(catToBuy);
//        } else {
//            System.out.println("Not enough Love to buy this cat!");
//
//            long remaining = catCost - playerData.getLove();
//            System.out.println(remaining + " more Love is needed to buy this cat.");
//        }
//    } else {
//        System.out.println("Invalid cat selection or no cats available!");
//    }
//}
//    private int getCatCostByRarity(int rarity) {
//        switch (rarity) {
//            case 1:
//                return 100;
//            case 2:
//                return 200;
//            case 3:
//                return 300;
//            case 4:
//                return 500;
//            case 5:
//                return 1000;
//            default:
//                return 100;
//    }
//
//}

    // method to buy a basic pet carrier
    public void buyBasicPetCarrier() {

        if (playerData.getLove() >= BASIC_PET_CARRIER_COST) {
            playerData.setLove(playerData.getLove() - BASIC_PET_CARRIER_COST);

            Pet new_pet = petCarrier.openBox(1);

            playerData.addPet(new_pet);

            displayCatDetails(new_pet);
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

            displayCatDetails(new_pet);
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

            displayCatDetails(new_pet);
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

    private void displayCatDetails(Pet cat) {
        System.out.println("Cat Details:");
        System.out.println("Name: " + cat.getName());
        System.out.println("Breed: " + cat.getBreed());
        System.out.println("Is Owned: " + cat.isOwned());
        System.out.println("Rarity: " + cat.getRarity());
    }
}