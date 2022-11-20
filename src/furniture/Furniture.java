/*
Abboud Afram
Danial Sabet
 */
package furniture;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Furniture implements Serializable {

    public static class square implements Icon, Serializable {

        int x;
        int y;
        int width;
        int height;
        Color color;

        /**
         * @invariant width >= 0
         * @invariant height >= 0
         *
         * @precondition width > 0
         * @precondition height > 0
         * square constructor, creating a square with a specific width, height and color.
         */
        public square(int width, int height, Color color) {
            this.width = width;
            this.height = height;
            this.color = color;
        }

        /**
         * returns the x-coordinates
         * @return x
         */
        public int getX() {
            return x;
        }

        /**
         * returns the y-coordinates
         * @return y
         */
        public int getY() {
            return y;
        }


        /**
         *creating the rectangle object that can be either a square or a rectangle object
         *drawing the object with the given width and height and its x and y-coordinates
         *paint the object with given color.
         */
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {

            Color temp = g.getColor();
            g.setColor(color);
            g.fillRect(getX(), getY(), getIconWidth(), getIconHeight());
            g.setColor(temp);
        }

        /**
         * returns the width of the square object
         * @return width
         */
        @Override
        public int getIconWidth() {
            return width;
        }

        /**
         * return the height of the square object
         * @return height
         */
        @Override
        public int getIconHeight() {
            return height;
        }

    }

    public static class circle implements Icon, Serializable {

        int radius;
        Color color;

        /**
         * @invariant radius >= 0
         *
         * @precondition radius > 0
         * circle constructor, creating a circle with a specific radius and color.
         */
        public circle(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        /**
         *creating the oval object that will be a circle object
         *drawing the object with the given radius and its x and y-coordinates
         *paint the object with given color.
         */
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {

            Color temp = g.getColor();
            g.setColor(color);
            g.fillOval(x, y, getIconWidth(), getIconHeight());
            g.setColor(temp);
        }

        /**
         * Returns the radius of the circle
         * @return radius
         */
        @Override
        public int getIconWidth() {
            return radius;
        }

        /**
         * Returns the radius of the circle
         * @return radius
         */
        @Override
        public int getIconHeight() {
            return radius;
        }

    }

}

