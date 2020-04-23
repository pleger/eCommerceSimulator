package OLD_Agents;

public class EndorsementList {
    public static final int ALTA_SEGURIDAD = 1;
    public static final int ALTA_VARIEDAD = 2;
    public static final int ALTA_CONFIANZA_VEN = 3;
    public static final int ALTA_CALIDAD = 4;
    public static final int ALTA_VERACIDAD = 5;

    public static final int BAJA_SEGURIDAD = 6;
    public static final int BAJA_VARIEDAD = 7;
    public static final int BAJA_CONFIANZA_VEN = 8;
    public static final int BAJA_CALIDAD = 9;
    public static final int BAJA_VERACIDAD = 10;

    /*relation between numbers above and endorsments list: an element in endorsment list MUST BE
    in position number-1 eg. ALTA_SEGURIDAD is number 1 and "alta seguridad" is in pos 1-1=0 of list;
    * */
    private static String[] endorsments = {"Alta Seguridad", "Alta Variedad", "Alta Confianza Vendedor",
            "Alta Calidad", "Alta Veracidad", "Baja Seguridad", "Baja Variedad", "Baja Confianza Vendedor",
            "Baja Calidad", "Baja Veracidad"};

    /**
     * Receives number of an endorsment and
     *
     * @return "name" of an endorsment
     */
    public static String getEndorsement(int endorsmentNumber) {
        return endorsments[endorsmentNumber - 1];
    }

    public static int searchEndorsmentNumber(String endorsment) {
        int i;
        for (i = 0; i < endorsments.length; i++) {
            if (endorsments[i].equals(endorsment)) break;
        }
        return i + 1;
    }

}