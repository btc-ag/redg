/*
 * This file was generated by RedG.
 * https://btc-redg.github.com/
 *       ____           ________
 *      / __ \___  ____/ / ____/
 *     / /_/ / _ \/ __  / / __
 *    / _, _/  __/ /_/ / /_/ /
 *   /_/ |_|\___/\__,_/\____/
 *
 * DO NOT EDIT MANUALLY!
 * Re-run the code generation to reflect database changes.
 */
package com.btc.redg.generated;

import java.util.ArrayList;
import java.util.List;

import com.btc.redg.runtime.*;
import com.btc.redg.models.*;

/**
 * <table summary="The table model attributes and their values">
 *     <tr>
 *         <td><strong>Attribute</strong></td>
 *         <td><strong>Value</strong></td>
 *     </tr>
 *     <tr>
 *         <td>Table name</td>
 *         <td>DATES_TABLE</td>
 *     </tr>
 *     <tr>
 *         <td>Full table name</td>
 *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
 *     </tr>
 * </table>
 */
public class GDatesTable implements RedGEntity {

    protected AbstractRedG redG;

    // do not manually make this public and instantiate it directly. Use the RedG Main class
    GDatesTable(AbstractRedG redG) {
        this.redG = redG;
        try {
            this.aDate = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.sql.Date.class);
            this.aTime = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.sql.Time.class);
            this.aTimestamp = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.sql.Timestamp.class);
            this.aTimestampWtz = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.lang.Object.class);
            this.bDate = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.sql.Date.class);
            this.bTime = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.sql.Time.class);
            this.bTimestamp = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.sql.Timestamp.class);
            this.bTimestampWtz = redG.getDefaultValueStrategy().getDefaultValue(getTableModel(), java.lang.Object.class);

        } catch (Exception e) {
            throw new RuntimeException("Could not get default value", e);
        }
    }

    GDatesTable(int meaningOfLife, AbstractRedG redG) {
        // First parameter exists simply because this constructor needs a different signature from the constructor above if the tables have no NOT NULL FK
        // Only for ExistingGDatesTable , otherwise NOT NULL constraints cannot be checked and no default values are generated.
        this.redG = redG;
    }

    private java.sql.Date aDate;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_DATE</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>DATE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable aDate(java.sql.Date value) {
        this.aDate = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_DATE</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>DATE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.sql.Date aDate() {
        return this.aDate;
    }

    private java.sql.Time aTime;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_TIME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIME</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable aTime(java.sql.Time value) {
        this.aTime = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_TIME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIME</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.sql.Time aTime() {
        return this.aTime;
    }

    private java.sql.Timestamp aTimestamp;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable aTimestamp(java.sql.Timestamp value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting aTimestamp to null violates a NOT NULL constraint!");
        }
        this.aTimestamp = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.sql.Timestamp aTimestamp() {
        return this.aTimestamp;
    }

    private java.lang.Object aTimestampWtz;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_TIMESTAMP_WTZ</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP WITH TIME ZONE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable aTimestampWtz(java.lang.Object value) {
        this.aTimestampWtz = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_TIMESTAMP_WTZ</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP WITH TIME ZONE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.lang.Object aTimestampWtz() {
        return this.aTimestampWtz;
    }

    private java.sql.Date bDate;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_DATE</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>DATE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable bDate(java.sql.Date value) {
        this.bDate = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_DATE</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>DATE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.sql.Date bDate() {
        return this.bDate;
    }

    private java.sql.Time bTime;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_TIME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIME</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable bTime(java.sql.Time value) {
        this.bTime = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_TIME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIME</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.sql.Time bTime() {
        return this.bTime;
    }

    private java.sql.Timestamp bTimestamp;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable bTimestamp(java.sql.Timestamp value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting bTimestamp to null violates a NOT NULL constraint!");
        }
        this.bTimestamp = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.sql.Timestamp bTimestamp() {
        return this.bTimestamp;
    }

    private java.lang.Object bTimestampWtz;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_TIMESTAMP_WTZ</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP WITH TIME ZONE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable bTimestampWtz(java.lang.Object value) {
        this.bTimestampWtz = value;
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_TIMESTAMP_WTZ</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP WITH TIME ZONE</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public java.lang.Object bTimestampWtz() {
        return this.bTimestampWtz;
    }


    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>A_TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable aTimestamp(java.util.String value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting aTimestamp to null violates a NOT NULL constraint!");
        }
        this.aTimestamp = com.btc.redg.runtime.util.DateConverter.convertDate(value, java.sql.Timestamp.class);
        return this;
    }

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>\"RT-CG-DCM\".PUBLIC.DATES_TABLE</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>B_TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>TIMESTAMP</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>false</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>false</td>
     *     </tr>
     * </table>
     */
    public GDatesTable bTimestamp(java.util.String value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting bTimestamp to null violates a NOT NULL constraint!");
        }
        this.bTimestamp = com.btc.redg.runtime.util.DateConverter.convertDate(value, java.sql.Timestamp.class);
        return this;
    }


    public List<RedGEntity> getDependencies() {
        List<RedGEntity> dependencies = new ArrayList<>();
        return dependencies;
    }


    public String getSQLString() {
        return String.format("INSERT INTO \"RT-CG-DCM\".PUBLIC.DATES_TABLE (" +
                        "" +
                        "" +
                        "A_DATE, A_TIME, A_TIMESTAMP, A_TIMESTAMP_WTZ, B_DATE, B_TIME, B_TIMESTAMP, B_TIMESTAMP_WTZ" +
                        ") VALUES (" +
                        "" +
                        "" +
                        "%s, %s, %s, %s, %s, %s, %s, %s)",
                this.redG.getSQLValuesFormatter().formatValue(this.aDate(),
                        "DATE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "A_DATE"),
                this.redG.getSQLValuesFormatter().formatValue(this.aTime(),
                        "TIME", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "A_TIME"),
                this.redG.getSQLValuesFormatter().formatValue(this.aTimestamp(),
                        "TIMESTAMP", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "A_TIMESTAMP"),
                this.redG.getSQLValuesFormatter().formatValue(this.aTimestampWtz(),
                        "TIMESTAMP WITH TIME ZONE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "A_TIMESTAMP_WTZ"),
                this.redG.getSQLValuesFormatter().formatValue(this.bDate(),
                        "DATE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "B_DATE"),
                this.redG.getSQLValuesFormatter().formatValue(this.bTime(),
                        "TIME", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "B_TIME"),
                this.redG.getSQLValuesFormatter().formatValue(this.bTimestamp(),
                        "TIMESTAMP", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "B_TIMESTAMP"),
                this.redG.getSQLValuesFormatter().formatValue(this.bTimestampWtz(),
                        "TIMESTAMP WITH TIME ZONE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE",
                        "DATES_TABLE", "B_TIMESTAMP_WTZ")
        );
    }

    public String getPreparedStatementString() {
        return "INSERT INTO \"RT-CG-DCM\".PUBLIC.DATES_TABLE (" +
                "" +
                "" +
                "A_DATE, A_TIME, A_TIMESTAMP, A_TIMESTAMP_WTZ, B_DATE, B_TIME, B_TIMESTAMP, B_TIMESTAMP_WTZ" +
                ") VALUES (" +
                "" +
                "" +
                "?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public Object[] getPreparedStatementValues() {
        return new Object[]{
                this.aDate(),
                this.aTime(),
                this.aTimestamp(),
                this.aTimestampWtz(),
                this.bDate(),
                this.bTime(),
                this.bTimestamp(),
                this.bTimestampWtz()
        };
    }

    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[]{
                new AttributeMetaInfo("A_DATE", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "DATE", 91, java.sql.Date.class, false),
                new AttributeMetaInfo("A_TIME", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "TIME", 92, java.sql.Time.class, false),
                new AttributeMetaInfo("A_TIMESTAMP", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "TIMESTAMP", 93, java.sql.Timestamp.class, true),
                new AttributeMetaInfo("A_TIMESTAMP_WTZ", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "TIMESTAMP WITH TIME ZONE", 2014, java.lang.Object.class, false),
                new AttributeMetaInfo("B_DATE", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "DATE", 91, java.sql.Date.class, false),
                new AttributeMetaInfo("B_TIME", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "TIME", 92, java.sql.Time.class, false),
                new AttributeMetaInfo("B_TIMESTAMP", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "TIMESTAMP", 93, java.sql.Timestamp.class, true),
                new AttributeMetaInfo("B_TIMESTAMP_WTZ", "DATES_TABLE", "\"RT-CG-DCM\".PUBLIC.DATES_TABLE", "TIMESTAMP WITH TIME ZONE", 2014, java.lang.Object.class, false)
        };
    }

    private static String serializedTableModel = "rO0ABXNyAB5jb20uYnRjLnJlZGcubW9kZWxzLlRhYmxlTW9kZWy6onnZkJ7mxgIACloAGGhhc0NvbHVtbnNBbmRGb3JlaWduS2V5c0wACWNsYXNzTmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wAB2NvbHVtbnN0ABBMamF2YS91dGlsL0xpc3Q7TAALZm9yZWlnbktleXNxAH4AAkwAE2luY29taW5nRm9yZWlnbktleXNxAH4AAkwAF2pvaW5UYWJsZVNpbXBsaWZpZXJEYXRhdAAPTGphdmEvdXRpbC9NYXA7TAAEbmFtZXEAfgABTAALcGFja2FnZU5hbWVxAH4AAUwAC3NxbEZ1bGxOYW1lcQB+AAFMAAdzcWxOYW1lcQB+AAF4cAB0AAtHRGF0ZXNUYWJsZXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAIdwQAAAAIc3IAH2NvbS5idGMucmVkZy5tb2RlbHMuQ29sdW1uTW9kZWzzSAVsdW349wIADFoAEWV4cGxpY2l0QXR0cmlidXRlWgAHbm90TnVsbFoAEHBhcnRPZlByaW1hcnlLZXlJAApzcWxUeXBlSW50WgAGdW5pcXVlTAASY29udmVuaWVuY2VTZXR0ZXJzcQB+AAJMAA9kYkZ1bGxUYWJsZU5hbWVxAH4AAUwABmRiTmFtZXEAfgABTAALZGJUYWJsZU5hbWVxAH4AAUwADGphdmFUeXBlTmFtZXEAfgABTAAEbmFtZXEAfgABTAAHc3FsVHlwZXEAfgABeHAAAAAAAABbAHNyAB9qYXZhLnV0aWwuQ29sbGVjdGlvbnMkRW1wdHlMaXN0ergXtDynnt4CAAB4cHQAIFwiUlQtQ0ctRENNXCIuUFVCTElDLkRBVEVTX1RBQkxFdAAGQV9EQVRFdAALREFURVNfVEFCTEV0AA1qYXZhLnNxbC5EYXRldAAFYURhdGV0AAREQVRFc3EAfgAIAAAAAAAAXABxAH4AC3QAIFwiUlQtQ0ctRENNXCIuUFVCTElDLkRBVEVTX1RBQkxFdAAGQV9USU1FcQB+AA50AA1qYXZhLnNxbC5UaW1ldAAFYVRpbWV0AARUSU1Fc3EAfgAIAAEAAAAAXQBzcgAjamF2YS51dGlsLkNvbGxlY3Rpb25zJFNpbmdsZXRvbkxpc3Qq7ykQPKeblwIAAUwAB2VsZW1lbnR0ABJMamF2YS9sYW5nL09iamVjdDt4cHNyACpjb20uYnRjLnJlZGcubW9kZWxzLkNvbnZlbmllbmNlU2V0dGVyTW9kZWxMlfxmFxggjQIAAkwAIWZ1bGx5UXVhbGlmaWVkQ29udmVydGVyTWV0aG9kTmFtZXEAfgABTAASc2V0dGVySmF2YVR5cGVOYW1lcQB+AAF4cHQAM2NvbS5idGMucmVkZy5ydW50aW1lLnV0aWwuRGF0ZUNvbnZlcnRlci5jb252ZXJ0RGF0ZXQAEGphdmEudXRpbC5TdHJpbmd0ACBcIlJULUNHLURDTVwiLlBVQkxJQy5EQVRFU19UQUJMRXQAC0FfVElNRVNUQU1QcQB+AA50ABJqYXZhLnNxbC5UaW1lc3RhbXB0AAphVGltZXN0YW1wdAAJVElNRVNUQU1Qc3EAfgAIAAAAAAAH3gBxAH4AC3QAIFwiUlQtQ0ctRENNXCIuUFVCTElDLkRBVEVTX1RBQkxFdAAPQV9USU1FU1RBTVBfV1RacQB+AA50ABBqYXZhLmxhbmcuT2JqZWN0dAANYVRpbWVzdGFtcFd0enQAGFRJTUVTVEFNUCBXSVRIIFRJTUUgWk9ORXNxAH4ACAAAAAAAAFsAcQB+AAt0ACBcIlJULUNHLURDTVwiLlBVQkxJQy5EQVRFU19UQUJMRXQABkJfREFURXEAfgAOcQB+AA90AAViRGF0ZXEAfgARc3EAfgAIAAAAAAAAXABxAH4AC3QAIFwiUlQtQ0ctRENNXCIuUFVCTElDLkRBVEVTX1RBQkxFdAAGQl9USU1FcQB+AA5xAH4AFXQABWJUaW1lcQB+ABdzcQB+AAgAAQAAAABdAHNxAH4AGXNxAH4AHHEAfgAecQB+AB90ACBcIlJULUNHLURDTVwiLlBVQkxJQy5EQVRFU19UQUJMRXQAC0JfVElNRVNUQU1QcQB+AA5xAH4AInQACmJUaW1lc3RhbXBxAH4AJHNxAH4ACAAAAAAAB94AcQB+AAt0ACBcIlJULUNHLURDTVwiLlBVQkxJQy5EQVRFU19UQUJMRXQAD0JfVElNRVNUQU1QX1dUWnEAfgAOcQB+ACh0AA1iVGltZXN0YW1wV3R6cQB+ACp4c3EAfgAGAAAAAHcEAAAAAHhzcQB+AAYAAAAAdwQAAAAAeHNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAABAAAAAAeHQACkRhdGVzVGFibGV0ABZjb20uYnRjLnJlZGcuZ2VuZXJhdGVkdAAgXCJSVC1DRy1EQ01cIi5QVUJMSUMuREFURVNfVEFCTEVxAH4ADg==";
    private static TableModel tableModel;

    public static String getSerializedTableModel() {
        return serializedTableModel;
    }

    public static TableModel getTableModel() throws java.io.IOException, ClassNotFoundException {
        if (tableModel == null) {
            byte[] data = java.util.Base64.getDecoder().decode(serializedTableModel);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(data));
            tableModel = (TableModel) ois.readObject();
        }
        return tableModel;
    }
}