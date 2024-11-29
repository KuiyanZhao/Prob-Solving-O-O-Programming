import java.io.*;
import java.util.ArrayList;

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
public class FoundationDatabase {
    /* The source file used to generate the SCP array. */
    private String scpIn;

    /* The source file used to generate the Researcher array. */
    private String researcherIn;

    /* The destination file that the database should be written to. */
    private String databaseOutput;

    /* An array of SCP objects. */
    private SCP[] scp;

    /* An array of Researcher objects. */
    private Researcher[] researcher;

    public FoundationDatabase(String scpIn, String researcherIn, String databaseOutput) {
        this.scpIn = scpIn;
        this.researcherIn = researcherIn;
        this.databaseOutput = databaseOutput;
    }

    public boolean addResearcher(int itemNumber, String data) {
        Researcher newResearcher;
        try {
            newResearcher = new Researcher(data);
        } catch (BadDataException e) {
            newResearcher = new Researcher(e);
        }

        boolean isNotExist = true;
        for (int i = 0; i < researcher.length; i++) {
            if (researcher[i].equals(newResearcher)) {
                isNotExist = false;
                break;
            }
        }

        // add new researcher to the array
        if (isNotExist) {
            Researcher[] researcher2 = new Researcher[researcher.length + 1];
            for (int i = 0; i < researcher.length; i++) {
                researcher2[i] = researcher[i];
            }
            researcher2[researcher.length] = newResearcher;
            researcher = researcher2;
        }

        int index = -1;
        boolean flag = false;
        for (int i = 0; i < scp.length; i++) {
            if (scp[i].getItemNumber() == itemNumber) {
                flag = true;
                index = i;
                break;
            }
        }

        // the SCP does not exist
        if (!flag) {
            return false;
        } else {
            if (newResearcher.compatible(scp[index])) {
                Researcher[] scpResearchers = scp[index].getResearchers();
                if (scpResearchers == null) {
                    scp[index].setResearchers(new Researcher[]{newResearcher});
                } else {
                    for (int ii = 0; ii < scpResearchers.length; ii++) {
                        if (scpResearchers[ii].equals(newResearcher)) {
                            return false;
                        }
                    }

                    Researcher[] newScpResearchers = new Researcher[scpResearchers.length + 1];
                    for (int ii = 0; ii < scpResearchers.length; ii++) {
                        newScpResearchers[ii] = scpResearchers[ii];
                    }
                    newScpResearchers[scpResearchers.length] = newResearcher;
                    scp[index].setResearchers(newScpResearchers);
                }
            } else {
                return false;
            }

            return true;
        }
    }

    public boolean autoAssignResearcher() {
        boolean[] avaliable = new boolean[researcher.length];
        for (int i = 0; i < avaliable.length; i++) {
            avaliable[i] = true;
        }

        for (int i = 0; i < scp.length; i++) {
            scp[i].setResearchers(null);
            boolean isMatch = false;
            for (int j = 0; j < researcher.length; j++) {
                // already allocate
                if (!avaliable[j]) {
                    continue;
                }

                if (researcher[j].compatible(scp[i])) {
                    if (researcher[j].getClearance() == scp[i].getClearanceLevel()) {
                        scp[i].setResearchers(new Researcher[]{researcher[j]});
                        avaliable[j] = false;
                        isMatch = true;
                        break;
                    }
                }
            }

            if (!isMatch) {
                for (int j = 0; j < researcher.length; j++) {
                    // already allocate
                    if (!avaliable[j]) {
                        continue;
                    }

                    if (researcher[j].compatible(scp[i])) {
                        scp[i].setResearchers(new Researcher[]{researcher[j]});
                        avaliable[j] = false;
                        break;
                    }
                }
            }
        } // end for

        for (int i = 0; i < scp.length; i++) {
            if (!"Bad SCP Data".equals(scp[i].getObjectName())
                    && scp[i].getResearchers() == null) {
                return false;
            }
        }

        return true;
    }

    public boolean modifyResearcher(String oldData, String newData) {
        Researcher oldResearcher;
        try {
            oldResearcher = new Researcher(oldData);
        } catch (BadDataException e) {
            oldResearcher = new Researcher(e);
        }

        Researcher newResearcher;
        try {
            newResearcher = new Researcher(newData);
        } catch (BadDataException e) {
            newResearcher = new Researcher(e);
            return false;
        }

        // not found in the searcher array
        int index = -1;
        boolean flag = false;
        for (int i = 0; i < researcher.length; i++) {
            if (researcher[i].equals(oldResearcher)) {
                flag = true;
                index = i;
                break;
            }
        }

        if (!flag) {
            return false;
        } else {
            researcher[index] = newResearcher;
            if (oldResearcher.getClassification() != newResearcher.getClassification()
                    || oldResearcher.getClearance() != newResearcher.getClearance()) {
                autoAssignResearcher();
            }
            return true;
        }
    }

    public boolean modifySCP(String oldData, String newData) {
        SCP oldScp;
        try {
            oldScp = new SCP(oldData);
        } catch (BadDataException e) {
            oldScp = new SCP(e);
        }

        SCP newScp;
        try {
            newScp = new SCP(newData);
        } catch (BadDataException e) {
            newScp = new SCP(e);
            return false;
        }

        // not found in the SCP array
        int index = -1;
        boolean flag = false;
        for (int i = 0; i < scp.length; i++) {
            if (scp[i].equals(oldScp)) {
                flag = true;
                index = i;
                break;
            }
        }

        if (!flag) {
            return false;
        } else {
            scp[index] = newScp;
            autoAssignResearcher();
            return true;
        }
    }

    public boolean outputDatabase() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseOutput))) {
            for (int i = 0; i < scp.length; i++) {
                writer.write(scp[i].toString());
                if (scp[i].getResearchers() == null) {
                    writer.write(",VACANT");
                } else {
                    Researcher[] researchers = scp[i].getResearchers();
                    for (int j = 0; j < researchers.length; j++) {
                        writer.write(",");
                        writer.write(researchers[j].toString());
                    }
                }
                writer.write("\n");
                writer.flush();
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean readResearcher() {
        ArrayList<Researcher> researcherArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(researcherIn))) {
            String in = "";
            Researcher researcher1;
            while ((in = reader.readLine()) != null) {
                try {
                    researcher1 = new Researcher(in);
                } catch (BadDataException e) {
                    researcher1 = new Researcher(e);
                }
                researcherArrayList.add(researcher1);
            }
        } catch (IOException e) {
            return false;
        }

        researcher = researcherArrayList.toArray(new Researcher[0]);
        return true;
    }

    public boolean readSCP() {
        ArrayList<SCP> scpArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(scpIn))) {
            String in = "";
            SCP scp1;
            while ((in = reader.readLine()) != null) {
                try {
                    scp1 = new SCP(in);
                } catch (BadDataException e) {
                    scp1 = new SCP(e);
                }
                scpArrayList.add(scp1);
            }
        } catch (IOException e) {
            return false;
        }

        scp = scpArrayList.toArray(new SCP[0]);
        return true;
    }
}
