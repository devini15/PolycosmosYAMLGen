/*
The ugly underbelly.
This class keeps a large portion of my terrible code out of the main class.
Basically just has instructions to build every window that the main class can show so they don't have to live there.
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;


public class OptWin extends OptionBoxes {
    private static final String VERSION = "v0.1 for Polycosmos 0.13.1"; //Version info

    //Window IDS
    public static final int MAIN = 0;

    //Window Sizes
    private static final int MAIN_WIDTH = 700;
    private static final int MAIN_HEIGHT = 900;

    //Class objects these are jank because I made this as an object class but then changed it to static
    //during development.

    private static JFrame frame;

    public static JFrame getFrame(){
        //initialize an empty frame
        frame = setUpFrame();
        //Configure empty frame with parameters based on ID
        mainFrame();
        return frame;
    }
    private static void makeFrame(int id){

    }

    /**
     * Generates a template frame
     * @return Empty JFrame with properties shared by all frames used
     */
    private static JFrame setUpFrame(){
        JFrame empty = new JFrame();
        empty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        empty.setResizable(false);
        empty.setLocationRelativeTo(null);
        return empty;
    }

    private static void mainFrame(){
        frame.setTitle("Hades YAML Generator " + VERSION);
        frame.setSize(MAIN_WIDTH, MAIN_HEIGHT);

        Box vBox1 = Box.createVerticalBox();

        //Box Config: Filling in info and setting default values.
        nameBox.setText("Player{PLAYER}");
        nameBox.randBox.setEnabled(false);
        nameBox.setHint("Slot name for this game, can be anything, limit 16 characters.");
        nameBox.setEnabled(true);
        descBox.setText("Generated with Devini15's Polycosmos YAML generator");
        descBox.setHint("Can help if generating multiple YAML files");
        descBox.setEnabled(true);
        descBox.randBox.setEnabled(false);
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
        locBox.optDown.setAction(onLocationSystemSelected());
        locBox.setEnabled(true);
        scoreBox.setSlide(72, 1000, 150);
        scoreBox.setHint("This sets how many checks are available based on the score.\n" +
                "Each room gives its \"depth\" in score when completed, and each check needs 1 more\n" +
                "point to be unlocked (so check 10 needs 10 points, which can be obtained, for example,\n" +
                "by completing rooms 5 and 6). Your score rolls over every time you set a new high, so\n" +
                "clearing room 6 with a score of 17 and a high score of 19 would grant a check and leave you\n" +
                "with 3 points and a high score of 20.");
        scoreBox.setEnabled(false); //False by default until Score is selected as the location system
        saneBox.setHint("Additional Checks & Items");
        saneBox.randBox.setAction(onSanityRandomizationBoxClicked());
        saneBox.randBox.setEnabled(true);
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
        sakeBox.setHint("How many different keepsake unlocks are needed to win the world.");
        sakeBox.setEnabled(true);
        listBox.setSlide(0, 35, 0);
        listBox.setHint("How many different Fated List completions are needed to win the world.");
        listBox.setEnabled(true);
        hotBox.setDrop(new String[] {"Reverse Heat", "Minimal Heat", "Vanilla Heat"});
        hotBox.setHint("Reverse Heat: you start with heat pacts that cannot be disabled until you get the corresponding pact item.\n" +
                "Minimal Heat: Pact settings determine your minimum heat. Heat cannot go below that level.\n" +
                "Vanilla Heat: Opt out of the other 2 heat systems (disables pact settings.)");
        hotBox.setEnabled(true);
        helpBox.setSlide(0, 100, 0);
        helpBox.setHint("Percentage of filler items in the pool that will be \"Helper Items\".\n" +
                "Helpers give a boost to your Max Health, Initial Money, or chance of obtaining rare Boons.");
        helpBox.setEnabled(true);
        trapBox.setSlide(0, 100, 10);
        trapBox.setHint("Percentage of filler items in the pool that will be \"Traps\".\n" +
                "Traps diminish your money or health during a run.");
        trapBox.setEnabled(true);
        revEmBox.setToggle(true);
        revEmBox.setHint("Reveres the order in which extreme measures are applied.\n" +
                "So level 1 is applied to Hades, instead of Meg/The Furies.\n" +
                "For a more balanced experience.");
        revEmBox.setEnabled(true);
        greekBox.setToggle(true);
        greekBox.setHint("Ignore deaths on Greece for deathlink.\nLeave off for the memes.");
        greekBox.setEnabled(true);
        hintBox.setToggle(true);
        hintBox.setHint("Store displays what each item is in the multiworld before purchase.");
        hintBox.setEnabled(true);
        roomBox.setToggle(false);
        roomBox.setHint("When enabled, defeating Hades gives all room clears in Rooms mode or\n" +
                "all room clears for current weapon in Rooms & Weapons mode");
        roomBox.setEnabled(true);
        deathBox.setToggle(false);
        deathBox.setHint("If you die in the game, everyone else (who has death link) dies in the game.\n" +
                "Also, if they die you die. It's kind of poetic in a way.");
        deathBox.setEnabled(true);

        //Non-Box Items
        JButton helpButton = new JButton();
        helpButton.setAction(onHelpButtonClicked());
        helpButton.setText("Edit Help Item distribution");
        helpButton.setEnabled(true);

        //Put it all together
        vBox1.add(nameBox.box());
        vBox1.add(descBox.box());
        vBox1.add(balBox.box());
        vBox1.add(accBox.box());
        vBox1.add(startBox.box());
        vBox1.add(locBox.box());
        vBox1.add(scoreBox.box());
        vBox1.add(saneBox.box());
        vBox1.add(keepBox.box());
        vBox1.add(weapBox.box());
        vBox1.add(aspBox.box());
        vBox1.add(shopBox.box());
        vBox1.add(fateBox.box());
        vBox1.add(hellBox.box());
        vBox1.add(clearBox.box());
        vBox1.add(sakeBox.box());
        vBox1.add(listBox.box());
        vBox1.add(hotBox.box());
        vBox1.add(helpBox.box());
        vBox1.add(helpButton);
        vBox1.add(trapBox.box());
        vBox1.add(revEmBox.box());
        vBox1.add(greekBox.box());
        vBox1.add(hintBox.box());
        vBox1.add(roomBox.box());
        vBox1.add(deathBox.box());

        frame.add(vBox1);
    }

    private static  void openHelpFrame(){
        //Box config
        maxHealthBox.setSlide(0, 100, 40);
        maxHealthBox.setHint("% of help items that boost your max health");
        maxHealthBox.setEnabled(true);
        initMoneyBox.setSlide(0, 100, 30);
        initMoneyBox.setHint("% of help items that boost your initial money per run");
        initMoneyBox.setEnabled(true);

        //other items
        JLabel boonLabel = new JLabel();

        //Slider Logic
        maxHealthBox.optSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider sender = (JSlider) e.getSource();
                if(initMoneyBox.optSlide.getValue() > (100 - sender.getValue())){
                    initMoneyBox.optSlide.setValue(100 - sender.getValue());
                }
                boonLabel.setText("Boon Rarity Help Item Rate: " +
                        (100 - sender.getValue() - initMoneyBox.optSlide.getValue()) + "%");
            }
        });
        initMoneyBox.optSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider sender = (JSlider) e.getSource();
                if(maxHealthBox.optSlide.getValue() > (100 - sender.getValue())){
                    maxHealthBox.optSlide.setValue(100 - sender.getValue());
                }
                boonLabel.setText("Boon Rarity Help Item Rate: " +
                        (100 - sender.getValue() - maxHealthBox.optSlide.getValue()) + "%");
            }
        });
        JFrame helpFrame = setUpFrame();
        helpFrame.setTitle("Help Item Distribution Editor");
        helpFrame.setSize(500, 500);

        Box vBoxH = Box.createVerticalBox();
        vBoxH.add(maxHealthBox.box());
        vBoxH.add(initMoneyBox.box());
        vBoxH.add(boonLabel);

        helpFrame.add(vBoxH);
        helpFrame.setVisible(true);


    }

    private static Action onLocationSystemSelected(){
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

    private static Action onSanityRandomizationBoxClicked(){
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
                boolean notRandom = !saneBox.randBox.isSelected();
                keepBox.setEnabled(notRandom);
                weapBox.setEnabled(notRandom);
                aspBox.setEnabled(notRandom);
                shopBox.setEnabled(notRandom);
                fateBox.setEnabled(notRandom);
            }
        };
    }

    private static Action onHelpButtonClicked(){
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
                openHelpFrame();
            }
        };
    }
}
