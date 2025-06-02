/*
The ugly underbelly.
This class keeps a large portion of my terrible code out of the main class.
Basically just has instructions to build every window that the main class can show so they don't have to live there.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;


public class OptWin {
    private static final String VERSION = "v0.1 for Polycosmos 0.13.1"; //Version info

    //Window IDS
    public static final int MAIN = 0;

    //Window Sizes
    private static final int MAIN_WIDTH = 500;
    private static final int MAIN_HEIGHT = 500;

    //User input values
    private String name = "Player";
    private String desc = "";
    private int bal = 50;


    //Class objects
    private JFrame frame;

    /**
     * Makes a JFrame and populates it with appropriate options based on ID
     * @param id ID of desired frame, use magic constant.
     */
    public OptWin(int id){
        makeFrame(id);
    }
    public JFrame getFrame(){
        return frame;
    }
    private void makeFrame(int id){
        //initialize an empty frame
        frame = setUpFrame();
        //Configure empty frame with parameters based on ID
        if(id == 0) mainFrame();
    }

    /**
     * Generates a template frame
     * @return Empty JFrame with properties shared by all frames used
     */
    private JFrame setUpFrame(){
        JFrame empty = new JFrame();
        empty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        empty.setResizable(false);
        empty.setLocationRelativeTo(null);
        return empty;
    }

    private void mainFrame(){
        frame.setTitle("Hades YAML Generator " + VERSION);
        frame.setSize(MAIN_WIDTH, MAIN_HEIGHT);

        Box vBox1 = Box.createVerticalBox();

        //option boxes
        OptBox nameBox = new OptBox("Name", OptBox.TEXT_FIELD); //slot name
        OptBox descBox = new OptBox("Description", OptBox.TEXT_FIELD); //YAML description
        OptBox balBox = new OptBox("Progression Balancing", OptBox.SLIDER); //Progression Balancing
        OptBox accBox = new OptBox("Accessibility", OptBox.DROP_DOWN); //Accessibility
//        Box invBox = Box.createVerticalBox(); //Starting inventory NOT YET IMPLEMENTED
//        Box exLocBox = Box.createHorizontalBox(); //Excluded Locations NOT YET IMPLEMENTED
        OptBox startBox = new OptBox("Starting Weapon", OptBox.DROP_DOWN); //Starting Weapon
        OptBox locBox = new OptBox("Location System", OptBox.DROP_DOWN); //Location type
        OptBox scoreBox = new OptBox("Score Amount",OptBox.SLIDER); //Score options
        Box saneBox = Box.createVerticalBox(); //Sanity options
            OptBox saneHeadBox = new OptBox("Sanity", OptBox.GENERIC);
            OptBox keepBox = new OptBox ("Keepsakesanity", OptBox.TOGGLE); //Keepsakes
            OptBox weapBox = new OptBox ("Weaponsanity", OptBox.TOGGLE); //Weapons
            OptBox aspBox = new OptBox("Hidden Aspectsanity", OptBox.TOGGLE); //Weapon aspects
            OptBox shopBox = new OptBox("Storesanity", OptBox.TOGGLE); //Contractor shop
            OptBox fateBox = new OptBox("Fatesanity", OptBox.TOGGLE); //Fated list of prophecies
        OptBox hellBox = new OptBox("Required Hades Defeats", OptBox.SLIDER); //Needed Hades Defeats
        OptBox clearBox = new OptBox("Required Weapon Clears", OptBox.SLIDER); //Needed Weapon Clears
        OptBox sakeBox = new OptBox("Required Keepsakes", OptBox.SLIDER); //needed Keepsakes
        OptBox listBox = new OptBox("Required Fated List Items", OptBox.SLIDER); //Needed Fated List Items
        OptBox hotBox = new OptBox("Heat System", OptBox.DROP_DOWN); //Heat System
        Box pactBox = Box.createHorizontalBox(); //Pact Amounts
        Box packBox = Box.createHorizontalBox(); //Pack Values
        Box fillBox = Box.createHorizontalBox(); //Filler items
        Box otpBox = Box.createVerticalBox(); //Other Options
            Box revEmBox = Box.createHorizontalBox(); //Reverse Extreme Measures Order
            Box greekBox = Box.createHorizontalBox(); //Death Link Amnesty for Greece Deaths
            Box hintBox = Box.createHorizontalBox(); //Store items say what they are
            Box roomBox = Box.createHorizontalBox(); //Defeating Hades clears all rooms

        //Box Config
        nameBox.setText("Player");
        nameBox.setHint("Slot name for this game, can be anything");
        nameBox.setEnabled(true);
        descBox.setText("Generated with Devini15's Polycosmos YAML generator");
        descBox.setHint("Can help if generating multiple YAML files");
        descBox.setEnabled(true);
        balBox.setSlide(0, 99, 50);
        balBox.setHint("How early in the multiworld required items are.\n" +
                "The higher the number the less frequently you'll get stuck\nDisabled = 0, Normal = 50");
        balBox.setEnabled(true);
        accBox.setDrop(new String[]{"Locations", "Items", "Minimal"});
        accBox.setHint("Locations: Ensure everything can be reached and acquired.\n" +
                "Items: Ensure logically relevant items can be acquired.\n" +
                "Minimal: Ensure what is needed to reach your goal can be acquired.");
        accBox.setEnabled(true);
        startBox.setDrop(new String[] {"Sword","Bow","Spear","Shield","Fist","Gun"});
        startBox.setHint("1st weapon available on game start");
        startBox.setEnabled(true);
        locBox.setDrop(new String[] {"Rooms", "Score", "Rooms & Weapons"});
        locBox.setHint("Rooms: Every new room depth reached is a check.\n" +
                "Score: Checks are based on a score system, points awarded for new and repeated rooms\n" +
                "Rooms & Weapons: Like \"Rooms\" but each weapon has it's own check for each room.");
        locBox.optDown.setAction(onLocationSystemSelected(locBox, scoreBox));
        locBox.setEnabled(true);
        scoreBox.setSlide(72, 150, 1000);
        scoreBox.setHint("This sets how many checks are available based on the score.\n" +
                "Each room gives its \"depth\" in score when completed, and each check needs 1 more\n" +
                "point to be unlocked (so check 10 needs 10 points, which can be obtained, for example,\n" +
                "by completing rooms 5 and 6). Your score rolls over every time you set a new high, so\n" +
                "clearing room 6 with a score of 17 and a high score of 19 would grant a check and leave you\n" +
                "with 3 points and a high score of 20.");
        scoreBox.setEnabled(false); //False by default until Score is selected as the location system
        saneHeadBox.setHint("Additional Checks & Items");
        keepBox.setToggle(true);
        keepBox.setHint("Shuffles keepsakes into the item pool and makes each keepsake location a check.\n" +
                "For simplicity, this does not affect Hades or Persephone");
        keepBox.setEnabled(true);
        weapBox.setToggle(true);
        weapBox.setHint("Shuffles weapons (except your starting weapon) into the item pool, and makes obtaining\n" +
                "each weapon at the House Contractor's shop a check.\n" +
                "Need to be sent the weapon item to gain the skill to equip them");
        weapBox.setEnabled(true);
        aspBox.setToggle(true);
        aspBox.setHint("Shuffles weapon aspects into the item pool, and makes obtaining each aspect a check\n" +
                "(which needs to be unlocked before it is able to be purchased.)");
        aspBox.setEnabled(true);
        shopBox.setToggle(true);
        shopBox.setHint("Shuffles important items from the House Contractor's shop into the item pool.\n" +
                "Need to be sent the items to gain the perks.");
        shopBox.setEnabled(true);
        fateBox.setToggle(true);
        fateBox.setHint("Shuffles most rewards from the Fated List of Prophecies into the item pool,\n" +
                "and makes the corresponding items from the list a check.\n" +
                "Can make the games significantly longer.");
        fateBox.setEnabled(true);
        hellBox.setSlide(1, 20, 1);
        hellBox.setHint("How many times you need to defeat Hades to win the world. 10 is for credits.");
        hellBox.setEnabled(true);
        clearBox.setSlide(1, 6, 1);
        clearBox.setHint("How many different weapon clears you need to win the world.");
        clearBox.setEnabled(true);
        sakeBox.setSlide(0, 23, 0);
        sakeBox.setHint("How many different keepsake unlocks are needed to win the world");
        sakeBox.setEnabled(true);
    }

    private Action onLocationSystemSelected(OptBox locBox, OptBox scoreBox){
        return new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(locBox.optDown.getSelectedItem().equals("Score")) scoreBox.setEnabled(true);
                else scoreBox.setEnabled(false);
            }
        };

    }
}
