//Simple collection print out
def beatles = ["John", "Paul", "George", "Ringo"];
for (int i=0; i < beatles.size(); i++) {
    println("Hello, " + beatles[i]);
}

//Collection with $ print out
def greeting = "Hello, ";
for (int i=0; i < beatles.size(); i++) {
    println("$greeting" + "${beatles[i]}")
}

//Collection with $ print out and arithmetic operation
for (int i=0; i < beatles.size(); i++) {
    println("$greeting" + "${beatles[i]}")
    println("${i*10}")
}

//Collection with $ print out and foreach loop
for (beatle in beatles) {
    println("$greeting" + "$beatle")
}
