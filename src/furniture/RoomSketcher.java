/*
Abboud Afram
Danial Sabet
 */
package furniture;

import util.Disc;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.util.LinkedHashMap;

public class RoomSketcher extends AbstractFurniture {
    //all panels for the RoomSketcher
    private JPanel mainPanel;  // for centerComponent
    private JPanel shoppingListPanel;  //for shoppingList

    public LinkedHashMap<String, JLabel> list = new LinkedHashMap<String, JLabel>();
    private int ChairCounter = 1;
    private int BedCounter = 1;
    private int TableCounter = 1;
    private int CouchCounter = 1;
    private int LampCounter = 1;
    private int WardrobeCounter = 1;

    private int index;
    private Colors color;
    private FurnitureVar furniture;

    Handler handler;

    /**
     * This method is for creating the mainpanel that contains all furniture objects.
     * @return the mainpanel
     * @postcondition return mainPanel
     */
    public JComponent createCenterComponent() {

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1100, 800));

        mainPanel.setLayout(new AbsoluateLayoutManager());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        //creating the first window object
        Furniture.square window_one = new Furniture.square(170, 10, Color.CYAN);
        JLabel windowLabel_one = new JLabel(window_one);
        windowLabel_one.setLocation(800, 0);

        //creating the second window object
        Furniture.square window_two = new Furniture.square(170, 10, Color.CYAN);
        JLabel windowLabel_two = new JLabel(window_two);
        windowLabel_two.setLocation(200, 0);

        //creating the door object
        Color color = new Color(182, 160, 0);
        Furniture.square door = new Furniture.square(100, 10, color);
        JLabel doorLabel = new JLabel(door);
        doorLabel.setLocation(200, 766);

        //Show the mainPanel
        mainPanel.add(windowLabel_one);
        mainPanel.add(windowLabel_two);
        mainPanel.add(doorLabel);
        setVisible(true);

        return mainPanel;
    }

    /**
     * This method is for creation of the toolbar with save and open buttons
     * @return toolbar
     */
    public JComponent createToolbarComponent() {

        //creating the toolbar object & the open and sve buttons
        JToolBar toolBar = new JToolBar("ToolBar");
        JButton fileButton = new JButton("Open File");
        fileButton.addActionListener(e -> fileButtonPressed());
        JButton saveButton = new JButton("Save File");
        saveButton.addActionListener(e -> saveButtonPressed());

        //adding the buttons to the toolbar
        toolBar.add(fileButton);
        toolBar.add(saveButton);

        return toolBar;
    }

    /**
     * This method is for creation of all furniture buttons and put them inside the buttonPanel.
     * @return buttonPanel
     */
    public JComponent createElementsComponent() {

        //creating the buttonPanel that contains all FurnitureButtons
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 10, 5));
        buttonPanel.setPreferredSize(new Dimension(190, 750));

        JLabel label;
        label = new JLabel("Furniture");
        label.setLayout(new BorderLayout());

        JButton chairButton = new JButton("Chair");

        chairButton.addActionListener(e -> chairButtonPressed());

        JButton couchButton = new JButton("Couch");
        couchButton.addActionListener(e -> couchButtonPressed());

        JButton tableButton = new JButton("Table");
        tableButton.addActionListener(e -> tableButtonPressed());

        JButton bedButton = new JButton("Bed");
        bedButton.addActionListener(e -> bedButtonPressed());

        JButton lampButton = new JButton("Lamp");
        lampButton.addActionListener(e -> lampButtonPressed());
        JButton wardrobeButton = new JButton("Wardrobe");
        wardrobeButton.addActionListener(e -> wardrobeButtonPressed());

        //adding all buttons to the buttonPanel
        buttonPanel.add(label);
        buttonPanel.add(chairButton);
        buttonPanel.add(couchButton);
        buttonPanel.add(bedButton);
        buttonPanel.add(tableButton);
        buttonPanel.add(lampButton);
        buttonPanel.add(wardrobeButton);

        return buttonPanel;
    }

    /**
     * This method is for creation of the shopping list.
     * @return shoppingListPanel
     */
    public JComponent createShoppingListComponent() {
        //creating the shoppingListPanel
        shoppingListPanel = new JPanel();

        JTextArea textArea = new JTextArea("Shopping List");
        JTextArea line = new JTextArea("==============");

        //deciding the size and the layout of the panel.
        shoppingListPanel.setPreferredSize(new Dimension(50, 750));
        shoppingListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        shoppingListPanel.setBackground(Color.WHITE);
        shoppingListPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        shoppingListPanel.add(textArea);
        shoppingListPanel.add(line);


        //show panel
        shoppingListPanel.setVisible(true);
        return shoppingListPanel;
    }

    /**
     * adding the name of the furniture to the shoppingListPanel.
     * @param e JLabel that contains the furniture objects
     * @param name the name of the furniture object
     */
    public void addFurniture(JLabel e, String name) {
        shoppingListPanel.add(e);
    }

    /**
     * removing the name of the furniture object from the shoppingList
     * @param e JLabel that contains the furniture objects
     * @param name the name of the furniture object
     */
    public void removeFurniture(JLabel e, String name) {
        if (list.containsKey(name)) {
            shoppingListPanel.remove(e);
            shoppingListPanel.repaint();
            shoppingListPanel.revalidate();
        }
    }

    /**
     * @precondition a JFileChooser object to chose a file.
     * @precondition two Component arrays to transfer all datafrom a file to the program.
     * This method is for opening the file after pressing the openButton
     * @postcondition The method will open a saved file
     */
    public void fileButtonPressed() {
        //creating a fileChooser to select a specific file.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            //reading all data from the file and transfer it to the content array
            Object[] content = (Object[]) Disc.readFromFile(selectedFile);

            /*putting the furniture objects in a panelContent array
            putting the name of different furniture objects to the shoppingContent array*/
            assert content != null;
            Component[] panelContent = (Component[]) content[0];
            Component[] shoppingContent = (Component[]) content[1];

            mainPanel.removeAll();

            //adding all objects to the mainPanel
            for (Component c : panelContent) {
                mainPanel.add(c);
                handler = new Handler(this, c);
                mainPanel.revalidate();
                mainPanel.repaint();
            }

            shoppingListPanel.removeAll();

            //adding all objects to the shoppingListPanel.
            for (Component c : shoppingContent) {
                shoppingListPanel.add(c);
                handler = new Handler(this, c);
                shoppingListPanel.revalidate();
                shoppingListPanel.repaint();

            }


        }

    }

    /**
     * @precondition using JFileChooser object to select a file to save
     * This method is for saving the file after pressing the save button.
     * @postcondition writing all data to a file to save
     */
    public void saveButtonPressed() {
        //JFileChooser to select a file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());

            /*getting all component from the mainPanel and shoppingListPanel
            and adding them to two different arrays to save all data*/
            Component[] panelContent = mainPanel.getComponents();
            Component[] shoppingContent = shoppingListPanel.getComponents();


            Object[] contents = {panelContent, shoppingContent};

            Disc.writeToFile(fileToSave, contents);


        }
    }

    /**
     * @precondition furniture = null
     * By pressing the chairButton a new window to choose the shape will appear on the screen.
     * @postxondition furniture = chair
     */
    public void chairButtonPressed() {
        furniture = FurnitureVar.chair;
        image("Chair shape", "Please choose chair shape");

    }

    /**
     * @precondition furniture = null
     * By pressing the couchButton a new window to choose the shape will appear on the screen.
     * @postxondition furniture = couch
     */
    public void couchButtonPressed() {
        furniture = FurnitureVar.couch;
        image("Couch shape", "Please choose couch shape");

    }

    /**
     * @precondition furniture = null
     * By pressing the bedButton a new window to choose the shape will appear on the screen.
     * @postxondition furniture = bed
     */
    public void bedButtonPressed() {
        furniture = FurnitureVar.bed;
        image("Bed shape", "Please choose bed shape");

    }

    /**
     * @precondition furniture = null
     * By pressing the lampButton a new window to choose the shape will appear on the screen.
     * @postxondition furniture = lamp
     */
    public void lampButtonPressed() {
        furniture = FurnitureVar.lamp;
        image("Lamp shape", "Please choose lamp shape");

    }

    /**
     * @precondition furniture = null
     * By pressing the tableButton a new window to choose the shape will appear on the screen.
     * @postxondition furniture = table
     */
    public void tableButtonPressed() {

        furniture = FurnitureVar.table;
        image("Table shape", "Please choose table shape");

    }

    /**
     * @precondition furniture = null
     * By pressing the wardrobeButton a new window to choose the shape will appear on the screen.
     * @postxondition furniture = wardrobe
     */
    public void wardrobeButtonPressed() {
        furniture = FurnitureVar.wardrobe;
        image("Wardrobe shape", "Please choose wardrobe shape");

    }

    /**
     * This method is for the creation of the shape window that appears on the screen after pressing the furniture buttons.
     * @param frameName a String for choosing the name of the frame of the shapeWindow
     * @param labelName a String for the text in the shapeWindow
     */
    public void image(String frameName, String labelName) {

        /*
        creating a JFrame, JPanel and a JLabel for the text inside the panel
        creating three different buttons representing the shapes of the furniture
        */
        JFrame frame = new JFrame(frameName);
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel(labelName);
        JButton squareButton = new JButton("SQUARE");
        JButton circleButton = new JButton("CIRCLE");
        JButton rectangleButton = new JButton("RECTANGLE");

        final int IMG_WIDTH = 50;
        final int IMG_HEIGHT = 70;
        final int GAP = 4;
        BufferedImage circleImg = new BufferedImage(IMG_WIDTH, IMG_WIDTH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.yellow);
        int width = IMG_WIDTH - 2 * GAP;
        int height = IMG_WIDTH - 2 * GAP;
        int width_two = IMG_HEIGHT - 2 * GAP;
        g2.fillOval(GAP, GAP, width, height);
        circleButton.setIcon(new ImageIcon(circleImg));

        BufferedImage squareImg = new BufferedImage(IMG_WIDTH, IMG_WIDTH, BufferedImage.TYPE_INT_ARGB);
        g2 = squareImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.yellow);
        g2.fillRect(GAP, GAP, width, height);
        squareButton.setIcon(new ImageIcon(squareImg));

        BufferedImage recImg = new BufferedImage(IMG_HEIGHT, IMG_WIDTH, BufferedImage.TYPE_INT_ARGB);
        g2 = recImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.yellow);
        g2.fillRect(GAP, GAP, width_two, height);
        rectangleButton.setIcon(new ImageIcon(recImg));
        if (furniture == FurnitureVar.chair) {

            circleButton.setVisible(false);
            rectangleButton.setVisible(false);

        }
        if (furniture == FurnitureVar.couch) {

            circleButton.setVisible(false);

        }
        if (furniture == FurnitureVar.bed) {

            circleButton.setVisible(false);
            squareButton.setVisible(false);

        }
        if (furniture == FurnitureVar.lamp) {

            squareButton.setVisible(false);
            rectangleButton.setVisible(false);

        }
        if (furniture == FurnitureVar.table) {

            squareButton.setVisible(false);
        }

        if (furniture == FurnitureVar.wardrobe) {

            circleButton.setVisible(false);


        }


        circleButton.addActionListener(e -> {
            index = 3;
            frame.dispose();
            colorWindow();
        });
        squareButton.addActionListener(e -> {
            index = 1;
            frame.dispose();
            colorWindow();
        });
        rectangleButton.addActionListener(e -> {
            index = 2;
            frame.dispose();
            colorWindow();
        });

        g2.dispose();
        //adding all components to the imagePanel
        imagePanel.add(imageLabel);
        imagePanel.add(squareButton);
        imagePanel.add(circleButton);
        imagePanel.add(rectangleButton);

        //showing the frame
        frame.add(imagePanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method is for the creation of the color window that appears on the screen after choosing the shape of the furniture object.
     */
    public void colorWindow() {

        /*
        To create a colorWindow, a JFrame, JPanel and a JLabel was creates
        Three
         */
        JFrame frame = new JFrame("COLOR");
        JPanel colorPanel = new JPanel();
        JLabel colorLabel = new JLabel("Please choose color");
        JButton redButton = new JButton("RED");
        JButton blackButton = new JButton("BLACK");
        JButton greyButton = new JButton("GREY");

        redButton.addActionListener(e -> {
            color = Colors.RED;
            redButtonPressed();
            frame.dispose();
        });
        blackButton.addActionListener(e -> {
            color = Colors.BLACK;
            blackButtonPressed();
            frame.dispose();
        });
        greyButton.addActionListener(e -> {
            color = Colors.GREY;
            grayButtonPressed();
            frame.dispose();

        });

        //adding all components to colorPanel
        colorPanel.add(colorLabel);
        colorPanel.add(redButton);
        colorPanel.add(blackButton);
        colorPanel.add(greyButton);

        //showing the frame
        frame.add(colorPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(colorLabel);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * @precondition color = RED
     * @precondition furniture = chair, couch, table, lamp and wardrobe.
     * After pressing the red button, depends of the furniture and shape that was chosen,
     * a specific object will be created and added to the main panel.
     * the name of the object will be added to the shopping list.
     * @postcondition creating a furniture object and add it to the mainPanel, add its name to the shoppingListPanel
     */
    public void redButtonPressed() {

        if (color == Colors.RED && furniture == FurnitureVar.chair) {
            Furniture.square square = new Furniture.square(60, 60, Color.RED);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedChair = "Red Chair #" + Integer.toString(ChairCounter);
            JLabel b = new JLabel(RedChair);
            handler = new Handler(this, b);
            list.put(RedChair, b);
            ChairCounter += 1;
            addFurniture(b, RedChair);
            setVisible(true);
        }
        if (furniture == FurnitureVar.bed && color == Colors.RED) {
            Furniture.square rec = new Furniture.square(250, 150, Color.RED);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedBed = "Red Bed #" + Integer.toString(BedCounter);
            JLabel b = new JLabel(RedBed);
            handler = new Handler(this, b);
            list.put(RedBed, b);
            BedCounter += 1;
            addFurniture(b, RedBed);
            setVisible(true);

        }
        if (furniture == FurnitureVar.table && color == Colors.RED && index == 2) {
            Furniture.square rec = new Furniture.square(150, 80, Color.RED);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedTable = "Red Table #" + Integer.toString(TableCounter);
            JLabel b = new JLabel(RedTable);
            handler = new Handler(this, b);
            list.put(RedTable, b);
            TableCounter += 1;
            addFurniture(b, RedTable);
            setVisible(true);

        } else if (index == 2 && color == Colors.RED && furniture == FurnitureVar.couch) {
            Furniture.square rec = new Furniture.square(175, 100, Color.RED);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedCouch = "Red Couch #" + Integer.toString(CouchCounter);
            JLabel b = new JLabel(RedCouch);
            handler = new Handler(this, b);
            list.put(RedCouch, b);
            RedCouch += 1;
            addFurniture(b, RedCouch);
            setVisible(true);
        }
        if (index == 1 && color == Colors.RED && furniture == FurnitureVar.couch) {
            Furniture.square square = new Furniture.square(70, 65, Color.RED);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedCouch = "Red Couch #" + Integer.toString(CouchCounter);
            JLabel b = new JLabel(RedCouch);
            handler = new Handler(this, b);
            list.put(RedCouch, b);
            CouchCounter += 1;
            addFurniture(b,RedCouch);
            setVisible(true);
        }
        if (furniture == FurnitureVar.lamp && color == Colors.RED) {
            Furniture.circle circle = new Furniture.circle(50, Color.RED);
            JLabel a = new JLabel(circle);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedLamp = "Red Lamp #" + Integer.toString(LampCounter);
            JLabel b = new JLabel(RedLamp);
            handler = new Handler(this, b);
            list.put(RedLamp, b);
            LampCounter += 1;
            addFurniture(b, RedLamp);
            setVisible(true);

        }
        if (index == 3 && color == Colors.RED && furniture == FurnitureVar.table) {
            Furniture.circle circle = new Furniture.circle(100, Color.RED);
            JLabel a = new JLabel(circle);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedTable = "Red Table #" + Integer.toString(TableCounter);
            JLabel b = new JLabel(RedTable);
            handler = new Handler(this, b);
            list.put(RedTable, b);
            TableCounter += 1;
            addFurniture(b, RedTable);
            setVisible(true);
        }
        if (index == 1 && color == Colors.RED && furniture == FurnitureVar.wardrobe) {
            Furniture.square square = new Furniture.square(70, 70, Color.RED);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedWardrobe = "Red Wardrobe #" + Integer.toString(WardrobeCounter);
            JLabel b = new JLabel(RedWardrobe);
            handler = new Handler(this, b);
            list.put(RedWardrobe, b);
            WardrobeCounter += 1;
            addFurniture(b, RedWardrobe);
            setVisible(true);
        }
        if (index == 2 && color == Colors.RED && furniture == FurnitureVar.wardrobe) {
            Furniture.square square = new Furniture.square(165, 100, Color.RED);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String RedWardrobe = "Red Wardrobe #" + Integer.toString(WardrobeCounter);
            JLabel b = new JLabel(RedWardrobe);
            handler = new Handler(this, b);
            list.put(RedWardrobe, b);
            WardrobeCounter += 1;
            addFurniture(b, RedWardrobe);
            setVisible(true);
        }
    }

    /**
     * @precondition color = black
     * @precondition furniture = chair, couch, table, lamp and wardrobe
     * After pressing the black button, depends of the furniture and shape that was chosen,
     * a specific object will be created and added to the main panel.
     * the name of the object will be added to the shopping list.
     * @postcondition creating a furniture object and add it to the mainPanel, add its name to the shoppingListPanel
     */
    public void blackButtonPressed() {

        if (color == Colors.BLACK && furniture == FurnitureVar.chair) {
            Furniture.square square = new Furniture.square(60, 60, Color.BLACK);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackChair = "Black Chair #" + Integer.toString(ChairCounter);
            JLabel b = new JLabel(BlackChair);
            handler = new Handler(this, b);
            list.put(BlackChair, b);
            ChairCounter += 1;
            addFurniture(b, BlackChair);
            setVisible(true);
        }
        if (furniture == FurnitureVar.bed && color == Colors.BLACK) {
            Furniture.square rec = new Furniture.square(250, 150, Color.BLACK);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackBed = "Black Bed #" + Integer.toString(BedCounter);
            JLabel b = new JLabel(BlackBed);
            handler = new Handler(this, b);
            list.put(BlackBed, b);
            BedCounter += 1;
            addFurniture(b, BlackBed);
            setVisible(true);

        }
        if (furniture == FurnitureVar.table && color == Colors.BLACK && index == 2) {
            Furniture.square rec = new Furniture.square(150, 80, Color.BLACK);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackTable = "Black Table #" + Integer.toString(TableCounter);
            JLabel b = new JLabel(BlackTable);
            handler = new Handler(this, b);
            list.put(BlackTable, b);
            BedCounter += 1;
            addFurniture(b, BlackTable);
            setVisible(true);

        }
        if (index == 2 && color == Colors.BLACK && furniture == FurnitureVar.couch) {
            Furniture.square rec = new Furniture.square(175, 100, Color.BLACK);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackCouch = "Black Couch #" + Integer.toString(CouchCounter);
            JLabel b = new JLabel(BlackCouch);
            handler = new Handler(this, b);
            list.put(BlackCouch, b);
            CouchCounter += 1;
            addFurniture(b, BlackCouch);
            setVisible(true);
        }
        if (index == 1 && color == Colors.BLACK && furniture == FurnitureVar.couch) {
            Furniture.square square = new Furniture.square(70, 65, Color.BLACK);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackCouch = "Black Couch #" + Integer.toString(CouchCounter);
            JLabel b = new JLabel(BlackCouch);
            handler = new Handler(this, b);
            list.put(BlackCouch, b);
            CouchCounter += 1;
            addFurniture(b, BlackCouch);
            setVisible(true);
        }
        if (furniture == FurnitureVar.lamp && color == Colors.BLACK) {
            Furniture.circle circle = new Furniture.circle(50, Color.BLACK);
            JLabel a = new JLabel(circle);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackLamp = "Black Lamp #" + Integer.toString(LampCounter);
            JLabel b = new JLabel(BlackLamp);
            handler = new Handler(this, b);
            list.put(BlackLamp, b);
            LampCounter += 1;
            addFurniture(b, BlackLamp);
            setVisible(true);

        }
        if (index == 3 && color == Colors.BLACK && furniture == FurnitureVar.table) {
            Furniture.circle circle = new Furniture.circle(100, Color.BLACK);
            JLabel a = new JLabel(circle);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackTable = "Black Table #" + Integer.toString(TableCounter);
            JLabel b = new JLabel(BlackTable);
            handler = new Handler(this, b);
            list.put(BlackTable, b);
            TableCounter += 1;
            addFurniture(b, BlackTable);
            setVisible(true);
        }
        if (index == 1 && color == Colors.BLACK && furniture == FurnitureVar.wardrobe) {
            Furniture.square square = new Furniture.square(70, 70, Color.BLACK);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackWardrobe = "Black Wardrobe #" + Integer.toString(WardrobeCounter);
            JLabel b = new JLabel(BlackWardrobe);
            handler = new Handler(this, b);
            list.put(BlackWardrobe, b);
            WardrobeCounter += 1;
            addFurniture(b, BlackWardrobe);
            setVisible(true);
        }
        if (index == 2 && color == Colors.BLACK && furniture == FurnitureVar.wardrobe) {
            Furniture.square square = new Furniture.square(165, 100, Color.BLACK);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String BlackWardrobe = "Black Wardrobe #" + Integer.toString(WardrobeCounter);
            JLabel b = new JLabel(BlackWardrobe);
            handler = new Handler(this, b);
            list.put(BlackWardrobe, b);
            WardrobeCounter += 1;
            addFurniture(b, BlackWardrobe);
            setVisible(true);
        }

    }

    /**
     * @precondition color = gray
     * @precondition furniture = chair, couch, table, lamp and wardrobe
     * After pressing the gray button, depends of the furniture and shape that was chosen,
     * a specific object will be created and added to the main panel.
     * the name of the object will be added to the shopping list.
     * @postcondition creating a furniture object and add it to the mainPanel, add its name to the shoppingListPanel
     */
    public void grayButtonPressed() {

        if (color == Colors.GREY && furniture == FurnitureVar.chair) {
            Furniture.square square = new Furniture.square(60, 60, Color.GRAY);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayChair = "Gray Chair #" + Integer.toString(ChairCounter);
            JLabel b = new JLabel(GrayChair);
            handler = new Handler(this, b);
            list.put(GrayChair, b);
            ChairCounter += 1;
            addFurniture(b, GrayChair);
            setVisible(true);
        }
        if (furniture == FurnitureVar.bed && color == Colors.GREY) {
            Furniture.square rec = new Furniture.square(250, 150, Color.GRAY);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayBed = "Gray Bed #" + Integer.toString(BedCounter);
            JLabel b = new JLabel(GrayBed);
            handler = new Handler(this, b);
            list.put(GrayBed, b);
            BedCounter += 1;
            addFurniture(b, GrayBed);
            setVisible(true);

        }
        if (furniture == FurnitureVar.table && color == Colors.GREY && index == 2) {
            Furniture.square rec = new Furniture.square(150, 80, Color.GRAY);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayTable = "Gray Table #" + Integer.toString(TableCounter);
            JLabel b = new JLabel(GrayTable);
            handler = new Handler(this, b);
            list.put(GrayTable, b);
            TableCounter += 1;
            addFurniture(b, GrayTable);
            setVisible(true);

        }
        if (index == 2 && color == Colors.GREY && furniture == FurnitureVar.couch) {
            Furniture.square rec = new Furniture.square(175, 100, Color.GRAY);
            JLabel a = new JLabel(rec);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayCouch = "Gray Couch #" + Integer.toString(CouchCounter);
            JLabel b = new JLabel(GrayCouch);
            handler = new Handler(this, b);
            list.put(GrayCouch, b);
            CouchCounter += 1;
            addFurniture(b, GrayCouch);
            setVisible(true);
        }
        if (index == 1 && color == Colors.GREY && furniture == FurnitureVar.couch) {
            Furniture.square square = new Furniture.square(70, 65, Color.GRAY);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayCouch = "Gray Couch #" + Integer.toString(CouchCounter);
            JLabel b = new JLabel(GrayCouch);
            handler = new Handler(this, b);
            list.put(GrayCouch, b);
            CouchCounter += 1;
            addFurniture(b, GrayCouch);
            setVisible(true);
        }
        if (furniture == FurnitureVar.lamp && color == Colors.GREY) {
            Furniture.circle circle = new Furniture.circle(50, Color.GRAY);
            JLabel a = new JLabel(circle);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayLamp = "Gray Lamp #" + Integer.toString(LampCounter);
            JLabel b = new JLabel(GrayLamp);
            handler = new Handler(this, b);
            list.put(GrayLamp, b);
            LampCounter += 1;
            addFurniture(b, GrayLamp);
            setVisible(true);

        }
        if (index == 3 && color == Colors.GREY && furniture == FurnitureVar.table) {
            Furniture.circle circle = new Furniture.circle(100, Color.GRAY);
            JLabel a = new JLabel(circle);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayTable = "Gray Table #" + Integer.toString(TableCounter);
            JLabel b = new JLabel(GrayTable);
            handler = new Handler(this, b);
            list.put(GrayTable, b);
            TableCounter += 1;
            addFurniture(b, GrayTable);
            setVisible(true);
        }
        if (index == 1 && color == Colors.GREY && furniture == FurnitureVar.wardrobe) {
            Furniture.square square = new Furniture.square(70, 70, Color.GRAY);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayWardrobe = "Gray Wardrobe #" + Integer.toString(WardrobeCounter);
            JLabel b = new JLabel(GrayWardrobe);
            handler = new Handler(this, b);
            list.put(GrayWardrobe, b);
            WardrobeCounter += 1;
            addFurniture(b, GrayWardrobe);
            setVisible(true);
        }
        if (index == 2 && color == Colors.GREY && furniture == FurnitureVar.wardrobe) {
            Furniture.square square = new Furniture.square(165, 100, Color.GRAY);
            JLabel a = new JLabel(square);
            a.setLocation(500, 300);
            mainPanel.add(a);
            handler = new Handler(this, a);
            setVisible(true);

            String GrayWardrobe = "Gray Wardrobe #" + Integer.toString(WardrobeCounter);
            JLabel b = new JLabel(GrayWardrobe);
            handler = new Handler(this, b);
            list.put(GrayWardrobe, b);
            WardrobeCounter += 1;
            addFurniture(b, GrayWardrobe);
            setVisible(true);
        }
    }

}




