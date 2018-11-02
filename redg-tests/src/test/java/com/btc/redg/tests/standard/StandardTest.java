package com.btc.redg.tests.standard;

import com.btc.redg.generated.standard.GReservation;
import com.btc.redg.generated.standard.GRestaurant;
import com.btc.redg.generated.standard.RedG;
import com.btc.redg.generator.extractor.DatabaseManager;
import com.btc.redg.tests.Helpers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class StandardTest {

    @Before
    public void initializeDatabase() throws Exception {
        Class.forName("org.h2.Driver");
        final Connection connection = DriverManager.getConnection("jdbc:h2:mem:redg-standard", "", "");
        assertNotNull(connection);
        final File sqlFile = Helpers.getResourceAsFile("standard-schema.sql");
        DatabaseManager.executePreparationScripts(connection, new File[]{sqlFile});
    }

    @Test
    public void test() throws Exception {
        final Connection connection = DriverManager.getConnection("jdbc:h2:mem:redg-standard", "", "");

        final RedG redG = new RedG();
        prepareTestData(redG);

        redG.insertDataIntoDatabase(connection);

        checkData(connection);
    }

    private void checkData(Connection connection) throws Exception {
        final Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from RESTAURANT");
        rs.next();
        Helpers.assertResultSet(rs, 0, "Susan's Steakhouse");
        assertFalse(rs.next());

        rs = statement.executeQuery("select count(*) from GUEST");
        rs.next();
        Helpers.assertResultSet(rs, 1);
        assertFalse(rs.next());

        rs = statement.executeQuery("select * from WAITER");
        rs.next();
        Helpers.assertResultSet(rs, 0, "Sally", 0);
        rs.next();
        Helpers.assertResultSet(rs, 1, "Stefan", 0);
        assertFalse(rs.next());

        rs = statement.executeQuery("select * from RESERVATION order by TIME");
        rs.next();
        Helpers.assertResultSet(rs, 0, 0, new Timestamp(1L));
        rs.next();
        Helpers.assertResultSet(rs, 0, 0, new Timestamp(1234567890L));
        assertFalse(rs.next());

        rs = statement.executeQuery("select * from WAITER_RESERVATION order by TIME");
        rs.next();
        Helpers.assertResultSet(rs, 1, 0, 0, new Timestamp(1L));
        rs.next();
        Helpers.assertResultSet(rs, 0, 0, 0, new Timestamp(1234567890L));
        assertFalse(rs.next());
    }

    private void prepareTestData(final RedG redg) {
        GReservation reservation = redg.addReservation(
                redg.dummyGuest(),
                redg.addRestaurant()
                        .name("Susan's Steakhouse")
        ).time(new Timestamp(1234567890L));
        redg.addWaiterReservation(redg.addWaiter().name("Sally"), reservation);

        GReservation reservation2 = redg.addReservation(redg.dummyGuest(), redg.findSingleEntity(GRestaurant.class, r -> r.name().startsWith("Susan")));
        redg.addWaiterReservation(redg.addWaiter().name("Stefan"), reservation2);
    }
}
