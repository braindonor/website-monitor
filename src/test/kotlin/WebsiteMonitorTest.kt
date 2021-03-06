
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WebsiteMonitorTest {

    private companion object {
        const val TEST_CONFIGURATION_FILE_PATH = "src/test/resources/website-monitor-config.json"
    }

    @Test
    fun testReadJsonConfigurationFromFile() {
        val json = ConfigurationUtil().readJsonConfigurationFile(TEST_CONFIGURATION_FILE_PATH)
        assert(json.isNotEmpty())
    }

    @Test
    fun testFailureToReadJsonConfigurationFromFile() {
        val json = ConfigurationUtil().readJsonConfigurationFile("file_not_exist.json")
        assert(json.isEmpty())
    }

    @Test
    fun testGetListOfWebsites() {
        assert(ConfigurationUtil().getWebsites(TEST_CONFIGURATION_FILE_PATH).count() == 3)
        for (website in ConfigurationUtil().getWebsites(TEST_CONFIGURATION_FILE_PATH)) {
            assert(website.url.isNotEmpty())
            assert(website.content.isNotEmpty())
        }
    }

    @Test
    fun testGetCheckPeriodSeconds() {
        assert(ConfigurationUtil().getCheckPeriodSecondsInMillis(TEST_CONFIGURATION_FILE_PATH) > 0)
    }

    @Test
    fun testGetWebResponseReturnsSomeText() {
        val responseData: WebRequest.ResponseData = WebRequest().getResponse("https://www.google.com")
        assert(responseData.response.text.isNotEmpty())
    }

    @Test
    fun testValidateSuccessStatusCode() {
        val responseData: WebRequest.ResponseData = WebRequest().getResponse("https://www.google.com")
        assert(WebRequest().validateStatusCode(responseData))
    }

    @Test
    fun testValidateResponseMatchesRegex() {
        val responseData: WebRequest.ResponseData = WebRequest().getResponse("https://www.google.com")
        assertTrue(WebRequest().validateResponseText(responseData.response, "<title>Google</title>"))
    }

    @Test
    fun testValidateResponseDoesNotMatchRegex() {
        val responseData: WebRequest.ResponseData = WebRequest().getResponse("https://www.google.com")
        assertFalse(WebRequest().validateResponseText(responseData.response, "<title>Googly</title>"))
    }
}