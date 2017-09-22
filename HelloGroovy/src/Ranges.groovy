//Print a range of numbers
def numbers = 1..10;
for (num in numbers) {
    println(num);
}

//Print a range of alphabets
def alphabetRange = "a".."g";
for (var in alphabetRange) {
    println(var)
}

//Print a range of enum
def enum DAYS {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN
}
def weekdays = DAYS.MON..DAYS.FRI;
for (var in weekdays) {
    println(var);
}

//Print extents of an enum
println("Extents:")
println(weekdays.from)
println(weekdays.to)