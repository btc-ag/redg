/*
 * This file was generated by RedG.
 * https://btc-ag.github.io/redg
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
 *         <td>DEMO_USER</td>
 *     </tr>
 *     <tr>
 *         <td>Full table name</td>
 *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
 *     </tr>
 * </table>
 */
public class GDemoUser implements RedGEntity {

    protected AbstractRedG redG;

    // do not manually make this public and instantiate it directly. Use the RedG Main class
    GDemoUser(AbstractRedG redG, java.lang.String dtype, GDemoCompany worksAtDemoCompany) {
        this.redG = redG;
        this.dtype = dtype;
        if (worksAtDemoCompany == null) {
            throw new IllegalArgumentException("worksAtDemoCompany may not be null!");
        }
        this.worksAtDemoCompany = worksAtDemoCompany;


        try {
            this.id = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("ID"), java.math.BigDecimal.class);
            this.username = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("USERNAME"), java.lang.String.class);
            this.firstName = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("FIRST_NAME"), java.lang.String.class);
            this.lastName = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("LAST_NAME"), java.lang.String.class);

        } catch (Exception e) {
            throw new RuntimeException("Could not get default value", e);
        }
    }

    GDemoUser(AbstractRedG redG, GDemoCompany worksAtDemoCompany) {
        // dummy constructor (without explicit attributes)
        try {
            this(
                    redG,
                    redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("DTYPE"), java.lang.String.class),
                    worksAtDemoCompany
            );
        } catch (Exception e) {
            throw new RuntimeException("Could not get default value", e);
        }
    }

    GDemoUser(int meaningOfLife, AbstractRedG redG) {
        // First parameter exists simply because this constructor needs a different signature from the constructor above if the tables have no NOT NULL FK
        // Only for ExistingGDemoUser , otherwise NOT NULL constraints cannot be checked and no default values are generated.
        this.redG = redG;
    }

    private java.lang.String dtype;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>DTYPE</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public GDemoUser dtype(java.lang.String value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting dtype to null violates a NOT NULL constraint!");
        }
        this.dtype = value;
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
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>DTYPE</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public java.lang.String dtype() {
        return this.dtype;
    }

    private java.math.BigDecimal id;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>ID</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>DECIMAL</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>true</td>
     *     </tr>
     * </table>
     */
    public GDemoUser id(java.math.BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting id to null violates a NOT NULL constraint!");
        }
        this.id = value;
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
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>ID</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>DECIMAL</td>
     *     </tr>
     *     <tr>
     *         <td>Not nullable?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Part of Primary key?</td>
     *         <td>true</td>
     *     </tr>
     *     <tr>
     *         <td>Unique?</td>
     *         <td>true</td>
     *     </tr>
     * </table>
     */
    public java.math.BigDecimal id() {
        return this.id;
    }

    private java.lang.String username;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>USERNAME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public GDemoUser username(java.lang.String value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting username to null violates a NOT NULL constraint!");
        }
        this.username = value;
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
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>USERNAME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public java.lang.String username() {
        return this.username;
    }

    private java.lang.String firstName;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>FIRST_NAME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public GDemoUser firstName(java.lang.String value) {
        this.firstName = value;
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
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>FIRST_NAME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public java.lang.String firstName() {
        return this.firstName;
    }

    private java.lang.String lastName;

    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>LAST_NAME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public GDemoUser lastName(java.lang.String value) {
        this.lastName = value;
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
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>LAST_NAME</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
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
    public java.lang.String lastName() {
        return this.lastName;
    }


    private GDemoCompany worksAtDemoCompany;

    /**
     * This is a foreign key referencing {@link GDemoCompany}
     */
    public GDemoUser worksAtDemoCompany(GDemoCompany value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting worksAtDemoCompany to null violates a NOT NULL constraint!");
        }
        this.worksAtDemoCompany = value;
        return this;
    }

    /**
     * This is a foreign key referencing {@link GDemoCompany}
     */
    public GDemoCompany worksAtDemoCompany() {
        return this.worksAtDemoCompany;
    }


    /**
     * <table summary="The column model attributes and their values">
     *     <tr>
     *         <td><strong>Attribute</strong></td>
     *         <td><strong>Value</strong></td>
     *     </tr>
     *     <tr>
     *         <td>Table name</td>
     *         <td>DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Full table name</td>
     *         <td>"RT-CG-TT".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>WORKS_AT</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>DECIMAL</td>
     *     </tr>
     * </table>
     */
    public java.math.BigDecimal worksAt() {
        return this.worksAtDemoCompany.id();
    }

    public List<RedGEntity> getDependencies() {
        List<RedGEntity> dependencies = new ArrayList<>();
        if (worksAtDemoCompany != null) dependencies.add(worksAtDemoCompany);
        return dependencies;
    }


    public String getSQLString() {
        return String.format("INSERT INTO \"RT-CG-TT\".PUBLIC.DEMO_USER (" +
                        "WORKS_AT" +
                        "" +
                        ", " +
                        "DTYPE, ID, USERNAME, FIRST_NAME, LAST_NAME" +
                        ") VALUES (" +
                        "%s" +
                        "" +
                        ", " +
                        "%s, %s, %s, %s, %s)",
                this.redG.getSqlValuesFormatter().formatValue(this.worksAtDemoCompany.id(),
                        "DECIMAL", "\"RT-CG-TT\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "WORKS_AT"),
                this.redG.getSqlValuesFormatter().formatValue(this.dtype(),
                        "VARCHAR", "\"RT-CG-TT\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "DTYPE"),
                this.redG.getSqlValuesFormatter().formatValue(this.id(),
                        "DECIMAL", "\"RT-CG-TT\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "ID"),
                this.redG.getSqlValuesFormatter().formatValue(this.username(),
                        "VARCHAR", "\"RT-CG-TT\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "USERNAME"),
                this.redG.getSqlValuesFormatter().formatValue(this.firstName(),
                        "VARCHAR", "\"RT-CG-TT\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "FIRST_NAME"),
                this.redG.getSqlValuesFormatter().formatValue(this.lastName(),
                        "VARCHAR", "\"RT-CG-TT\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "LAST_NAME")
        );
    }

    public String getPreparedStatementString() {
        return "INSERT INTO \"RT-CG-TT\".PUBLIC.DEMO_USER (" +
                "WORKS_AT" +
                "" +
                ", " +
                "DTYPE, ID, USERNAME, FIRST_NAME, LAST_NAME" +
                ") VALUES (" +
                "?" +
                "" +
                ", " +
                "?, ?, ?, ?, ?)";
    }

    public Object[] getPreparedStatementValues() {
        return new Object[]{
                this.worksAtDemoCompany.id(),
                this.dtype(),
                this.id(),
                this.username(),
                this.firstName(),
                this.lastName()
        };
    }

    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[]{
                new AttributeMetaInfo("WORKS_AT", "DEMO_USER", "\"RT-CG-TT\".PUBLIC.DEMO_USER", "DECIMAL", 3, java.math.BigDecimal.class, true),
                new AttributeMetaInfo("DTYPE", "DEMO_USER", "\"RT-CG-TT\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, true),
                new AttributeMetaInfo("ID", "DEMO_USER", "\"RT-CG-TT\".PUBLIC.DEMO_USER", "DECIMAL", 3, java.math.BigDecimal.class, true),
                new AttributeMetaInfo("USERNAME", "DEMO_USER", "\"RT-CG-TT\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, true),
                new AttributeMetaInfo("FIRST_NAME", "DEMO_USER", "\"RT-CG-TT\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, false),
                new AttributeMetaInfo("LAST_NAME", "DEMO_USER", "\"RT-CG-TT\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, false)
        };
    }

    private static String serializedTableModel = "rO0ABXNyAB5jb20uYnRjLnJlZGcubW9kZWxzLlRhYmxlTW9kZWx0b/Ys7hpPuAIACloAGGhhc0NvbHVtbnNBbmRGb3JlaWduS2V5c0wACWNsYXNzTmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wAB2NvbHVtbnN0ABBMamF2YS91dGlsL0xpc3Q7TAALZm9yZWlnbktleXNxAH4AAkwAE2luY29taW5nRm9yZWlnbktleXNxAH4AAkwAF2pvaW5UYWJsZVNpbXBsaWZpZXJEYXRhdAAPTGphdmEvdXRpbC9NYXA7TAAEbmFtZXEAfgABTAALcGFja2FnZU5hbWVxAH4AAUwAC3NxbEZ1bGxOYW1lcQB+AAFMAAdzcWxOYW1lcQB+AAF4cAF0AAlHRGVtb1VzZXJzcgATamF2YS51dGlsLkFycmF5TGlzdHiB0h2Zx2GdAwABSQAEc2l6ZXhwAAAABncEAAAABnNyAB9jb20uYnRjLnJlZGcubW9kZWxzLkNvbHVtbk1vZGVsZ6XLR7cE1PwCAA1aABFleHBsaWNpdEF0dHJpYnV0ZVoAB25vdE51bGxaABBwYXJ0T2ZGb3JlaWduS2V5WgAQcGFydE9mUHJpbWFyeUtleUkACnNxbFR5cGVJbnRaAAZ1bmlxdWVMABJjb252ZW5pZW5jZVNldHRlcnNxAH4AAkwAD2RiRnVsbFRhYmxlTmFtZXEAfgABTAAGZGJOYW1lcQB+AAFMAAtkYlRhYmxlTmFtZXEAfgABTAAMamF2YVR5cGVOYW1lcQB+AAFMAARuYW1lcQB+AAFMAAdzcWxUeXBlcQB+AAF4cAEBAAAAAAAMAHNyAB9qYXZhLnV0aWwuQ29sbGVjdGlvbnMkRW1wdHlMaXN0ergXtDynnt4CAAB4cHQAGyJSVC1DRy1UVCIuUFVCTElDLkRFTU9fVVNFUnQABURUWVBFdAAJREVNT19VU0VSdAAQamF2YS5sYW5nLlN0cmluZ3QABWR0eXBldAAHVkFSQ0hBUnNxAH4ACAABAAEAAAADAXEAfgALdAAbIlJULUNHLVRUIi5QVUJMSUMuREVNT19VU0VSdAACSURxAH4ADnQAFGphdmEubWF0aC5CaWdEZWNpbWFsdAACaWR0AAdERUNJTUFMc3EAfgAIAAEAAAAAAAwAcQB+AAt0ABsiUlQtQ0ctVFQiLlBVQkxJQy5ERU1PX1VTRVJ0AAhVU0VSTkFNRXEAfgAOcQB+AA90AAh1c2VybmFtZXEAfgARc3EAfgAIAAAAAAAAAAwAcQB+AAt0ABsiUlQtQ0ctVFQiLlBVQkxJQy5ERU1PX1VTRVJ0AApGSVJTVF9OQU1FcQB+AA5xAH4AD3QACWZpcnN0TmFtZXEAfgARc3EAfgAIAAAAAAAAAAwAcQB+AAt0ABsiUlQtQ0ctVFQiLlBVQkxJQy5ERU1PX1VTRVJ0AAlMQVNUX05BTUVxAH4ADnEAfgAPdAAIbGFzdE5hbWVxAH4AEXNxAH4ACAABAQAAAAADAHEAfgALdAAbIlJULUNHLVRUIi5QVUJMSUMuREVNT19VU0VSdAAIV09SS1NfQVRxAH4ADnEAfgAVdAAHd29ya3NBdHEAfgAXeHNxAH4ABgAAAAF3BAAAAAFzcgAjY29tLmJ0Yy5yZWRnLm1vZGVscy5Gb3JlaWduS2V5TW9kZWywIo6l+NfYkAIABFoAB25vdE51bGxMAAxqYXZhVHlwZU5hbWVxAH4AAUwABG5hbWVxAH4AAUwACnJlZmVyZW5jZXNxAH4AA3hwAXQADEdEZW1vQ29tcGFueXQAEndvcmtzQXREZW1vQ29tcGFueXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABcQB+ACZzcgApY29tLmJ0Yy5yZWRnLm1vZGVscy5Gb3JlaWduS2V5Q29sdW1uTW9kZWzVeRDNwI/rZgIACEkACnNxbFR5cGVJbnRMAA9kYkZ1bGxUYWJsZU5hbWVxAH4AAUwABmRiTmFtZXEAfgABTAALZGJUYWJsZU5hbWVxAH4AAUwACWxvY2FsTmFtZXEAfgABTAAJbG9jYWxUeXBlcQB+AAFMAARuYW1lcQB+AAFMAAdzcWxUeXBlcQB+AAF4cAAAAAN0ABsiUlQtQ0ctVFQiLlBVQkxJQy5ERU1PX1VTRVJxAH4AJnEAfgAOdAAHd29ya3NBdHEAfgAVdAACaWRxAH4AF3h4c3EAfgAGAAAAAHcEAAAAAHhzcQB+AC0/QAAAAAAAAHcIAAAAEAAAAAB4dAAIRGVtb1VzZXJ0ABZjb20uYnRjLnJlZGcuZ2VuZXJhdGVkdAAbIlJULUNHLVRUIi5QVUJMSUMuREVNT19VU0VScQB+AA4=";
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