package test

import org.testng.Assert;
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class PurchaseTest extends BaseClass {

    Elements purchasePage = new Elements()

    @BeforeMethod
    void navigate() throws InterruptedException {
        setUp()
        Thread.sleep(2000)
        purchasePage.clickOnAvailableItem('Samsung galaxy s6')
        purchasePage.addToCart()
        purchasePage.clickOkOnAlert()
        purchasePage.clickCartLink()
    }

    @Test(priority = 101)
    void checkPlaceOrderPositiveTest() {
        purchasePage.clickPlaceOrder()
        purchasePage.placeOrder()
        Assert.assertTrue(purchasePage.isReceiptPageLoaded(),
                'Receipt not appear after user complete purchase successfully')
    }

    @Test(priority = 102, dataProvider = 'messageError')
    void checkPlaceOrderNegativeTest(String message) {
        purchasePage.clickPlaceOrder()
        purchasePage.clickPurchaseButton()
        Assert.assertTrue(purchasePage.getAlertMessage().equals(message),
                'Message error not appear win user leave the form empty ')

    }

    @DataProvider
    Object[][] messageError() {
        [
                ['Please fill out Name and Creditcard.'],
        ]
    }

    @AfterMethod(alwaysRun = true)
    void close() {
        tearDown()
    }
}
