package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CargaInformacion {

    @FindBy(xpath = "//*[@id=\"imPgTitle\"]")
    private WebElement titulo;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_2\"]")
    private WebElement campoTexto;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_3\"]")
    private WebElement campoCorreo;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_4\"]")
    private WebElement campoTextArea;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_5\"]")
    private WebElement campoFecha;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_6\"]")
    private WebElement campoLista;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_7_0\"]")
    private WebElement chckbxSeleccionMultiple1;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_7_1\"]")
    private WebElement chckbxSeleccionMultiple2;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_7_2\"]")
    private WebElement chckbxSeleccionMultiple3;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_8_0\"]")
    private WebElement rdbtnCombo1;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_8_1\"]")
    private WebElement rdbtnCombo2;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_8_2\"]")
    private WebElement rdbtnCombo3;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_submit\"]")
    private WebElement btnEnviar;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_buttonswrap\"]/input[2]")
    private WebElement btnReset;

    @FindBy(xpath = "//*[@id=\"imObjectForm_1_5_icon\"]")
    private WebElement iconoCalendario;

    @FindBy(xpath = "//*[@id=\"imDPleft\"]")
    private WebElement btnRetrocederMes;

    @FindBy(xpath = "//*[@id=\"imDPright\"]")
    private WebElement btnAvanzarMes;

    WebDriverWait webDriverWait;

    public CargaInformacion(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public String recuperarTitulo(){
        webDriverWait.until(ExpectedConditions.visibilityOf(titulo));
        String texto = titulo.getText();
        return texto;
    }

    public void rellenarCampoTexto(String texto){
        campoTexto.sendKeys(texto);
    }

    public void rellenarCampoMail(String mail){
        campoCorreo.sendKeys(mail);
    }

    public void rellenarCampoAreaTexto(String areaTexto){
        campoTextArea.sendKeys(areaTexto);
    }

    public void rellenarFecha(String fecha){
        campoFecha.sendKeys(fecha);
    }

    public void rellenarLista(String valor){
        Select select = new Select(campoLista);
        select.selectByVisibleText(valor);
    }

    public void seleccionMultiple(int indicador){
        switch (indicador){
            case 1:
                chckbxSeleccionMultiple1.click();
                break;
            case 2:
                chckbxSeleccionMultiple2.click();
                break;
            case 3:
                chckbxSeleccionMultiple3.click();
                break;
            default:
                System.out.println("Valor no procesable");
        }
    }

    public void seleccionMultiple(String indicador){
        String[] indicadores = indicador.split(",");
        for (String nro:indicadores){
            int numero = Integer.parseInt(nro);
            switch (numero){
                case 1:
                    chckbxSeleccionMultiple1.click();
                    break;
                case 2:
                    chckbxSeleccionMultiple2.click();
                    break;
                case 3:
                    chckbxSeleccionMultiple3.click();
                    break;
                default:
                    System.out.println("Valor no procesable");
            }
        }
    }

    public void seleccionRadioButton(int indicador){
        switch (indicador){
            case 1:
                rdbtnCombo1.click();
                break;
            case 2:
                rdbtnCombo2.click();
                break;
            case 3:
                rdbtnCombo3.click();
                break;
            default:
                System.out.println("Valor no procesable");
        }
    }

    public void clickBtnEnviar(){
        btnEnviar.click();
    }

    public void clickBtnReset(){
        btnReset.click();
    }
}
