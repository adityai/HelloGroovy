//Simple function sample
def numbers = 1..9;
println("Number is even = true")
for (num in numbers) {
    println(num + ": " + isEven(num));
}

def isEven(def num) {
    return num%2 == 0
}