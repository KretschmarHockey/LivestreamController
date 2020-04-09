/*
 * Copyright (C) 2020 Joshua Kretschmar JoshuaJKretschmar@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

/**
 * Livestream Graphic is the abstract base class for all graphics contexts which
 * allow an application to draw onto components.
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 * @version %I% %G%
 */
public abstract class LivestreamGraphic {

    /**
     * Visibility is different to if graphic is displayed. Visibility is if a
     * graphic was on screen it would visible.
     */
    private boolean isVisible;

    /**
     * Constructor. Sets visibility to false by default.
     */
    LivestreamGraphic() {
        isVisible = false;
    }

    /**
     * @return Visibility of graphic
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * @param visible The visibility to be set.
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Every graphic has it's own draw method to be designed.
     *
     * @param g The Graphics instance.
     */
    public void draw(Graphics g) {
    }

    /**
     * Draw a rectangle.
     *
     * @param g The Graphics instance.
     * @param rect The definition of the rectangle to draw.
     */
    public void drawRectangle(Graphics g, Rectangle rect) { // TODO: Add possibility of gradient
        Graphics2D g2 = (Graphics2D) g;

        g2.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics Instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     * @param font The font used to draw.
     */
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) { // TODO: Fix Font Overdrawing
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in Java 2D 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the string
        g.drawString(text, x, y);
    }

    /**
     * Draw a String semi-condensed if wider than maxWidth. TODO: Not final
     * version.
     *
     * @param g The Graphics Instance.
     * @param text The String to draw.
     * @param rect The Rectangle for position and width.
     * @param font The font used to draw.
     */
    public void drawStringSemiCondensed(Graphics g, String text, Rectangle rect, Font font) {
        // Get the width of string
        int width = g.getFontMetrics(font).stringWidth(text);
        if (rect.width < width) {
            // Changing text attribute to semi-condensed
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_SEMI_CONDENSED);
            Font newFont = font.deriveFont(attributes);
            g.setFont(newFont);
        } else {
            g.setFont(font);
        }

        g.drawString(text, rect.x, rect.y);
    }

    /**
     * Draws an image the shape and size of the rectangle.
     *
     * @param g The Graphics Instance.
     * @param img The image to draw.
     * @param rect The shape of the image.
     */
    public void drawImage(Graphics g, Image img, Rectangle rect) {
        g.drawImage(img, rect.x, rect.y, rect.width, rect.height, null);
    }

    /**
     * Draws a scaled down image. Should only be used for player pictures.
     *
     * @param g The Graphics Instance.
     * @param img The image of the player to draw. To be shaped properly: Image
     * must be 1920x1920. The bottom 40 and Right 80 pixels must be transparent.
     * @param rect The rectangle for the position of the image.
     */
    public void drawPlayerHeadshot(Graphics g, Image img, Rectangle rect) { //TODO: Fix scale algorithm
        g.drawImage(img, rect.x, rect.y, img.getWidth(null) / 8, img.getHeight(null) / 8, null);
    }

    /**
     * Sets all graphic's images alpha.
     *
     * @param g The graphics instance.
     * @param alpha The alpha level for the images. 0-255.
     */
    public void setImageAlpha(Graphics g, int alpha) {
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha / 255);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(ac);
    }
}
