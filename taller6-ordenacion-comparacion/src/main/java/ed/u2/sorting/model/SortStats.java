package ed.u2.sorting.model;

public class SortStats {
    public String algorithmName;
    public long comparisons = 0;
    public long swaps = 0;
    public long timeNano = 0;

    public SortStats(String name) {
        this.algorithmName = name;
    }
}