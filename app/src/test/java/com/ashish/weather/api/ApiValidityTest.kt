package com.ashish.weather.api

import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset

class ApiValidityTest {

    @Test
    @Throws(Exception::class)
    fun testAvailability() {
        val connection =
            URL("https://api.openweathermap.org/data/2.5/weather?units=metric&appid=e7c7c90365190f0a6c0329e1bf041087&q=dubai,uae").openConnection()
        val response = connection.getInputStream()
        val buffer = StringBuffer()
        BufferedReader(
            InputStreamReader(
                response,
                Charset.defaultCharset()
            )
        ).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
        }
        assert(buffer.isNotEmpty())
    }
}