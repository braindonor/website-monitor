Website Monitor v1.0
====================
Author: Pentti Kavanagh penttik@gmail.com

Website Monitor is a simple utility to monitor a list of http urls at a time interval.
Both the list of http urls and time interval can be configured in the website-monitor-config.json file.

The project is coded on Kotlin and built in IntelliJ IDEA Community Edition 2021.1.2.

The executable jar is in build/libs/website-monitor-1.0-SNAPSHOT.jar
The example configuration json file is website-monitor-config.json and should exist in the directory of execution.
The log file website-monitor.log is written to the directory of execution.

The executable has been tested on MacOS Catalina on Java(TM) SE Runtime Environment 18.3 (build 10.0.2+13)

Installation
============

Copy the jar (build/libs/website-monitor-1.0-SNAPSHOT.jar) file and the configuration file (website-monitor-config.json)
to the directory where you would like to execute it.

Edit the website-monitor-config.json to configure the urls you wish to monitor and the response content (regEx) to verify.
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







