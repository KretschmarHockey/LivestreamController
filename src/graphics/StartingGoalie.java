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
    private Rectangle thisSeasonBackground = new Rectangle(840, 865, 565, 30);
    private Rectangle divider1 = new Rectangle(830, 780, 10, 215);
    private Rectangle divider2 = new Rectangle(920, 780, 10, 80);
    private Rectangle divider3 = new Rectangle(1230, 780, 10, 80);
    private Rectangle divider4 = new Rectangle(840, 860, 565, 5);
    private Rectangle divider5 = new Rectangle(840, 895, 565, 5);
    private Rectangle divider6 = new Rectangle(976, 900, 7, 95);
    private Rectangle divider7 = new Rectangle(1119, 900, 7, 95);
    private Rectangle divider8 = new Rectangle(1262, 900, 7, 95);

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
    private Rectangle firstNameTextBound = new Rectangle(940, 812, 290, 0);
    private Rectangle lastNameTextBound = new Rectangle(940, 850, 290, 0);

    private int alpha = 255;

    // Team Logo
    private BufferedImage teamLogo;
    private Rectangle teamLogoBound = new Rectangle(610, 820, 135, 135);
    // Player Head
    private BufferedImage playerProfile;
    private Rectangle playerProfileBound = new Rectangle(600, 760, 215, 215);
    // Ad
    private BufferedImage advertisement;
    private Rectangle advertisementBound = new Rectangle(1240, 780, 165, 80);

    // Fonts
    private final Font fontPlain30 = new Font("SansSerif", Font.PLAIN, 30);
    private final Font fontBold30 = new Font("SansSerif", Font.BOLD, 30);
    private final Font fontBold35 = new Font("SansSerif", Font.BOLD, 35);
    private final Font fontBold40 = new Font("SansSerif", Font.BOLD, 40);
    private final Font fontBold60 = new Font("SansSerif", Font.BOLD, 60);

    public StartingGoalie(StartingGoalieObject goalie) {
        this.goalie = goalie;
    }

    /**
     * Draws Starting Goalie Graphic.
     *
     * @param g The Graphics Instance
     */
    @Override
    public void draw(Graphics g) {
        // Load images
        if (teamLogo == null || playerProfile == null) {
            try {
                teamLogo = ImageIO.read(new File("assets/logos/" + goalie.getTeam() + ".png"));
                playerProfile = ImageIO.read(new File("assets/playerprofiles/" + goalie.getFullName() + ".png"));
                advertisement = ImageIO.read(new File("assets/logos/Pita Pit.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(StartingGoalie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Set Image Alpha
        setImageAlpha(g, alpha);

        // Draws white background
        g.setColor(new Color(250, 250, 250, alpha));
        drawRectangle(g, background);

        // Player Head Background
        switch (goalie.getTeam()) {
            case "Botany Swarm":
                g.setColor(new Color(122, 42, 59, alpha));
                break;
            case "Canterbury Red Devils":
                g.setColor(new Color(alpha));
                break;
            case "Dunedin Thunder":
                g.setColor(new Color(alpha));
                break;
            case "Skycity Stampede":
                g.setColor(new Color(alpha));
                break;
            case "West Auckland Admirals":
                g.setColor(new Color(alpha));
                break;
            default:
                g.setColor(new Color(250, 250, 250, alpha));
                break;
        }

        drawRectangle(g, headBackground);
        drawImage(g, teamLogo, teamLogoBound);
        drawScaledImage(g, playerProfile, playerProfileBound); //Image must be 1920x1920. Bottom 40 pixels transparent. Right 80

        // Other backgrounds
        g.setColor(new Color(34, 49, 66, alpha));
        drawRectangle(g, nameBackground); // Unsure on colour
        drawImage(g, advertisement, advertisementBound);
        g.setColor(new Color(19, 83, 132, alpha));
        drawRectangle(g, thisSeasonBackground);

        // Stats Background
        g.setColor(new Color(250, 250, 250, alpha));
        drawRectangle(g, recTextBound);
        drawRectangle(g, gaaTextBound);
        drawRectangle(g, svTextBound);
        drawRectangle(g, soTextBound);
        g.setColor(new Color(220, 220, 220, alpha));
        drawRectangle(g, numberBound);
        drawRectangle(g, recStatBound);
        drawRectangle(g, gaaStatBound);
        drawRectangle(g, svStatBound);
        drawRectangle(g, soStatBound);

        // Dividers
        g.setColor(new Color(150, 150, 150, alpha));
        drawRectangle(g, divider1);
        drawRectangle(g, divider2);
        drawRectangle(g, divider3);
        drawRectangle(g, divider4);
        drawRectangle(g, divider5);
        drawRectangle(g, divider6);
        drawRectangle(g, divider7);
        drawRectangle(g, divider8);

        // Draw Text
        g.setColor(new Color(10, 10, 10, alpha));
        drawCenteredString(g, goalie.getNumber(), numberBound, fontBold60);
        drawCenteredString(g, "REC", recTextBound, fontPlain30);
        drawCenteredString(g, "GAA", gaaTextBound, fontPlain30);
        drawCenteredString(g, "SV%", svTextBound, fontPlain30);
        drawCenteredString(g, "SO", soTextBound, fontPlain30);
        drawCenteredString(g, goalie.getRecord(), recStatBound, fontBold35);
        drawCenteredString(g, goalie.getGoalsAgainstAverage(), gaaStatBound, fontBold35);
        drawCenteredString(g, goalie.getSavePercentage(), svStatBound, fontBold35);
        drawCenteredString(g, goalie.getShutouts(), soStatBound, fontBold35);
        g.setColor(new Color(250, 250, 250, alpha));
        drawCenteredString(g, "THIS SEASON", thisSeasonBackground, fontBold30);
        // First Name
        g.setColor(new Color(240, 240, 240, alpha));
        drawStringSemiCondensed(g, goalie.getFirstName(), firstNameTextBound, fontPlain30);
        // Last Name
        drawStringSemiCondensed(g, goalie.getLastName(), lastNameTextBound, fontBold40);

    }

    /**
     * Sets all values to starting position.
     */
    public void startSettings() {
        background = new Rectangle(580, 1170, 830, 230);
        headBackground = new Rectangle(585, 1180, 245, 215);
        nameBackground = new Rectangle(930, 1180, 300, 80);
        advertisementBound = new Rectangle(1240, 1180, 165, 80);
        thisSeasonBackground = new Rectangle(840, 1265, 565, 30);
        divider1 = new Rectangle(830, 1180, 10, 215);
        divider2 = new Rectangle(920, 1180, 10, 80);
        divider3 = new Rectangle(1230, 1180, 10, 80);
        divider4 = new Rectangle(840, 1260, 565, 5);
        divider5 = new Rectangle(840, 1295, 565, 5);
        divider6 = new Rectangle(976, 1300, 7, 95);
        divider7 = new Rectangle(1119, 1300, 7, 95);
        divider8 = new Rectangle(1262, 1300, 7, 95);
        numberBound = new Rectangle(840, 1180, 80, 80);
        recTextBound = new Rectangle(840, 1300, 136, 35);
        gaaTextBound = new Rectangle(983, 1300, 136, 35);
        svTextBound = new Rectangle(1126, 1300, 136, 35);
        soTextBound = new Rectangle(1269, 1300, 136, 35);
        recStatBound = new Rectangle(840, 1335, 136, 60);
        gaaStatBound = new Rectangle(983, 1335, 136, 60);
        svStatBound = new Rectangle(1126, 1335, 136, 60);
        soStatBound = new Rectangle(1269, 1335, 136, 60);
        firstNameTextBound = new Rectangle(940, 1210, 290, 0);
        lastNameTextBound = new Rectangle(940, 1250, 290, 0);
        teamLogoBound = new Rectangle(610, 1220, 135, 135);
        playerProfileBound = new Rectangle(600, 1160, 215, 215);
        alpha = 255;
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
        if (nameBackground.y > 780) {
            nameBackground.y--;
        }
        if (advertisementBound.y > 780) {
            advertisementBound.y--;
        }
        if (thisSeasonBackground.y > 865) {
            thisSeasonBackground.y--;
        }
        if (divider1.y > 780) {
            divider1.y--;
        }
        if (divider2.y > 780) {
            divider2.y--;
        }
        if (divider3.y > 780) {
            divider3.y--;
        }
        if (divider4.y > 860) {
            divider4.y--;
        }
        if (divider5.y > 895) {
            divider5.y--;
        }
        if (divider6.y > 900) {
            divider6.y--;
        }
        if (divider7.y > 900) {
            divider7.y--;
        }
        if (divider8.y > 900) {
            divider8.y--;
        }
        if (numberBound.y > 780) {
            numberBound.y--;
        }
        if (recTextBound.y > 900) {
            recTextBound.y--;
        }
        if (gaaTextBound.y > 900) {
            gaaTextBound.y--;
        }
        if (svTextBound.y > 900) {
            svTextBound.y--;
        }
        if (soTextBound.y > 900) {
            soTextBound.y--;
        }
        if (recStatBound.y > 935) {
            recStatBound.y--;
        }
        if (gaaStatBound.y > 935) {
            gaaStatBound.y--;
        }
        if (svStatBound.y > 935) {
            svStatBound.y--;
        }
        if (soStatBound.y > 935) {
            soStatBound.y--;
        }
        if (firstNameTextBound.y > 810) {
            firstNameTextBound.y--;
        }
        if (lastNameTextBound.y > 850) {
            lastNameTextBound.y--;
        }
        if (teamLogoBound.y > 820) {
            teamLogoBound.y--;
        }
        if (playerProfileBound.y > 760) {
            playerProfileBound.y--;
        }

        return background.y > 770;
    }

    public boolean close() {
        if (alpha > 0) {
            alpha--;
        }

        return alpha > 0;
    }
    
    public void setInvisible() {
        alpha = 0;
    }
}
