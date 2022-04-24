/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lojainformatica.DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.mata
 */
public class CadastroDAO {
    
    public static String url = "jdbc:mysql://localhost:3306/lojainformatica"+"?useTimezone=true&serverTimezone=UTC&useSSL=false";
    public static String login = "root";
    public static String senha = "";
    
    public static boolean Salvar(String marca, String hd, String processador) throws Exception{
        
            boolean retorno = false;
            Connection conexao = null;
            
        try {
            //Passo 1 - informar qual driver vamos utilizar
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - utilizar o DriverManager para criar um objeto de conexão
            conexao = DriverManager.getConnection(url,login,senha);
            
            //Passo 3 - Criar um objeto Statement
            //Statement instrucaoSQL = conexao.createStatement();
            
            //Passo 4 - Executar comando SQL 
            //String query = "INSERT INTO computador (marca, HD, Processador) values ('%s','%s','%s')";
            //boolean linhasAfetadas = instrucaoSQL.execute(String.format(query, marca, hd, processador));
            
            PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO computador (marca, HD, Processador) VALUES (?,?,?) ");
            
            comandoSQL.setString(1, marca);
            comandoSQL.setString(2, hd);
            comandoSQL.setString(3, processador);
            
            int linhasAfetadas = comandoSQL.executeUpdate();
            if(linhasAfetadas>0){
                retorno = true;
            }else{
                retorno = false;
                throw new Exception("Não foi possivel cadastrar o produto");
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
            retorno = false;
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
           retorno = false;
        }
        return retorno;
        //LEMBRAR DE POR UM CLOSE CONNECTION
        
    }
}
