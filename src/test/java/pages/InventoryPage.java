package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.components.HeaderComponent;
import pages.components.ProductCardComponent;
import utils.SortOptions;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {
    private final HeaderComponent headerComponent;

    private final By productItems = By.cssSelector(".inventory_item");
    private final By sortDropdown = By.cssSelector(".product_sort_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public List<ProductCardComponent> getAllProducts() {
        List<WebElement> elements = driver.findElements(productItems);
        List<ProductCardComponent> products = new ArrayList<>();

        for (WebElement container : elements) {
            products.add(new ProductCardComponent(driver, container));
        }

        return products;
    }

    public ProductCardComponent getProductById(int id) {
        for (ProductCardComponent product : getAllProducts()) {
            if (product.getProductId() == id) {
                return product;
            }
        }

        throw new RuntimeException("Product does not exist: " + id);
    }

    public void selectSortOption(SortOptions option) {
        WebElement dropdownElement = driver.findElement(sortDropdown);
        Select select = new Select(dropdownElement);
        select.selectByValue(option.getValue());
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }
}