
public class Main {
    public static void main(String[] args) {
        Weight tomato = new Vegetables();
        WeightAdapter tomatoAdapter = new WeightAdapterImpl(tomato);

        System.out.println(tomatoAdapter.getKg());
    }
}
