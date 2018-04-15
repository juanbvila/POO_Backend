/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import db.Db;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utilidades.Respuesta;
import utilidades.Util;

/**
 *
 * @author JuanLH
 */
public class Detalle_Salida {
    int id_salida,referencia;
    float cantidad,costo;

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    public String insert(Detalle_Salida info){
        Db dbase = Util.getConection();
        Respuesta r = new Respuesta();
        String sql = "INSERT INTO \"Detalle_Salida\"(\n" +
        "            id_salida, referencia, cantidad, costo)\n" +
        "    VALUES (?, ?, ?, ?);";
        try{
            PreparedStatement p = Db.conexion.prepareStatement(sql);
            p.setInt(1,info.getId_salida());
            p.setInt(2, info.getReferencia());
            p.setFloat(3, info.getCantidad());
            p.setFloat(4, info.getCosto());
            p.execute();
            dbase.CerrarConexion();
            r.setId(1);
            r.setMensaje("Se inserto correctamiente");
            return Respuesta.ToJson(r);
        }
        catch(SQLException e){
            r.setId(-1);
            r.setMensaje("Error en la base de datos");
            System.err.println(e.getMessage());
            return Respuesta.ToJson(r);
        }
    }
}
