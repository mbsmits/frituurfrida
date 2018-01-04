package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import be.vdab.entities.Saus;

public class SausRepository extends AbstractRepository {
	
	private static final String	BEGIN_SELECT		= "select id, naam, ingredienten from sauzen ";
	private static final String	FIND_ALL			= BEGIN_SELECT + "order by naam";
	private static final String	FIND_BY_INGREDIENT	= BEGIN_SELECT + "where ingredienten like '%?%' order by naam";
	private static final String	DELETE				= "delete from sauzen where id in ?";
	
	public List<Saus> findAll() {
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
			List<Saus> sauzen = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
				while (resultSet.next()) {
					sauzen.add(resultSetRijNaarSaus(resultSet));
				}
			}
			connection.commit();
			return sauzen;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public List<Saus> findByIngredient(String ingredient) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_INGREDIENT)) {
			List<Saus> sauzen = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, ingredient);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					sauzen.add(resultSetRijNaarSaus(resultSet));
				}
			}
			connection.commit();
			return sauzen;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	private Saus resultSetRijNaarSaus(ResultSet resultSet) throws SQLException {
		return new Saus(resultSet.getLong("id"), resultSet.getString("naam"), resultSet.getString("ingredienten"));
	}
	
	public void delete(Set<Long> idStream) {
		String temp = idStream.toString().replaceAll("[", "(").replaceAll("]", ")");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, temp);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
}
