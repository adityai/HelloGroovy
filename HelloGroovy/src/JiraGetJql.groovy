import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

url = "https://issues.jenkins-ci.org/rest/api/2/search?jql=key=JENKINS-46847"

@Grab (group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.5.0')
def client = new RESTClient(url)

def emptyHeaders = [:]
emptyHeaders."Accept" = 'application/json'
emptyHeaders."Prefer" = 'test'

def response = client.get(path: "/");

println("Status: " + response.status)
if (response.data) {
    println("Content Type: " + response.contentType)
    println("Headers: " + response.getAllHeaders())
    println("Body:\n" + response.data)
    //println("Body:\n" + JsonOutput.prettyPrint(JsonOutput.toJson(response.data)))
}