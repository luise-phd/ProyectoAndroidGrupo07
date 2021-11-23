package com.ingluise.ProyectoAndroidGrupo07;

public class Horas_extras {
    private String cod;
    private String mes;
    private String emp;
    private int can_he;

    //Métodos constructores
    public Horas_extras() {
        this.cod = "";
        this.mes = "";
        this.emp = "";
        this.can_he = 0;
    }

    public Horas_extras(String cod, String mes, String emp, int he) {
        this.cod = cod;
        this.mes = mes;
        this.emp = emp;
        this.can_he = he;
    }

    //Métodos de acceso
    public String getCod() {
        return cod;
    }

    public String getMes() {
        return mes;
    }

    public String getEmp() {
        return emp;
    }

    public int getCan_he() {
        return can_he;
    }

    //Métodos modificadores
    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public void setCan_he(int can_he) {
        this.can_he = can_he;
    }
}
