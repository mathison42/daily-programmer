
// https://www.reddit.com/r/dailyprogrammer/comments/6ba9id/20170515_challenge_315_easy_xor_multiplication/


// Read in Input
File inputFile = new File("input.txt")
def input = inputFile.text.split("\n")*.trim()
def intInput = []
input.each{ it ->
    def temp = it.split(" ")
    if (temp.size() != 2){
        throw new RuntimeException("Oh no! Couldn't read in '${it}' and '${temp.toString()}' correctly.")
    }
    intInput << [Integer.parseInt(temp[0]), Integer.parseInt(temp[1])]
}

println "intInput: " + intInput.toString()
println ""

// Iterate and find all values
def results = []
for (int z = 0; z < intInput.size(); z++) {
    def ints = intInput[z]
    // Convert each int to a binary string
    String one = Integer.toBinaryString(ints[0])
    String two = Integer.toBinaryString(ints[1])

    // Convert to Binary Integers
    int result = 0
    def add = one
    // Perform XOR Multiplication
    for (int i = two.size()-1; i >= 0; i--) {
        if (two[i] == "1") {
            result = Integer.parseInt(Integer.toBinaryString(result), 2) +
                    Integer.parseInt(add.toString(), 2)
        } else if (two[i] == "0"){
            // Nothing
        } else {
            println "[Error] Should never reach this point..."
            System.exit(1)
        }
        add += "0"
    }
    results << result
}

// Check Answers
for (int z = 0; z < intInput.size(); z++) {
    def ints = intInput[z]
    int result = results[z]
    int value = ints[0] * ints[1]
    if (result == value) {
        println "${ints[0]}@${ints[1]}=${result}"
    } else {
        println "Fail:(${value}) - ${ints[0]}@${ints[1]}=${result}"
    }
}
