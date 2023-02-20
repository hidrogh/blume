package blume_system.config;

public class RankupContainer {
	public static String currentStatus = "";
	public static void setCurrentStatus(String status) {
		currentStatus = status;
	}
	public static String getCurrentStatus() {
		return currentStatus;
	}
}
