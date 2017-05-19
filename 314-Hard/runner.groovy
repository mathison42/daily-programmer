// https://www.reddit.com/r/dailyprogrammer/comments/6arlw4/20170512_chalenge_314_hard_finding_point_nemo/

File inputFile = new File("input.txt")

// THIS WAS ADDING RANDOM CHARACTERS AND MADE THE .split("") IMPOSSIBLE.
// WASTED TOO MUCH TIME ON THIS ERROR.
// ONCE DISCOVERD, EVERYTHING WORKED.
//def totalInput = inputFile.getText("UTF-8").split("\n")

def totalInput = []
inputFile.eachLine{ line ->
    totalInput << line
}

final String LAND   = "#"
final String OCEAN  = " "

int width = -1
int height = -1
def map = []
// Read in file
// First line width/height
// All others are the map
for (int i = 0; i < totalInput.size(); i++) {
    def temp = []
    // Assume first line is the width depth
    if (i == 0) {
        temp = totalInput[i].split(" ")*.trim()
        if (temp.size() != 2) {
            println "Error: Should not happen."
        }
        width = Integer.parseInt(temp[0])
        height = Integer.parseInt(temp[1])
    } else {
        map << totalInput[i].split("")
    }
}

println "width: " + width + " - height: " + height

map = padZeros(map, width, height)
// printMap(map)

int maxDistance = -1
int maxX = -1
int maxY = -1
// Iterate through each map point
for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
        // Calculate the Euclidean Distance for each OCEAN pt against each LAND pt
        if (map[y][x] == OCEAN) {
            int curMinDis = -1
            int curMinX = -1
            int curMinY = -1
            // Ite
            for (int a = 0; a < width; a++) {
                for (int b = 0; b < height; b++) {
                    // Only calculate distance if it's a LAND, otherwise skip
                    if (map[b][a] == LAND) {
                        // If a is greater than half of the width from x, go around the world the other way...
                        int newA = a
                        if ((a-x).abs() > (x + (width-a).abs())) {
                            newA = x + (width-a).abs()
                        }
                        int tempMinDis = getEuclideanDistance([y, x], [b, newA])
                        // If a smaller distance is found for a single point, save it
                        if (tempMinDis < curMinDis || curMinDis == -1) {
                            curMinDis = tempMinDis
                            curMinX = x
                            curMinY = y
                        }
                    }
                }
            }
            // If a points smallest distance is greater than the current known, save it
            if (curMinDis > maxDistance || curMinDis == -1) {
                maxDistance = curMinDis
                maxX = curMinX
                maxY = curMinY
            }
        }
    }
}
println "Furthest Distance: " + maxDistance
println "maxX: " + maxX
println "maxY: " + maxY

// EuclideanDistance Algorithm: Math.sqrt((q2-p2)**2 + (q1-p1)**2)
private double getEuclideanDistance(def point1, def point2) {
    int a = (point2[0] - point1[0])**2
    int b = (point2[1] - point1[1])**2
    return Math.sqrt(a + b)
}

// Since the text editor may remove extra spaces from the end of a file, pad it to the width
def padZeros(def array, int width, int height){
    for (int i = 0; i < height; i++) {
        while(array[i].size() < width) {
            array[i] = [*array[i], " "]
        }
    }
    return array
}

void printMap(def map) {
    for (int i = 0; i < map.size(); i++) {
        println map[i]
    }
}
