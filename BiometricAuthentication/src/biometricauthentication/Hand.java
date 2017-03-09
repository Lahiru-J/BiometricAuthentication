package biometricauthentication;

import java.io.Serializable;

/**
 *
 * @author Lahiru
 */
public class Hand implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /* Length of specific finger*/
    private double LittleFingerLen;
    private double RingFingerLen;
    private double MiddleFingerLen;
    private double IndexFingerLen;
    private double PalmLength1;
    private double PalmLength2;

    public Hand(double LittleFingerLen, double RingFingerLen, double MiddleFingerLen,
            double IndexFingerLen, double PalmLength1, double PalmLength2) {

        this.LittleFingerLen = LittleFingerLen;
        this.RingFingerLen = RingFingerLen;
        this.MiddleFingerLen = MiddleFingerLen;
        this.IndexFingerLen = IndexFingerLen;
        this.PalmLength1 = PalmLength1;
        this.PalmLength2 = PalmLength2;
    }

    public Hand(String userId, double LittleFingerLen, double RingFingerLen,
            double MiddleFingerLen, double IndexFingerLen, double PalmLength1,
            double PalmLength2) {
        this.userId = userId;
        this.LittleFingerLen = LittleFingerLen;
        this.RingFingerLen = RingFingerLen;
        this.MiddleFingerLen = MiddleFingerLen;
        this.IndexFingerLen = IndexFingerLen;
        this.PalmLength1 = PalmLength1;
        this.PalmLength2 = PalmLength2;
    }

    public double getLittleFingerLen() {
        return LittleFingerLen;
    }

    public double getRingFingerLen() {
        return RingFingerLen;
    }

    public double getPalmLength1() {
        return PalmLength1;
    }

    public double getPalmLength2() {
        return PalmLength2;
    }

    public double getMiddleFingerLen() {
        return MiddleFingerLen;
    }

    public double getIndexFingerLen() {
        return IndexFingerLen;
    }

}
