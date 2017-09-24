import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {

	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	public WordDAO(){}

	public int registWords(ArrayList<Word> words){
		int result = 0;
		try{
			String SQL = "INSERT INTO dictionary VALUES(?,?)";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useUnicode=true&characterEncoding=utf8", "root", "");

			for(int i=0;i<words.size();i++){
				Word wd = words.get(i);
				st = con.prepareStatement(SQL);
				st.setString(1,wd.getEnglish());
				st.setString(2,wd.getJapanese());
				//SQL実行
				result = result + st.executeUpdate();

			}
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			if (st != null) {
				try{
					st.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (con != null) {
				try{
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public List<Word> getWords() {
		List<Word> words = new ArrayList<>();
		try{
			String SQL = "SELECT english,japanese FROM dictionary";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useUnicode=true&characterEncoding=utf8", "root", "");

			st = con.prepareStatement(SQL);
			rs = st.executeQuery();

			while (rs.next()){
				Word wd = new Word(rs.getString("english"),rs.getString("japanese"));
				words.add(wd);
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			if (st != null) {
				try{
					st.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (con != null) {
				try{
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return words;
	}
}