list = File.read!("input.txt")
|> String.split()
|> Enum.map(&String.graphemes/1)

{:ok , count} = Agent.start(fn -> 0 end)

list
|> Enum.with_index()
|> Enum.filter(fn {_, row_index} -> row_index > 0 and row_index < length(list) - 1  end)
|> Enum.each( fn {row, row_index} ->
    Enum.with_index(row)
    |> Enum.filter(fn {_, col_index} -> col_index > 0 and col_index < length(list) - 1 end)
    |> Enum.each(fn {char, col_index} ->
        if char == "A" do
            ld = [
                {1, 1},
                {-1, -1}
            ]
            rd = [
                {-1, 1},
                {1, -1}
            ]
            ms_ld = ld
            |> Enum.map(fn {dx, dy} ->
                Enum.at(list, row_index + dx) |> Enum.at(col_index + dy)
            end)

            ms_rd = rd
            |> Enum.map(fn {dx, dy} ->
                Enum.at(list, row_index + dx) |> Enum.at(col_index + dy)
            end)
            if ms_ld == ["M", "S"] or ms_ld == ["S", "M"] do
              if ms_rd == ["M", "S"] or ms_rd == ["S", "M"] do
                Agent.update(count, &(&1 +1))
              end
            end
        end
    end)
end)

IO.inspect(Agent.get(count,& &1))
