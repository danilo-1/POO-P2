/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.dblistener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author usuário
 */
public class Disciplinas {
    private int id;
    private String nome;
    private String dds;
    private String horario;
    private int qtAulas;
    private Double p1;
    private Double p2;
    
    public static ArrayList<Disciplinas> getDisciplinas() throws Exception {
        ArrayList<Disciplinas> list = new ArrayList<>();
        Connection con = dblistener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Disciplinas");
        
        while(rs.next()) {
            list.add(new Disciplinas(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("dds"),
                    rs.getString("horario"),
                    rs.getInt("qtAulas"),
                    rs.getDouble("p1"),
                    rs.getDouble("p2")
            ));
        }
        
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    public static void addDisciplinas(String nome, String dds, String horario, int qtAulas) throws Exception {
        Connection con = dblistener.getConnection();
        String sql = "INSERT INTO Disciplinas(nome, dds, horario, qtAulas)"
                + "VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, dds);
        stmt.setString(3, horario);
        stmt.setLong(4, qtAulas);
        stmt.execute();
        stmt.close();
        con.close();
    }
     public static void removeDisciplinas(int id) throws Exception {
        Connection con = dblistener.getConnection();
        String sql = "DELETE FROM Disciplinas WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        con.close();
    }
     public static void updateNotas(int id, Double p1, Double p2) throws Exception {
        Connection con = dblistener.getConnection();
        String sql = "UPDATE Disciplinas SET p1 = ?, p2 = ? WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setDouble(1, p1);
        stmt.setDouble(2, p2);
        stmt.setInt(3, id);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Disciplinas(int id, String nome, String dds, String horario, int qtAulas, Double p1, Double p2) {
        this.id = id;
        this.nome = nome;
        this.dds = dds;
        this.horario = horario;
        this.qtAulas = qtAulas;
        this.p1 = p1;
        this.p2 = p2;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDds() {
        return dds;
    }

    public void setDds(String dds) {
        this.dds = dds;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getQtAulas() {
        return qtAulas;
    }

    public void setQtAulas(int qtAulas) {
        this.qtAulas = qtAulas;
    }

    public Double getP1() {
        return p1;
    }

    public void setP1(Double p1) {
        this.p1 = p1;
    }

    public Double getP2() {
        return p2;
    }

    public void setP2(Double p2) {
        this.p2 = p2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
