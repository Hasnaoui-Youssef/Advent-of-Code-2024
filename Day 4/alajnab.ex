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
list
|>Enum.with_index()
|>Enum.each(fn {row, row_index} ->
    Enum.with_index(row)
    |>Enum.each(fn {col, col_index} ->
        if col == "X" do
            #IO.puts("Lkina x eeee f ligne #{row_index}, w colonne #{col_index}")
            direction
            |>Enum.each(fn {dx, dy} ->
                n_x = row_index + dx
                n_y = col_index + dy
                if n_x >= 0 and n_x < length(list)
                and n_y >= 0 and n_y < length(Enum.at(list, n_x)) do
                  neighbor = Enum.at(list, n_x) |> Enum.at(n_y)
                  if neighbor == "M" do
                    m_nx = n_x + dx
                    m_ny = n_y + dy
                    if m_nx >= 0 and m_nx < length(list)
                    and m_ny >= 0 and m_ny < length(Enum.at(list, m_nx)) do
                      m_nei = Enum.at(list, m_nx) |> Enum.at(m_ny)
                      if m_nei == "A" do
                        a_nx = m_nx + dx
                        a_ny = m_ny + dy
                        if a_nx >= 0 and a_nx < length(list)
                        and a_ny >= 0 and a_ny < length(Enum.at(list, a_nx)) do
                           a_nei = Enum.at(list, a_nx) |> Enum.at(a_ny)
                           if a_nei == "S" do
                                IO.puts("Lkina xmas bl x : #{row_index} : #{col_index} fl direction : #{dx} : #{dy}")
                                Agent.update(count, &(&1 + 1))
                           end
                        end
                      end
                    end
                  end
                end
            end)
        end
    end)
end)
