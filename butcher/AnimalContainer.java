package blume_system.butcher;

public class AnimalContainer {
	public static int[] totalAmount = {0,0,0,0};
	public static void setChickenAmount(int i) { //chicken
		totalAmount[0] = i;
	}
	public static int getChickenAmount() {
		return totalAmount[0];
	}
	
	public static void setPigAmount(int i) { //pig
		totalAmount[1] = i;
	}
	public static int getPigAmount() {
		return totalAmount[1];
	}
	
	public static void setSheepAmount(int i) { //sheep
		totalAmount[2] = i;
	}
	public static int getSheepAmount() {
		return totalAmount[2];
	}
	
	public static void setCowAmount(int i) { //cow
		totalAmount[3] = i;
	}
	public static int getCowAmount() {
		return totalAmount[3];
	}
}
