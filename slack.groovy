import groovy.json.JsonSlurper

// POST example
def slack(){
    try {
        def body = '{ "attachments": [{ "title": "Hello world", "text": "its ok", "color": "#f00"}], "channel": "your-channel-name"}'
        def http = new URL("https://hooks.slack.com/services/xxxxxxx/xxxxxxxx/xxxxxxxxxxxxxx").openConnection() as HttpURLConnection
        http.setRequestMethod('POST')
        http.setDoOutput(true)
        http.setRequestProperty("Accept", 'application/json')
        http.setRequestProperty("Content-Type", 'application/json')
    
        http.outputStream.write(body.getBytes("UTF-8"))
        http.connect()
    
        def response = [:]    
    
        if (http.responseCode == 200) {
            response = new JsonSlurper().parseText(http.inputStream.getText('UTF-8'))
        } else {
            response = new JsonSlurper().parseText(http.errorStream.getText('UTF-8'))
        }
    
    } catch (Exception e) {
        // handle exception, e.g. Host unreachable, timeout etc.
    }
}

slack()
