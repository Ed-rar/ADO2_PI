/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lojacarro.DAO;

import com.mycompany.lojacarro.Carro;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo.ramos
 */
public class CadastroDAO {

    public static String url = "jdbc:mysql://localhost:3306/lojacarro" + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    public static String login = "root";
    public static String senha = "";

    public static boolean salvar(String marca, String modelo, String motorizacao) throws Exception {

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
            //String query = "INSERT INTO computador (marca, modelo, motorizacao) values ('%s','%s','%s')";
            //boolean linhasAfetadas = instrucaoSQL.execute(String.format(query, marca, modelo, motorizacao));
            PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO carro (marca, modelo, motorizacao) VALUES (?,?,?) ");

            comandoSQL.setString(1, marca);
            comandoSQL.setString(2, modelo);
            comandoSQL.setString(3, motorizacao);

            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
                throw new Exception("Não foi possivel cadastrar o veículo");
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

    public static ArrayList<Carro> listarCarro() {

        Connection conexao = null;
        ArrayList<Carro> listaRetorno = new ArrayList<Carro>();
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            PreparedStatement sql = conexao.prepareStatement("SELECT * FROM carro");
            rs = sql.executeQuery();

            while (rs.next()) {

                //instacio um objeto da classe da Classe que eu criei ('Carro' no caso) e leio um ResultSet
                Carro objCarro = new Carro();
                
                objCarro.setIdCarro(rs.getInt("idCarro"));
                objCarro.setMarca(rs.getString("marca"));
                objCarro.setModelo(rs.getString("modelo"));
                objCarro.setMotorizacao(rs.getString("motorizacao"));

                listaRetorno.add(objCarro);

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
    
    public static ArrayList<Carro> listarCarroFiltro(String modelo) {

        Connection conexao = null;
        ArrayList<Carro> listaRetorno = new ArrayList<>();
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            PreparedStatement sql = conexao.prepareStatement("SELECT * FROM computador WHERE modelo LIKE '%"+modelo+"%'");
            //sql.setString(0,modelo);
            
            rs = sql.executeQuery();

            while (rs.next()) {

                //instacio um objeto da classe que eu criei ('Carro' no caso) e leio um ResultSet
                Carro objCarro = new Carro();
                
                objCarro.setIdCarro(rs.getInt("idCarro"));
                objCarro.setMarca(rs.getString("marca"));
                objCarro.setModelo(rs.getString("modelo"));
                objCarro.setMotorizacao(rs.getString("motorizacao"));

                listaRetorno.add(objCarro);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao listar veículos");
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
    
    public static boolean alterar(Carro objCarro){
        
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            PreparedStatement sql = conexao.prepareStatement("UPDATE carro SET modelo=?, motorizacao=? WHERE idCarro=?");
            sql.setString(1, objCarro.getModelo());
            sql.setString(2, objCarro.getMotorizacao());
            sql.setInt(3, objCarro.getIdCarro());

            int linhasAfetadas = sql.executeUpdate();
            
            if(linhasAfetadas>0){
               retorno = true;   
            } else {
               retorno = false; 
            }
            
            
        } catch (Exception ex) {
            System.out.println("Erro ao atualizar dados");
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
    
    public static boolean excluir(int idCarro) {

        Connection conexao = null;
        boolean retorno = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            PreparedStatement sql = conexao.prepareStatement("DELETE FROM carro WHERE idCarro=?");
            sql.setInt(1, idCarro);

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
/*
CREATE DATABASE lojacarro;

USE lojacarro;

CREATE TABLE carro(idCarro INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(40) NOT NULL,
    modelo VARCHAR(40) NOT NULL,
    motorizacao VARCHAR(40) NOT NULL);

SELECT * FROM carro;
*/
