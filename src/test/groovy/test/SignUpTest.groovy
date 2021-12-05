package test

import org.apache.commons.lang3.RandomStringUtils
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test


class SignUpTest extends BaseClass {

    Elements signUpPage = new Elements()

    @BeforeMethod
    void navigate() throws InterruptedException {
        setUp()
        Thread.sleep(2000)
    }

    @Test(priority = 101, dataProvider = 'generateValidUserDataForSignUp')
    void signUpTest(String userName, String password) throws InterruptedException {
        signUpPage.clickSignupButton()
        signUpPage.signup(userName, password)
        boolean isSuccessMessageDisplayed = signUpPage.isSuccessMessageDisplayed()
        Assert.assertTrue(isSuccessMessageDisplayed, "'User can not sign up to system!!")

    }

    @Test(priority = 102, dataProvider = 'generateInvalidUserDataForSignUp')
    void checkSignUpNegativeTest(String userName, String password, String message) {
        signUpPage.clickSignupButton()
        signUpPage.signup(userName, password)

        boolean isValidationMessageDisplayed = signUpPage.getAlertMessage().equals(message)
        Assert.assertTrue(isValidationMessageDisplayed, 'User can sign up with wrong data !!')
    }

    @DataProvider
    Object[][] generateValidUserDataForSignUp() {
        int randomStringLength = 10
        String charset = (('a'..'z') + ('A'..'Z')).join()
        String numset = (('0'..'9')).join()
        String randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())
        String randomNumeric = RandomStringUtils.random(randomStringLength, numset.toCharArray())

        [
                [randomString, randomNumeric],
        ]
    }

    @DataProvider
    Object[][] generateInvalidUserDataForSignUp() {
        int randomStringLength = 10
        String charset = (('a'..'z') + ('A'..'Z')).join()
        String numset = (('0'..'9')).join()
        String randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())
        String randomNumeric = RandomStringUtils.random(randomStringLength, numset.toCharArray())
        [
                [
                        randomString,
                        '',
                        'Please fill out Username and Password.'
                ],

                [
                        '',
                        randomNumeric,
                        'Please fill out Username and Password.'
                ],

                [
                        'alaa',
                        '123456',
                        'This user already exist.'
                ],
        ]
    }

    @AfterMethod(alwaysRun = true)
    void close() {
        tearDown()
    }

}
