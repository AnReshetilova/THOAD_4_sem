package by.reshetilova.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {

    protected Connection connector;

    public abstract void connect();

    public void close() throws SQLException {
        connector.close();
    }

    public abstract ArrayList<T> getAll();

    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement ps = null;

        try{
            ps = connector.prepareStatement(sql);
        } catch(SQLException e){
            e.printStackTrace();
        }

        return ps;
    }

    public void closePreparedStatement(PreparedStatement ps){
        if (ps != null){
            try{
                ps.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
