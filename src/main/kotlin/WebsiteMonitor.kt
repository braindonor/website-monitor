import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WebsiteMonitor {

    companion object {
        private val logger = Logger()

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {

            val filePath = getFilePath(args)

            if (ConfigurationUtil().readJsonConfigurationFile(filePath).isNotEmpty()) {
                logger.log(
                    "INFO: WebsiteMonitor started: executing with json configuration file: '${filePath}'"
                )

                val delayPeriod = ConfigurationUtil().getCheckPeriodSecondsInMillis(filePath)

                while (true) {
                    for (website in ConfigurationUtil().getWebsites(filePath)) {
                        launch { monitorWebsite(website.url, website.content) }
                    }
                    delay(delayPeriod)
                }
            }
        }

        private fun monitorWebsite(website: String, content: String) {

            val responseData: WebRequest.ResponseData

            try {
                responseData = WebRequest().getResponse(website)
                WebRequest().validateStatusCode(responseData)
                WebRequest().validateResponseText(responseData.response, content)
            } catch (exception: Exception) {
                Logger().log(
                    "ERROR: $website connection exception: ${exception.message}"
                )
            }
        }

        private fun getFilePath(args: Array<String>): String {

            return if (args.isNotEmpty()) {
                args[0]
            } else {
                ConfigurationUtil.CONFIGURATION_FILE_PATH
            }
        }
    }
}