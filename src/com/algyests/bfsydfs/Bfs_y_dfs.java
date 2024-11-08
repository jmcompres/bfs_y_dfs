package com.algyests.bfsydfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs_y_dfs {

    private static List<List<Character>> generarListaAdyacencia(int[][] matriz)
    {
        List<List<Character>> listaAdyacencia = new ArrayList<>();
        int n = matriz.length;

        for (int i = 0; i<n; i++)
        {
            listaAdyacencia.add(new ArrayList<Character>());
            for (int j = 0; j<n; j++)
                if (matriz[i][j] == 1) listaAdyacencia.get(i).add((char) ('A'+j));
        }

        return listaAdyacencia;
    }

    private static List<Character> bfs(List<List<Character>> grafo, char nodoInicial)
    {
        List<Character> listaRecorrido = new LinkedList<Character>();

        Queue<Character> colaNodos = new LinkedList<Character>();
        boolean[] nodosVisitados = new boolean[grafo.size()];

        colaNodos.offer(nodoInicial);
        nodosVisitados[nodoInicial-'A'] = true;

        while (!colaNodos.isEmpty())
        {
            char nodoActual = colaNodos.poll();
            listaRecorrido.add(nodoActual);
            for (Character nodoAdyacente : grafo.get(nodoActual-'A'))
            {
                if (!nodosVisitados[nodoAdyacente-'A'])
                {
                    colaNodos.offer(nodoAdyacente);
                    nodosVisitados[nodoAdyacente-'A'] = true;
                }
            }
        }

        return listaRecorrido;
    }

    private static List<Character> recursionDFS(List<List<Character>> grafo, char nodoActual, boolean[] nodosVisitados)
    {
        nodosVisitados[nodoActual-'A'] = true;
        List<Character> listaRecorrido = new LinkedList<Character>();

        for (Character nodoAdyacente : grafo.get(nodoActual-'A'))
        {
            if (!nodosVisitados[nodoAdyacente-'A'])
            {
                listaRecorrido.add(nodoAdyacente);
                listaRecorrido.addAll(recursionDFS(grafo, nodoAdyacente, nodosVisitados));
            }
        }

        return listaRecorrido;
    }

    private static List<Character> dfs(List<List<Character>> grafo, char nodoInicial)
    {
        boolean[] nodosVisitados = new boolean[grafo.size()];
        List<Character> listaRecorrido = new LinkedList<Character>();
        listaRecorrido.add(nodoInicial);
        listaRecorrido.addAll(recursionDFS(grafo, nodoInicial, nodosVisitados));

        return listaRecorrido;
    }

    public static void main(String[] args) {

        int[][] matriz = {{0,1,0,0,1,0,1,0,0,0},    //Matriz de int para no tener que escribir tantos trues y falses
                          {1,0,1,1,0,0,0,0,0,0},
                          {0,1,0,0,1,0,0,1,1,0},
                          {0,1,0,0,0,0,0,1,1,0},
                          {1,0,1,0,0,0,0,0,0,1},
                          {0,0,0,0,0,0,0,1,1,0},
                          {1,0,0,0,0,0,0,0,0,1},
                          {0,0,1,1,0,1,0,0,1,1},
                          {0,0,1,1,0,1,0,1,0,0},
                          {0,0,0,0,1,0,1,1,0,0}};

        List<List<Character>> listaAdyacencia = generarListaAdyacencia(matriz);
        
        List<Character> recorridoBFS = bfs(listaAdyacencia, 'A');
        System.out.println("Recorrido BFS empezando desde el nodo A: ");
        for (Character nodoActual : recorridoBFS)
            System.out.print(nodoActual+" ");

        System.out.println("\n");

        List<Character> recorridoDFS = dfs(listaAdyacencia, 'A');
        System.out.println("Recorrido DFS empezando desde el nodo A: ");
        for (Character nodoActual : recorridoDFS)
            System.out.print(nodoActual+" ");

    }

}
