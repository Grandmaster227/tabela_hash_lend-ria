import java.util.List;

abstract class HashTable {
    protected int size;

    public HashTable(int size) {
        this.size = size;
    }

    protected abstract int hashFunction(String key);

    public abstract void insert(String key);

    public abstract String search(String key);

    public abstract int getCollisions();

    public long measureInsertionTime(List<String> keys) {
        long startTime = System.nanoTime();
        for (String key : keys) {
            insert(key);
        }
        return System.nanoTime() - startTime;
    }

    public long measureSearchTime(List<String> keys) {
        long startTime = System.nanoTime();
        for (String key : keys) {
            search(key);
        }
        return System.nanoTime() - startTime;
    }

    public abstract void printKeyDistribution();
}
