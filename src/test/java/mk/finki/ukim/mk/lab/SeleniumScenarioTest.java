package mk.finki.ukim.mk.lab;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.Assert;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    private HtmlUnitDriver driver;

    @BeforeEach
    public void setup() {
        this.driver = new HtmlUnitDriver(true);

    }



    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonsPage productsPage = BalloonsPage.to(this.driver);
        productsPage.assertElemts( 0, 0, 0);
    }

}
