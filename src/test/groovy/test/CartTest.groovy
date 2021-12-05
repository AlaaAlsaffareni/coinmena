package test

import org.testng.Assert
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class CartTest extends BaseClass {

    Elements cartPage = new Elements()

    @BeforeClass
    void navigate() throws InterruptedException {
        setUp()
        Thread.sleep(2000)
    }

    @Test(priority = 101, dataProvider = 'items')
    void checkAddItemToCartTest(String itemName) {
        cartPage.clickOnAvailableItem(itemName)
        cartPage.addToCart()
        cartPage.clickOkOnAlert()
        cartPage.clickCartLink()
        Assert.assertTrue(cartPage.isItemInCart(itemName), 'item not available in cart after add it')
    }

    @Test(priority = 102, dataProvider = 'items')
    void checkRemoveItemFromCartTest(String itemName) {
//        cartPage.clickCartLink()
        cartPage.deleteItem()
        Assert.assertFalse(cartPage.isItemInCart(itemName), 'User Can not delete items from cart !!')
    }

    @DataProvider
    Object[][] items() {
        [
                ['Samsung galaxy s6'],
        ]
    }

    @AfterClass(alwaysRun = true)
    void close() {
        tearDown()
    }
}
