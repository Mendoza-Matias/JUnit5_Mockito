package org.example.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String name;
    private List<Cuenta> cuentas ;

    public Banco(){
        cuentas = new ArrayList<>();
    }

    public Banco(String name){
        this.name = name;
    }
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void agregarCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
        cuenta.setBanco(this);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void transferir(Cuenta origen , Cuenta destino , float monto){
       origen.debito(monto);
       destino.credito(monto);
    }


}
