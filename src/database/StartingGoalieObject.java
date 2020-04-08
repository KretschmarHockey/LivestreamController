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
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 */
public class StartingGoalieObject {

    int team;
    int number;
    String firstName;
    String lastName;
    int w;
    int otw;
    int otl;
    int l;
    Time toi;
    int ga;
    int svs;
    int sog;
    int so;

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

    public String getNumber() {
        return Integer.toString(number);
    }

    public String getFirstName() {
        return firstName.toUpperCase();
    }

    public String getLastName() {
        return lastName.toUpperCase();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getRecord() {
        return w + "-" + otw + "-" + otl + "-" + l;
    }

    public String getGoalsAgainstAverage() {
        float mip = (toi.getHours() * 60) + toi.getMinutes() + (toi.getSeconds() / 60);
        return String.format("%.2f", (ga / mip) * 60f);
    }

    public String getSavePercentage() {
        DecimalFormat df = new DecimalFormat(".000");
        float savePercentage = (float) svs / sog;
        return df.format(savePercentage);
    }

    public String getShutouts() {
        return Integer.toString(so);
    }

}
