package org.chocolates.mapper;

import org.chocolates.model.Chocolate;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChocolateMapper implements RowMapper<Chocolate> {
    @Override
    public Chocolate map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Chocolate(rs.getInt("id"), rs.getString("name"), rs.getInt("qty"));
    }
}
