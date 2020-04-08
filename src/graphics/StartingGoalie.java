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

import database.StartingGoalieObject;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 */
public class StartingGoalie extends LivestreamGraphic {

    private StartingGoalieObject goalie;
    private Rectangle background = new Rectangle(580, 770, 830, 230);
    private Rectangle headBackground = new Rectangle(585, 780, 245, 215);
    private Rectangle nameBackground = new Rectangle(930, 780, 300, 80);
    private Rectangle adBackground = new Rectangle(1240, 780, 165, 80);
    private Rectangle thisSeasonBackground = new Rectangle(840, 865, 565, 30);
    private Rectangle dividerOne = new Rectangle(830, 780, 10, 215);
    private Rectangle dividerTwo = new Rectangle(920, 780, 10, 80);
    private Rectangle dividerThree = new Rectangle(1230, 780, 10, 80);
    private Rectangle dividerFour = new Rectangle(840, 860, 565, 5);
    private Rectangle dividerFive = new Rectangle(840, 895, 565, 5);
    private Rectangle dividerSix = new Rectangle(976, 900, 7, 95);
    private Rectangle dividerSeven = new Rectangle(1119, 900, 7, 95);
    private Rectangle dividerEight = new Rectangle(1262, 900, 7, 95);

    // Bounds
    private Rectangle numberBound = new Rectangle(840, 780, 80, 80);
    private Rectangle recTextBound = new Rectangle(840, 900, 136, 35);
    private Rectangle gaaTextBound = new Rectangle(983, 900, 136, 35);
    private Rectangle svTextBound = new Rectangle(1126, 900, 136, 35);
    private Rectangle soTextBound = new Rectangle(1269, 900, 136, 35);
    private Rectangle recStatBound = new Rectangle(840, 935, 136, 60);
    private Rectangle gaaStatBound = new Rectangle(983, 935, 136, 60);
    private Rectangle svStatBound = new Rectangle(1126, 935, 136, 60);
    private Rectangle soStatBound = new Rectangle(1269, 935, 136, 60);

    private int alpha = 255;

    // Team Logo
    private BufferedImage teamLogo;
    private Rectangle teamLogoBound = new Rectangle(610, 820, 135, 135);
    // Player Head
    private BufferedImage playerProfile;
    // Ad
    /**
     * Draws Starting Goalie Graphic.
     *
     * @param g The Graphics Instance
     */
    @Override
    public void draw(Graphics g) {
        // Draws white background
        g.setColor(new Color(250, 250, 250, alpha));
        drawRectangle(g, background);

        // Player Head Background
        g.setColor(new Color(122, 42, 59, alpha));
        drawRectangle(g, headBackground); // TODO: team dependent
        g.setColor(new Color(0, 0, 250, alpha));
        drawRectangle(g, nameBackground); // Unsure on colour
        drawRectangle(g, adBackground);
        drawRectangle(g, thisSeasonBackground);

        // Stats Background
        g.setColor(new Color(200, 200, 200, alpha));
        drawRectangle(g, numberBound);
        drawRectangle(g, recTextBound);
        drawRectangle(g, gaaTextBound);
        drawRectangle(g, svTextBound);
        drawRectangle(g, soTextBound);
        drawRectangle(g, recStatBound);
        drawRectangle(g, gaaStatBound);
        drawRectangle(g, svStatBound);
        drawRectangle(g, soStatBound);

        // Dividers
        g.setColor(new Color(150, 150, 150, alpha));
        drawRectangle(g, dividerOne);
        drawRectangle(g, dividerTwo);
        drawRectangle(g, dividerThree);
        drawRectangle(g, dividerFour);
        drawRectangle(g, dividerFive);
        drawRectangle(g, dividerSix);
        drawRectangle(g, dividerSeven);
        drawRectangle(g, dividerEight);

        // Draw Text
        g.setColor(new Color(250, 250, 250, alpha));
        drawCenteredString(g, "THIS SEASON", thisSeasonBackground, new Font("SansSerif", Font.BOLD, 30));
        // First Name
        drawStringSemiCondensed(g, "JONATHAN", 290, new Font("SansSerif", Font.PLAIN, 30), 940, 810);
        // Last Name
        drawStringSemiCondensed(g, "QUICK", 290, new Font("SansSerif", Font.BOLD, 40), 940, 850);
        
        // Load images
        try {
            teamLogo = ImageIO.read(new File("assets/logos/Botany Swarm.png")); // Able to change logo
            playerProfile = ImageIO.read(new File("assets/playerprofiles/Joel Rindelaub.png"));
        } catch (IOException ex) {
            Logger.getLogger(StartingGoalie.class.getName()).log(Level.SEVERE, null, ex);
        }
        drawImage(g, teamLogo, teamLogoBound);

    }

    /**
     * Sets all values to starting position.
     */
    public void startSettings() {
        background = new Rectangle(580, 1170, 830, 230);
        headBackground = new Rectangle(585, 1180, 245, 215);
        teamLogoBound = new Rectangle(610, 1220, 135, 135);
    }

    /**
     * Animates opening the starting goalies.
     *
     * @return True while animating, False when finished
     */
    public boolean open() {
        if (background.y > 770) {
            background.y--;
        }
        if (headBackground.y > 780) {
            headBackground.y--;
        }
        if (teamLogoBound.y > 820) {
            teamLogoBound.y--;
        }
        
        return background.y > 770 || headBackground.y > 780 || teamLogoBound.y > 820;
    }

    public boolean close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
