package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import TransferObject.PoemTO;

public class PoemCrudDAO extends DALFascade {

	private DataBaseManager db = new DataBaseManager();
	private Connection connection = db.getConnection();

	public PoemCrudDAO(IPoemCrudDAO ipoem) {
		super(ipoem);
	}

	// Function to take Data of Books from DB
	public Object[][] getBooksFromDB() {

		int rowCount = getTotalRowsInBooks();
		Object[][] bookData = new Object[rowCount][4];

		try {
			Statement statement = connection.createStatement();

			String selectQuery = "SELECT * FROM books";

			ResultSet resultSet = statement.executeQuery(selectQuery);

			if (connection != null) {

				int i = 0;
				while (resultSet.next()) {
					// Retrieve data from the result set
					String bookName = resultSet.getString("name");
					String bookAuthor = resultSet.getString("author");
					String yearAuthorDied = resultSet.getString("year_author_died");
					bookData[i][0] = (i + 1);
					bookData[i][1] = bookName;
					bookData[i][2] = bookAuthor;
					bookData[i][3] = yearAuthorDied;

					i++;
				}

				return bookData;
			}
			resultSet.close();
			statement.close();
//				db.closeConnection();
		} catch (SQLException e) {

		}
		return bookData;
	}

	// Function to used No. of Rows in 2D Array
	public int getTotalRowsInBooks() {

		int rowCount = 0;
		try {
			Statement statement = connection.createStatement();

			String countQuery = "SELECT COUNT(*) FROM books"; // To make a 2D Array

			ResultSet rowCountQuery = statement.executeQuery(countQuery);

			if (rowCountQuery.next()) {
				rowCount = rowCountQuery.getInt(1);
			}

			statement.close();
//			db.closeConnection();
		} catch (SQLException e) {

		}

		return rowCount;
	}

	// Function to take Data of Poems from DB
	public Object[][] getPoemsFromDB(String bookName, String title) {

		int rowCount = getTotalRowsInPoemsVerses(bookName);
		Object[][] bookData = new Object[rowCount][4];

		try {
			Statement statement = connection.createStatement();

			String selectQuery = "SELECT p.id as id, p.title as title, v.misra_1 as misra_1, v.misra_2 as misra_2 "
					+ "FROM books b " + "JOIN poems p ON p.book_id = b.id " + "JOIN verse v ON v.poem_id = p.id "
					+ "WHERE b.name = '" + bookName + "' AND p.title = '" + title + "'";

			ResultSet resultSet = statement.executeQuery(selectQuery);

			if (connection != null) {

				int i = 0;
				while (resultSet.next()) {
					// Retrieve data from the result set
					String poemTitle = resultSet.getString("title");
					String misra_1 = resultSet.getString("misra_1");
					String misra_2 = resultSet.getString("misra_2");
					bookData[i][0] = (i + 1);
					bookData[i][1] = poemTitle;
					bookData[i][2] = misra_1;
					bookData[i][3] = misra_2;

					i++;
				}

				return bookData;
			}
			resultSet.close();
			statement.close();
//					db.closeConnection();
		} catch (SQLException e) {

		}
		return bookData;
	}

	// Function to used No. of Rows in 2D Array
	public int getTotalRowsInPoemsVerses(String bookName) {

		int rowCount = 0;
		try {
			Statement statement = connection.createStatement();

			String countQuery = "SELECT COUNT(*) " + "FROM books b " + "JOIN poems p ON p.book_id = b.id "
					+ "JOIN verse v ON v.poem_id = p.id " + "WHERE b.name = '" + bookName + "'"; // To make a 2D Array

			ResultSet rowCountQuery = statement.executeQuery(countQuery);

			if (rowCountQuery.next()) {
				rowCount = rowCountQuery.getInt(1);
			}

			statement.close();
//				db.closeConnection();
		} catch (SQLException e) {

		}

		return rowCount;
	}

