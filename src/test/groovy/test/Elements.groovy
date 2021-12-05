package test

import org.apache.commons.lang3.RandomStringUtils
import org.openqa.selenium.By

class Elements {

    String userName = "//a[@id='nameofuser']"
    String logoutLink = "//a[@id='logout2']"

    String userNameField = "//input[@id='sign-username']"
    String passwordField = "//input[@id='sign-password']"
    String signUpButton = "//button[@onclick='register()']"
    String closeButton = "//div[@id='signInModal']/div/div/div[3]/button[1]"

    String loginUserNameField = "//input[@id='loginusername']"
    String loginPasswordField = "//input[@id='loginpassword']"
    String loginButton = "//button[@onclick='logIn()']"
    String loginCloseButton = "//div[@id='logInModal']/div/div/div[3]/button[1]"

    String placeOrderButton = "//button[@class='btn btn-success']"
    String getCartItem = "//table/tbody/tr/td[text()='"
    String deleteLink  = "//*[@id='tbodyid']/tr[1]/td[4]/a"


    String nameField = "//input[@id='name']"
    String cardField = "//input[@id='card']"
    String purchaseButton = "//button[@onclick='purchaseOrder()']"


    String recieptInfo = "//p[@class='lead text-muted ']"
    String recieptTitle = "//h2[text()='Thank you for your purchase!']"
    String okButton = "//button[contains(@class,'confirm')]"

    String carouselSlide = "//div[@class='carousel slide']"
    String signUpLink = "//a[@id='signin2']"
    String loginLink = "//a[@id='login2']"
    String cartLink = "//a[@id='cartur']"
    String firstItems = "//div[@id='tbodyid']/div[1]/div/div/h4/a[text()='"


    String productImage = "//div[@class='item active']/img"
    String productName = "//h2[@class='name']"
    String productPrice = "//h3[@class='price-container']"
    String addToCartButton = "//a[@onclick='addToCart(1)']"

    void clickSignupButton() {
        BaseClass.getWebDriver().findElement(By.xpath(signUpLink)).click()
    }

    void clickSigninButton() {
        BaseClass.getWebDriver().findElement(By.xpath(loginLink)).click()
    }

    void clickLogoutButton() {
        BaseClass.getWebDriver().findElement(By.xpath(logoutLink)).click()
    }

    void clickOkOnAlert() {
        BaseClass.getWebDriver().switchTo().alert().accept()
    }

    void clickClose() {
        BaseClass.getWebDriver().findElement(By.xpath(loginCloseButton)).click()
    }

    void clickPlaceOrder() {
        Thread.sleep(2000)
        BaseClass.getWebDriver().findElement(By.xpath(placeOrderButton)).click()
    }

    void signup(String name, String password) throws InterruptedException {
        Thread.sleep(2000)
        BaseClass.getWebDriver().findElement(By.xpath(userNameField)).sendKeys(name)
        BaseClass.getWebDriver().findElement(By.xpath(passwordField)).sendKeys(password)
        BaseClass.getWebDriver().findElement(By.xpath(signUpButton)).click()
    }

    void signin(String name, String password) throws InterruptedException {
        Thread.sleep(2000)
        BaseClass.getWebDriver().findElement(By.xpath(loginUserNameField)).sendKeys(name)
        BaseClass.getWebDriver().findElement(By.xpath(loginPasswordField)).sendKeys(password)
        BaseClass.getWebDriver().findElement(By.xpath(loginButton)).click()
    }

    void clickOnAvailableItem(String itemName) throws InterruptedException {
        BaseClass.getWebDriver().findElement(By.xpath(firstItems+itemName+"']")).click()
        Thread.sleep(2000)
    }

    void addToCart() throws InterruptedException {
        BaseClass.getWebDriver().findElement(By.xpath(addToCartButton)).click()
        Thread.sleep(2000)
    }

    void deleteItem() throws InterruptedException {
        BaseClass.getWebDriver().findElement(By.xpath(deleteLink)).click()
        Thread.sleep(2000)
    }

    void clickCartLink() throws InterruptedException {
        BaseClass.getWebDriver().findElement(By.xpath(cartLink)).click()
        Thread.sleep(2000)
    }

    void placeOrder() throws InterruptedException {
        int randomStringLength = 10
        String charset = (('a'..'z') + ('A'..'Z')).join()
        String numset = (('0'..'9')).join()
        String randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())
        String randomNumeric = RandomStringUtils.random(randomStringLength, numset.toCharArray())
        Thread.sleep(2000)
        BaseClass.getWebDriver().findElement(By.xpath(nameField)).sendKeys(randomString)
        BaseClass.getWebDriver().findElement(By.xpath(cardField)).sendKeys(randomNumeric)
        BaseClass.getWebDriver().findElement(By.xpath(purchaseButton)).click()
        Thread.sleep(2000)
    }

    void clickPurchaseButton() throws InterruptedException {
        Thread.sleep(2000)
        BaseClass.getWebDriver().findElement(By.xpath(purchaseButton)).click()
    }

    boolean isReceiptPageLoaded() throws InterruptedException {
        Thread.sleep(2000)
        return BaseClass.getWebDriver().findElement(By.xpath(recieptInfo)).isDisplayed() &&
                BaseClass.getWebDriver().findElement(By.xpath(recieptTitle)).isDisplayed() &&
                BaseClass.getWebDriver().findElement(By.xpath(okButton)).isDisplayed()
    }

    boolean isSuccessMessageDisplayed() {
        Thread.sleep(2000)
        return BaseClass.getWebDriver().switchTo().alert().getText().contains("Sign up successful.")
    }

    boolean isWelcomePageDisplayed() {
        Thread.sleep(2000)
        return BaseClass.getWebDriver().findElement(By.xpath(logoutLink)).isDisplayed() &&
                BaseClass.getWebDriver().findElement(By.xpath(userName)).isDisplayed()
    }

    boolean isItemInCart(String itemName) {
        Thread.sleep(2000)
        return BaseClass.getWebDriver().findElement(By.xpath(getCartItem+itemName+"']")).isDisplayed()
    }

    String getAlertMessage() {
        Thread.sleep(2000)
        return BaseClass.getWebDriver().switchTo().alert().getText()
    }

}
