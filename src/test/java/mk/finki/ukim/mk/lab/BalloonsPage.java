package mk.finki.ukim.mk.lab;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Balloon;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonsPage extends  AbstractPage{
    public BalloonsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "tr[class=product]")
    private List<WebElement> productRows;


    @FindBy(css = ".delete-product")
    private List<WebElement> deleteButtons;


    @FindBy(className = "edit-product")
    private List<WebElement> editButtons;


    @FindBy(css = ".add-product-btn")
    private List<WebElement> addProductButton;

    public static BalloonsPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public void assertElemts( int deleteButtons, int editButtons, int addButtons) {

        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddProductButton().size());
    }

}
