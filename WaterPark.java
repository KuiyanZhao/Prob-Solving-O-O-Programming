import java.util.ArrayList;

/**
 * HW-09 -- Challenge
 * <p>
 * Manage amusement parks and water parks.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-03-15
 */
public class WaterPark implements Park {
    private double admissionCost;
    private boolean indoor;
    private double land;
    private boolean lazyRiver;
    private String name;
    private boolean outdoor;
    private ArrayList<Ride> rides;
    private boolean[] seasons;
    private boolean wavePool;

    public WaterPark(String name, double admissionCost, double land,
                     ArrayList<Ride> rides, boolean indoor, boolean outdoor,
                     boolean lazyRiver, boolean wavePool, boolean[] seasons) {
        this.admissionCost = admissionCost;
        this.name = name;
        this.land = land;
        this.rides = rides;
        this.indoor = indoor;
        this.outdoor = outdoor;
        this.lazyRiver = lazyRiver;
        this.wavePool = wavePool;
        this.seasons = seasons;
    }

    @Override
    public void addRide(Ride ride) throws WrongRideException {
        if (!(ride instanceof Waterslide)) {
            throw new WrongRideException("A waterpark can only have waterslide rides!");
        }
        this.rides.add(ride);
    }

    @Override
    public void close() {
        name = "";
        admissionCost = 0;
        land = 0;
        rides = null;
        seasons = null;
        indoor = false;
        outdoor = false;
        lazyRiver = false;
        wavePool = false;
    }

    @Override
    public void enlarge(double addedLand, double maxLand, boolean addedIndoor, boolean addedOutdoor)
            throws SpaceFullException {
        if (land + addedLand > maxLand) {
            throw new SpaceFullException("There is no more land to use for this park!");
        }

        this.land += addedLand;

        if (addedIndoor) {
            indoor = true;
        }

        if (addedOutdoor) {
            outdoor = true;
        }
    }

    @Override
    public double getAdmissionCost() {
        return admissionCost;
    }

    @Override
    public double getLand() {
        return land;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Ride> getRides() {
        return rides;
    }

    @Override
    public boolean[] getSeasons() {
        return seasons;
    }

    @Override
    public boolean isIndoor() {
        return indoor;
    }

    @Override
    public boolean isOutdoor() {
        return outdoor;
    }

    @Override
    public void removeRide(Ride ride) {
        rides.remove(ride);
    }

    @Override
    public void setAdmissionCost(double admissionCost) {
        this.admissionCost = admissionCost;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSeasons(boolean[] seasons) {
        this.seasons = seasons;
    }

    public boolean isLazyRiver() {
        return lazyRiver;
    }

    public boolean isWavePool() {
        return wavePool;
    }

    public void modifyRide(Ride ride, String newName, String newColor,
                           int newMinHeight, int newMaxRiders, double mewSplashDepth) {
        int index = rides.indexOf(ride);
        Ride oldRide = rides.get(index);
        oldRide.setName(newName);
        oldRide.setColor(newColor);
        oldRide.setMinHeight(newMinHeight);
        oldRide.setMaxRiders(newMaxRiders);
        if (oldRide instanceof Waterslide) {
            ((Waterslide) oldRide).setSplashDepth(mewSplashDepth);
        }
    }

    public void setLazyRiver(boolean lazyRiver) {
        this.lazyRiver = lazyRiver;
    }

    public void setWavePool(boolean wavePool) {
        this.wavePool = wavePool;
    }
}
