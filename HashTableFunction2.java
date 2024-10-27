class HashTableFunction2 extends HashTable {
    private String[] table;
    private int collisions;

    public HashTableFunction2(int size) {
        super(size);
        collisions = 0;
        table = new String[size]; //  armazena uma chave por posição
    }

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

        // verifica se já existe uma chave na posição e conta como colisão
        if (table[index] != null) {
            collisions++;
        }

        // substitui a chave existente, se houver colisão
        table[index] = key;
    }

    @Override
    public String search(String key) {
        int index = hashFunction(key);
        // retorna a chave se ela estiver na posição correspondente
        return table[index] != null && table[index].equals(key) ? table[index] : null;
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
    public void printKeyDistribution() {
        int filledSlots = 0;
        System.out.println("Distribuição de chaves - Tabela 2:");

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                filledSlots++;
                System.out.println("Posição " + i + " contém a chave: " + table[i]);
            } else {
                System.out.println("Posição " + i + " está vazia.");
            }
        }

        System.out.println("Total de posições ocupadas: " + filledSlots);
        System.out.println("Total de posições vazias: " + (size - filledSlots));
    }
}
