package my.learning.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(User u) {
		var conn = Database.instance().getConnection();
		try {
			var stmt = conn.prepareStatement("insert into user (name) values (?)");
			stmt.setString(1, u.getName());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Optional<User> findById(int id) {
		var conn = Database.instance().getConnection();
		try {
			var stmt = conn.prepareStatement("select name from user where id=?");
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				User user = new User(id, rs.getString("name"));
				return Optional.of(user);
			}
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return Optional.empty();
	}

	@Override
	public void update(User u) {
		var conn = Database.instance().getConnection();
		try {
			var stmt = conn.prepareStatement("update user set name=? where id=?");
			stmt.setString(1, u.getName());
			stmt.setInt(2, u.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public void delete(User u) {
		var conn = Database.instance().getConnection();
		try {
			var stmt = conn.prepareStatement("delete from user where id=?");
			stmt.setInt(1, u.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<User> findAll() {
		var conn = Database.instance().getConnection();
		List<User> users = new ArrayList<>();
		try {
			var stmt = conn.createStatement();
			var rs = stmt.executeQuery("select id, name from user");
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2)));
			}
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return users;
	}

}
