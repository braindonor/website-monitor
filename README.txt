    Website Monitor v1.0
    ====================

    Website Monitor is a simple utility to monitor a list of http urls at a time interval.
    Both the list of http urls and time interval can be configured in the website-monitor-config.json file.

    The project is coded in Kotlin and built in IntelliJ IDEA Community Edition 2021.1.2.

    The executable jar is in build/libs/website-monitor-1.0-SNAPSHOT.jar

    The example configuration json file is website-monitor-config.json and should exist in the directory of
    execution.

    The log file website-monitor.log is written to the directory of execution.

    The executable has been tested on MacOS Catalina on Java(TM) SE Runtime Environment 18.3 (build 10.0.2+13)

    Building
    ========

    The project can be built in IntelliJ IDEA Community Edition 2021.1.2.

    Execute the Gradle jar task to create build/libs/website-monitor-1.0-SNAPSHOT.jar.

    Dependencies are listed in the build.gradle.kts file.

    Installation
    ============

    Copy the jar (build/libs/website-monitor-1.0-SNAPSHOT.jar) file and the configuration file
    (website-monitor-config.json) to the directory where you would like to execute it.

    Edit the website-monitor-config.json to configure the urls you wish to monitor and
    the response content (regEx) to verify.

    The checking interval in seconds can be customised by changing the property checkPeriodSeconds.

    website-monitor-config.json
        {
          "checkPeriodSeconds": 10,
          "websites": [
            {
              "url": "https://www.google.com",
              "content": "<title>Google</title>"
            },
            {
              "url": "https://duckduckgo.com/",
              "content": "About DuckDuckGo"
            }
          ]
        }

    Execution
    =========

    Either execute the command from the project root:

        java -jar build/libs/website-monitor-1.0-SNAPSHOT.jar

    or, from your preferred installation directory:

        java -jar website-monitor-1.0-SNAPSHOT.jar

    If you wish to use your own json configuration file, pass it as the first argument:

        java -jar website-monitor-1.0-SNAPSHOT.jar MyConfig.json

    Logging Examples
    ================

    * Response with status code 200 (response time in milliseconds)
        Fri Jun 04 17:26:24 BST 2021 : SUCCESS: https://www.google.com request time: 89 ms

    * Response with valid content
        Fri Jun 04 17:26:24 BST 2021 : SUCCESS: https://www.google.com matches regex '<title>Google</title>'

    * Response with any status code other than 200
        Fri Jun 04 17:25:53 BST 2021 : ERROR: https://www.invalidsite.com status code : 403 request time: 143 ms

    * Response with invalid content
        Fri Jun 04 17:26:24 BST 2021 : ERROR: https://www.yahoo.com does not match regex 'Yahoo Not Home'

    * Connection exception (in this case the exception message thrown is the url)
        Fri Jun 04 17:38:09 BST 2021 : ERROR: https://www.yahoo.com connection exception: www.yahoo.com


    Possible Future Enhancements
    ============================

    * Implement a logging framework to make logging customisable, allow log file rollover, and improve
      logging resource usage.  Include unit tests for logging

    * Explore the use of coroutines to allow parallel processing of web response checks.

    * Check for json responses and provide more robust validation of content.

    * Allow the dynamic loading of the configuration file, so that the process does not
      need to be restarted.

    * Improve the handling of exceptions when a web request fails with an underlying exception.

    * Use a mocking framework to provide better unit test coverage for the web response scenarios
      and WebsiteMonitor class.

    License
    =======

    MIT License

    Copyright (c) 2021 Pentti Kavanagh penttik(at)gmail.com

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
