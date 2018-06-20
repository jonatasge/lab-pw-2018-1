package pw.todo.jdbc;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/todo-jdbc/todo")
public class TodoController extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String op = request.getParameter("op");
    op = (op == null ? "" : op);

    Todo todo = new Todo();
    todo.setCodigo(request.getParameter("codigo"));
    todo.setTodo(request.getParameter("todo"));
    
    List<Todo> todos = null;
    try {
      if (op.equals("incluir")) {
        TodoModel.incluir(todo);
      } else if (op.equals("salvar")) {
        TodoModel.salvar(todo);
      } else if (op.equals("excluir")) {
        TodoModel.excluir(todo);
      }

      todos = TodoModel.listar();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    
    //Adiciona a variável na requisição para o JSP trabalhar.
    request.setAttribute("todos", todos);

    //Redireciona requisição para o JSP.
    request.
      getRequestDispatcher("/todo-jdbc/todo-view.jsp").
      forward(request, response);
  }
}
