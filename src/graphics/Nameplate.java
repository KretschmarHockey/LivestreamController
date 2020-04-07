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

import java.awt.*;

/**
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 */
public class Nameplate extends LivestreamGraphic {

    private String text = "";
    private Rectangle background = new Rectangle(960, 855, 0, 80);
    private Rectangle foreground = new Rectangle(960, 860, 0, 70);
    private Rectangle textBound = new Rectangle(960, 590, 0, 70);
    private int alpha = 255;

    /**
     * Changes text of nameplate.
     *
     * @param text The string to be displayed
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Draws nameplate.
     *
     * @param g The Graphics Instance
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Draws white background
        g.setColor(new Color(255, 255, 255, alpha));
        g2.fillRect(background.x, background.y, background.width, background.height); // TODO: Add gradient
        // Draws maroon foreground
        g.setColor(new Color(122, 42, 59, alpha)); // TODO: Changeable colour
        g.fillRect(foreground.x, foreground.y, foreground.width, foreground.height); // TODO: Add gradient
        // Draws text
        g.setColor(new Color(255, 255, 255, alpha));
        drawCenteredString(g, text, textBound, new Font("SansSerif", Font.BOLD, 35));
        // Draws green cover to hide text interpolating down
        g.setColor(Color.GREEN);
        g.fillRect(690, 0, 535, 855);
    }

    /**
     * Sets all values to starting position.
     */
    public void startSettings() {
        background = new Rectangle(960, 855, 0, 80);
        foreground = new Rectangle(960, 860, 0, 70);
        textBound = new Rectangle(960, 590, 0, 70);
        alpha = 255;
    }

    /**
     * Animates opening the nameplate.
     *
     * @return True while animating, False when finished
     */
    public boolean open() {
        // Interpolates variables
        if (background.x > 690) {
            background.x--;
        }
        if (background.width < 535) {
            background.width += 2;
        }
        if (foreground.x > 695) {
            foreground.x--;
        }
        if (foreground.width < 525) {
            foreground.width += 2;
        }
        if (textBound.y < 860) {
            textBound.y++; // TODO: Move after timer finished
        }
        // Fixes variable from going over by 1 pixel
        if (background.width > 535) {
            background.width = 535;
        }
        if (foreground.width > 525) {
            foreground.width = 525;
        }

        return background.x > 690 || background.width < 535 || foreground.x > 695 || foreground.width < 525;
    }

    /**
     * Fades nameplate out.
     *
     * @return True while animating, False when finished
     */
    public boolean close() {
        if (alpha > 0) {
            alpha--;
        }

        return alpha > 0;
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics Instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     * @param font The font used to draw.
     */
    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) { // TODO: Fix Font Overdrawing
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
}
