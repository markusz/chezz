package model;

/**
 * Created by Markus on 28.06.2014.
 */
public enum Player {
    FIRST, SECOND;

    public int getId() {
        if (this == FIRST) {
            return 1;
        }
        return 2;
    }
}
