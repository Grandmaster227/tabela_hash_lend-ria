import java.util.LinkedList;
import java.util.List;

class HashTableFunction2 extends HashTable {
    private List<String>[] table;
    private int collisions;

    @SuppressWarnings("unchecked")
    public HashTableFunction2(int size) {
        super(size);
        collisions = 0;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Função hash diferente da `HashTableFunction1`
    @Override
    protected int hashFunction(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += c;
        }
        return Math.abs(hash) % size;
    }

    @Override
    public void insert(String key) {
        int index = hashFunction(key);
        List<String> bucket = table[index];

        // Verifica se a posição já possui elementos (conta como colisão se não está vazia)
        if (!bucket.isEmpty()) {
            collisions++;
        }

        // Insere o novo elemento na lista encadeada no bucket correspondente
        bucket.add(key);
    }

    @Override
    public String search(String key) {
        int index = hashFunction(key);
        List<String> bucket = table[index];

        // Busca pela chave dentro do bucket
        for (String k : bucket) {
            if (k.equals(key)) {
                return k;
            }
        }
        return null; // Retorna null se a chave não for encontrada
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
    public void printKeyDistribution() {
        int filledSlots = 0;
        int clusters = 0; // Contador de clusters (posições com mais de uma chave)

        System.out.println("Distribuição de chaves - Tabela 2:");

        for (int i = 0; i < size; i++) {
            List<String> bucket = table[i];
            if (bucket != null && !bucket.isEmpty()) {
                filledSlots++;
                System.out.println("Posição " + i + " contém " + bucket.size() + " chave(s): " + bucket);
                if (bucket.size() > 1) {
                    clusters++; // Incrementa clusters quando há mais de uma chave na posição
                }
            } else {
                System.out.println("Posição " + i + " está vazia."); // Imprime se a posição estiver vazia
            }
        }

        System.out.println("Total de posições ocupadas: " + filledSlots);
        System.out.println("Total de clusters (posições com múltiplas chaves): " + clusters);
        System.out.println("Total de posições vazias: " + (size - filledSlots));
    }


}
