import tkinter as tk
from tkinter import ttk, RAISED
import numpy as np
from collections import defaultdict
from tkinter import font as tkFont
import matplotlib.pyplot as plt
import networkx as nx
import copy

INF = 9999

def check_period(last_component_h):
    for i in range(len(last_component_h)):
        if last_component_h[i] == True:
            return True
    return False

def last_component(entry1, list_scc_period):
    last_component_ = list_scc_period[len(list_scc_period) - 1]
    if len(last_component_) == int(entry1.get()):
        last_component_ = list_scc_period[len(list_scc_period) - 2]
    last_component_return = []
    for i in range(len(last_component_)):
        if len(last_component_[i]) > 1:
            last_component_return.append(last_component_[i])
    return last_component_return

def period_matrix(entry1, entries, window, canvas1, Time):
    # matrix = get_matrix(entry1, entries)
    # dist = list(map(lambda i: list(map(lambda j: j, i)), matrix))
    #
    # for k in range(len(matrix[0])):
    #     for i in range(len(matrix[0])):
    #         for j in range(len(matrix[0])):
    #             # dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
    #             dist[i][j] = max(min(dist[i][k], dist[k][j]), dist[i][j])
    #
    # l = []
    # for i in range(len(dist)):
    #     l.append(int(dist[i][i]))
    #
    # num1 = l[0]
    # num2 = l[1]
    # lcm = find_lcm(num1, num2)
    #
    # for i in range(2, len(l)):
    #     lcm = find_lcm(lcm, l[i])
    #
    # var_per = tk.StringVar()
    # var_per.set(f"perA = {lcm}")
    # label_k = tk.Label(window, textvariable=var_per, relief=RAISED, font=('Arial', 25))
    # canvas1.create_window(1100, 100, window=label_k, tags="komponent")

    # print(lcm)
    scc_list_full = []
    scc_one_it = []
    matrix = get_matrix(entry1, entries)
    dist = list(map(lambda i: list(map(lambda j: j, i)), matrix))
    new_matrix = copy.deepcopy(dist)
    h = dist[0][0]
    for i in range(len(dist)):
        for j in range(len(dist)):
            if dist[i][j] > h:
                h = dist[i][j]
    for k in range(1 ,int(h) + 1):
        for i in range(len(dist)):
            for j in range(len(dist)):
                if min(int(dist[i][j]), int(dist[j][i])) < k:
                    new_matrix[i][j] = 0
        print(new_matrix)
        scc_one_it = SCC(entry1, new_matrix, Time, scc_one_it)
        scc_list_full.append(scc_one_it)

    print("SCC FULL = ")
    print(scc_list_full)
    if len(scc_list_full[len(scc_list_full) - 1]) == int(entry1.get()):
        h = len(scc_list_full) - 1
    else:
        h = len(scc_list_full)

    print("H = ")
    print(h)

    last_component_h = last_component(entry1, scc_list_full)
    print("LAST = ")
    print(last_component_h)

    for i in range(len(last_component_h)):
        for j in range(len(last_component_h[i])):
            if int(matrix[int(last_component_h[i][j])][int(last_component_h[i][j])]) < h:
                last_component_h[i][j] = False
            else:
                last_component_h[i][j] = True

    print("LAST_FALSE_TRUE = ")
    print(last_component_h)

    for i in range(len(last_component_h)):
        period = check_period(last_component_h[i])
        if period == False:
            print("Period: " + str(2))
            var_per = tk.StringVar()
            var_per.set(f"perA = {2}")
            label_k = tk.Label(window, textvariable=var_per, relief=RAISED, font=('Arial', 25))
            canvas1.create_window(1100, 100, window=label_k, tags="komponent")
            break
        if i + 1 == len(last_component_h):
            print("Period: " + str(1))
            var_per = tk.StringVar()
            var_per.set(f"perA = {1}")
            label_k = tk.Label(window, textvariable=var_per, relief=RAISED, font=('Arial', 25))
            canvas1.create_window(1100, 100, window=label_k, tags="komponent")

