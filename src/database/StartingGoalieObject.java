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
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;

/**
 * Provides data structure for converting database results into
 * {@link graphics.StartingGoalie}.
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 * @version %I% %G%
 */
public class StartingGoalieObject {

    private final int team;
    private final int number;
    private final String firstName;
    private final String lastName;
    private final int w;
    private final int otw;
    private final int otl;
    private final int l;
    private final Time toi;
    private final int ga;
    private final int svs;
    private final int sog;
    private final int so;

    /**
     * Constructor.
     * <p>
     * The result set format includes:
     * <ul>
     * <li> int Team ID.
     * <li> int Player number.
     * <li> String Player first name.
     * <li> String Player last name.
     * <li> int Number of wins.
     * <li> int Number of overtime wins.
     * <li> int Number of overtime losses.
     * <li> int Number of losses.
     * <li> Time Time on ice.
     * <li> int Number of goals against.
     * <li> int Number of saves.
     * <li> int Number of shots on goal.
     * <li> int Number of shutouts.
     * </ul>
     *
     * @param rs The results from database
     * @throws SQLException Throws when {@code rs} is not formatted correctly.
     */
    public StartingGoalieObject(ResultSet rs) throws SQLException {
        rs.next();
        this.team = rs.getInt(1);
        this.number = rs.getInt(2);
        this.firstName = rs.getString(3);
        this.lastName = rs.getString(4);
        this.w = rs.getInt(5);
        this.otw = rs.getInt(6);
        this.otl = rs.getInt(7);
        this.l = rs.getInt(8);
        this.toi = rs.getTime(9);
        this.ga = rs.getInt(10);
        this.svs = rs.getInt(11);
        this.sog = rs.getInt(12);
        this.so = rs.getInt(13);
    }

    /**
     * @return team_name from team_id
     */
    public String getTeam() {
        switch (this.team) {
            case 1:
                return "Botany Swarm";
            case 2:
                return "Canterbury Red Devils";
            case 3:
                return "Dunedin Thunder";
            case 4:
                return "Skycity Stampede";
            case 5:
                return "West Auckland Admirals";
            default:
                return null;
        }
    }

    /**
     * @return goalie number.
     */
    public String getNumber() {
        return Integer.toString(number);
    }

    /**
     * @return first name of player.
     */
    public String getFirstName() {
        return firstName.toUpperCase();
    }

    /**
     * @return last name of player.
     */
    public String getLastName() {
        return lastName.toUpperCase();
    }

    /**
     * @return full name of player.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Formatted W-OTW-OTL-L.
     *
     * @return record of player.
     */
    public String getRecord() {
        return w + "-" + otw + "-" + otl + "-" + l;
    }

    /**
     * Calculated (GA / MIP) * 60. Formated 0.00.
     *
     * @return goals against average of player.
     */
    public String getGoalsAgainstAverage() {
        float mip = (toi.getHours() * 60) + toi.getMinutes() + (toi.getSeconds() / 60);
        return String.format("%.2f", (ga / mip) * 60f);
    }

    /**
     * Calculated (SVS / SOG). Formated .000.
     *
     * @return save percentage of player.
     */
    public String getSavePercentage() {
        DecimalFormat df = new DecimalFormat(".000");
        float savePercentage = (float) svs / sog;
        return df.format(savePercentage);
    }

    /**
     * @return number of shutouts of player.
     */
    public String getShutouts() {
        return Integer.toString(so);
    }

}
