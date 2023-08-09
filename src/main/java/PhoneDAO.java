import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAO extends AbstractDAO <Integer, Phone> {
    public static final String SQLSelectAllPhone = "SELECT*FROM phone";
    public static final String SQLSelectPhoneId = "SELECT*FROM phone WHERE id = ?";
    public static final String SQLDeletePhoneId = "DELETE FROM phone WHERE id = ? ";
    public static final String SQLDeletePhone = "DELETE FROM phone WHERE id = ? AND name = ? AND number = ?";
    public static final String SQLInsertPhone = "INSERT INTO phone (id, name, number) " +
            "VALUES (?,?, ?)";
    public static final String SQLUpdateNumber = "UPDATE phone SET number = ?" +
            "WHERE Name = ?";

    @Override
    public List<Phone> findAll() {
        List<Phone> phones = new ArrayList<>();
        try(Connection connection = GetConnection.open();
            Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(SQLSelectAllPhone);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String number = rs.getString(3);
                phones.add(new Phone(id,name,number));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return phones;
    }

    @Override
    public Phone findEntityById(Integer id) {
        Phone phone = new Phone();
        try(Connection connection = GetConnection.open();
            PreparedStatement statement = connection.prepareStatement(SQLSelectPhoneId)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                phone.setId(id);
                phone.setName(rs.getString(2));
                phone.setNumber(rs.getString(3));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return phone;
    }

    @Override
    public boolean delete(Integer id) {
        boolean delId = false;
        try(Connection connection = GetConnection.open();
            PreparedStatement statement = connection.prepareStatement(SQLDeletePhoneId)){
            statement.setInt(1, id);
            int k = statement.executeUpdate();
            if (k !=0)
                delId = true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return delId;
    }

    @Override
    public boolean delete(Phone entity) {
        boolean delPhone = false;
        try(Connection connection = GetConnection.open();
            PreparedStatement statement = connection.prepareStatement(SQLDeletePhone)){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getNumber());
            int k = statement.executeUpdate();
            if (k !=0)
                delPhone = true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return delPhone;
    }

    @Override
    public boolean create(Phone entity) {
        boolean createPhone = false;
        try(Connection connection = GetConnection.open();
            PreparedStatement statement = connection.prepareStatement(SQLInsertPhone)){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getNumber());
            int k = statement.executeUpdate();
            if (k!=0)
                createPhone = true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return createPhone;
    }

    @Override
    public Phone update(Phone entity) {
        try(Connection connection = GetConnection.open();
            PreparedStatement statement = connection.prepareStatement(SQLUpdateNumber)){
            statement.setString(1, entity.getNumber());
            statement.setString(2, entity.getName());
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return entity;
    }
}
