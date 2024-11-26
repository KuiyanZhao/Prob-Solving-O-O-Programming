/**
 * HW-06 -- Debug
 * <p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-02-15
 */
public class State {
    private String capital;
    private int population;

    public State(String capital, int population) {
        this.capital = capital;
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }


    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
