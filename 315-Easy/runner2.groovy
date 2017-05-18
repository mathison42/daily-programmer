
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
    // def ints = [13,11]
    // def bins = ["1101","1011"]
    String one = bins[0]
    String two = bins[1]

    println ""
    println "ints: " + ints[0] + " x " + ints[1]
    println "bins: " + one + " x " + two
    // Convert to Binary Integers
    String result = ""
    def array = []
    def add = one
    for (int i = two.size()-1; i >= 0; i--) {
        if (two.charAt(i) == "1") {
            // Add
            array << add.split("")
        } else if (two.charAt(i) == "0") {
            //Do nothing
            array << ["0"]
        } else {
            println "Non 1 or 0 value."
        }
        add += "0"
    }
    padZeros(array)
    // makeInts(array)
    for (int j = array[0].size()-1; j >= 0; j--) {
        def value = 0
        for (int i = array.size()-1; i >= 0; i--) {
            // println array[i][j].getClass()
            if (array[i][j] instanceof String) {
                value += Integer.parseInt(array[i][j])
            } else {
                value += array[i][j]
            }

        }
        if (value > 1) {
            result = "0" + result
            value = value - 1
            array[array.size()-1][j-1] = value
        } else {
            result = value.toString() + result
        }
        println result
        printArray(array)
    }
    println Integer.parseInt(result, 2)
    results << Integer.parseInt(result, 2)
}

// Check Answers
for (int z = 0; z < intInput.size(); z++) {
    def ints = intInput[z]
    def bins = binInput[z]
    // def ints = [13,11]
    // def bins = ["1101","1011"]
    int result = results[z]
    int value = ints[0] * ints[1]
    if (result == value) {
        println "${ints[0]}@${ints[1]}=${result}"
    } else {
        println "Fail:(${value}) - ${ints[0]}@${ints[1]}=${result}"
    }
}

void padZeros(def array){
    int cols = array[array.size()-1].size()+10
    for (int i = 0; i < array.size(); i++) {
        while(array[i].size() < cols) {
            array[i] = ["0", *array[i]]
        }
    }
    def temp = []
    for (int i = 0; i < cols; i++) {
        temp << 0
    }
    array << temp
    printArray(array)
}

void makeInts(def array) {
    for (int i = 0; i < array.size(); i++) {
        for (int j = 0; j < array[i].size(); j++) {
            array[i][j] = Integer.parseInt(array[i][j])
            println array[i][j].getClass()
        }
    }
}

void printArray(def array) {
    println "Array: "
    for (int i = 0; i < array.size(); i++) {
            print "["
        for (int j = 0; j < array[i].size(); j++) {
            print "" + array[i][j] + " "
        }
        print "]"
        println ""
    }
    println "\n"
}
