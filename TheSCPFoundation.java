import java.io.*;

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
public class TheSCPFoundation {
    public TheSCPFoundation() {

    }

    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        FoundationDatabase foundationDatabase = null;
        String in = "";
        String scpIn, researcherIn, dataOut, mainOut;
        int choose;
        boolean res;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            in = reader.readLine();
        } catch (IOException a) {
            System.out.println("IO Read Failure");
        }

        if ("".equals(in) || in == null) {
            System.out.println("IO Read Failure");
        } else {
            String[] filenames = in.split(" ");
            if (filenames.length != 4) {
                System.out.println("IO Read Failure");
            } else {
                scpIn = filenames[0];
                researcherIn = filenames[1];
                dataOut = filenames[2];
                mainOut = filenames[3];

                foundationDatabase = new FoundationDatabase(scpIn, researcherIn, dataOut);
                try {
                    writer = new BufferedWriter(new FileWriter(mainOut));
                    writer.write("Foundation Database Started\n");
                    writer.flush();

                    // handle command
                    try {
                        while ((in = reader.readLine()) != null) {
                            choose = Integer.parseInt(in);
                            if (choose == 1) {
                                res = foundationDatabase.readSCP();
                            } else if (choose == 2) {
                                res = foundationDatabase.readResearcher();
                            } else if (choose == 3) {
                                res = foundationDatabase.autoAssignResearcher();
                            } else if (choose == 4) {
                                String oldData = reader.readLine();
                                String newData = reader.readLine();
                                res = foundationDatabase.modifySCP(oldData, newData);
                            } else if (choose == 5) {
                                String oldData = reader.readLine();
                                String newData = reader.readLine();
                                res = foundationDatabase.modifyResearcher(oldData, newData);
                            } else if (choose == 6) {
                                int itemNumber = Integer.parseInt(reader.readLine());
                                String data = reader.readLine();
                                res = foundationDatabase.addResearcher(itemNumber, data);
                            } else if (choose == 7) {
                                res = foundationDatabase.outputDatabase();
                            } else {
                                try {
                                    writer.write("Command Failure\n");
                                    writer.flush();
                                } catch (IOException ex) {
                                    System.out.println("IO Write Failure");
                                }
                                break;
                            }

                            String line = "";
                            if (res) {
                                line = String.format("%d Success\n", choose);
                            } else {
                                line = String.format("%d Failure\n", choose);
                            }

                            try {
                                writer.write(line);
                                writer.flush();
                            } catch (IOException ex) {
                                System.out.println("IO Write Failure");
                            }

                        } // end while
                    } catch (NumberFormatException e) {
                        try {
                            writer.write("Command Failure\n");
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println("IO Write Failure");
                        }
                    } catch (IOException e) {
                        try {
                            writer.write("IO Read Failure\n");
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println("IO Write Failure");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("IO Write Failure");
                }
            } // end else
        }

        // close IO stream
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Close IO Failure");
            }
        }

        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("Close IO Failure");
            }
        }
    }
}
