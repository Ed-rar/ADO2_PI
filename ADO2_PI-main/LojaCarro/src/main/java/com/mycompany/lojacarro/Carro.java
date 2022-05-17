/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.lojacarro;

/**
 *
 * @author eduardo.ramos
 */
public class Carro {

public static String marca = "EduardoRamos";
public int idCarro;
public String modelo;
public String motorizacao;

public Carro(int idCarro, String marca, String modelo, String motor){   
       setIdCarro(idCarro);
       setMarca(this.marca);
       setModelo(modelo);
       setMotorizacao(motor);
}

public Carro() {}

    public static String getMarca() {
        return marca;
    }

    public static void setMarca(String marca) {
        Carro.marca = marca;
    }
    
    public int getIdCarro() {
        return idCarro;
    }
    
    public void setIdCarro(int idCarro){
        this.idCarro = idCarro;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotorizacao() {
        return motorizacao;
    }

    public void setMotorizacao(String motorizacao) {
        this.motorizacao = motorizacao;
    }


}
