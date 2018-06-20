<!DOCTYPE html>
<%@page import="pw.todo.jdbc.Todo"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Todo</title>
<meta charset="utf-8">
<link rel="stylesheet" href="bootstrap.min.css">
</head>
<body style="margin-top: 15px">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-2 col-md-8">
        <ol class="breadcrumb">
          <li><a href="/">Menu</a></li>
          <li class="active">Todo</li>
        </ol>
        <div class="panel panel-default">
          <div class="panel-body">
            <form>
              <div class="form-group">
                <input
                  name="codigo"
                  value="${param.codigo}"
                  type="text"
                  placeholder="Codigo"
                  class="form-control">
              </div>
              <div class="form-group">
                <input
                  name="todo"
                  value="${param.todo}"
                  type="text"
                  placeholder="Todo"
                  class="form-control">
              </div>
              <button name="op" value="incluir" class="btn btn-default">Incluir</button>
              <button name="op" value="salvar" class="btn btn-default">Salvar</button>
              <button name="op" value="excluir" class="btn btn-default">Excluir</button>
            </form>
          </div>
        </div>
        <table class="table table-bordered table-striped">
          <tr>
          	<td>Codigo</td>
            <td>Todo</td>
            <td>#</td>
          </tr>
          <%
          List<Todo> todos = (List<Todo>) request.getAttribute("todos");
          for (Todo v:todos) {
          %>
            <tr>
           	  <td><a href="venda?codigo=<%=v.getCodigo()%>&todo=<%=v.getTodo()%>"><%=v.getCodigo()%></a></td>
              <td><%=v.getTodo()%></td>
              <td><a href="todo?op=excluir&codigo=<%=v.getCodigo()%>">Excluir</a></td>
            </tr>
          <%
          }
          %>
        </table>
      </div>
    </div>
  </div>
</body>
</html>