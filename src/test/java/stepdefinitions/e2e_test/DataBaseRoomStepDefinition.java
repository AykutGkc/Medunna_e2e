package stepdefinitions.e2e_test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pojos.RoomPojo;


import java.sql.*;

import static org.junit.Assert.assertEquals;
import static stepdefinitions.e2e_test.MedunnaRoomStepDefinitions.roomId;
import static stepdefinitions.e2e_test.MedunnaRoomStepDefinitions.roomNummerFaker;

public class DataBaseRoomStepDefinition {
    Connection connection;
    Statement statement;
    @Given("connet to database")
    public void connet_to_database() throws SQLException {
        //1.Adim: Connection olustur
        connection=DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2","select_user","Medunna_pass_@6");

        //2.Adim: Statement olustur.
        statement=connection.createStatement();

    }

    @Then("read room and validate")
    public void read_room_and_validate() throws SQLException {
        RoomPojo expectedData=new RoomPojo(roomNummerFaker,"PREMIUM_DELUXE",true,123.00,"Created For End To End Test");

        //3.Adim: Query calistir
        String query= "SELECT * FROM room WHERE id ="+roomId;//roomId --> UI testten geliyor
        ResultSet resultSet=statement.executeQuery(query);
        resultSet.next();

        assertEquals(expectedData.getRoomNumber(),resultSet.getObject("room_number"));
        assertEquals(expectedData.getRoomType(),resultSet.getObject("room_type"));
        assertEquals(expectedData.getStatus(),resultSet.getObject("status"));
        assertEquals(expectedData.getPrice()+"0",resultSet.getObject("price")+"");
        assertEquals(expectedData.getDescription(),resultSet.getObject("description"));
    }

}
