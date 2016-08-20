package com.example.jannat.playerinformationwithfragment;

import java.io.Serializable;

/**
 * Created by BS151 on 7/20/2016.
 */
public class Player implements Serializable{
    private int imageId;
    private String name;
    private String batingStyle;
    private String bowlingStyle;
    private double batingAvgInT20;
    private double batingAvgInODI;
    private double bowlingAvgInT20;
    private double bowlingAvgInODI;
    private String role;


    public Player(int imageId, String name, String batingStyle, String bowlingStyle, double batingAvgInT20, double batingAvgInODI, double bowlingAvgInT20, double bowlingAvgInODI, String role) {
        this.imageId = imageId;
        this.name = name;
        this.batingStyle = batingStyle;
        this.bowlingStyle = bowlingStyle;
        this.batingAvgInT20 = batingAvgInT20;
        this.batingAvgInODI = batingAvgInODI;
        this.bowlingAvgInT20 = bowlingAvgInT20;
        this.bowlingAvgInODI = bowlingAvgInODI;
        this.role = role;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatingStyle() {
        return batingStyle;
    }

    public void setBatingStyle(String batingStyle) {
        this.batingStyle = batingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public double getBatingAvgInT20() {
        return batingAvgInT20;
    }

    public void setBatingAvgInT20(double batingAvgInT20) {
        this.batingAvgInT20 = batingAvgInT20;
    }

    public double getBatingAvgInODI() {
        return batingAvgInODI;
    }

    public void setBatingAvgInODI(double batingAvgInODI) {
        this.batingAvgInODI = batingAvgInODI;
    }

    public double getBowlingAvgInT20() {
        return bowlingAvgInT20;
    }

    public void setBowlingAvgInT20(double bowlingAvgInT20) {
        this.bowlingAvgInT20 = bowlingAvgInT20;
    }

    public double getBowlingAvgInODI() {
        return bowlingAvgInODI;
    }

    public void setBowlingAvgInODI(double bowlingAvgInODI) {
        this.bowlingAvgInODI = bowlingAvgInODI;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
