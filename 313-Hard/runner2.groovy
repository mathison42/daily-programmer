
// https://www.reddit.com/r/dailyprogrammer/comments/69fxq8/20170505_challenge_313_hard_embedded_word_list/


// Current approach assumes continuous matching string... that might not be true...
// twone -> three = thrwonee not... threewone

File input = new File("input.txt");

def array = input.getText().split('\n')*.trim()

// Shorten Initial Array
// But it actually increases the total count and the total runtime
// def finalArray = []
// for (int i = array.size()-1; i>=0; i--) {
//     boolean found = false
//     int jStart
//     if (finalArray.size() < 25000) {
//         jStart = 0
//     } else if (finalArray.size() < 50000) {
//         jStart = 20000
//     } else if (finalArray.size() < 70000) {
//         jStart = 45000
//     } else if (finalArray.size() < 80000) {
//         jStart = 65000
//     } else if (finalArray.size() < 90000) {
//         jStart = 75000
//     } else {
//         jStart = 85000
//     }
//     for (int j = jStart; j<finalArray.size(); j++) {
//         if (finalArray[j].contains(array[i])) {
//             found = true
//             break
//         }
//     }
//     if (!found) {
//         finalArray << array[i]
//     }
// }
//
// println "New Size: " + finalArray.size()
println "Original Size: " + array.size()

String result = "lly"
// Starting the string with `lly` lowers the total count by 14
// Having an empty starting string is 406
// Having an empty starting string with the preprocessing is 404
String temp
String tempResult
// Iterate through input list
array.each{ it ->
    def overallLoc = 0
    // println "result : " + result
    // println "it: " + it
    // println "size: " + it.length()
    temp = result.toString()
    tempResult = result.toString()
    // Iterate through each input character
    for(int i=0; i < it.size(); i++) {
        // Find first character in result string
        def loc = temp.indexOf(it[i])
        // println "char: " + it[i]
        // println "temp: " + temp
        // println "loc: " + loc
        // If not found, add it to tempResult in order
        if(loc == -1) {
            tempResult = tempResult.substring(0,overallLoc) + it[i] + tempResult.substring(overallLoc)
            overallLoc++
        // If found, remove the search result string to that index location and
        // Add that removed string to the tempResult
        } else {
            temp = temp.substring(loc+1)
            overallLoc = overallLoc + loc + 1
        }
        // println tempResult
    }
    result = tempResult
    // println "resultTemp : " + result
    // println "=========================="
}

println "Final String: " + result
println "Final Size: " + result.size()

// Check if all input exists
array.each{ it ->
    String check = result
    int checkLoc = -1
    for(int i=0; i<it.size(); i++) {
        checkLoc = check.indexOf(it[i])
        if (checkLoc == -1) {
            println "Not Found: ${it}"
            break;
        } else {
            check = check.substring(checkLoc)
        }
    }
}
