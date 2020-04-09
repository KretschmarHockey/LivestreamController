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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database Object.
 *
 * Provides a connection to NZIHL database and a method for executing select
 * queries.
 *
 * @author Joshua Kretschmar JoshuaJKretschmar@gmail.com
 * @version %I% %G%
 */
public class Database {

    Connection conn;
    Statement stmt;

    /**
     * Connects to NZIHL database with admin credentials.
     *
     * @throws SQLException Throws when connection cannot be made.
     */
    public Database() throws SQLException {
        // Allocate a database 'Connection' object
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nzihlplayers",
                "josh", "AndrewHay2005"); // TODO: Change database name
        // Allocate a 'Statement' object in the Connection
        stmt = conn.createStatement();
    }

    /**
     * Executes a SQL SELECT query.
     *
     * @param strSelect The query to executed.
     * @return The query result in a 'ResultSet' object.
     * @throws SQLException
     */
    public ResultSet select(String strSelect) throws SQLException {
        System.out.println("The SQL statement is: " + strSelect); // Echo for debugging

        ResultSet rset = stmt.executeQuery(strSelect);

        return rset;
    }
}
