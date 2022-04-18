package pl.coderslab.users;

import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    // create - utwórz
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?);";
    // read - wyświetl
    private static final String READ_FROM_DB_QUERY = "SELECT * FROM users WHERE id = ?;";
    // update - zaktualizuj
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    // delete - usuń
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    // znajdź wszystkich
    private static final String FIND_ALL = "SELECT * FROM users;";

    public String hashPassword(String password){
        return org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate(); //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userID){
        try(Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_FROM_DB_QUERY);
            statement.setString(1,String.valueOf(userID));
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void update(User user){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int userId){
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
            }
        catch (SQLException e){
            e.printStackTrace();
        }
        }

        public User[] findAll(){
        try(Connection connection = DbUtil.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            User[] users = new User[0];
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = Arrays.copyOf(users, users.length+1);
                users[users.length-1] = user;
            }

            return users;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    }


