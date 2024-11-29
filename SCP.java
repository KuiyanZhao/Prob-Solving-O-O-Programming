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
public class SCP {
    private int clearanceLevel;
    private boolean contact;
    private int itemNumber;
    private String objectClass;
    private String objectName;
    private Researcher[] researchers;

    public SCP(BadDataException e) {
        this.objectName = "Bad SCP Data";
        String temp = e.getMessage().split(":")[0];
        this.itemNumber = Integer.parseInt(temp.substring(4));
        this.clearanceLevel = 0;
        this.contact = false;
        this.objectClass = null;
        this.researchers = null;
    }

    public SCP(String data) throws BadDataException {
        String[] datas = data.split(",");
        if (datas.length != 5) {
            throw new BadDataException(String.format("%s: Bad SCP Data", datas[0]));
        }

        try {
            this.itemNumber = Integer.parseInt(datas[0].substring(4));
            this.clearanceLevel = Integer.parseInt(datas[3]);
        } catch (NumberFormatException e) {
            throw new BadDataException(String.format("%s: Bad SCP Data", datas[0]));
        }

        this.objectName = datas[1];

        if (!"SAFE".equals(datas[2])
                && !"EUCLID".equals(datas[2])
                && !"KETER".equals(datas[2])
                && !"THAUMIEL".equals(datas[2])
                && !"NEUTRALIZED".equals(datas[2])
                && !"DECOMMISSIONED".equals(datas[2])
                && !"APOLLYON".equals(datas[2])
                && !"ARCHON".equals(datas[2])) {
            throw new BadDataException(String.format("%s: Bad SCP Data", datas[0]));
        }
        this.objectClass = datas[2];
        this.contact = Boolean.parseBoolean(datas[4]);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof SCP)) return false;

        SCP scp = (SCP) obj;
        if ("Bad SCP Data".equals(this.objectName)
                || "Bad SCP Data".equals(scp.getObjectName())) {
            return false;
        }

        return this.itemNumber == scp.getItemNumber();
    }

    public int getClearanceLevel() {
        return clearanceLevel;
    }

    public boolean isContact() {
        return contact;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public String getObjectName() {
        return objectName;
    }

    public Researcher[] getResearchers() {
        return researchers;
    }

    public void setClearanceLevel(int clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setResearchers(Researcher[] researchers) {
        this.researchers = researchers;
    }

    @Override
    public String toString() {
        return String.format("SCP-%d,%s,%s,%d,%b",
                itemNumber, objectName, objectClass, clearanceLevel, contact);
    }
}
