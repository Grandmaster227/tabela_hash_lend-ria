import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "names_5000.csv";
        HashTable table1 = new HashTableFunction1(1000);
        HashTable table2 = new HashTableFunction2(1000);

        // lê arquivo CSV
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // mede o tempo de inserção e busca da Tabela 1
        long insertTimeTable1 = table1.measureInsertionTime(names);
        long searchTimeTable1 = table1.measureSearchTime(names);

        // mede o tempo de inserção e busca da Tabela 2
        long insertTimeTable2 = table2.measureInsertionTime(names);
        long searchTimeTable2 = table2.measureSearchTime(names);
        
        System.out.println(" RELATÓRIO DEFINITIVO");

        System.out.println("\nTabela 1 (HashTableFunction1):");
        System.out.println("Número total de colisões: " + table1.getCollisions());
        System.out.println("Tabela 1 - Tempo total de inserção: " + (insertTimeTable1 / 1_000_000.0) + " ms");
        System.out.println("Tabela 1 - Tempo total de busca: " + (searchTimeTable1 / 1_000_000.0) + " ms");

        System.out.println("\nTabela 2 (HashTableFunction2):");
        System.out.println("Número total de colisões: " + table2.getCollisions());
        System.out.println("Tabela 2 - Tempo total de inserção: " + (insertTimeTable2 / 1_000_000.0) + " ms");
        System.out.println("Tabela 2 - Tempo total de busca: " + (searchTimeTable2 / 1_000_000.0) + " ms");

        System.out.println("----------------------");

        System.out.println("Tabela 1 - Colisões: " + table1.getCollisions());
        System.out.println("Tabela 2 - Colisões: " + table2.getCollisions());


        System.out.println("\nDistribuição de chaves na Tabela 1:");

        table1.printKeyDistribution();

        System.out.println("\nDistribuição de chaves na Tabela 2:");

        table2.printKeyDistribution();

        System.out.println("-----------THE END-----------");
    }
}
