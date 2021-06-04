
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WebsiteMonitor {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val logger = Logger()
            val delayPeriod = ConfigurationUtil().getCheckPeriodSeconds()
            var responseData: WebRequest.ResponseData

            logger.log("WebsiteMonitor started")

            if(args.isNotEmpty()) {
                logger.log("command line argument: ${args[0]}")
            }

            val job = launch {
                while ( true ) {
                    delay(delayPeriod)
                    for (website in ConfigurationUtil().getWebsites()) {
                        try {
                            responseData = WebRequest().getResponse(website.url)
                            WebRequest().validateStatusCode(responseData)
                            WebRequest().validateResponseText(responseData.response, website.content)
                        } catch (exception: Exception) {
                            Logger().log("ERROR: ${website.url} " +
                                    "connection exception: ${exception.message}")
                        }
                    }
                }
            }
        }
    }
}