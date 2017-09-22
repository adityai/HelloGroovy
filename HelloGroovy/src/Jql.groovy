@Grapes(
        @Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.6')
)
@Grapes(
        @Grab(group='org.apache.httpcomponents', module='httpclient', version='4.5.2')
)

import groovyx.net.http.HTTPBuilder

def doGET() {
    try {
        def http = new HTTPBuilder('https://issues.jenkins-ci.org')
        http.get( path : '/rest/api/2/search', query : [jql:'key=JENKINS-46847'] )
                { resp ->
                    return resp.entity.content.text
                }
    }
    catch(groovyx.net.http.HttpResponseException e)
    {
        println e.toString()
    }
}

println 'doGET(): ' + doGET()