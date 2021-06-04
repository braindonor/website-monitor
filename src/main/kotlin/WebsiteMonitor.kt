import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WebsiteMonitor {

    companion object {
        private val logger = Logger()

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {

            val filePath: String = if (args.isNotEmpty()) {
                args[0]
            } else {
                ConfigurationUtil.CONFIGURATION_FILE_PATH
            }

            if (ConfigurationUtil().readJsonConfigurationFile(filePath).isNotEmpty()) {
                logger.log("INFO: WebsiteMonitor started: executing with json configuration file: '${filePath}'")

                val delayPeriod = ConfigurationUtil().getCheckPeriodSecondsInMillis(filePath)

                val job = launch {
                    while (true) {
                        monitorWebsites(filePath)
                        delay(delayPeriod)
                    }
                }
            }
        }

        private fun monitorWebsites(filePath: String) {
            var responseData: WebRequest.ResponseData

            for (website in ConfigurationUtil().getWebsites(filePath)) {
                try {
                    responseData = WebRequest().getResponse(website.url)
                    WebRequest().validateStatusCode(responseData)
                    WebRequest().validateResponseText(responseData.response, website.content)
                } catch (exception: Exception) {
                    Logger().log(
                        "ERROR: ${website.url} connection exception: ${exception.message}"
                    )
                }
            }
        }
    }
}