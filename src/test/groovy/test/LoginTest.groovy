package test

import org.apache.commons.lang3.RandomStringUtils
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class LoginTest extends BaseClass {

    Elements signInPage = new Elements()

    @BeforeMethod
    void navigate() throws InterruptedException {
        setUp()
        Thread.sleep(2000)
    }

    @Test(priority = 101, dataProvider = 'userData')
    void checkLoginPositiveTest(String userName, String password) {
        signInPage.clickSigninButton()
        signInPage.signin(userName, password)

        boolean isWelcomePageLoaded = signInPage.isWelcomePageDisplayed()
        signInPage.clickLogoutButton()
        Assert.assertTrue(isWelcomePageLoaded, 'User can not login !!' )
    }

    @Test(priority = 102, dataProvider = 'userInvalidData')
    void checkLoginNegativeTest(String userName, String password, String message) {
        signInPage.clickSigninButton()
        signInPage.signin(userName, password)

        boolean isValidationMessageDisplayed = signInPage.getAlertMessage().equals(message)
        signInPage.clickOkOnAlert()
        signInPage.clickClose()
        Assert.assertTrue(isValidationMessageDisplayed, 'User can login with wrong data!!')
    }

    @AfterMethod(alwaysRun = true)
    void close() {
        tearDown()
    }

    @DataProvider
    Object[][] userData() {
        [

                [
                        'alaa',
                        '123456',
                ],
        ]
    }

    @DataProvider
    Object[][] userInvalidData() {
        int randomStringLength = 10
        String charset = (('a'..'z') + ('A'..'Z')).join()
        String randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())
        [

                [
                        'alaa',
                        '',
                        'Please fill out Username and Password.',
                ],

                [
                        '',
                        '123123',
                        'Please fill out Username and Password.',
                ],

                [
                        'alaa',
                        '123',
                        'Wrong password.',
                ],

                [
                        randomString,
                        '123456',
                        'User does not exist.',
                ],

        ]
    }
}
