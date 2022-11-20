/*
Abboud Afram
Danial Sabet
 */
package furniture;

import java.awt.*;

import javax.swing.*;

public abstract class AbstractFurniture extends JFrame {

    public abstract JComponent createCenterComponent();

    public abstract JComponent createToolbarComponent();

    public abstract JComponent createElementsComponent();

    public abstract JComponent createShoppingListComponent();


    public AbstractFurniture() {
        setTitle("Room Sketcher");
        // setup layout manager for frame
        JComponent component = createCenterComponent();
        JComponent toolbar = createToolbarComponent();
        JComponent elements = createElementsComponent();
        JComponent shoppingList = createShoppingListComponent();


        setLayout(new BorderLayout());
        setSize(new Dimension(1440, 837));


        add(elements, BorderLayout.WEST);
        add(shoppingList, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        add(component, BorderLayout.EAST);

        // show frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}