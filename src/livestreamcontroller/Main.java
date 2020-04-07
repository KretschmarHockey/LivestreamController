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
import javax.swing.*;
import java.sql.SQLException;

/**
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
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
        controlFrame.setSize(300, 300);
        controlFrame.setVisible(true);

        // Creating Green Screen Frame
        JFrame greenScreenFrame = new JFrame();
        greenScreenFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        greenScreenFrame.getContentPane().add(greenScreenWindow);
        greenScreenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        greenScreenFrame.setUndecorated(true);
        greenScreenFrame.pack();
        greenScreenFrame.setVisible(true);
    }

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
