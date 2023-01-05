package org.example.dao;

import org.example.connections.MySqlConnection;
import org.example.model.Coordonnees;
import org.example.model.WorldCity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorldcityDao implements GenericDao<WorldCity, Integer> {

    @Override
    public WorldCity create(WorldCity worldCity) {
        Connection connection = MySqlConnection.getConnection();
        try {
            String insertRequest = "INSERT INTO worldcities (city, city_ascii, lat, lng, country, iso2, iso3) " +
                    "VALUES( ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertRequest, PreparedStatement.RETURN_GENERATED_KEYS);
            preparePreparedStatement(ps, worldCity);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()) {
                worldCity.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return worldCity;
    }

    @Override
    public List<WorldCity> readAll() {
        ArrayList<WorldCity> worldCities = null;
        Connection connection = MySqlConnection.getConnection();
        String selectRequest = "SELECT * FROM worldcities";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectRequest);
            worldCities = new ArrayList<>();
            while (result.next()) {
                worldCities.add(createWorlcity(result));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return worldCities;
    }

    @Override
    public WorldCity readById(Integer id) {
        Connection connection = MySqlConnection.getConnection();

        WorldCity worldCity = new WorldCity();
        try {
            String selectRequest = "SELECT * FROM worldcities WHERE num = ?";
            PreparedStatement ps = connection.prepareStatement(selectRequest);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                worldCity = createWorlcity(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return worldCity;
    }

    public WorldCity getWorldCityByCity(String city) {
        Connection connection = MySqlConnection.getConnection();

        WorldCity worldCity = new WorldCity();
        try {
            String selectRequest = "SELECT * FROM worldcities WHERE city = ?";
            PreparedStatement ps = connection.prepareStatement(selectRequest);
            ps.setString(1, city);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                worldCity = createWorlcity(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return worldCity;
    }

    @Override
    public WorldCity update(WorldCity worldCity) {
        Connection connection = MySqlConnection.getConnection();
        try {
            String updateRequest = "UPDATE worldcities SET city = ?, city_ascii = ?, lat = ?, lng = ?, " +
                    "country = ?, iso2 = ?, iso3 = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(updateRequest);
            preparePreparedStatement(ps, worldCity);
            ps.setInt(8, worldCity.getId());
            int nombreLigne = ps.executeUpdate();
            return nombreLigne > 0 ? worldCity : null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = MySqlConnection.getConnection();
        try {
            String deleteRequest = "DELETE FROM worldcities WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteRequest);
            ps.setInt(1, id);
            int nombreLigne = ps.executeUpdate();
            return nombreLigne > 0 ? true : false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private WorldCity createWorlcity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String city = rs.getString("city");
        String city_ascii = rs.getString("city_ascii");
        float lat = rs.getFloat("lat");
        float lng = rs.getFloat("lng");
        String country = rs.getString("country");
        String iso2 = rs.getString("iso2");
        String iso3 = rs.getString("iso3");
        new WorldCity(id, city, city_ascii, lat, lng, country, iso2, iso3);
        return new WorldCity(id, city, city_ascii, lat, lng, country, iso2, iso3);
    }

    private void preparePreparedStatement(PreparedStatement ps, WorldCity wc) throws SQLException {
        ps.setString(1, wc.getCity());
        ps.setString(2, wc.getCity_ascii());
        ps.setFloat(3, wc.getLat());
        ps.setFloat(4, wc.getLng());
        ps.setString(5, wc.getCountry());
        ps.setString(6, wc.getIso2());
        ps.setString(7, wc.getIso3());
    }
}
