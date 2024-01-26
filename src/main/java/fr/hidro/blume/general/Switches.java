package fr.hidro.blume.general;

public class Switches {
    static boolean stopStopWPBlockBreak;
    public static void setStopWPBlockBreak(boolean b) {
        stopStopWPBlockBreak = b;
    }
    public static boolean getStopWPBlockBreak() {
        return stopStopWPBlockBreak;
    }

    //world protection - block place
    static boolean stopStopWPBlockPlace;
    public static void setStopWPBlockPlace(boolean b) {
        stopStopWPBlockPlace = b;
    }
    public static boolean getStopWPBlockPlace() {
        return stopStopWPBlockPlace;
    }

    //world protection - item spawn
    static boolean stopStopWPItemSpawn;
    public static void setStopWPItemSpawn(boolean b) {
        stopStopWPItemSpawn = b;
    }
    public static boolean getStopWPItemSpawn() {
        return stopStopWPItemSpawn;
    }

    //world protection - block xp drop
    static boolean stopStopWPBlockExp;
    public static void setStopWPBlockExp(boolean b) {
        stopStopWPBlockExp = b;
    }
    public static boolean getStopWPBlockExp() {
        return stopStopWPBlockExp;
    }

    //world protection - player item drop
    static boolean stopStopWPPlayerDropItem;
    public static void setStopWPPlayerDropItem(boolean b) {
        stopStopWPPlayerDropItem = b;
    }
    public static boolean getStopWPPlayerDropItem() {
        return stopStopWPPlayerDropItem;
    }

    //world protection - creature spawn event (breed)
    static boolean stopCreatureBreed;
    public static void setStopWPCreatureBreed(boolean b) {
        stopCreatureBreed = b;
    }
    public static boolean getStopWPCreatureBreed() {
        return stopCreatureBreed;
    }

    //world protection - ender dragon block damage
    static boolean stopDragonDamage;
    public static void setStopWPDragonDamage(boolean b) {
        stopDragonDamage = b;
    }
    public static boolean getStopWPDragonDamage() {
        return stopDragonDamage;
    }

    //world protection - stop villager damage event
    static boolean stopVillagerDamage;
    public static void setStopWPVillagerDamage(boolean b) {
        stopVillagerDamage = b;
    }
    public static boolean getStopWPVillagerDamage() {
        return stopVillagerDamage;
    }
}
