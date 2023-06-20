package stepdefinitions.database_stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static utilities.DataBaseUtils.getResulSet;

public class US01_DBStepDefs {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("{string} ile guest useri cagir")
    public void ile_guest_useri_cagir(String username) throws SQLException {

        //Connection ve Statement oluştur
//        connection = DriverManager.getConnection("jdbc:postgresql://managementonschools.com:5432/school_management", "select_user", "43w5ijfso");
//        statement = connection.createStatement();

        //Query'yi çalıştır
        String query = "SELECT * FROM guest_user WHERE username = '"+username+"'";
        resultSet = getResulSet(query);
        //resultSet = statement.executeQuery(query);

    }

    @Then("body sunlari icermeli: {string}, {string}, {string}, {string}, {string}, {string}, {string},{string},{string}")
    public void body_sunlari_icermeli(String name, String surname, String birthplace, String phone, String gender, String dateOfBirth, String ssn , String username, String password) throws SQLException {

        resultSet.next();//Pointer varsayılan olarak sütun isimlerini gösterir. Next methodu ile table üzerine alınarak data okunabilir.

        String actUsername = resultSet.getString("username");
        String actName = resultSet.getString("name");
        String actSurname = resultSet.getString("surname");
        String actBirthPlace = resultSet.getString("birth_place");
        String actPhone = resultSet.getString("phone_number");
        String actGender = resultSet.getString("gender");
        String actDateOfBirth = resultSet.getString("birth_day");
        String actSsn = resultSet.getString("ssn");

        assertEquals(username,actUsername);
        assertEquals(name,actName);
        assertEquals(surname,actSurname);
        assertEquals(birthplace,actBirthPlace);
        assertEquals(phone,actPhone);
        assertEquals(gender,actGender);
        assertEquals(dateOfBirth,actDateOfBirth);
        assertEquals(ssn,actSsn);

    }

    @Given("birthplace {string} ile guest useri cagir")
    public void birthplace_ile_guest_useri_cagir(String birthplace) throws SQLException {
        //Connection ve Statement oluştur
//        connection = DriverManager.getConnection("jdbc:postgresql://managementonschools.com:5432/school_management", "select_user", "43w5ijfso");
//        statement = connection.createStatement();

        //Query'yi çalıştır
        String query = "SELECT * FROM guest_user WHERE birth_place = '"+birthplace+"'";
        //resultSet = statement.executeQuery(query);
        resultSet = getResulSet(query);

    }
    @Then("kac tane user oldugunu bul")
    public void kac_tane_user_oldugunu_bul() throws SQLException {

        while (resultSet.next()){

            System.out.println(resultSet.getString("name")+"--"+resultSet.getString("surname")+"--"+resultSet.getString("birth_place"));

        }
    }

    @Given("silinen {string} ile guest useri cagir")
    public void silinenIleGuestUseriCagir(String username) {

        String query = "SELECT * FROM guest_user WHERE username = '"+username+"'";
        resultSet = getResulSet(query);

    }

    @Then("tablein bos oldugunu dogrula")
    public void tableinBosOldugunuDogrula() throws SQLException {

        assertFalse(resultSet.next());

    }
}



