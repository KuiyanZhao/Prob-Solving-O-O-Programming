import java.util.stream.Stream;

/**
 * PJ03 -- Handout
 * <p>
 * Data clean.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author YourNameHere, YourLabSectionHere
 * @version 2024-03-21
 */
public class Researcher {
    /* The active status ofthe Researcher. */
    private boolean active;
    private char classification;
    /* The clearance level 1-5 of the Researcher. */
    private int clearance;
    private int idNumber;
    private String name;

    public Researcher(BadDataException e) {
        this.name = e.getMessage();
        this.active = false;
        this.classification = '\0';
        this.clearance = 0;
        this.idNumber = 0;
    }

    public Researcher(String data) throws BadDataException {
        String[] datas = data.split(",");
        if (datas.length != 5) {
            throw new BadDataException("Bad Researcher Data");
        }

        this.name = datas[0];

        try {
            this.idNumber = Integer.parseInt(datas[1]);
            this.clearance = Integer.parseInt(datas[2]);
        } catch (NumberFormatException e) {
            throw new BadDataException("Bad Researcher Data");
        }

        if (datas[3].length() != 1) {
            throw new BadDataException("Bad Researcher Data");
        }

        this.classification = datas[3].charAt(0);

        if (this.classification < 'A' || this.classification > 'Z') {
            throw new BadDataException("Bad Researcher Data");
        }

        this.active = Boolean.parseBoolean(datas[4]);

        if (this.classification == 'E' && this.active) {
            throw new BadDataException("Bad Researcher Data");
        }
    }

    public boolean compatible(SCP scp) {
        if (scp == null) {
            return false;
        }

        // condition 5
        if ("Bad SCP Data".equals(scp.getObjectName())
                || "Bad Researcher Data".equals(this.name)) {
            return false;
        }

        // condition 4
        if (this.clearance < scp.getClearanceLevel()) {
            return false;
        }

        // condition 3
        if (this.classification == 'B'
                && scp.isContact()
                && !"SAFE".equals(scp.getObjectClass())) {
            return false;
        }

        // condition 2
        if (this.classification == 'A' && scp.isContact()) {
            return false;
        }

        // condition 1
        if (this.classification == 'E' || !this.active) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Researcher)) return false;

        Researcher researcher = (Researcher) obj;
        if ("Bad Researcher Data".equals(this.name)
                || "Bad Researcher Data".equals(researcher.getName())) {
            return false;
        }

        return this.name.equals(researcher.getName())
                && this.idNumber == researcher.getIdNumber()
                && this.clearance == researcher.getClearance();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public char getClassification() {
        return classification;
    }

    public void setClassification(char classification) {
        this.classification = classification;
    }

    public int getClearance() {
        return clearance;
    }

    public void setClearance(int clearance) {
        this.clearance = clearance;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%d,%c,%b",
                name, idNumber, clearance, classification, active);
    }
}
