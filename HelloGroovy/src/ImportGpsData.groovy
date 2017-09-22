//Import GPS data from gpx file which is in xml format
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

//Print specific attributes and element values of the XML using each
gpx.wpt.each { point ->
    println("Latitude: " + point.@lat);
    println("Longitude: " + point.@lon);
    println("Timestamp: " + point.time);
}
