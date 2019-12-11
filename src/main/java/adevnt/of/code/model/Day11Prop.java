package adevnt.of.code.model;

import java.math.BigInteger;

public class Day11Prop {
    private int x = 0;
    private int y = 0;
    private BigInteger manualInput = BigInteger.ZERO;
    private int panelCount = 0;
    private int dir = 0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BigInteger getManualInput() {
        return manualInput;
    }

    public void setManualInput(BigInteger manualInput) {
        this.manualInput = manualInput;
    }

    public int getPanelCount() {
        return panelCount;
    }

    public void setPanelCount(int panelCount) {
        this.panelCount = panelCount;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
