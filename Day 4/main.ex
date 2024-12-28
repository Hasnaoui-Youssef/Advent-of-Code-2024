direction = [
        {-1 , 0},
        {1, 0},
        {0, -1},
        {0, 1},
        {-1, -1},
        {-1, 1},
        {1, 1},
        {1, -1}
]
defmodule Grid do
   def walk_grid(list, direction) do
        list
        |> Enum.with_index()
        |> Enum.reduce(0, fn {row, row_index}, acc ->
            row
            |> Enum.with_index()
            |>Enum.reduce(acc, fn {char, col_index}, acc2 ->
               if char == "X" do
                direction
                |> Enum.reduce(acc2, fn {dx, dy}, acc3 ->
                   check_next(list, row_index, col_index , dx, dy, "X", acc3)
                end)
               else
                 acc2
               end
            end)
        end)
   end
   defp check_next(list, x, y, dx, dy, char, count) do
     n_x = dx + x
     n_y = dy + y
     if n_x >= 0 and n_x < length(list)
     and n_y >= 0 and n_y < length(Enum.at(list, n_x)) do
       neighbor = Enum.at(list, n_x) |> Enum.at(n_y)
       case {char, neighbor} do
         {"X", "M"} -> check_next(list, n_x, n_y, dx, dy, "M", count)
         {"M", "A"} -> check_next(list, n_x, n_y, dx, dy, "A", count)
         {"A", "S"} -> count + 1
         _ -> count
       end
     else
        count
     end
    end
end


_list = File.read!("input.txt")
|>String.split()
|>Enum.map(&String.graphemes/1)
|>Grid.walk_grid(direction)
|>IO.inspect()
