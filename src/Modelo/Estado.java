/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author GATEWAY1-
 */
public class Estado implements java.io.Serializable{
    private int id,act_server,repeticiones_salto,tiempo_espera_salto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAct_server() {
        return act_server;
    }

    public void setAct_server(int act_server) {
        this.act_server = act_server;
    }

    public int getRepeticiones_salto() {
        return repeticiones_salto;
    }

    public void setRepeticiones_salto(int repeticiones_salto) {
        this.repeticiones_salto = repeticiones_salto;
    }

    public int getTiempo_espera_salto() {
        return tiempo_espera_salto;
    }

    public void setTiempo_espera_salto(int tiempo_espera_salto) {
        this.tiempo_espera_salto = tiempo_espera_salto;
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSincronizacion() {
        return sincronizacion;
    }

    public void setSincronizacion(String sincronizacion) {
        this.sincronizacion = sincronizacion;
    }

    public String getSincronizacion_unit() {
        return sincronizacion_unit;
    }

    public void setSincronizacion_unit(String sincronizacion_unit) {
        this.sincronizacion_unit = sincronizacion_unit;
    }

    public String getAutocaptura() {
        return autocaptura;
    }

    public void setAutocaptura(String autocaptura) {
        this.autocaptura = autocaptura;
    }
    private String idname, version, sincronizacion,sincronizacion_unit,autocaptura;
}
