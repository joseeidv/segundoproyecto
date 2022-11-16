package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Reporte.EstadoPrueba;
import utils.Reporte.PdfQaNovaReports;

public class Validaciones {

    public static void validarObjeto(WebElement webElement, String descripcionElemento){
        WebDriverWait webDriverWait = new WebDriverWait(DriverContext.getDriver(), Integer.valueOf(ReadProperties.readFromConfig("Propiedades.properties").getProperty("segundosEspera")));
        String identificador;
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            identificador = webElement.getAttribute("xpath");
            if (identificador == null){
                identificador = webElement.getAttribute("id");
            }
            System.out.println("Se despliega correctamente el elemento "+ descripcionElemento +" ,identificador " + identificador);
            PdfQaNovaReports.addWebReportImage("Validacion elemento " + descripcionElemento, "Se despliega correctamente el elemento " +descripcionElemento+ ", identificador: " +identificador, EstadoPrueba.PASSED, false);
        } catch (Exception e) {
            System.out.println("No se despliega elemento " + descripcionElemento);
            PdfQaNovaReports.addWebReportImage("Validacion elemento " + descripcionElemento, "No se despliega elemento " + descripcionElemento, EstadoPrueba.FAILED, true);
        }
    }

    public static void validarTexto(WebElement webElement, String texto) {
        String textoWeb = webElement.getText();
        if (textoWeb.equals(texto)) {
            PdfQaNovaReports.addReport("Comparación texto", "El texto '"+texto+"', se encuentra correctamente en la pagina web", EstadoPrueba.PASSED, false);
        } else {
            String letraTexto, letraTextoWeb, diferencia = "";
            for (int x = 0; x < texto.length(); x++) {
                letraTexto = texto.substring(x, x+1);
                letraTextoWeb = textoWeb.substring(x, x+1);
                if (!letraTexto.equals(letraTextoWeb)) {
                    System.out.println("Diferencia de textos en el caracter número: "+(x+1)+", en el texto web despliega: '"+letraTextoWeb+"', se esperaba: '"+letraTexto+"' .");
                    diferencia = diferencia + "-En el caracter "+(x+1)+" en el texto web despliega: '"+letraTextoWeb+"', se esperaba: '"+letraTexto+"'."+ "\n";
                }
            }
            PdfQaNovaReports.addReport("Comparación texto", "El texto '"+texto+"', es distinto al desplegado en la pagina web, el cual es '"+textoWeb+"' \n*Las diferencias son las siguientes: \n"+diferencia, EstadoPrueba.FAILED, false);
        }
    }
}
