import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class ConfigurationUtil {

    private companion object {
        const val CONFIGURATION_FILE_PATH = "website-monitor-config.json"
    }

    fun readJsonConfigurationFile(filePath: String): String {
        val fileObject = File(filePath)
        return fileObject.readText()
    }

    fun getCheckPeriodSeconds(filePath: String = CONFIGURATION_FILE_PATH): Long {
        val json = readJsonConfigurationFile(filePath)
        val `object` = JSONTokener(json).nextValue() as JSONObject
        return `object`.getLong("checkPeriodSeconds") * 1000
    }

    fun getWebsites(filePath: String = CONFIGURATION_FILE_PATH): MutableList<Website> {
        val json = readJsonConfigurationFile(filePath)
        val `object` = JSONTokener(json).nextValue() as JSONObject
        val websitesArray = `object`.getJSONArray("websites")
        val websites: MutableList<Website> = mutableListOf()

        for (website in websitesArray) {
            website as JSONObject
            websites.add(Website(website.get("url").toString(), website.get("content").toString()))
        }
        return websites
    }

    data class Website(val url: String, val content: String)
}



