#include <bits/stdc++.h>

std::vector<std::vector<int>> data;

void read_data(std::string &path)
{
    std::ifstream file(path);
    if (!file.is_open())
    {
        std::cerr << "Nejemch nakra L fichier !\n";
        return;
    }
    std::string line;
    while (std::getline(file, line))
    {
        std::istringstream iss(line);
        int num;
        std::vector<int> line_data((std::istream_iterator<int>(iss)), {});
        while (iss >> num)
            line_data.push_back(num);
        data.push_back(line_data);
    }
}
inline bool cond(int x, int y, int d)
{
    return ((x - y) * d > 0) && abs(x - y) <= 3;
}
int check_line(std::vector<int> &v)
{
    int direction = (v[0] == v[1]) ? 0 : (v[0] < v[1]) ? -1
                                                       : 1;
    for (int i = 0; i < v.size() - 1; ++i)
    {
        if (!cond(v[i], v[i + 1], direction))
            return 0;
    }
    return 1;
}
int tnahi_wehed(std::vector<int> &x)
{
    for (int i = 0; i < x.size(); ++i)
    {
        std::vector<int> t;
        for (int j = 0; j < x.size(); ++j)
        {
            if (i != j)
                t.push_back(x[j]);
        }
        if (check_line(t))
            return 1;
    }
    return 0;
}
int main()
{
    int counter = 0;
    std::string path("input.txt");
    read_data(path);
    for (auto &x : data)
        counter += tnahi_wehed(x);

    std::cout << counter << '\n';
    return 0;
}