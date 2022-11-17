package testClasses;

import pages.*;
import utils.Reporte.EstadoPrueba;
import utils.Reporte.PdfQaNovaReports;

import java.io.IOException;
import java.text.ParseException;

public class Logeo {

    private Login login;
    private CargaInformacion cargaInformacion;
    private MatrizInformacion matrizInformacion;
    private DescargaArchivos descargaArchivos;

    private CargarArchivos cargarArchivos;

    public Logeo(){

    }

    public void CasoLogin1(String usuario, String clave) throws ParseException, IOException {
        login = new Login();
        cargaInformacion = new CargaInformacion();
        matrizInformacion = new MatrizInformacion();
        descargaArchivos = new DescargaArchivos();
        cargarArchivos = new CargarArchivos();
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
        //matrizInformacion.validarDespliegue();
        //matrizInformacion.validarTextoTitulo("Matriz de información");
        //matrizInformacion.escribirFiltro("prueba");
        //matrizInformacion.clickBtnFiltrar();
        //matrizInformacion.recuperarDatosFiltrados();
        cargaInformacion.clickBtnEnviar();
        //descargaArchivos.ingresarDescargarArchivos();
        //descargaArchivos.descargarPorBoton();
        //descargaArchivos.descargarPorLink();
        //descargaArchivos.descargarPorImagen();
        cargarArchivos.ingresarCargarArchivos();
        cargarArchivos.validarDespliegue();
        cargarArchivos.validarTextoTitulo();
        cargarArchivos.cargarArchivos();
        cargarArchivos.clickBtnEnviar();
        matrizInformacion.validarDespliegue();
        PdfQaNovaReports.addReport("Archivo de Carga", "Archivo cargado correctamente", EstadoPrueba.PASSED,false);
    }
}
