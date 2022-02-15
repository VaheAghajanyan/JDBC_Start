package com.synisys.jdbcStart.util.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {
    public Connection getConnection() throws SQLException;
}
