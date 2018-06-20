package pw.todo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoModel {

  private static Connection obterConexao() throws SQLException {
    //Estabelecer uma conexão com o banco de dados.
    String url = "jdbc:derby://localhost:1527/tododb;create=true";
    String user = "app";
    String password = "app";
    return DriverManager.getConnection(url, user, password);
  }

  public static void incluir(Todo todo) throws SQLException {
    Connection conn = obterConexao();
    
    //Obter sentença SQL.
    String sql = "insert into todo (codigo, todo) values (?, ?)";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, todo.getCodigo());
    pstmt.setString(2, todo.getTodo());
    pstmt.execute();
  }

  public static void salvar(Todo todo) throws SQLException {
    Connection conn = obterConexao();

    //Obter sentença SQL.
    String sql = "update todo set todo = ? where codigo = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, todo.getTodo());
    pstmt.setString(2, todo.getCodigo());
    pstmt.execute();
  }

  public static List<Todo> listar() throws SQLException {
    Connection conn = obterConexao();
    
    Statement stmt = conn.createStatement();
    String sql = "select codigo, todo from todo order by codigo";
    ResultSet rs = stmt.executeQuery(sql);
  
    List<Todo> listaDeTodos = new ArrayList<Todo>();
    while (rs.next()) {
      // Cria um todo para o registro.
      Todo todo = new Todo();
      todo.setCodigo(rs.getString("codigo"));
      todo.setTodo(rs.getString("todo"));
      // Adiciona o todo na lista de todos.
      listaDeTodos.add(todo);
    }
    return listaDeTodos;
  }

  public static void excluir(Todo todo) throws SQLException {
    Connection conn = obterConexao();
    
    //Obter sentença SQL.
    String sql = "delete from todo where codigo = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, todo.getCodigo());
    pstmt.execute();
  }

}
