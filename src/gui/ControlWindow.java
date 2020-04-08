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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.TextAttribute;
import javax.swing.plaf.basic.BasicBorders;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        //*********************Nameplate*********************//
        // Nameplate Label
        JLabel lNameplate = new JLabel("Nameplate");
        Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lNameplate.setFont(new Font("SansSerif", Font.BOLD, 30).deriveFont(fontAttributes));
        lNameplate.setForeground(Color.RED);

        // Set Nameplate Button
        JButton bSetNameplate = new JButton("Set");
        bSetNameplate.setBackground(Color.BLACK);
        bSetNameplate.setForeground(Color.WHITE);
        bSetNameplate.setBorder(new BasicBorders.ButtonBorder(null, Color.RED, null, Color.RED));
        bSetNameplate.setFocusPainted(false);
        bSetNameplate.setPreferredSize(new Dimension(50, 25));

        // Toggle Nameplate Button
        JButton bToggleNameplate = new JButton("Show");
        bToggleNameplate.setBackground(Color.BLACK);
        bToggleNameplate.setForeground(Color.WHITE);
        bToggleNameplate.setBorder(new BasicBorders.ButtonBorder(null, Color.RED, null, Color.RED));
        bToggleNameplate.setFocusPainted(false);
        bToggleNameplate.setPreferredSize(new Dimension(50, 25));

        // Nameplate Text field
        JTextField tfNameplate = new JTextField();
        tfNameplate.setMaximumSize(new Dimension(200, 20));

        // Nameplate Display Label
        JLabel lDisplayNameplate = new JLabel();
        lDisplayNameplate.setForeground(Color.RED);
        lDisplayNameplate.setBorder(BorderFactory.createLineBorder(Color.RED));

        //*****************Starting Goalies********************//
        // Starting Goalie Label
        JLabel lStartingGoalie = new JLabel("Starting Goalies");
        lStartingGoalie.setFont(new Font("SansSerif", Font.BOLD, 30).deriveFont(fontAttributes));
        lStartingGoalie.setForeground(Color.RED);

        // Toggle Starting Goalie Button
        JButton bToggleStartingGoalie = new JButton("Show");
        bToggleStartingGoalie.setBackground(Color.BLACK);
        bToggleStartingGoalie.setForeground(Color.WHITE);
        bToggleStartingGoalie.setBorder(new BasicBorders.ButtonBorder(null, Color.RED, null, Color.RED));
        bToggleStartingGoalie.setFocusPainted(false);
        bToggleStartingGoalie.setPreferredSize(new Dimension(50, 25));

        // Designing Layout
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lNameplate)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(bSetNameplate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(bToggleNameplate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(tfNameplate)
                        .addComponent(lDisplayNameplate))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lStartingGoalie)
                        .addComponent(bToggleStartingGoalie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lNameplate)
                        .addComponent(lStartingGoalie))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(bToggleNameplate)
                        .addComponent(bSetNameplate)
                        .addComponent(bToggleStartingGoalie))
                .addComponent(tfNameplate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lDisplayNameplate)
        );

        // Adding functionality
        // Set text in text field to nameplate
        bSetNameplate.addActionListener(e -> {
            greenScreenWindow.setText(tfNameplate.getText().toUpperCase());
            lDisplayNameplate.setText(tfNameplate.getText().toUpperCase());
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
        
        // Toggle starting goalie visibility
        bToggleStartingGoalie.addActionListener(e -> {
            if (greenScreenWindow.toggleStartingGoalies()) {
                bToggleStartingGoalie.setBackground(Color.RED);
                bToggleStartingGoalie.setText("Hide");
                System.out.println("[cw] Shown Starting Goalies");
            } else {
                bToggleStartingGoalie.setBackground(Color.BLACK);
                bToggleStartingGoalie.setText("Show");
                System.out.println("[cw] Hidden Starting Goalies");
            }
        });
    }
    
    public JComponent getMainComponent() {
        return mainPanel;
    }
}
