package manager;

import db.DBConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final CategoryManager categoryManager = new CategoryManager();
    private final UserManager userManager = new UserManager();

    public void add(Item item) {
        String sql = "insert into item(title,price,category_id,pic_url,user_id) values(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicUrl());
            ps.setInt(5, item.getUser().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAll() {
        String sql = "select * from item order by id DESC limit 20";
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getById(int id) {
        String sql = "select * from item where id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getAllByUserId(int userId) {
        String sql = "select * from item  where user_id = ? order by id DESC limit 20";
        return getItems(userId, sql);
    }

    public List<Item> getAllByCategoryId(int categoryId) {
        String sql = "SELECT * FROM item where category_id = ? order by id desc limit 20";
        return getItems(categoryId, sql);
    }

    public void deleteItemById(int id) {
        String sql = "delete from item where id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Item item) {
        String sql = "update item set title=?,price=?,category_id=?,pic_url=? where id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicUrl());
            ps.setInt(5, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Item> getItems(int someId, String sql) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, someId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        return Item.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .price(resultSet.getDouble("price"))
                .category(categoryManager.getById(resultSet.getInt("category_id")))
                .picUrl(resultSet.getString("pic_url"))
                .user(userManager.getById(resultSet.getInt("user_id")))
                .build();
    }
}
