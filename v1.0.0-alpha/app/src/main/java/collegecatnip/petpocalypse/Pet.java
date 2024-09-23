package collegeccatnip.petpocalypse
/**
 * Class to hold the Pet
 *
 * @author Kendall Devich
 * Date Created: 9/23/2024
 *
 * @modifier Kendall Devich
 * Last Modified: 9/23/2024
 * Patch Notes:
 *		The base of the pet class.
 */
 
public class Pet
{
	// Attributes
	private String nickName;
	private String breed;
	private String flavor_text;
	private int action;
	private int rarity;
	private int pettionary_id;
	
	// Getters and Setters
	public String getNickName(){
		return nickName;
	}
	
	public Void setNickName(String name){
		nickName = name;
	}
	
	public String getBreed(){
		return breed;
	}
	
	public String getFlavorText(){
		return flavor_text;
	}
	
	public int getAction(){
		return action;
	}
	
	public int getRarity(){
		return rarity;
	}
	
	public int getPettionaryID() {
		return pettionary_id;
	}

}	