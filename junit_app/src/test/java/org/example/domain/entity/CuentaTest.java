package org.example.domain.entity;

import org.example.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class CuentaTest {
    /*Primer test*/
    Cuenta cuenta;

    /*beforEach -> antes de cada ejecucion de un metodo , propio de cada uno
    * afterEach -> despues de que se haya ejecutado el metodo*/

    @BeforeEach
    void initMetodoTest(){
        this.cuenta = new Cuenta("Carlos",250.000F);
        System.out.println("Iniciando el metodo");
    }


    @Test
    @DisplayName("Test para probar el nombre de la cuenta") /*Nombre mas descriptivo*/
    void testNombreCuenta(){
        Cuenta cuenta = new Cuenta();
        cuenta.setPersona("Juan");

        String expect = "Juan";
        String real = cuenta.getPersona();

        /*Realizar con un lambda*/
        assertEquals(expect,real,()-> "El nombre de la cuenta no es el esperado");
        /*
        Afirmar si el valor
        esperado es igual
        al real*/
    }

    @Test
    void testSaldoCuenta(){
       assertEquals(
               250.000F,
               cuenta.getSaldo()
       );

    }

    /*TDD
    *
    * 1° Se escriben las pruebas
    * 2° implmentamos en el codigo
    * */
    @Test
    void testReferenciaDeCuenta(){

        Cuenta cuenta = new Cuenta("Juan",250.000F);
        Cuenta cuentaDos = new Cuenta ("Juan",250.000F);

        // assertNotEquals(cuentaDos,cuenta);
        assertEquals(cuentaDos,cuenta);
    }


    @Test
    void testCreditotoCuenta() {
        cuenta.credito(50);
        assertNotNull(cuenta.getSaldo());
        assertEquals(300.000F, cuenta.getSaldo());

    }

    /*
    * 1° Test
    * 2° Implements
    * */

    @Test
    void testDineroInsuficienteExceptionCuenta(){
        Cuenta cuenta = new Cuenta("Jose",200000F);

        /*Send exception and test*/
        Exception exception = assertThrows(DineroInsuficienteException.class, ()->{
            cuenta.debito(3000000F);
        });
        /*if message == message a error*/
        String real = exception.getMessage();
        String expect = "Dinero insuficiente";

        assertEquals(expect,real);
    }

    @Test
    void testTrasferirDineroCuentas() {

        Cuenta cuenta = new Cuenta("Carolina Mendoza",250000F);
        Cuenta cuenta1 = new Cuenta("Matias Mendoza",600000F);

        Banco santander = new Banco("Banco santander");

        santander.transferir(cuenta,cuenta1,100000F);


        assertEquals(700000F,cuenta1.getSaldo());
        assertEquals(150000,cuenta.getSaldo());
    }

    @Tag("param") /*Prueba con etiqueta - ejecuta solo los asociados , puedo tener mas de uno*/
    @Nested /*INDICA QUE ES UNA CLASE ANIDADA / INNERCLASS*/
    class SistemaOperativoTest{ //Clase anidada , dentro BeforeEach y AfterEach / no los otros
        @Test
        @EnabledOnOs(OS.WINDOWS)
        void testSoloWIndows(){
        }

        @Test
        @EnabledOnOs({OS.LINUX,OS.MAC})
        void testSoloLinuxMac(){

        }

    }
    @Test
    @EnabledOnJre(JRE.JAVA_19)
    void testSoloJava19(){

    }
   /*Assumptions*/

    @Test
    @Disabled /*Desabilitar prueba*/
    void testRelacionBancoCuentas() {
        boolean esDev = "dev".equals(System.getProperty("ENV"));
        Cuenta cuenta = new Cuenta("Carolina Mendoza",250000F);
        Cuenta cuentaUno = new Cuenta("Matias Mendoza",600000F);

        Banco santander = new Banco();

        santander.agregarCuenta(cuenta);

        santander.agregarCuenta(cuentaUno);
        santander.setName("Banco santander");

        Assumptions.assumeTrue(esDev); /*Se ejecuta si se cumple la suposicion
        assumeThat --> sirve para habilitar o desabilitar una parte de codigo ,
         primero va una condicion y despues una lambda
        */

        assertAll(()->{
                    assertEquals(2,santander.getCuentas().size());
                },
                ()->{
                    assertEquals("Banco santander",cuenta.getBanco().getName());
                },
                ()->{
                    assertEquals("Carolina Mendoza",santander.getCuentas().stream().filter(c ->c.getPersona().equals("Carolina Mendoza")).findFirst().get().getPersona());
                }
        );
    }

    @RepeatedTest(5) /*Repetir un test varias veces / es mejor utilizarlo en casos que tenemos valores que cambien,*/
    void testDebitoCuenta() {
        cuenta.debito(50F);
        assertNotNull(cuenta.getSaldo());
        assertEquals(200.000F, cuenta.getSaldo());

    }

    @Test
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS) /*Tiempo que tarda la prueba / si dura mas tira error*/
    void pruebaTimeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(6); /*Le doy una demora en la ejecucion , genera una carga pesada*/
    }

}