	// Function to take Data of Poems(Only Poems Table)
	public Object[][] getPoemTitleFromDB(String bookName) {

		int rowCount = getTotalRowsInPoemsForPoemsTitle(bookName);
		Object[][] bookData = new Object[rowCount][2];

		try {
			Statement statement = connection.createStatement();

			String selectQuery = "SELECT title " + "FROM poems " + "WHERE book_id = (SELECT id " + "FROM books "
					+ "WHERE name = '" + bookName + "')";

			ResultSet resultSet = statement.executeQuery(selectQuery);

			if (connection != null) {

				int i = 0;
				while (resultSet.next()) {
					String poemTitle = resultSet.getString("title");
					bookData[i][0] = (i + 1);
					bookData[i][1] = poemTitle;

					i++;
				}

				return bookData;
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {

		}
		return bookData;
	}

	// Function to used No. of Rows in 2D Array
	public int getTotalRowsInPoemsForPoemsTitle(String bookName) {

		int rowCount = 0;
		try {
			Statement statement = connection.createStatement();

			String countQuery = "SELECT COUNT(*) FROM poems";

			ResultSet rowCountQuery = statement.executeQuery(countQuery);

			if (rowCountQuery.next()) {
				rowCount = rowCountQuery.getInt(1);
			}
			statement.close();
		} catch (SQLException e) {

		}
		return rowCount;
	}

	// Function to insert Poem Data/Verse/Misra in DB
	public void addPoemInDB(PoemTO poem, String bookName) {

		try {
			Statement statement = connection.createStatement();

			if (connection != null) {

				String createPoemQuery = "INSERT INTO poems (title, book_id) "
									   + "SELECT '" + poem.getPoemTitle() + "', id "
									   + "FROM books "
									   + "WHERE name = '" + bookName  + "'";

				int a = statement.executeUpdate(createPoemQuery);
				System.out.println("Row Affected : " + a);
			}

			statement.close();
//				db.closeConnection();
		} catch (SQLException e) {

		}
	}

	public void updatePoemInDB(PoemTO poem, String updatedPoem) {

		try {
			Statement statement = connection.createStatement();

			if (connection != null) {

				String updatePoemQuery = "UPDATE poems " + "SET title = '" + updatedPoem + "' " + "WHERE title = '"
						+ poem.getPoemTitle() + "'";

				statement.executeUpdate(updatePoemQuery);
			}

			statement.close();
//				db.closeConnection();
		} catch (SQLException e) {

		}
	}

	public void deletePoemFromDB(PoemTO poem) {

		try {
			Statement statement = connection.createStatement();

			if (connection != null) {

				while (true) {
					String createBookSQLSquery = "DELETE FROM verse "
											   + "WHERE poem_id = '" + poem.getPoemTitle() + "'";

					int execution = statement.executeUpdate(createBookSQLSquery);
					if(execution == 0) {
						break;
					}
				}

				String deletePoemQuery = "DELETE FROM poems "
									   + "WHERE title = '" + poem.getPoemTitle() + "'";

				statement.executeUpdate(deletePoemQuery);
			}

			statement.close();
//				db.closeConnection();
		} catch (SQLException e) {

		}
	}

	public int getPoemTitleFromDB(PoemTO poem) {

		int poemID = 0;
		try {
			Statement statement = connection.createStatement();

			if (connection != null) {

				String selectQuery = "SELECT id FROM poems " + "WHERE title = '" + poem.getPoemTitle() + "'";

				ResultSet resultSet = statement.executeQuery(selectQuery);
				while (resultSet.next()) {
					poemID = resultSet.getInt("id");
				}
			}

			statement.close();
		} catch (SQLException e) {

		}
		return poemID;
	}

	// Function to insert Verses in DB
	public void addVersesInDB(PoemTO poem) {

		try {
			Statement statement = connection.createStatement();

			if (connection != null) {

				String createBookSQLSquery = "INSERT INTO verse (misra_1, misra_2, poem_id) " + "VALUES ('"
						+ poem.getMisra_1() + "', '" + poem.getMisra_2() + "', '" + poem.getPoem_id() + "')";

//					String addPoemTitleSQLquery ="INSERT INTO poem "
				statement.executeUpdate(createBookSQLSquery);

			}

			statement.close();
//					db.closeConnection();
		} catch (SQLException e) {

		}
	
	}
}
	