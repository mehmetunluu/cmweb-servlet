package com.backend.exercise.hibernate.dialect;

import java.sql.Types;

/**
 * Created by M.UNLU on 29.02.2016.
 */
public class MySqlDialect extends  org.hibernate.dialect.MySQLDialect {

    public MySqlDialect() {
        super();
        registerColumnType(Types.VARCHAR,"nvarchar($l)");
    }
}
