package gist
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import internal.GlobalVariable



class LoginSteps {
	/**
	 * The step definitions below match with Login.feature
	 */
	@Given("I navigate to github login page")
	def navigateToLoginPage() {
		WebUI.openBrowser('https://github.com/login')
		WebUI.maximizeWindow()
	}

	@When("I enter username (.*) and password (.*)")
	def enterCredentials(String username, String password) {
		//set valid credential on Profile
		username = GlobalVariable.username
		password = GlobalVariable.password
		
		WebUI.setText(findTestObject('Gist/Page_SignInGitHub/txt_userName'), username)

		WebUI.setEncryptedText(findTestObject('Gist/Page_SignInGitHub/txt_password'), password)
	}

	@And("I click Log in button")
	def clickLogin() {
		WebUI.click(findTestObject('Gist/Page_SignInGitHub/btn_Login'))
	}

	@Then("I should be able to login successfully")
	def verifyDashboard() {
		assert WebUI.getUrl() == 'https://github.com/' || 'https://github.com/sessions/verified-device'
	}
	
	@When("I enter invalid username (.*) and invalid password (.*)")
	def enterInvalidCredentials(String username, String password) {
		WebUI.setText(findTestObject('Gist/Page_SignInGitHub/txt_userName'), username)

		WebUI.setEncryptedText(findTestObject('Gist/Page_SignInGitHub/txt_password'), password)
	}

	@Then("I should not be able to login successfully")
	def verifyFailedLogin() {
		WebUI.verifyTextPresent('Incorrect username or password.', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.closeBrowser()
	}
}