import java.util.LinkedList;
import java.util.List;

class HashTableFunction1 extends HashTable {
    private List<String>[] table;
    private int collisions;

    @SuppressWarnings("unchecked")
    public HashTableFunction1(int size) {
        super(size);
        collisions = 0;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    protected int hashFunction(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = 31 * hash + c;
        }
        return Math.abs(hash) % size;
    }

    @Override
    public void insert(String key) {
        int index = hashFunction(key);
        List<String> bucket = table[index];

        if (!bucket.isEmpty()) {
            collisions++;
        }

        bucket.add(key);
    }

    @Override
    public String search(String key) {
        int index = hashFunction(key);
        List<String> bucket = table[index];

        for (String k : bucket) {
            if (k.equals(key)) {
                return k;
            }
        }
        return null;
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
    public void printKeyDistribution() {
        int filledSlots = 0;
        int clusters = 0;

        System.out.println("Distribuição de chaves - Tabela 1:");

        for (int i = 0; i < size; i++) {
            List<String> bucket = table[i];
            if (bucket != null && !bucket.isEmpty()) {
                filledSlots++;
                System.out.println("Posição " + i + " contém " + bucket.size() + " chave(s): " + bucket);
                if (bucket.size() > 1) {
                    clusters++;
                }
            } else {
                System.out.println("Posição " + i + " está vazia.");
            }
        }

        System.out.println("Total de posições ocupadas: " + filledSlots);
        System.out.println("Total de clusters (posições com múltiplas chaves): " + clusters);
        System.out.println("Total de posições vazias: " + (size - filledSlots));
    }

}
