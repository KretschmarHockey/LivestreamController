/*
 * Copyright (C) 2020 Johke
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
package livestreamcontroller;

import gui.GreenScreenWindow;
import gui.ControlWindow;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Provides launch for application.
 * 
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 * @version %I% %G%
 */
public class Main {

    private static void createAndShowGui() throws SQLException {
        GreenScreenWindow greenScreenWindow = new GreenScreenWindow();
        ControlWindow controlWindow = new ControlWindow(greenScreenWindow);

        // Creating Control Window Frame
        JFrame controlFrame = new JFrame("Control Window");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.getContentPane().add(controlWindow.getMainComponent());
        controlFrame.pack();
        controlFrame.setLocationByPlatform(true);
        controlFrame.setSize(1280, 720);
        controlFrame.setVisible(true);

        // Creating Green Screen Frame
        JFrame greenScreenFrame = new JFrame();
        greenScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: Just for testing purposes
        greenScreenFrame.getContentPane().add(greenScreenWindow);
        greenScreenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        greenScreenFrame.setUndecorated(true);
        greenScreenFrame.pack();
        greenScreenFrame.setVisible(true);
    }
    
    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGui();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
