import javax.swing.*;

public class OptionBoxes {
    //MAIN
    public static OptBox nameBox = new OptBox("Name", OptBox.TEXT_FIELD); //slot name
    public static OptBox descBox = new OptBox("Description", OptBox.TEXT_FIELD); //YAML description
    public static OptBox balBox = new OptBox("Progression Balancing", OptBox.SLIDER); //Progression Balancing
    public static OptBox accBox = new OptBox("Accessibility", OptBox.DROP_DOWN); //Accessibility
    //public static Box invBox = Box.createVerticalBox(); //Starting inventory NOT YET IMPLEMENTED
    //public static Box exLocBox = Box.createHorizontalBox(); //Excluded Locations NOT YET IMPLEMENTED
    public static OptBox startBox = new OptBox("Starting Weapon", OptBox.DROP_DOWN); //Starting Weapon
    public static OptBox locBox = new OptBox("Location System", OptBox.DROP_DOWN); //Location type
    public static OptBox scoreBox = new OptBox("Score Amount",OptBox.SLIDER); //Score options
    public static OptBox saneBox = new OptBox("Sanity Options", OptBox.GENERIC); //Sanity options
    public static OptBox keepBox = new OptBox ("Keepsakesanity", OptBox.TOGGLE); //Keepsakes
    public static OptBox weapBox = new OptBox ("Weaponsanity", OptBox.TOGGLE); //Weapons
    public static OptBox aspBox = new OptBox("Hidden Aspectsanity", OptBox.TOGGLE); //Weapon aspects
    public static OptBox shopBox = new OptBox("Storesanity", OptBox.TOGGLE); //Contractor shop
    public static OptBox fateBox = new OptBox("Fatesanity", OptBox.TOGGLE); //Fated list of prophecies
    public static OptBox hellBox = new OptBox("Required Hades Defeats", OptBox.SLIDER); //Needed Hades Defeats
    public static OptBox clearBox = new OptBox("Required Weapon Clears", OptBox.SLIDER); //Needed Weapon Clears
    public static OptBox sakeBox = new OptBox("Required Keepsakes", OptBox.SLIDER); //needed Keepsakes
    public static OptBox listBox = new OptBox("Required Fated List Items", OptBox.SLIDER); //Needed Fated List Items
    public static OptBox hotBox = new OptBox("Heat System", OptBox.DROP_DOWN); //Heat System
    //public static Box pactBox = Box.createHorizontalBox(); //Pact Amounts NOT YET IMPLEMENTED
    //public static Box packBox = Box.createHorizontalBox(); //Pack Values NOT YET IMPLEMENTED
    public static OptBox helpBox = new OptBox("Helper Filler %", OptBox.SLIDER); //Helper Filler items
    public static OptBox trapBox = new OptBox("Trap Filler %", OptBox.SLIDER); //Trap Filler Items
    public static OptBox revEmBox = new OptBox("Reverse Extreme Measures", OptBox.TOGGLE); //Reverse Extreme Measures Order
    public static OptBox greekBox = new OptBox("Ignore Greece Deaths", OptBox.TOGGLE); //Death Link Amnesty for Greece Deaths
    public static OptBox hintBox = new OptBox("Store Hints", OptBox.TOGGLE); //Store items say what they are
    public static OptBox roomBox = new OptBox("Auto Rooms", OptBox.TOGGLE); //Defeating Hades clears all rooms
    public static OptBox deathBox = new OptBox("Death Link", OptBox.TOGGLE); //Death Link

    //HELP
    public static OptBox maxHealthBox = new OptBox("Max Health", OptBox.SLIDER);
    public static OptBox initMoneyBox = new OptBox("Initial Money", OptBox.SLIDER);
}
