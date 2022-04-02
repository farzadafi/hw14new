public class WeightAdapterImpl implements WeightAdapter {

    private final Weight tomato;

    public WeightAdapterImpl(Weight tomato) {
        this.tomato = tomato;
    }

    private double convertMPHtoKMPH(double kg) {
        return kg * 1000.0;
    }

    @Override
    public double getKg() {
        return convertMPHtoKMPH(tomato.getKg());
    }
}
