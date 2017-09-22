//Import GPS data from gpx file which is in xml format
@groovy.lang.GrabConfig(systemClassLoader=true)
@Grapes([
        @Grab (
                group = 'org.codehaus.groovy.modules.http-builder',
                module = 'http-builder',
                version = '0.5.0'),
        @Grab ('mysql:mysql-connector-java:5.1.6'),
        @Grab(group='org.codehaus.groovy', module='groovy', version='2.4.12'),
        @Grab(group='org.codehaus.groovy', module='groovy-all', version='2.4.12'),
])

import groovyx.net.http.RESTClient;
import groovy.sql.Sql;
import org.codehaus.groovy.control.messages.WarningMessage;
import org.codehaus.groovy.runtime;

def file = new File('../data/fells_loop.gpx');
println (file.exists());

//Use XmlSlurper to parse the content of the gpx file
def slurper = new XmlSlurper();
def gpx = slurper.parse(file);
println(gpx);
println("Name: " + gpx.name);
println("Desc: " + gpx.desc);
println("GPX Version: " + gpx.@version);
println("Creator: " + gpx.@creator);

//Print specific attributes and element values of the XML using for loop
for (point in gpx.wpt) {
    println("Latitude: " + point.@lat);
    println("Longitude: " + point.@lon);
    println("Timestamp: " + point.time);
}

def forecastApi = new RESTClient('https://api.darksky.net');
def credentialsFile = new File('credentials.groovy');
def configSlurper = new ConfigSlurper();
def credentials = configSlurper.parse(credentialsFile.toURL());
def sql = Sql.newInstance(
        "jdbc:mysql://localhost:3306/gps",
        "root",
        "vagrant",
        "com.mysql.jdbc.Driver");

//Print specific attributes and element values of the XML using each
gpx.wpt.each { point ->
    println("Latitude: " + point.@lat);
    println("Longitude: " + point.@lon);
    println("Timestamp: " + point.time);

    if (!''.equals(point.time.toString().trim())){
        def queryString = "forecast/${credentials.apiKey}/${point.@lat},${point.@lon},${point.time}";
        println(queryString);
        def response = forecastApi.get(path: queryString);
        println(response.status);
        println(response.data);

        println("${response.data.currently.summary}");
        println("${response.data.currently.temperature} degrees")
    }
    else {
        println("No timestamp.")
    }
}

sql.close();