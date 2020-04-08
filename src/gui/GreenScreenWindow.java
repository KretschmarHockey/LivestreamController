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
package gui;

import database.StartingGoalieObject;
import graphics.Nameplate;
import graphics.StartingGoalie;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 */
public final class GreenScreenWindow extends JPanel {

    private final Nameplate nameplate = new Nameplate();
    private StartingGoalie startingGoalie;

    public GreenScreenWindow() {
        setWindowColour(0, 255, 0);
    }

    /**
     * Sets 'green' screen colour.
     *
     * @param r The red component.
     * @param g The green component.
     * @param b The blue component.
     */
    public void setWindowColour(int r, int g, int b) {
        this.setBackground(new Color(r, g, b));
    }

    /**
     * Draws Graphics on window
     *
     * @param g The Graphics Instance
     */
    private void draw(Graphics g) {
        // Nameplate
        nameplate.draw(g);

        // Starting Goalie
        if (startingGoalie != null) {
            startingGoalie.draw(g);
        }
    }

    /**
     * Draws Window.
     *
     * @param g The Graphics Instance.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Sets the nameplate text.
     *
     * @param text The string to be set for nameplate.
     */
    void setText(String text) {
        nameplate.setText(text);
        repaint();
    }

    /**
     * Displays nameplate if hidden. Hides nameplate if displayed.
     *
     * @return Whether the nameplate is displayed.
     */
    public boolean toggleNameplate() {
        if (nameplate.isVisible()) {
            nameplate.setVisible(false);
            new Timer(1, e -> {
                if (nameplate.close()) {
                    repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }).start();
        } else {
            nameplate.setVisible(true);
            nameplate.startSettings();
            new Timer(1, e -> {
                if (nameplate.open()) {
                    repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }).start();
        }

        return nameplate.isVisible();
    }

    /**
     * Displays nameplate if hidden. Hides nameplate if displayed.
     *
     * @return Whether the nameplate is displayed.
     */
    public boolean toggleStartingGoalies() {
        if (startingGoalie.isVisible()) {
            startingGoalie.setVisible(false);
            new Timer(1, e -> {
                if (startingGoalie.close()) {
                    repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }).start();
        } else {
            startingGoalie.setVisible(true);
            startingGoalie.startSettings();
            new Timer(1, e -> {
                if (startingGoalie.open()) {
                    repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }).start();
        }

        return startingGoalie.isVisible();
    }

    public void setStartingGoalie(ResultSet rs) throws SQLException {
        startingGoalie = new StartingGoalie(new StartingGoalieObject(rs));
    }
    
    public boolean isStartingGoalieVisible() { // Doesn't look good for some reason
        if (startingGoalie == null) {
            return false;
        }
        return startingGoalie.isVisible();
    }
}
