import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

class PositionState{
    public int x;
    public int y; 
    public int d;
    PositionState(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(!(obj instanceof PositionState)) return false;
        PositionState s = (PositionState) obj;
        return s.x == x && s.y == y && s.d == d;
    }
    @Override
    public int hashCode(){
        return Objects.hash(x, y, d);
    }
}


class Main {
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    static int n;
    static int m;
    static ArrayList<char[]> grid = new ArrayList<>();
  public static void main(String[] args) {
    try {
      File myObj = new File("input.txt");
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                grid.add(data.toCharArray());
            } }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
    n = grid.size();
    m = grid.get(0).length;
    for(int i = 0; i < n; i ++){
        for(int j = 0; j < m; j++){
            char c = grid.get(i)[j];
            Direction d;
            if(c != '.' && c != '#' && c != 'X'){
                switch (c) {
                    case '^' -> d = Direction.UP;
                    case '>' -> d = Direction.RIGHT;
                    case '<' -> d = Direction.LEFT;
                    case 'v' -> d = Direction.DOWN;
                    default -> throw new IllegalStateException("Unexpected value: " + c); 
                }
                int count = 0;
                for(int k = 0; k < n; k++){
                    for(int l = 0; l < m; l++){
                        if(grid.get(k)[l] == '.'){
                            grid.get(k)[l] = '#';
                            Set<PositionState> visited = new HashSet<>();
                            if(bruteForceP2( i, j, d, visited)){
                                count ++;
                            }
                            grid.get(k)[l] = '.';
                        }
                    }
                }
                System.out.println("Count : " + count);
            }
        }
    }
  }
 
  static boolean bruteForceP2( int x, int y, Direction d, Set<PositionState> visited){
    int d_num;
    switch(d){
        case Direction.UP -> d_num = 0;
        case Direction.DOWN -> d_num = 1;
        case Direction.LEFT -> d_num = 2;
        case Direction.RIGHT -> d_num = 3;
        default -> d_num = -1;
    }
    if(d_num == -1 ) return false;
    PositionState arr = new PositionState(x, y, d_num);
    if(visited.contains(arr)){
        //System.err.println("Jina lena alakal mara");
        return true;
    }
    visited.add(arr);
    int new_x = x, new_y = y;
    switch(d){
        case Direction.UP -> new_x --;
        case Direction.DOWN -> new_x ++ ;
        case Direction.LEFT -> new_y -- ;
        case Direction.RIGHT -> new_y ++ ;
        default -> System.out.println("What");
    }
    if(isOutOfBounds(new_x, new_y)){
        //System.err.println("Khrajna f x kbal ma nalkaw obstacle " + x + " y " + y);
        return false;
    }
    while(grid.get(new_x)[new_y] == '#'){
        switch(d){
            case Direction.UP -> {
                d = Direction.RIGHT;
                new_x = x;
                new_y = y + 1;
            }
            case Direction.DOWN -> {
                d = Direction.LEFT;
                new_x = x;
                new_y = y - 1;
            }
            case Direction.LEFT -> {
                d = Direction.UP;
                new_x = x - 1;
                new_y = y;
            }

            case Direction.RIGHT -> {
                d = Direction.DOWN;
                new_x = x + 1;
                new_y = y ;
            }

            default -> System.out.println("What");
        }
        if(isOutOfBounds(new_x, new_y)){
            //System.out.println("Khrajna baed ma dorna men obstacle f x " + x + " y " + y);
            return false;
        }
    }
    return bruteForceP2( new_x, new_y, d, visited );
  }
  static Boolean isOutOfBounds(int x, int y){
    return !(x >= 0 && x < n && y >= 0 && y < m);
  }
  static Direction swapRight(Direction d){
        return switch(d){
            case UP -> 
                Direction.RIGHT;
            case DOWN -> 
                Direction.LEFT;
            case LEFT -> 
                Direction.UP;
            case RIGHT -> 
                Direction.DOWN;
        };

  } 
}
