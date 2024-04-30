package ru.nsu.yukhnina;

public class Position {
    private double xPos;
    private double yPos;
    private int width;
    private int height;

    /**
     * @param xPos
     * @param yPos
     * @param width
     * @param height
     */
    public Position(double xPos, double yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public double getXPos() {
        return xPos % width;
    }

    public double getYPos() {
        return yPos % height;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos % width;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos % height;
    }

}
