package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Google {

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")
    WebElement inputBuscar;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")
    WebElement btnBuscar;

    public Google(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void ingresarBusqueda(String busqueda){
        inputBuscar.sendKeys(busqueda);
    }

    public void clickBtnBuscar(){
        btnBuscar.click();
    }
}
