/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.lojainformatica;

/**
 *
 * @author matheus.mata
 */
public class Computador {

public static String marca = "EduardoRamos";
public int idComputador;
public String HD;
public String Processador;

public Computador() {}

    public static String getMarca() {
        return marca;
    }

    public static void setMarca(String marca) {
        Computador.marca = marca;
    }
    
    public int getIdComputador() {
        return idComputador;
    }
    
    public void setIdComputador(int idComputador){
        this.idComputador = idComputador;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getProcessador() {
        return Processador;
    }

    public void setProcessador(String Processador) {
        this.Processador = Processador;
    }


}
