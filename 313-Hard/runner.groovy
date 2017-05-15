
// https://www.reddit.com/r/dailyprogrammer/comments/69fxq8/20170505_challenge_313_hard_embedded_word_list/


// Current approach assumes continuous matching string... that might not be true...
// twone -> three = thrwonee not... threewone
// Invalid approach, attempt #2 = runner2.groovy

File input = new File("input.txt");

def array = input.getText().split('\n')

def result = ""
array.each{ it ->
    int max = 0;
    int size = result.size()
    int begResult
    int endResult
    int begIt
    int endIt
    for (int a = 0; a < it.size(); a++) {
        int curMax = 0
        int curBegResult = -1
        int curEndResult = -1
        int curBegIt = -1
        int curEndIt = -1
        // Iterate through each it character
        for(int i = a; i < it.size(); i++) {
            // Iterate through each result character
            for (int j = 0; j < result.size(); j++) {
                // Compare current result character to it character
                // Discover max sequential characters in a row
                // Remember current max sequential characters and beg/end locations in result and it
                if (it[i] == result[j]) {
                    // It equals!
                    curMax++
                    // Null, to 0, that's true true... need to change of
                    if (curBegResult == -1 && curBegIt == -1) {
                        println "beg: " + result[j] + " : " + j
                        println "beg: " + it[i] + " : " + i
                        curBegResult = j
                        curBegIt = i
                    }
                    i++
                    if (i >= it.size()) {
                        break
                    }
                } else {
                    if ((curBegResult != -1 && curBegIt != -1) && (curEndResult == -1 && curEndIt == -1)) {
                        println "end: " + result[j] + " : " + j
                        println "end: " + it[i] + " : " + i
                        curEndResult = j
                        curEndIt = i
                        // Hit the end of the max list, try again but shift it down one
                        i = it.size()
                        break
                    }
                }
            }
        }
        if (curMax > max) {
            begResult = curBegResult
            begIt = curBegIt
            endResult = curEndResult
            endIt = curEndIt
            max = curMax
        }
    }
    println "result : " + result
    println "it: " + it
    println "max: " + max
    println "begResult: " + begResult
    println "endResult: " + endResult
    println "begIt: " + begIt
    println "endIt: " + endIt
    // If result is 0, enter first value
    if (result.size() == 0) {
        result = it
    // From the max results, enter it into the result
    // Enter the beginning not-found string from it immediately before the first found character in the result
    // Enter the ending not-found string from it immediately after the last found character in result
    } else {
        // result.plus -> insert an array into the middle/end/beginning of array
        for (int beg = 0; beg < it.substring(0, begIt).size(); beg++) {
            result.insert(begResult + beg, it[beg])
        }
        for (int end = 0; end < it.substring(endIt).size(); end++) {
            result.insert(endResult + end, it[end])
        }
        // result.insert(begResult, it.substring(0, begIt))
        // result.insert(endResult, it.substring(endIt) // add it at the end of the string? iterate through random location mulitple times...
    }
}

println "Final String: " + result
