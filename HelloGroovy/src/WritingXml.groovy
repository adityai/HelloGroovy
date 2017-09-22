//Import GPS data from gpx file which is in xml format
def file = new File('../data/fells_loop.gpx');

//Use XmlSlurper to parse the content of the gpx file
def slurper = new XmlSlurper();
def gpx = slurper.parse(file);

//Create a markup builder object
def markupBuilder = new groovy.xml.StreamingMarkupBuilder();
def xml = markupBuilder.bind {
    //Create a parent element
    route {
        //Markup is abbreviated as mkp
        mkp.comment(gpx.name);
        gpx.wpt.each { point ->
            //Write XML
            routepoint(timestamp: point.time.toString()) {
                latitude(point.@lat);
                longitude(point.@lon);
            }
        }
    }
}

def outFile = new File('../data/fells_loop_truncated.xml');
outFile.write(xml.toString());
//Verify that the new file exists
println(outFile.exists());
