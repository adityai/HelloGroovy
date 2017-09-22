//Closure with timestamp
def myClosure = {
    println "In a closure";
    println new Date();
}

//Executing closure in a for loop
for (i in 1..3) {
    myClosure();
    sleep(1000);
}

//Executing closure in a each loop
(1..3).each {
    myClosure()
}

//Executing an anonymous block closure in a each loop
(1..3).each {
    println "In closure"
}

//Executing an anonymous block closure in a each loop and accessing the loop counter value
(1..3).each {
    println "In closure: $it"
}

//Executing an anonymous block closure in a each loop and accessing the loop counter with a defined reference
(1..3).each { i ->
    println "In closure: $i"
}

//Executing an anonymous block closure in a findAll and each loop and accessing the loop counter with a defined reference
(1..10).findAll({ return it%2==0  } ).each { i ->
    println "In closure: $i"
}


