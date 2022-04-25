/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lojainformatica.DAO;

import com.mycompany.lojainformatica.Computador;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.mata
 */
public class CadastroDAO {

    public static String url = "jdbc:mysql://localhost:3306/lojainformatica" + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    public static String login = "root";
    public static String senha = "";

    public static boolean Salvar(String marca, String hd, String processador) throws Exception {

        boolean retorno = false;
        Connection conexao = null;

        try {
            //Passo 1 - informar qual driver vamos utilizar
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - utilizar o DriverManager para criar um objeto de conexão
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Criar um objeto Statement
            //Statement instrucaoSQL = conexao.createStatement();
            //Passo 4 - Executar comando SQL 
            //String query = "INSERT INTO computador (marca, HD, Processador) values ('%s','%s','%s')";
            //boolean linhasAfetadas = instrucaoSQL.execute(String.format(query, marca, hd, processador));
            PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO computador (marca, hd, processador) VALUES (?,?,?) ");

            comandoSQL.setString(1, marca);
            comandoSQL.setString(2, hd);
            comandoSQL.setString(3, processador);

            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
                throw new Exception("Não foi possivel cadastrar o produto");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
            retorno = false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        }finally {

            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch(Exception ex){
                System.out.println("não foi possivel fechar conexão com banco de dados");
            }

        }
        return retorno;


    }

    public static ArrayList<Computador> listarComputador() {

        Connection conexao = null;
        ArrayList<Computador> listaRetorno = new ArrayList<Computador>();
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            PreparedStatement sql = conexao.prepareStatement("SELECT * FROM computador");
            rs = sql.executeQuery();

            while (rs.next()) {

                //instacio um objeto da classe da Classe que eu criei ('Computador' no caso) e leio um ResultSet
                Computador objComputador = new Computador();
                
                objComputador.setIdComputador(rs.getInt("idComputador"));
                objComputador.setMarca(rs.getString("marca"))
                objComputador.setHD(rs.getString("hd"));
                objComputador.setProcessador(rs.getString("processador"));

                listaRetorno.add(objComputador);

            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar computadores");
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch(Exception ex){
                System.out.println("não foi possivel fechar conexão com banco de dados");
            }

        }
        return listaRetorno;
    }
    
    public static boolean Excluir(int idComputador) {

        Connection conexao = null;
        boolean retorno = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            PreparedStatement sql = conexao.prepareStatement("DELETE FROM computador WHERE idComputador=?");
            sql.setInt(1, idComputador);

            int linhasAfetadas = sql.executeUpdate();
            
            if(linhasAfetadas>0){
               retorno = true;   
            } else {
               retorno = false; 
            }
            
            
        } catch (Exception ex) {
            System.out.println("Erro ao excluir dados");
        } finally {

            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch(Exception ex){
                System.out.println("Não foi possivel fechar conexão com banco de dados");
            }

        }
        return retorno;
    }
}
