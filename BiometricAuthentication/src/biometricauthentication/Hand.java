package biometricauthentication;

import java.io.Serializable;

/**
 *
 * @author Lahiru
 */
public class Hand implements Serializable {

    /* Length of specific finger*/
    private double LittleFingerLen;
    private double RingFingerLen;
    private double MiddleFingerLen;
    private double IndexFingerLen;
    private double ThumbFingerLen;

    public Hand(double LittleFingerLen, double RingFingerLen, double MiddleFingerLen,
            double IndexFingerLen, double ThumbFingerLen) {

        this.LittleFingerLen = LittleFingerLen;
        this.RingFingerLen = RingFingerLen;
        this.MiddleFingerLen = MiddleFingerLen;
        this.IndexFingerLen = IndexFingerLen;
        this.ThumbFingerLen = ThumbFingerLen;
    }

    public double getLittleFingerLen() {
        return LittleFingerLen;
    }

    public double getRingFingerLen() {
        return RingFingerLen;
    }

    public double getMiddleFingerLen() {
        return MiddleFingerLen;
    }

    public double getIndexFingerLen() {
        return IndexFingerLen;
    }

    public double getThumbFingerLen() {
        return ThumbFingerLen;
    }

}
