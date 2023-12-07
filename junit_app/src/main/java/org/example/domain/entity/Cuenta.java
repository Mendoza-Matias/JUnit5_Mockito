package org.example.domain.entity;

import org.example.exceptions.DineroInsuficienteException;

public class Cuenta {

    private String persona;

    private float saldo;

    private Banco banco;

    public Cuenta() {
    }

    public Cuenta(String persona) {
        this.persona = persona;
    }

    public Cuenta(float saldo){
        this.saldo = saldo;
    }

    public Cuenta(String persona, float saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }


    public void debito (float monto){

       float nuevoSaldo =  this.saldo = saldo - monto;
        /*Nueva
        instancia con el
        cambio*/

        if(nuevoSaldo < 0){
            throw new DineroInsuficienteException("Dinero insuficiente");
        }

        this.saldo = nuevoSaldo;
    }

    public void credito (float monto){
        this.saldo = saldo + monto;
    }


    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return  false;
        }

        Cuenta c = (Cuenta) obj;

        if(this.persona == null || this.saldo == 0){
            return false;
        }

        return this.persona.equals(c.getPersona())
                && this.saldo == (c.getSaldo());
    }

}
