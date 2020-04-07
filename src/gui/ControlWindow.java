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
package gui;

import database.Database;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.sql.SQLException;

/**
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 */
public class ControlWindow {

    private final JPanel mainPanel;

    public ControlWindow(GreenScreenWindow greenScreenWindow) throws SQLException {
        // Connect to database
        Database database = new Database();

        // Setting layout
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Designing Components
        // Set Button
        JButton bSetNameplate = new JButton("Set");
        bSetNameplate.setBackground(Color.BLACK);
        bSetNameplate.setForeground(Color.WHITE);
        bSetNameplate.setBorder(new BasicBorders.ButtonBorder(null, Color.RED, null, Color.RED));
        bSetNameplate.setFocusPainted(false);
        bSetNameplate.setPreferredSize(new Dimension(50, 25));

        // Toggle Button
        JButton bToggleNameplate = new JButton("Show");
        bToggleNameplate.setBackground(Color.BLACK);
        bToggleNameplate.setForeground(Color.WHITE);
        bToggleNameplate.setBorder(new BasicBorders.ButtonBorder(null, Color.RED, null, Color.RED));
        bToggleNameplate.setFocusPainted(false);
        bToggleNameplate.setPreferredSize(new Dimension(50, 25));

        // Text field
        JTextField tfNameplate = new JTextField();

        // Name Display Label
        JLabel lNameplate = new JLabel();
        lNameplate.setForeground(Color.RED);
        lNameplate.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Designing Layout
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(bSetNameplate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bToggleNameplate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(tfNameplate)
                                .addComponent(lNameplate))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(bToggleNameplate)
                                .addComponent(bSetNameplate))
                        .addComponent(tfNameplate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lNameplate)
        );

        // Adding functionality
        // Set text in text field to nameplate
        bSetNameplate.addActionListener(e -> {
            greenScreenWindow.setText(tfNameplate.getText().toUpperCase());
            lNameplate.setText(tfNameplate.getText().toUpperCase());
            System.out.println("[cw] Set Nameplate Text: " + tfNameplate.getText().toUpperCase());
        });

        // Toggles nameplate visibility
        bToggleNameplate.addActionListener(e -> {
            if (greenScreenWindow.toggleNameplate()) {
                bToggleNameplate.setBackground(Color.RED);
                bToggleNameplate.setText("Hide");
                System.out.println("[cw] Shown Nameplate");
            } else {
                bToggleNameplate.setBackground(Color.BLACK);
                bToggleNameplate.setText("Show");
                System.out.println("[cw] Hidden Nameplate");
            }

        });
    }

    public JComponent getMainComponent() {
        return mainPanel;
    }
}
