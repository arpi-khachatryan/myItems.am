package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Category category) {
        String sql = "insert into category(name) values(?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAll() {
        String sql = "select * from category";
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                categories.add(getCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getById(int id) {
        String sql = "select * from category where id = " + id;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery(sql);
            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteCategoryById(int id) {
        String query = "delete from category where id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Category category) {
        String sql = "update category set name=? where id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
