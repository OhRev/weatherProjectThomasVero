package org.example.dao;

import org.example.connections.MySqlConnection;
import org.example.model.Temperature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemperatureDao implements GenericDao<Temperature, Integer>{

    @Override
    public Temperature create(Temperature temperature) {

        Connection connection = MySqlConnection.getConnection();
        String insertRequest = "INSERT INTO temperatures (timesstamp , city , temperature ) VALUES (? , ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(insertRequest, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, temperature.getTimesstamp());
            ps.setString(2, temperature.getCity());
            ps.setFloat(3, temperature.getTemperature());

            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                temperature.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return temperature;
    }

    @Override
    public List<Temperature> readAll() {
        ArrayList<Temperature> temperatures = null;
        Connection connection = MySqlConnection.getConnection();
        String selectRequest = "SELECT * FROM temperatures ORDER BY city";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectRequest);
            temperatures = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt(1);
                Date date = result.getDate(2);
                String city = result.getString(3);
                float temperature = result.getFloat(4);

                Temperature myTemperature = new Temperature(id, date,city, temperature);
                temperatures.add(myTemperature);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return temperatures;
    }

    @Override
    public Temperature readById(Integer id) {
        Temperature myTemperature = null;
        Connection connection = MySqlConnection.getConnection();
        String selectRequest = "SELECT * FROM temperatures WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(selectRequest);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                int myId = result.getInt(1);
                Date date = result.getDate(2);
                String city = result.getString(3);
                float temperature = result.getFloat(4);
                myTemperature = new Temperature(myId, date,city, temperature);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myTemperature;
    }

    @Override
    public Temperature update(Temperature temperature) {
        Connection connection = MySqlConnection.getConnection();
        String updateRequest = "UPDATE temperatures SET timesstamp  = ?, city  = ?, temperature   = ?  WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(updateRequest);
            ps.setInt(1, temperature.getId());
            ps.setDate(2, temperature.getTimesstamp());
            ps.setString(3, temperature.getCity());
            ps.setFloat(4, temperature.getTemperature());
            return ps.executeUpdate() > 0 ? temperature : null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {

        Connection connection = MySqlConnection.getConnection();
        String deleteRequest = "DELETE FROM temparatures WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(deleteRequest);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
// condition ? instruction si condition vraie : instruction si condition fausse
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
