import java.sql.*;

public class JdbcTest {
   public static void main(String[] arg) throws Exception {
       //  加载驱动
       Class.forName("com.mysql.jdbc.Driver");
       //  获取连接
       String url_mysql = "jdbc:mysql://localhost:3306/tzalm0?useUnicode=true&characterEncoding=UTF-8";
       String user_mysql = "root";
       String password_mysql = "111";
       Connection connection = DriverManager.getConnection(url_mysql, user_mysql, password_mysql);
       //   sql
       String sqlstr =      "	select	* "
                       +    "	from	n_hrim_employee "
                       +    "	where	emp_id = ?";
       //   解析sql
       PreparedStatement preparedStatement = connection.prepareStatement(sqlstr);
//       Statement statement01 = connection.createStatement();
//       ResultSet resultSet01 = statement01.executeQuery(sqlstr);
       //   参数设置
       preparedStatement.setString(1, "123");
       //   执行
       ResultSet resultSet = preparedStatement.executeQuery();
       //   获取结果
       if (resultSet.next()){
           String emp_id = resultSet.getString("emp_id");
        }
       resultSet.close();
       preparedStatement.close();
       connection.close();
    }
}
