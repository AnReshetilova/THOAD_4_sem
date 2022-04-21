package by.reshetilova;

import by.reshetilova.DAO.DAOConnection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.SQLException;

public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }

    private static final Logger logger = Logger.getLogger(String.valueOf(Main.class));
    private static final String WITH_LIFE = "SELECT Planets.Name from Planets where Planets.Life = 1";
    private static final String WITH_MIN_RAD = "SELECT top(1) Planets.Name, max(Planets.Radius)[rad], count(Satellites.Name) cot from Planets inner join Satellites on Planets.Name = Satellites.Planet group by planets.Name order by rad asc, cot desc";
    private static final String WITH_MAX_RAD = "SELECT top(1) Planets.Name, sum(Satellites.Radius)[rad] from Planets inner join Satellites on Planets.Name = Satellites.Planet group by planets.Name order by rad desc";

    public static void main(String[] args) throws SQLException {
        logger.info("Started main");
        var manager = new DAOConnection();
        manager.connect();
        var planets = manager.getAll();
        System.out.println("PLANETS WITH LIFE");
        manager.getWithStatement(planets, WITH_LIFE);

        System.out.println("PLANET WITH MIN RAD AND MAX SAT. COUNT");
        manager.getWithStatement(planets, WITH_MIN_RAD);

        System.out.println("PLANET WITH MAX SAT. RADIUS");
        manager.getWithStatement(planets, WITH_MAX_RAD);
        manager.close();
    }
}