def find_lcm(num1, num2):
    if (num1 > num2):
        num = num1
        den = num2
    else:
        num = num2
        den = num1
    rem = num % den
    while (rem != 0):
        num = den
        den = rem
        rem = num % den
    gcd = den
    lcm = int(int(num1 * num2) / int(gcd))
    return lcm

def draw_graph(entry1, entries):
    G=nx.DiGraph(directed=True)
    nodes = []
    for i in range(int(entry1.get())):
        nodes.append(i+1)
    print(nodes)

    matrix = get_matrix(entry1, entries)
    edges = []
    for i in range(int(entry1.get())):
        for j in range(int(entry1.get())):
            if int(matrix[i][j]) != 0:
                edges.append((i+1, j+1))

    G.add_nodes_from(nodes)
    G.add_edges_from(edges)
    nx.draw(G, with_labels=True, font_weight='bold', arrowstyle="->", arrowsize=10,)
    plt.show()
    # print(edges)

def matrix_to_h(entry1, entries, graph):
    matrix = get_matrix(entry1, entries)
    print(matrix)
    for i in range(int(entry1.get())):
        for j in range(int(entry1.get())):
            if int(matrix[i][j]) != 0:
                # print(f'!= 0 {i}{j}')
                print(matrix[i][j])
                print(f'{i} == {j}')
                addEdge(i, j, graph)
            # print(f' == 0 {i}{j}')

    return graph

def matrix_to_h_scc(entry1, entries, graph):
    matrix = copy.deepcopy(entries)
    for i in range(int(entry1.get())):
        for j in range(int(entry1.get())):
            if int(matrix[i][j]) != 0:
                addEdge(i, j, graph)
    return graph

def addEdge(u, v, graph):
    graph[u].append(v)

def SCCUtil(u, low, disc, stackMember, st, Time, graph, scc_list):
    disc[u] = Time
    low[u] = Time
    Time += 1
    stackMember[u] = True
    st.append(u)

    for v in graph[u]:
        if disc[v] == -1:
            SCCUtil(v, low, disc, stackMember, st, Time, graph, scc_list)
            low[u] = min(low[u], low[v])
        elif stackMember[v] == True:
            low[u] = min(low[u], disc[v])

    w = -1
    if low[u] == disc[u]:
        scc_string = []
        while w != u:
            w = st.pop()
            print(w, end=" ")
            scc_string += str(w)
            # scc_string += ' '
            stackMember[w] = False

        print()
        print(scc_string)
        scc_list.append(scc_string)
        # print(scc_list)

def SCC(entry1, entries, Time, scc_one_it):
    graph = defaultdict(list)
    scc_list = []
    graph = matrix_to_h_scc(entry1, entries, graph)
    disc = [-1] * int(entry1.get())
    low = [-1] * int(entry1.get())
    stackmember = [False] * int(entry1.get())
    st = []

    for i in range(int(entry1.get())):
        if disc[i] == -1:
            SCCUtil(i, low, disc, stackmember, st, Time, graph, scc_list)
    # print(scc_list)
    scc_one_it = scc_list
    return scc_one_it

    # string_scc = []
    # string_k = []
    # for i in range(len(scc_list)):
    #     if i == 0:
    #         string_scc = f'K{i+1}'
    #         string_k.append(f'K{i+1} = ')
    #     else:
    #         string_scc += f', K{i+1}'
    #         string_k.append(f'\nK{i+1} = ')
    #
    # string_res = "SCC = {" + string_scc + "}"
    # # var_scc.set(string_res)
    #
    # y_coor_k = 0
    # print("SCC:")
    # for x in range(len(scc_list)):
    #     for y in range(len(scc_list[x])):
    #         scc_list[x][y] = int(scc_list[x][y]) + 1
    #         scc_list[x][y] = str(scc_list[x][y])
    #         print(f"SCC: {scc_list[x][y]}")
    #
    # for x in range(len(scc_list)):
    #     string_k_var = []
    #     for y in range(len(scc_list[x])):
    #         string_k_var += scc_list[x][y]
    #         print(string_k_var)
    # var_k = tk.StringVar()
    # var_k.set(f'K({x+1}) = {string_k_var}')
    # label_k = tk.Label(window, textvariable=var_k, relief=RAISED, font=('Arial', 25))
    # canvas1.create_window(800, 350 + y_coor_k, window=label_k, tags="komponent")
    # print(var_k.get())
    # y_coor_k += 60

