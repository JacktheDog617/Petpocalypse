import java.util.Scanner;
import java.util.ArrayList;

public class main
{
    private static int CAT = 0;
    private static int DOG = 1;
    
    private static TemporarySave saveManager;
    
    private static String input;
    private static Scanner console = new Scanner(System.in);
    
    private static String mainMenu = "************************************\n"
            + "Choose one: [X to Exit]\n"
            + "[B] Print current income\n"
            + "[S] Go to shop\n"
            + "[P] Show pets\n"
            + "[R] Open Room Editor\n"
            + "************************************";
    
    private static long start;
    private static long end;
    private static long elapsed;
    
    private static PlayerData player = new PlayerData();
    private static IncomeCalculator inCalc = new IncomeCalculator();
    
    private static Shop shop;
    
    private static String shopMenu = "------------=+=------------\n"
            + "[1] Basic Pet Box\n"
            + "[2] Silver Pet Box\n"
            + "[3] Gold Pet Box\n"
            + "[4] Room Item\n"
            + "[B] Go Back\n"
            + "------------=+=------------";
    
    public static void main(String args[])
    {
        saveManager = new TemporarySave(CAT);
        shop = new Shop(player);
        start = System.nanoTime();
        do
        {
            System.out.println(mainMenu);
            input = console.next().toUpperCase();
            switch(input)
            {
                case "B":
                    end = System.nanoTime();
                    elapsed = (end - start)/1000000000;
                    start = System.nanoTime();
                    System.out.println(elapsed);
                    player.updateMonies(inCalc.calculateIncome() * elapsed);
                    System.out.println("Love = " + player.getLove());
                    break;
                case "S":
                    do
                    {
                        System.out.println(shopMenu);
                        input = console.next();
                        switch(input)
                        {
                            case "1":
                                end = System.nanoTime();
                                elapsed = (end - start)/1000000000;
                                start = System.nanoTime();
                                shop.buyBasicPetCarrier();
                                break;
                            case "2":
                                end = System.nanoTime();
                                elapsed = (end - start)/1000000000;
                                start = System.nanoTime();
                                shop.buySilverPetCarrier();
                                break;
                            case "3":
                                end = System.nanoTime();
                                elapsed = (end - start)/1000000000;
                                start = System.nanoTime();
                                shop.buyGoldPetCarrier();
                                break;
                            case "4":
                                end = System.nanoTime();
                                elapsed = (end - start)/1000000000;
                                start = System.nanoTime();
                                shop.buyRoomItem(1);
                                break;
                            default:
                                System.out.println("Please enter a valid option.");
                        }
                    }while(!input.equalsIgnoreCase("B"));
                    break;
                case "P":
                    ArrayList<Pet> array_out = player.getPetsOwned();
                    for (Pet pet: array_out)
                    {
//                        if(pet.isOwned())
//                        {
                            System.out.println("Breed: " + pet.getBreed() + "   |   Owned: " + pet.isOwned());
//                        }
                    }
                    break;
                case "R":
                    System.out.println("In progress...");
                    break;
                default:
                    System.out.println("Please enter a valid option.");
            }
        }while(!input.equalsIgnoreCase("X"));
    }
}