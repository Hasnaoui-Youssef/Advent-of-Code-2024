


class Solution
{
    public static HashSet<Point> points = new();
    public static int n = 0, m = 0;
    public class Point(int x, int y)
    {
        public int x = x, y = y;
        public override string ToString() => $"( X : {x} Y : {y})";

        public override bool Equals(object? obj)
        {
            if (obj is Point other) {
                return this.x == other.x && this.y == other.y;
            }
            return false;
        }
        public override int GetHashCode()
        {
            return HashCode.Combine(x, y);
        }
    }

    static public int PGCD(int a, int b)
    {
        int res = Math.Min(a, b);
        while(res > 0)
        {
            if(a % res == 0 && b % res == 0)
            {
                return res;
            }
            res--;
        }
        return res;
    }
    static void findPoints(Point p, Point q)
    {
        //Kifeh bch nbadlouha ?
        // Nekhdhou delta x w delta y, men kol point nokeedou nfarksou les point T1 + a * dL li  fihom l constraints
        int b = p.x - q.x;
        int a = q.y - p.y;
        int c = a * p.x + b * p.y;
        int x1 = 2 * p.x - q.x, x2 = 2 * q.x - p.x;
        int y1 = (c - a * x1) / b, y2 = (c - a * x2) / b;
        if (x1 >= 0 && y1 >= 0 && x1 < n && y1 < m)
        {
            points.Add(new Point(x1, y1));
        }
        if ( x2 >= 0 && y2 >= 0 && x2 < n && y2 < m)
        {
            points.Add(new Point(x2, y2));
        }
        
    }
    static void findAllPoints(Point p, Point q)
    {
        int dx = p.x - q.x;
        int dy = p.y - q.y;
        int d = PGCD(dx, dy);
        if(d > 0)
        {
            dx /= d; dy /= d;
        }
        Console.WriteLine($" dX : {dx}, dY : {dy}");
        int nx = p.x ;
        int ny = p.y ;
        while (nx >= 0 && nx < n && ny>= 0 && ny < m)
        {
            Console.WriteLine($"NX : {nx}, NY : {ny}");
            points.Add(new Point(nx, ny));
            nx += dx;
            ny += dy;
        }
        nx = p.x;
        ny = p.y;
        while (nx >= 0 && nx < n && ny >= 0 && ny < m)
        {
            Console.WriteLine($"NX : {nx}, NY : {ny}");
            points.Add(new Point(nx, ny));
            nx -= dx;
            ny -= dy;
        }
    }
    public static void Main(string[] args)
    {
        string[] lines = File.ReadAllLines("input.txt");
        Dictionary<char, List<Point>> hm = new();
        n = lines.Length;
        m = lines[0].Length;
        for(int i = 0; i < lines.Length; i++)
        {
            for(int j = 0; j < lines[i].Length; j++)
            {
                if (lines[i][j] != '.')
                {
                    if (hm.ContainsKey(lines[i][j]))
                    {
                        hm[lines[i][j]].Add(new Point(i, j));
                    }
                    else
                    {
                        hm.Add(lines[i][j], [new Point(i,j)]);
                    }
                }
            }
        }
        foreach(KeyValuePair<char, List<Point>> entry in hm)
        {
            if(entry.Value.Count > 1)
            {
                Console.WriteLine(" Entry : " + entry.Key);
                for(int i = 0; i < entry.Value.Count - 1; i++)
                {
                    for(int j = i + 1; j < entry.Value.Count; j++)
                    {
                        Console.WriteLine($"Points : {entry.Value[i]} and : {entry.Value[j]}");
                        findAllPoints(entry.Value[i], entry.Value[j]);
                    }
                }
            }
            
        }
        //foreach(var point in points)
        //{
        //    Console.WriteLine(point.ToString());
        //}
        Console.WriteLine(points.Count);
    }
}