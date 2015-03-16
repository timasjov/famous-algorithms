public class DamerauLevenshteinDistance {
    
    private static int minimum3(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }    
    private static int minimum2(int a, int b) {
        return Math.min(a, b);
    }
    
    public static int[][] computeDamerauLevenshteinDistance(String str1,String str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {

                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = minimum3(
                        distance[i - 1][j] + 1, // deletion
                        distance[i][j - 1] + 1, // insertion
                        distance[i - 1][j - 1] + cost); // substitution
                if(i > 1 && j > 1 && str1.charAt(i - 1) == str2.charAt(j-2) && str1.charAt(i - 2) == str2.charAt(j - 1)) {
                    distance[i][j] = minimum2(
                        distance[i][j],
                        distance[i - 2][j - 2] + cost   // transposition
                    );
                }
            }
        }
        return distance;
    }

    public static void print(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%4d", matrix[row][col]);
            }
            System.out.println();
        }
    }

    public static int getDistance(int[][] matrix, String s1, String s2) {
        return matrix[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        String s1 = "abarrabcakkcadkcdabbaarra";
        String s2 = "barackadabadckdbaabararaa";
        int distMatrix[][] = computeDamerauLevenshteinDistance(s1, s2);
        int dist = getDistance(distMatrix, s1, s2);
        print(distMatrix);
        System.out.println("Distance: " + dist);
    }
}