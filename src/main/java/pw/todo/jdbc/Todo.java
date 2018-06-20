package pw.todo.jdbc;

public class Todo {
  private String codigo;
  public String getCodigo() {
    return codigo;
  }
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }
	  
  private String todo;
  public String getTodo() {
    return todo;
  }
  public void setTodo(String todo) {
    this.todo = todo;
  }

  public boolean hasCodigo() {
    return codigo != null && !codigo.trim().equals("");
  }
}