def count_component(list_scc):
    if len(list_scc) == 1:
        return len(list_scc)
    else:
        count = 0
        for i in range(len(list_scc)):
            if len(list_scc[i]) > 1:
                count += 1
        return count

def floyd_warshall(entry1, entries, window, canvas1, graph, Time):
    scc_list_full = []
    scc_one_it = []

    matrix = get_matrix(entry1, entries)
    print(matrix)
    dist = list(map(lambda i: list(map(lambda  j: j, i)), matrix))

    for k in range(len(matrix[0])):
        for i in range(len(matrix[0])):
            for j in range(len(matrix[0])):
                # dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
                dist[i][j] = max(min(dist[i][k], dist[k][j]), dist[i][j])

    y_coor_p = 0
    x_coor_p = 0
    var_matrix_b = tk.StringVar()
    var_matrix_b.set("Matica B:")
    label_matrix_b = tk.Label(window, textvariable=var_matrix_b, relief=RAISED, font=('Arial', 25))
    canvas1.create_window(1150, 300, window=label_matrix_b, tags="komponent")

    for i_p in range(len(dist)):
        for j_p in range(len(dist)):
            var_k_b = tk.StringVar()
            var_k_b.set(dist[i_p][j_p])
            label_s = tk.Label(window, textvariable=var_k_b, relief=RAISED, font=('Arial', 20))
            canvas1.create_window(1100 + x_coor_p, 350 + y_coor_p, window=label_s, tags="komponent")
            x_coor_p += 30
        x_coor_p = 0
        y_coor_p += 30

    h = dist[0][0]
    for i in range(len(dist)):
        for j in range(len(dist)):
            if dist[i][j] > h:
                h = dist[i][j]

    new_matrix = copy.deepcopy(dist)

    y_coor_k = 0
    for k in range(1 ,int(h) + 1):
        # print(k)

        # print(new_matrix)
        for i in range(len(dist)):
            for j in range(len(dist)):
                if min(int(dist[i][j]), int(dist[j][i])) < k:
                    # print(f"h = {k}, i = {i}, j = {j}, v = {dist[i][j]}")
                    new_matrix[i][j] = 0
                # else:
                    # new_matrix[i][j] = min(int(dist[i][j]), int(dist[j][i]))
                    # new_matrix[j][i] = min(int(dist[i][j]), int(dist[j][i]))

        print(new_matrix)
        #
        # count = 0

        # graph = matrix_to_h(entry1, new_matrix, graph)
        scc_one_it = SCC(entry1, new_matrix, Time, scc_one_it)
        scc_list_full.append(scc_one_it)
        # for i in range(len(dist)):
        #     for j in range(len(dist)):
        #         if new_matrix[i][j] != 0 and i != j and new_matrix[j][i] != 0 and new_matrix[i][i] != 0 and new_matrix[j][j] != 0:
        #             # if k == 2:
        #             count += 1
        #             print(f"V = {new_matrix[i][j]}, x = {i}, j = {j}")
        # print(count)

        # buff = list(range(len(new_matrix)))
        # res_string = f"SCC*(G(A,{k})) = "
        # res_string_k = ""
        #
        # bool_q = False
        # for q in range(len(new_matrix)):
        #     if bool_q == True:
        #         break
        #     for w in range(len(new_matrix)):
        #         if new_matrix[q][w] != 0:
        #             res_string = res_string + "{K"
        #             res_string = res_string + f"{k}"
        #             res_string = res_string + "}"
        #             bool_q = True
        #             res_string_k = f"K{k} = "
        #             break
        # print(res_string)
        #
        # res_komponent = []
        # # res_komponent.append([])
        # for x in range(len(new_matrix)):
        #     for y in range(len(new_matrix)):
        #         buff[y] = new_matrix[x][y]
        #     for nul in range(len(buff)):
        #         if buff[nul] != 0:
        #             # print(f"Komponent v h={k} bude {x}")
        #             res_komponent.append(x + 1)
        #             # print(res_string)
        #             break
        # res_string_k = res_string_k + str(res_komponent)
        # print(res_string_k)
        # # print(res_komponent)
        #
        # var_s = tk.StringVar()
        # var_s.set(res_string)
        # var_k = tk.StringVar()
        # var_k.set(res_string_k)
        #
        # label_s = tk.Label(window, textvariable=var_s, relief=RAISED, font=('Arial', 25))
        # canvas1.create_window(800, 300 + y_coor_k, window=label_s, tags="komponent")
        #
        # y_coor_k += 50
        #
        # label_k = tk.Label(window, textvariable=var_k, relief=RAISED, font=('Arial', 25))
        # canvas1.create_window(800, 300 + y_coor_k, window=label_k, tags="komponent")
        #
        # y_coor_k += 60
    # print(new_matrix)
    # print(dist)
    # print_graph(dist, matrix)
    # TODO: вивід компонентів
    # scc_list_full.append(scc_one_it)
    print("SCC*==")
    print(scc_list_full)
    _list_scc_period = scc_list_full
    check_k = 0
    check_kk = 0
    y_coor_component = 0

    for i in range(len(scc_list_full)):
        str_scc = f"SCC*(G(A, {i + 1})) = "
        for count in range(count_component(scc_list_full[i])):
            if count == 0:
                str_scc += "{"
                str_scc += f"K{check_k + 1}"
                check_k += 1
            if count + 1 == count_component(scc_list_full[i]):
                str_scc += "}"
            else:
                str_scc += f", K{check_k + 1}"
                check_k += 1
        if str_scc[len(str_scc) - 1] != " ":
            print(str_scc)
            var_str_scc = tk.StringVar()
            var_str_scc.set(str_scc)
            label_s = tk.Label(window, textvariable=var_str_scc, relief=RAISED, font=('Arial', 25))
            canvas1.create_window(800, 300 + y_coor_component, window=label_s, tags="komponent")
            y_coor_component += 50
        for j in range(len(scc_list_full[i])):
            str_component = f"K{check_kk + 1} = "
            if len(scc_list_full[i][j]) > 1:
                str_component_copy = copy.deepcopy(scc_list_full[i][j])
                for x in range(len(str_component_copy)):
                    str_component_copy[x] = str(int(str_component_copy[x]) + 1)
                # str_component += str(scc_list_full[i][j])
                str_component += str(str_component_copy)
                check_kk += 1
            if str_component[len(str_component) - 1] != " ":
                print(str_component)
                var_str_component = tk.StringVar()
                var_str_component.set(str_component)
                label_s = tk.Label(window, textvariable=var_str_component, relief=RAISED, font=('Arial', 25))
                canvas1.create_window(800, 300 + y_coor_component, window=label_s, tags="komponent")
                y_coor_component += 50
        y_coor_component += 10

    last_scc = []
    last_scc_none = []

    for i in range(len(scc_list_full)):
        # print(scc_list_full[i])
        last_scc = scc_list_full[i]

    for i in range(len(last_scc)):
        if len(last_scc[i]) != 1:
            last_scc_none.append(last_scc[i])
    print(last_scc_none)

    period_two = True
    period_two_break = False

    for i in range(len(last_scc_none)):
        if period_two_break == True:
            break
        for j in range(len(last_scc_none[i])):
            if matrix[int(last_scc_none[i][j])][int(last_scc_none[i][j])] != 0:
                period_two = False
                break
            if j == len(last_scc_none[i]) - 1 and period_two:
                period_two_break = True
                break

    # TODO: розрахунок та вивід періоди
    print("Perioda: ")


