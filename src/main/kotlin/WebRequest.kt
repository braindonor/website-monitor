import khttp.get
import khttp.responses.Response
import kotlin.system.measureTimeMillis

class WebRequest {

    data class ResponseData(val response: Response, val requestTimeMillis: Long)

    fun getResponse(url: String): ResponseData {
        val response: Response
        val elapsed = measureTimeMillis {
             response = get(url = url, timeout = 10.0)
        }
        return ResponseData(response, elapsed)
    }

    fun validateStatusCode(responseData: ResponseData): Boolean {
        return if (responseData.response.statusCode == 200) {
            Logger().log("SUCCESS: ${responseData.response.url} " +
                    "request time: ${responseData.requestTimeMillis} ms")
            true
        } else {
            Logger().log("ERROR: ${responseData.response.url} " +
                    "status code : ${responseData.response.statusCode} " +
                    "request time: ${responseData.requestTimeMillis} ms")
            false
        }
    }

    fun validateResponseText(response: Response, contentRegex: String): Boolean {
        return if (response.text.contains(contentRegex)) {
            Logger().log("SUCCESS: ${response.url} matches regex '$contentRegex'")
            true
        } else {
            Logger().log("ERROR: ${response.url} does not match regex '$contentRegex'")
            false
        }
    }
}