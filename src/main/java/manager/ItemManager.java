package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();

    public void add(Item item) {
        String sql = "insert into item(title,price,category_id,pic_url,user_id) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        try {
            Statement statement = connection.createStatement();
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
        try {
            Statement statement = connection.createStatement();
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
        String sql = "SELECT * FROM item where user_id=" + userId;
        List<Item> items = new ArrayList<>();
        List<Item> lastItems = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                items.add(item);
            }
            if (items != null && !items.isEmpty() && items.size() >= 20) {
                for (int i = 20; i > 0; i--) {
                    Item lastItem = items.get(items.size() - i);
                    lastItems.add(lastItem);
                }
            } else {
                for (int i = 0; i < items.size(); i++) {
                    Item lastItem = items.get(i);
                    lastItems.add(lastItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastItems;
    }


    public List<Item> getAllByCategoryId(int categoryId) {
        String sql = "SELECT * FROM item where category_id=" + categoryId;
        List<Item> items = new ArrayList<>();
        List<Item> lastItems = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                items.add(item);
            }
            if (items != null && !items.isEmpty() && items.size() >= 20) {
                for (int i = 20; i > 0; i--) {
                    Item lastItem = items.get(items.size() - i);
                    lastItems.add(lastItem);
                }
            } else {
                for (int i = 0; i < items.size(); i++) {
                    Item lastItem = items.get(i);
                    lastItems.add(lastItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastItems;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = categoryManager.getById(resultSet.getInt("category_id"));
        User user = userManager.getById(resultSet.getInt("user_id"));
        return Item.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .price(resultSet.getDouble("price"))
                .category(category)
                .picUrl(resultSet.getString("pic_url"))
                .user(user)
                .build();
    }

    public void deleteItemById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from item where id =?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void edit(Item item) {
//        String sql = "update item set title=?,price=?,category_id=?,pic_url=?,user_id=? where id=?";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, item.getTitle());
//            ps.setDouble(2, item.getPrice());
//            ps.setInt(3, item.getCategory().getId());
//            ps.setString(4, item.getPicUrl());
//            ps.setInt(5, item.getUser().getId());
//            ps.setInt(6, item.getId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



    public void edit(Item item) {
        String sql = "update item set title=?,price=?,category_id=?,pic_url=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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
}
