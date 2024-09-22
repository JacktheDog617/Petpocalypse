package collegecatnip.petpocalypse;
/**
 * Class to manage income for Player class
 *
 * @author Jaime Lee
 * Date Created: 9/20/2024
 *
 * Last modified: 9/22/2024
 * Patch Notes:
 *      implemented switch case so that pet income is calculated based off rarity
 *      changed total_income to round up before converting to integer
 *      added additional comments for extra clarity
 *      changed toggleMultiplier so that the valid input check works
 */
public class IncomeCalculator
{
    public double multiplier_array[][] = {
            // item ID....multiplier.....toggle
            {0,      0.05,          0},
            {1,      0.05,          0},
            {2,      0.05,          1},
            {3,      0.05,          0},
            {4,      0.05,          1}
    };

    /**
     * calculateIncome() will calculate the income amount that will be added to the current balance
     * and return the new balance
     * @param current_balance - current integer balance passed from Player Class
     * @param pet_array - Pet array, containing income rates and which pets to calculate with
     * @return new_balance calculated by this function
     */
    public int calculateIncome(int current_balance, int pet_array[])
    {
        int new_balance; // return new balance + total_income generated
        double total_income = 0; // for calculating total_income

        /* for every pet income, add to total_income */
        for(int i = 0; i < pet_array.length; i++)
        {
            // add income based on pet rarity
            switch(pet_array[i])
            {
                case 1:
                    total_income = total_income + 1;
                    break;
                case 2:
                    total_income = total_income + 2;
                    break;
                case 3:
                    total_income = total_income + 3;
                    break;
                default:
                    System.out.println("An error occured at line 34 of the calculateIncome() switch case.");
                    System.out.println("Interation " + i + " input " + pet_array[i] + " into the switch case.");
            }
        }
        /* for every multiplier activated, add multiplied income */
        for(int i = 0; i < multiplier_array.length; i++)
        {
            // if multiplier is activated
            if(multiplier_array[i][2] == 1)
            {
                // total income + the extra income generated by the multiplier
                total_income += (total_income * multiplier_array[i][1]);
                System.out.println(total_income);
            }
        }
        // new_balance = current_balance + total_income rounded up to nearest int
        new_balance = current_balance + (int)Math.ceil(total_income);
        return new_balance;
    }

    /**
     * toggleMultiplier will take in a given item_id and change it's toggle to 1 or 0 according to
     * activate parameter
     * @param item_id - this parameter requires an int or double of the ID of the item to be toggled
     * @param on_off - 1 to toggle on or 0 to toggle off, toggle changes whether or not the item
     *                 is used in income calculations
     * @return 0 if successful, 1 if failed
     */
    public int toggleMultiplier(double item_id, double on_off)
    {
        // return error if on_off is not a valid input (0/1)
        if(on_off!= 1 && on_off != 0)
        {
            System.out.println("Item failed to toggle on/off. on_off = " + on_off);
            return -1; // return failure flag
        }
        // boolean to exit for loop if intended action is completed
        boolean toggle_unfinished = true;
        // loop through array until array end OR multiplier is toggled
        for(int i = 0; (i < multiplier_array.length && toggle_unfinished); i++)
        {
            if(multiplier_array[i][0] == item_id) // check against item ID
            {
                multiplier_array[i][2] = on_off; // switch to 1 or 0
                toggle_unfinished = false; // exit loop
            }
        }
        return 0;// return successful execution flag
    }
}