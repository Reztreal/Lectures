package main.fhms;

/**
 * Insurance class stores insurance information.
 */
public class Insurance {
    private int type;
    final private String description[] = {
            "Undefined insurance type",
            "Full insurance",
            "Partial insurance",
            "No insurance"};

    /**
     * Constructor of an insurance instance.
     *
     * @param t_type Insurance type.
     */
    public Insurance(int t_type) {
        if (t_type < 1 || t_type > 3)
            t_type = 0; // undefined insurance type

        type = t_type;
    }


    /**
     * This function applies insurance to raw cost according to insurance
     * type.
     *
     * @param cost Cost without insurance applied.
     * @param must This parameter specify treatment is must or not.
     * @return float Insurance applied cost.
     */
    public float applyInsurenceToCost(float cost, boolean must) {
        if (type == 1) {
            return fullInsurance();
        } else if (type == 2) {
            return partialInsurance(cost, must);
        } else if (type == 3) {
            return noInsurance(cost);
        }

        System.out.println("Undefined insurence type.");
        return cost; // means there is no matched insurance type.
    }

    /**
     * Full insurance (type1) means full insurance.
     *
     * @return float Full insurance applied cost.
     */
    private float fullInsurance() {
        return 0.0f;
    }

    /**
     * Partial insurance (type2) means if the treatment is must, it is count
     * as full insurance (type1). If not, it is count as no insurance (type3).
     *
     * @param cost Cost without insurance applied.
     * @param must This parameter specify treatment is must or not.
     * @return float Partial insurance applied cost.
     */
    private float partialInsurance(float cost, boolean must) {
        if (must)
            return fullInsurance();

        return noInsurance(cost);
    }

    /**
     * No insurance (type3) means no insurance.
     *
     * @param cost Cost without insurance applied.
     * @return float No insurance applied cost.
     */
    private float noInsurance(float cost) {
        return cost;
    }

    /**
     * Standart getter of Insurance.type
     *
     * @return int Insurance.type
     */
    public int getType() {
        return type;
    }

    /**
     * Standart setter of Insurance.type
     *
     * @param type Insurance.type can be only 1, 2 or 3.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Returns description according to current insurance type.
     *
     * @return String Insurance.description[type]
     */
    public String getDescription() {
        return description[type];
    }
}
