package by.reshetilova.DAO;

import by.reshetilova.classes.Planet;
import by.reshetilova.classes.Satellite;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOConnection extends AbstractDAO<Planet> {
    private static final Logger logger = Logger.getLogger(DAOConnection.class);
    private static final String GET_INFO_PLANETS = "SELECT * from Planets";
    private static final String GET_INFO_SATELLITES= "SELECT * from Satellites";



    @Override
    public void connect() {
        try {
            String dbURL = "jdbc:sqlserver://DESKTOP-RCI9504;databaseName=PLANETS;integratedSecurity=false;"
                    + "encrypt=false;trustServerCertificate=true";

            super.connector = DriverManager.getConnection(dbURL,"sa","AnResh1");
            logger.info("ESTABLISHED CONNECTION WITH MSSQL");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        super.close();
        logger.info("Disconnected from mssql");
    }

    @Override
    public ArrayList<Planet> getAll(){
        var planets = new ArrayList<Planet>();
        var satellites = new ArrayList<Satellite>();
        var ps_planet = getPreparedStatement(GET_INFO_PLANETS);
        var ps_satellite = getPreparedStatement(GET_INFO_SATELLITES);

        try {
            var rs_planet = ps_planet.executeQuery();
            var rs_satellite = ps_satellite.executeQuery();
            while (rs_planet.next()) {
                planets.add(new Planet(rs_planet.getString(1), rs_planet.getDouble(2), rs_planet.getString(3), rs_planet.getBoolean(4), rs_planet.getBoolean(5)));
            }

            while (rs_satellite.next()) {
                satellites.add(new Satellite(rs_satellite.getString(1), rs_satellite.getString(2), rs_satellite.getDouble(3), rs_satellite.getDouble(4)));
            }

            for (var p: planets){
                for (var s: satellites) {
                    if (p.getName().equals(s.getPlanet())){
                        p.setSatellites(s);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePreparedStatement(ps_planet);
            closePreparedStatement(ps_satellite);
            return planets;
        }
    }

    public void getWithStatement(ArrayList<Planet> planets, String statement){
        var ps = getPreparedStatement(statement);

        try {
            var rs = ps.executeQuery();

            while (rs.next()) {
                for(var p: planets){

                    if (p.getName().equals(rs.getString(1))) {
                        System.out.println(p.toString());
                        System.out.println();

                        if (p.getSatellites() != null) {
                            for (var s : p.getSatellites()) {
                                System.out.println(s.toString());
                                System.out.println();
                            }
                        }

                        System.out.println("---------------------------------------");
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePreparedStatement(ps);
        }
    }
}