def print_graph(dist, graph):
    for i in range(len(graph[0])):
        for j in range(len(graph[0])):
            if dist[i][j] == 9999:
                print("%7s" % ("INF"), end=" ")
            else:
                print("%7d" % (dist[i][j]), end=' ')
            if j == len(graph[0]) - 1:
                print()

def algorithm(entry1, entries, var_is_convex, var_is_concave, var_is_monge):
    n=entry1.get()
    n=int(n)

    # is_monge = True
    # var_is_monge.set("Matrix is monge: yes")

    is_convex = True
    var_is_convex.set("Matica je konvexná: Áno")

    is_concave = True
    var_is_concave.set("Matica je konkávna: Áno")

    matrix_algorithm = matrix_to_list(entry1, entries)
    print(matrix_algorithm)
    for stepi in range(1, n):
        for stepj in range(1, n):
            for i in range(n - stepi):
                for j in range(n - stepj):
                    # get for pos
                    x1 = int(matrix_algorithm[(n) * i + j])
                    x2 = int(matrix_algorithm[(n) * (i + stepi) + (j + stepj)])
                    y1 = int(matrix_algorithm[(n) * (i) + (j + stepj)])
                    y2 = int(matrix_algorithm[(n) * (i + stepi) + (j)])
                    first = x1 if x1 < x2 else x2
                    second = y1 if y1 < y2 else y2

                    if first > second:
                        is_convex = False
                        var_is_convex.set("Matica je konvexná: Nie")
                    if first < second:
                        is_concave = False
                        var_is_concave.set("Matica je konkávna: Nie")
                    # if not is_concave and not is_convex:
                    #     is_monge = False
                    #     var_is_monge.set("Matrix is monge: no")

    # print(is_convex)
    # print(is_concave)
    # print(is_monge)

