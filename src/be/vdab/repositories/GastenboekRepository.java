package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import be.vdab.entities.GastenBoekEntry;

public class GastenboekRepository extends AbstractRepository {
	
	private final static String	FIND_ALL	= "select id,naam,datumtijd,bericht from gastenboek order by id desc";
	private final static String	CREATE		= "insert into gastenboek(naam,datumtijd,bericht) values (?,?,?)";
	private final static String	DELETE		= "delete from gastenboek where id in (";
	
	public List<GastenBoekEntry> findAll() {
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
			List<GastenBoekEntry> entries = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
				while (resultSet.next()) {
					entries.add(resultSetRijNaarGastenboekEntry(resultSet));
				}
			}
			connection.commit();
			return entries;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public void create(GastenBoekEntry entry) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, entry.getNaam());
			statement.setTimestamp(2, java.sql.Timestamp.valueOf(entry.getDatumTijd()));
			statement.setString(3, entry.getBericht());
			
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	private GastenBoekEntry resultSetRijNaarGastenboekEntry(ResultSet resultSet) throws SQLException {
		return new GastenBoekEntry(resultSet.getLong("id"), resultSet.getString("naam"),
				resultSet.getTimestamp("datumtijd").toLocalDateTime(), resultSet.getString("bericht"));
	}
	
	public void delete(Set<Long> ids) {
		StringBuilder sql = new StringBuilder(DELETE);
		ids.forEach(id -> sql.append("?,"));
		sql.setCharAt(sql.length() - 1, ')');
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql.toString())) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			int index = 1;
			for (long id : ids) {
				statement.setLong(index++, id);
			}
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
}
