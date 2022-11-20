/*
Abboud Afram
Danial Sabet
 */
package furniture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Handler implements MouseListener, MouseMotionListener {
    private int X, Y;
    Component pns;
    volatile boolean mouseDown = false;
    private final RoomSketcher roomSketcher;

    /**
     * Handler constructor, creating a Handler object to move the Furniture object inside the mainPanel
     * @param pns the component to be moved
     */
    public Handler(RoomSketcher roomSketcher, Component pns) {
        this.pns = pns;
        pns.addMouseListener(this);
        pns.addMouseMotionListener(this);
        this.roomSketcher = roomSketcher;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * this method will remove the component (the Furniture object) from the mainPanel by right clicking on it.
     * @param e is the event, while right clicking in the object it will be removed.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        if (e.getButton() == MouseEvent.BUTTON3) {
            e.getComponent().setVisible(false);
            mouseDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * @precondition X = e.getComponent().getX()
     * @precondition Y = e.getComponent().getY()
     * this method will move the Furniture objects by mouse dragging it, by dragging the object it will get a new coordinates
     * the new coordinates will be saved att the object will stay in the dragged place.
     * different shapes have different size, in this method all object will be moved inside the main panel.
     * All Furniture objects will not be placed in front of the door, Wardrobes can not be placed in front of neither the door or the windows.
     * @param e, is the event, while mouse dragging the object, its coordinates will be changed and saved.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        int new_X = e.getX() + e.getComponent().getX() - X;  //the new x-coordinate
        int new_Y = e.getY() + e.getComponent().getY() - Y;  //the new y-coordinate
        int w = e.getComponent().getBounds().width;
        int h = e.getComponent().getBounds().height;

// rectangle
        //BED
        if (w == 250) {
            //if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 615)  {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A BED THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 840 && new_Y > 10 && new_Y < 615) {
                e.getComponent().setLocation(new_X, new_Y);  //moving the object
            }

            //couch
        } else if (w == 175) {
            //if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 665) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A COUCH THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 915 && new_Y > 10 && new_Y < 665) {
                e.getComponent().setLocation(new_X, new_Y);
            }
            //table
        } else if (w == 150) {
            //if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 680) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A TABLE THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 940 && new_Y > 10 && new_Y < 680) {
                e.getComponent().setLocation(new_X, new_Y);
            }
        }
        //wardrobe
        else if (w == 165) {
            //if the object placed in front of the window
            if (new_X >= 800 && new_X <= 970 && new_Y <= 10 && new_Y >= 0) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A WARDROBE THERE");
            }
            //if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 665) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A WARDROBE THERE");
            }
            //if the object placed in front of the window
            if (new_X >= 200 && new_X <= 370 && new_Y <= 10 && new_Y >= 0) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A WARDROBE THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 925 && new_Y > 10 && new_Y < 665) {
                e.getComponent().setLocation(new_X, new_Y);
            }
        }
// square
        //chair
        if (w == 60) {
            //if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 705) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A CHAIR THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 1030 && new_Y > 10 && new_Y < 705) {
                e.getComponent().setLocation(new_X, new_Y);
            }
            //wardrobe
        } else if (w == 70) {
            //if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 695) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A WARDROBE THERE");
            }
            //if the object placed in front of the window
            if (new_X >= 800 && new_X <= 970 && new_Y <= 10 && new_Y >= 0) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A WARDROBE THERE");
            }
            //if the object placed in front of the window
            if (new_X >= 200 && new_X <= 370 && new_Y <= 10 && new_Y >= 0) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A WARDROBE THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 1020 && new_Y > 10 && new_Y < 695) {
                e.getComponent().setLocation(new_X, new_Y);
            }
        }
        //couch
        else if (w == 70 && h == 65) {
            ///if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 705) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A COUCH THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 1020 && new_Y > 10 && new_Y < 705) {
                e.getComponent().setLocation(new_X, new_Y);
            }
        }
//Circle
        //table
        if (w == 100) {
            /////if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 660) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A TABLE THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 990 && new_Y > 10 && new_Y < 660) {
                e.getComponent().setLocation(new_X, new_Y);
            }
            //lamp
        } else if (w == 50) {
            /////if the object placed in front of the door
            if (new_X >= 100 && new_X <= 300 && new_Y == 715) {
                e.getComponent().setLocation(500, 350);
                JFrame frame = new JFrame("WARNING");
                JOptionPane.showMessageDialog(frame, "YOU CAN NOT PUT A LAMP THERE");
            }
            //the size of the panel
            if (new_X > 10 && new_X < 1040 && new_Y > 10 && new_Y < 715) {
                e.getComponent().setLocation(new_X, new_Y);
            }

        }

    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