def matrix_to_list(entry1, entries):
    matrix_algorithm = get_matrix(entry1, entries)
    matrix_algorithm = np.asarray(matrix_algorithm).reshape(-1)
    print(matrix_algorithm)
    # print(get_matrix(entry1, entries))
    return matrix_algorithm

def get_matrix(entry1, entries):
    if entry1.get():
        matrix = []
        for i in range(int(entry1.get())):
            matrix.append([])
            for j in range(int(entry1.get())):
                matrix[i].append(entries[i][j].get())
        # print(matrix)
        return matrix

def matrix(entry1, entries, window, canvas1, var_is_convex, var_is_concave):
    x1 = entry1.get()
    x1 = int(x1)
    # print(x1)
    x_coor = 0
    y_coor = 0

    if entries:
        for i in range(len(entries)):
            for j in range(len(entries)):
                entries[i][j].destroy()
        entries.clear()

    for x in range(x1):
        entries.append([])
        for y in range(x1):
            # print(x, y)
        # label1 = tk.Label(window, text=float(x1))
        # canvas1.create_window(200, 230, window=label1)
            entries[x].append(ttk.Entry(window, width=3, font=('Arial', 25)))
            # entry2 = tk.Entry(window)
            canvas1.create_window(40 + x_coor, 130 + y_coor, window = entries[x][y], width=50)

            # if x == x1-1 and y == x1-1:
            #     entries[x][y].grid(row=x + 4, column=y + 1, sticky=tk.NW, pady=(2, 100), padx=(0,100))
            # else:
            #     entries[x][y].grid(row=x + 4, column=y + 1, sticky=tk.NW, pady=2)

            x_coor += 60
        y_coor += 60
        x_coor = 0

    var_is_convex.set("Matica je konvexná: ")
    var_is_concave.set("Matica je konkávna: ")
    # var_scc.set("SCC = {}")
    canvas1.delete("komponent")

