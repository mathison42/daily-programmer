
// https://www.reddit.com/r/dailyprogrammer/comments/6ba9id/20170515_challenge_315_easy_xor_multiplication/

File inputFile = new File("input.txt")

def first = inputFile.text.split("\n")*.trim()

def intInput = []
def binInput = []
first.each{ it ->
    def temp = it.split(" ")
    if (temp.size() != 2){
        throw new RuntimeException("Oh no! Couldn't read in '${it}' and '${temp.toString()}' correctly.")
    }
    intInput << [Integer.parseInt(temp[0]), Integer.parseInt(temp[1])]
    binInput << [Integer.toBinaryString(Integer.parseInt(temp[0])),
                 Integer.toBinaryString(Integer.parseInt(temp[1]))
                ]
}

println "intInput: " + intInput.toString()
println "binInput: " + binInput.toString()

def results = []
for (int z = 0; z < binInput.size(); z++) {
    def ints = intInput[z]
    def bins = binInput[z]
    String one = bins[0]
    String two = bins[1]

    // Convert to Binary Integers
    def result = Integer.parseInt("0",2)
    def add    = Integer.parseInt(one,2)
    println result
    for (int i = two.size()-1; i >= 0; i--) {
        println "add: "    + add
        if (two.charAt(i) == "1") {
            // Add
            result += add
        } else if (two.charAt(i) == "0") {
            //Do nothing
        } else {
            println "result: " + result
            println "add: "    + add
            println "one: "    + one
            println "two: "    + two
            println "Non 1 or 0 value."
        }

            println "result: " + result
            println "one: "    + one
            println "two: "    + two
        // Conver to String, append "0", convert back to binary
        add += Integer.parseInt(Integer.toBinaryString(add) + "0", 2)
    }
    results << result
    println result
    exit
}

// Check Answers
for (int z = 0; z < intInput.size(); z++) {
    def ints = intInput[z]
    def bins = binInput[z]
    int result = results[z]
    int value = ints[0] * ints[1]
    if (result == value) {
        println "{$ints[0]}@${ints[1]}=${result}"
    } else {
        println "Fail:(${value}) - {$ints[0]}@${ints[1]}=${result}"
    }
}
