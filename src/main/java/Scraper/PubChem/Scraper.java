package Scraper.PubChem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Scraper {

    String elementName;

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        Scanner query = new Scanner(System.in);
        System.out.print("Compound: ");
        String entry = query.nextLine();
        if (!Objects.equals(entry, "")) {
            driver.get("https://www.ncbi.nlm.nih.gov/pccompound/?term=" + entry);
            WebElement foundElement = driver.findElement(By.cssSelector("div.rprt:nth-child(1)"));
            if (foundElement != null) {
                List<WebElement> foundContent = foundElement.findElements(By.tagName("div"));
                for (WebElement content : foundContent) {
                    if (foundContent != null) {
                        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                        wait.until(d -> content.isDisplayed());
                        System.out.println(content.getText());
                    } else {
                        System.out.println("Not found Best match");
                    }
                }
            }
        }
    }
}
