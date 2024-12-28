
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if(other == null) return false;
        if(this.getClass() != other.getClass()) return false;
        Point p = (Point) other;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + y;
        return result;
    }
}

class Main {
    static ArrayList<char[]> grid = new ArrayList<>();
    static int sum = 0;
    static int len = 0;
    static int height = 0;
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            try (Scanner reader = new Scanner(file)){
                while(reader.hasNextLine()){
                    String data = reader.nextLine(); 
                    grid.add(data.toCharArray());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        len = grid.getFirst().length;
        height = grid.size();
        for (int i = 0; i < height; i++) {
           for(int j = 0; j < len; j++){
            if(grid.get(i)[j] == '0'){
                HashSet<Point> visited = new HashSet<>();
                int ans = dfs(0, i, j, visited);
                sum += ans;
            }
           }
        }
        System.out.println(sum);
    }
   
    
    // For part 2  na7iw l visited
    public static int dfs(int val, int x, int y, HashSet<Point> visited){
        if(val == 9){
            if(visited.contains(new Point(x, y))){
                return 0;
            }
            visited.add(new Point(x, y));
            return 1;
        }
        int acc = 0;
        if(inBounds(x + 1, y)){
            if(grid.get(x+1)[y] - '0' == val + 1){
                acc += dfs(val + 1, x + 1, y, visited);
            }
        }
        if(inBounds(x - 1, y)){
            if(grid.get(x-1)[y] - '0' == val + 1){
                acc += dfs(val + 1, x - 1, y, visited);
            }
        }
        if(inBounds(x , y + 1)){
            if(grid.get(x)[y + 1] - '0' == val + 1){
                acc += dfs(val + 1, x , y + 1, visited);
            }

        }
        if(inBounds(x, y - 1)){
            if(grid.get(x)[y - 1] - '0' == val + 1){
               acc += dfs(val + 1, x, y - 1, visited);
            }
        }
        return acc;
    }
    public static boolean inBounds(int x, int y){
        return x >= 0 && x < height && y >= 0 && y < len;
    }
}