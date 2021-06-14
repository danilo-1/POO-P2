<%-- 
    Document   : Disciplinas
    Created on : 14 de jun. de 2021, 08:37:54
    Author     : usuário
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="web.Disciplinas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(request.getParameter("criar") != null){
        String nome = request.getParameter("d.nome");
        String dds = request.getParameter("d.dds");
        String horario = request.getParameter("d.horario");
        int qtAulas = Integer.parseInt(request.getParameter("d.qtAulas"));
        Disciplinas.addDisciplinas(nome, dds, horario, qtAulas);
        response.sendRedirect(request.getRequestURI());
    } else if(request.getParameter("remove") != null){
        int id = Integer.parseInt(request.getParameter("remove.id"));
        Disciplinas.removeDisciplinas(id);
        response.sendRedirect(request.getRequestURI());
    }
    else if(request.getParameter("add") != null){
        int id = Integer.parseInt(request.getParameter("remove.id"));
        Double p1 = Double.parseDouble(request.getParameter("p1"));
        Double p2 = Double.parseDouble(request.getParameter("p2"));
        Disciplinas.updateNotas(id,p1,p2);
        response.sendRedirect(request.getRequestURI());
    }
    ArrayList<Disciplinas> disciplinas = Disciplinas.getDisciplinas();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
       
      
      
        <form method="post">
            Nome da matéria:<input type="text" name="d.nome" />
            dia da semana:<input type="text" name="d.dds" />
            horario:<input type="text" name="d.horario" />
            quantidades de aulas:<input type="number" name="d.qtAulas" />
            <input type="submit" name="criar" value="Criar" />
            
        </form>
        <hr/>
        <table border = 1>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>dia da semana</th>
                    <th>horario</th>
                    <th>quantidade de aulas</th>
                    
                    <th></th>
                </tr>
            </thead>
            <%for(Disciplinas disciplina: Disciplinas.getDisciplinas()){%>
            <tbody>
                <tr>
                    <td><%= disciplina.getNome()%></td>
                    <td><%= disciplina.getDds()%></td>
                    <td><%= disciplina.getHorario()%></td>
                    <td><%= disciplina.getQtAulas()%></td>
                    <td><form>
                            <input type="hidden" name="add.id" value="<%= disciplina.getId()%>" />
                            <input type="text" name="p1" value="<%= disciplina.getP1()%>">
                            <input type="text" name="p2" value="<%= disciplina.getP2()%>">
                            <button type="submit" name="add">adicionar</button>
                        </form></td>
                    <td><form>
                            <input type="hidden"  name="remove.id" value="<%= disciplina.getId()%>" />
                            <button type="submit" name="remove">Excluir</button>
                        </form></td>
                </tr>
            </tbody>
            <%}%>
        </table>
  
       
    </body>
</html>
