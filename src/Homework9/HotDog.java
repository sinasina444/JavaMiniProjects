package Homework9;


public class HotDog {

    private final Bun bun;

    private final Sausage sausage;

    private final Condiment condiment;

    public HotDog(Bun bun, Sausage sausage, Condiment condiment) {
        this.bun = bun;
        this.sausage = sausage;
        this.condiment = condiment;
    }

    public Bun getBun() {
        return bun;
    }

    public Sausage getSausage() {
        return sausage;
    }

    public Condiment getCondiment() {
        return condiment;
    }

    @Override public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        HotDog hotDog = (HotDog) other;

        if (bun != null ? !bun.equals(hotDog.bun) : hotDog.bun != null) {
            return false;
        }
        if (sausage != null ? !sausage.equals(hotDog.sausage) : hotDog.sausage != null) {
            return false;
        }
        return condiment != null ? condiment.equals(hotDog.condiment) : hotDog.condiment == null;

    }

    @Override public int hashCode() {
        int result = bun != null ? bun.hashCode() : 0;
        result = 31 * result + (sausage != null ? sausage.hashCode() : 0);
        result = 31 * result + (condiment != null ? condiment.hashCode() : 0);
        return result;
    }
}
