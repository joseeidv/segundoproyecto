package testClasses;

import pages.CargaInformacion;
import pages.Login;

import java.text.ParseException;

public class Logeo {

    private Login login;
    private CargaInformacion cargaInformacion;

    public Logeo(){

    }

    public void CasoLogin1(String usuario, String clave) throws ParseException {
        login = new Login();
        cargaInformacion = new CargaInformacion();
        login.validarTextoUsuario("Nombre del usuario:");
        login.ingresarUsuario(usuario);
        login.ingresarClave(clave);
        login.clickBtnIngresar();
        cargaInformacion.recuperarTitulo();
        cargaInformacion.rellenarCampoTexto("Prueba");
        cargaInformacion.rellenarCampoMail("prueba@gmail.com");
        cargaInformacion.rellenarCampoAreaTexto("prueba texto largo");
        cargaInformacion.seleccionarFechaCalendario("2022-03-19");
        cargaInformacion.rellenarLista("valor 3");
        cargaInformacion.seleccionMultiple("1,3");
        cargaInformacion.seleccionRadioButton(2);
        cargaInformacion.clickBtnEnviar();
    }
}