def main():
    entries = []
    entries_k = []

    graph = [[0, 3, 0, 0],
             [1, 0, 0, 3],
             [0, 3, 0, 0],
             [0, 0, 2, 0]]

    matrixI = [[0, 3, 0, 0],
               [1, 0, 0, 3],
               [0, 3, 0, 0],
               [0, 0, 2, 0]]

    Time = 0
    graph_scc = defaultdict(list)


    # window = ThemedTk(theme="black")
    window = tk.Tk()
    window.title("Matica")
    # myFont = tkFont.Font(size=25)
    # window.geometry()
    # window.configure(bg='black')
    # style = ThemedStyle(window)
    # style.set_theme('radiance')
    canvas1 = tk.Canvas(window, width=1400, height=750)
    canvas1.pack()

    # var0 = tk.StringVar()
    # label0 = ttk.Label(window, textvariable=var0, relief=RAISED)
    # var0.set("...")
    # label0.grid(row=0, column=0, sticky=tk.W, pady=2, columnspan=2)

    var = tk.StringVar()
    label1 = ttk.Label(window, textvariable=var, relief=RAISED, font=('Arial', 25))
    var.set("Zadajte rozmer matice (n > 1)")
    canvas1.create_window(220, 20, window=label1)
    # label1.grid(row=0, column=0, sticky= tk.W, pady= 2, columnspan=2)

    var1 = tk.StringVar()
    label2 = ttk.Label(window, textvariable=var1, relief=RAISED, font=('Arial', 25))
    var1.set("n: ")
    # label2.grid(row=1, column=0, sticky= tk.W, pady= 2)

    canvas1.create_window(19, 65, window=label2)

    entry1 = ttk.Entry(window, width=5, font=('Arial', 25))
    # entry1.grid(row=1, column=0, sticky= tk.W, pady= 2, padx=25)

    # graph_scc = matrix_to_h(entry1, entries, graph_scc)
    # SCC(entry1, Time, graph_scc)

    canvas1.create_window(100, 65, window=entry1)

    button1 = tk.Button(text='Ok', font=('Arial', 25), command= lambda: matrix(entry1, entries, window, canvas1, var_is_convex, var_is_concave))
    # button1.grid(row=2, column=0, sticky= tk.W, pady= 2)
    canvas1.create_window(200, 70, window=button1)

    # var2 = tk.StringVar()
    # label3 = ttk.Label(window, textvariable=var2, relief=RAISED)
    # var2.set("Matrix")
    # label3.grid(row=3, column=0, sticky=tk.W, pady=2)

    button2 = ttk.Button(text='Get Matrix', command=lambda: get_matrix(entry1, entries))
    # canvas1.create_window(200, 380, window=button2)

    button3= ttk.Button(text='Matrix List', command= lambda: matrix_to_list(entry1, entries))
    # canvas1.create_window(200, 400, window=button3)

    button4= tk.Button(text='Algoritmus', font=('Arial', 25), command= lambda: algorithm(entry1, entries, var_is_convex, var_is_concave, """var_is_monge"""))
    canvas1.create_window(800, 90, window=button4)
    # button4.grid(row=0, column=1)

    # button5 = ttk.Button(text='Graph', command= lambda: floyd_warshall(graph, window, canvas1))
    # button5.grid(row=0, column=2)

    var_is_convex = tk.StringVar()
    label_is_convex = ttk.Label(window, textvariable=var_is_convex, relief=RAISED, font=('Arial', 25))
    var_is_convex.set("Matica je konvexná: ")
    canvas1.create_window(800, 150, window=label_is_convex)

    var_is_concave = tk.StringVar()
    label_is_concave = ttk.Label(window, textvariable=var_is_concave, relief=RAISED, font=('Arial', 25))
    var_is_concave.set("Matica je konkávna: ")
    canvas1.create_window(800, 190, window=label_is_concave)

    # var_is_monge = tk.StringVar()
    # label_is_monge = ttk.Label(window, textvariable=var_is_monge, relief=RAISED)
    # var_is_monge.set("Matrix is monge: ")
    # canvas1.create_window(482, 210, window=label_is_monge)

    scc_list = []

    button6 = tk.Button(text='SCC*', font=('Arial', 25), command=lambda: floyd_warshall(entry1, entries, window, canvas1, graph_scc, Time))
    canvas1.create_window(800, 250, window=button6)
    # SCC = {K1, K2}
    # K1 = 0, 1, 2, 3

    # var_scc = tk.StringVar()
    # label_scc = ttk.Label(window, textvariable=var_scc, relief=RAISED, font=('Arial', 25))
    # var_scc.set("SCC = {}")
    # canvas1.create_window(800, 300, window=label_scc)

    button7 = tk.Button(text="Graf", font=('Arial', 25), command=lambda: draw_graph(entry1, entries))
    canvas1.create_window(800, 25, window=button7)

    button8 = tk.Button(text="Perioda", font=('Arial', 25), command=lambda: period_matrix(entry1, entries, window, canvas1, Time))
    canvas1.create_window(1100, 25, window=button8)

    window.mainloop()

if __name__ == "__main__":
    main()