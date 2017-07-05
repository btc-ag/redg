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
 *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
 *     </tr>
 * </table>
 */
public class GDemoUser implements RedGEntity {

    protected AbstractRedG redG;

    // do not manually make this public and instantiate it directly. Use the RedG Main class
    GDemoUser(AbstractRedG redG, GDemoBankAccount bankAcc) {
        this.redG = redG;
        if (bankAcc == null) {
            throw new IllegalArgumentException("bankAcc may not be null!");
        }
        this.bankAcc = bankAcc;


        try {
            this.id = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("ID"), java.math.BigDecimal.class);
            this.username = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("USERNAME"), java.lang.String.class);
            this.firstName = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("FIRST_NAME"), java.lang.String.class);
            this.lastName = redG.getDefaultValueStrategy().getDefaultValue(getTableModel().getColumnBySQLName("LAST_NAME"), java.lang.String.class);

        } catch (Exception e) {
            throw new RuntimeException("Could not get default value", e);
        }
    }

    GDemoUser(int meaningOfLife, AbstractRedG redG) {
        // First parameter exists simply because this constructor needs a different signature from the constructor above if the tables have no NOT NULL FK
        // Only for ExistingGDemoUser , otherwise NOT NULL constraints cannot be checked and no default values are generated.
        this.redG = redG;
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
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


    private GDemoCompany company;

    /**
     * This is a foreign key referencing {@link GDemoCompany}
     */
    public GDemoUser company(GDemoCompany value) {
        this.company = value;
        return this;
    }

    /**
     * This is a foreign key referencing {@link GDemoCompany}
     */
    public GDemoCompany company() {
        return this.company;
    }


    private GDemoBankAccount bankAcc;

    /**
     * This is a foreign key referencing {@link GDemoBankAccount}
     */
    public GDemoUser bankAcc(GDemoBankAccount value) {
        if (value == null) {
            throw new IllegalArgumentException("Setting bankAcc to null violates a NOT NULL constraint!");
        }
        this.bankAcc = value;
        return this;
    }

    /**
     * This is a foreign key referencing {@link GDemoBankAccount}
     */
    public GDemoBankAccount bankAcc() {
        return this.bankAcc;
    }


    public java.lang.String worksAtName() {
        if (this.company != null) {
            return this.company.name();
        }
        return null;
    }
    public java.lang.String worksAtCc() {
        if (this.company != null) {
            return this.company.countryCode();
        }
        return null;
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>ACC_BIC</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
     *     </tr>
     * </table>
     */
    public java.lang.String accBic() {
        return this.bankAcc.bic();
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
     *         <td>"RT-CG-MPFK".PUBLIC.DEMO_USER</td>
     *     </tr>
     *     <tr>
     *         <td>Column name</td>
     *         <td>ACC_IBAN</td>
     *     </tr>
     *     <tr>
     *         <td>SQL type</td>
     *         <td>VARCHAR</td>
     *     </tr>
     * </table>
     */
    public java.lang.String accIban() {
        return this.bankAcc.iban();
    }

    public List<RedGEntity> getDependencies() {
        List<RedGEntity> dependencies = new ArrayList<>();
        if (bankAcc != null) dependencies.add(bankAcc);
        if (company != null) dependencies.add(company);
        return dependencies;
    }


    public String getSQLString() {
        return String.format("INSERT INTO \"RT-CG-MPFK\".PUBLIC.DEMO_USER (" +
                        "ACC_BIC, ACC_IBAN" +
                        ", " +
                        "WORKS_AT_NAME, WORKS_AT_CC" +
                        ", " +
                        "ID, USERNAME, FIRST_NAME, LAST_NAME" +
                        ") VALUES (" +
                        "%s, %s" +
                        ", " +
                        "%s, %s" +
                        ", " +
                        "%s, %s, %s, %s)",
                this.redG.getSqlValuesFormatter().formatValue(this.bankAcc.bic(),
                        "VARCHAR", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "ACC_BIC"),
                this.redG.getSqlValuesFormatter().formatValue(this.bankAcc.iban(),
                        "VARCHAR", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "ACC_IBAN"),
                this.redG.getSqlValuesFormatter().formatValue((this.company != null) ? this.company.name() : null,
                        "VARCHAR", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "WORKS_AT_NAME"),
                this.redG.getSqlValuesFormatter().formatValue((this.company != null) ? this.company.countryCode() : null,
                        "VARCHAR", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "WORKS_AT_CC"),
                this.redG.getSqlValuesFormatter().formatValue(this.id(),
                        "DECIMAL", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "ID"),
                this.redG.getSqlValuesFormatter().formatValue(this.username(),
                        "VARCHAR", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "USERNAME"),
                this.redG.getSqlValuesFormatter().formatValue(this.firstName(),
                        "VARCHAR", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "FIRST_NAME"),
                this.redG.getSqlValuesFormatter().formatValue(this.lastName(),
                        "VARCHAR", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER",
                        "DEMO_USER", "LAST_NAME")
        );
    }

    public String getPreparedStatementString() {
        return "INSERT INTO \"RT-CG-MPFK\".PUBLIC.DEMO_USER (" +
                "ACC_BIC, ACC_IBAN" +
                ", " +
                "WORKS_AT_NAME, WORKS_AT_CC" +
                ", " +
                "ID, USERNAME, FIRST_NAME, LAST_NAME" +
                ") VALUES (" +
                "?, ?" +
                ", " +
                "?, ?" +
                ", " +
                "?, ?, ?, ?)";
    }

    public Object[] getPreparedStatementValues() {
        return new Object[]{
                this.bankAcc.bic(),
                this.bankAcc.iban(),
                (this.company != null) ? this.company.name() : null,
                (this.company != null) ? this.company.countryCode() : null,
                this.id(),
                this.username(),
                this.firstName(),
                this.lastName()
        };
    }

    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[]{
                new AttributeMetaInfo("ACC_BIC", "DEMO_USER", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, true),
                new AttributeMetaInfo("ACC_IBAN", "DEMO_USER", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, true),
                new AttributeMetaInfo("WORKS_AT_NAME", "DEMO_USER", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, false),
                new AttributeMetaInfo("WORKS_AT_CC", "DEMO_USER", "\"RT-CG-MPFK\".PUBLIC.DEMO_USER", "VARCHAR", 12, java.lang.String.class, false),
                new AttributeMetaInfo("ID", "DEMO_USER", "DEMO_USER", "DECIMAL", 3, java.math.BigDecimal.class, true),
                new AttributeMetaInfo("USERNAME", "DEMO_USER", "DEMO_USER", "VARCHAR", 12, java.lang.String.class, true),
                new AttributeMetaInfo("FIRST_NAME", "DEMO_USER", "DEMO_USER", "VARCHAR", 12, java.lang.String.class, false),
                new AttributeMetaInfo("LAST_NAME", "DEMO_USER", "DEMO_USER", "VARCHAR", 12, java.lang.String.class, false)
        };
    }

    private static String serializedTableModel = "rO0ABXNyAB5jb20uYnRjLnJlZGcubW9kZWxzLlRhYmxlTW9kZWx0b/Ys7hpPuAIACloAGGhhc0NvbHVtbnNBbmRGb3JlaWduS2V5c0wACWNsYXNzTmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wAB2NvbHVtbnN0ABBMamF2YS91dGlsL0xpc3Q7TAALZm9yZWlnbktleXNxAH4AAkwAE2luY29taW5nRm9yZWlnbktleXNxAH4AAkwAF2pvaW5UYWJsZVNpbXBsaWZpZXJEYXRhdAAPTGphdmEvdXRpbC9NYXA7TAAEbmFtZXEAfgABTAALcGFja2FnZU5hbWVxAH4AAUwAC3NxbEZ1bGxOYW1lcQB+AAFMAAdzcWxOYW1lcQB+AAF4cAF0AAlHRGVtb1VzZXJzcgATamF2YS51dGlsLkFycmF5TGlzdHiB0h2Zx2GdAwABSQAEc2l6ZXhwAAAACHcEAAAACHNyAB9jb20uYnRjLnJlZGcubW9kZWxzLkNvbHVtbk1vZGVsZ6XLR7cE1PwCAA1aABFleHBsaWNpdEF0dHJpYnV0ZVoAB25vdE51bGxaABBwYXJ0T2ZGb3JlaWduS2V5WgAQcGFydE9mUHJpbWFyeUtleUkACnNxbFR5cGVJbnRaAAZ1bmlxdWVMABJjb252ZW5pZW5jZVNldHRlcnNxAH4AAkwAD2RiRnVsbFRhYmxlTmFtZXEAfgABTAAGZGJOYW1lcQB+AAFMAAtkYlRhYmxlTmFtZXEAfgABTAAMamF2YVR5cGVOYW1lcQB+AAFMAARuYW1lcQB+AAFMAAdzcWxUeXBlcQB+AAF4cAABAAEAAAADAXNyAB9qYXZhLnV0aWwuQ29sbGVjdGlvbnMkRW1wdHlMaXN0ergXtDynnt4CAAB4cHQAHSJSVC1DRy1NUEZLIi5QVUJMSUMuREVNT19VU0VSdAACSUR0AAlERU1PX1VTRVJ0ABRqYXZhLm1hdGguQmlnRGVjaW1hbHQAAmlkdAAHREVDSU1BTHNxAH4ACAABAAAAAAAMAHEAfgALdAAdIlJULUNHLU1QRksiLlBVQkxJQy5ERU1PX1VTRVJ0AAhVU0VSTkFNRXEAfgAOdAAQamF2YS5sYW5nLlN0cmluZ3QACHVzZXJuYW1ldAAHVkFSQ0hBUnNxAH4ACAAAAAAAAAAMAHEAfgALdAAdIlJULUNHLU1QRksiLlBVQkxJQy5ERU1PX1VTRVJ0AApGSVJTVF9OQU1FcQB+AA5xAH4AFXQACWZpcnN0TmFtZXEAfgAXc3EAfgAIAAAAAAAAAAwAcQB+AAt0AB0iUlQtQ0ctTVBGSyIuUFVCTElDLkRFTU9fVVNFUnQACUxBU1RfTkFNRXEAfgAOcQB+ABV0AAhsYXN0TmFtZXEAfgAXc3EAfgAIAAABAAAAAAwAcQB+AAt0AB0iUlQtQ0ctTVBGSyIuUFVCTElDLkRFTU9fVVNFUnQAC1dPUktTX0FUX0NDcQB+AA5xAH4AFXQACXdvcmtzQXRDY3EAfgAXc3EAfgAIAAABAAAAAAwAcQB+AAt0AB0iUlQtQ0ctTVBGSyIuUFVCTElDLkRFTU9fVVNFUnQADVdPUktTX0FUX05BTUVxAH4ADnEAfgAVdAALd29ya3NBdE5hbWVxAH4AF3NxAH4ACAABAQAAAAAMAHEAfgALdAAdIlJULUNHLU1QRksiLlBVQkxJQy5ERU1PX1VTRVJ0AAdBQ0NfQklDcQB+AA5xAH4AFXQABmFjY0JpY3EAfgAXc3EAfgAIAAEBAAAAAAwAcQB+AAt0AB0iUlQtQ0ctTVBGSyIuUFVCTElDLkRFTU9fVVNFUnQACEFDQ19JQkFOcQB+AA5xAH4AFXQAB2FjY0liYW5xAH4AF3hzcQB+AAYAAAACdwQAAAACc3IAI2NvbS5idGMucmVkZy5tb2RlbHMuRm9yZWlnbktleU1vZGVssCKOpfjX2JACAARaAAdub3ROdWxsTAAMamF2YVR5cGVOYW1lcQB+AAFMAARuYW1lcQB+AAFMAApyZWZlcmVuY2VzcQB+AAN4cAF0ABBHRGVtb0JhbmtBY2NvdW50dAAHYmFua0FjY3NyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACcQB+ACpzcgApY29tLmJ0Yy5yZWRnLm1vZGVscy5Gb3JlaWduS2V5Q29sdW1uTW9kZWxKe+CqdqjqfgIACEkACnNxbFR5cGVJbnRMAA9kYkZ1bGxUYWJsZU5hbWVxAH4AAUwABmRiTmFtZXEAfgABTAALZGJUYWJsZU5hbWVxAH4AAUwACWxvY2FsTmFtZXEAfgABTAAJbG9jYWxUeXBlcQB+AAFMABdwcmltYXJ5S2V5QXR0cmlidXRlTmFtZXEAfgABTAAHc3FsVHlwZXEAfgABeHAAAAAMdAAdIlJULUNHLU1QRksiLlBVQkxJQy5ERU1PX1VTRVJxAH4AKnEAfgAOdAAGYWNjQmljcQB+ABV0AANiaWNxAH4AF3EAfgAuc3EAfgA3AAAADHQAHSJSVC1DRy1NUEZLIi5QVUJMSUMuREVNT19VU0VScQB+AC5xAH4ADnQAB2FjY0liYW5xAH4AFXQABGliYW5xAH4AF3hzcQB+ADEAdAAMR0RlbW9Db21wYW55dAAHY29tcGFueXNxAH4ANT9AAAAAAAAMdwgAAAAQAAAAAnEAfgAmc3EAfgA3AAAADHQAHSJSVC1DRy1NUEZLIi5QVUJMSUMuREVNT19VU0VScQB+ACZxAH4ADnQAC3dvcmtzQXROYW1lcQB+ABV0AARuYW1lcQB+ABdxAH4AInNxAH4ANwAAAAx0AB0iUlQtQ0ctTVBGSyIuUFVCTElDLkRFTU9fVVNFUnEAfgAicQB+AA50AAl3b3Jrc0F0Q2NxAH4AFXQAC2NvdW50cnlDb2RlcQB+ABd4eHNxAH4ABgAAAAB3BAAAAAB4c3EAfgA1P0AAAAAAAAB3CAAAABAAAAAAeHQACERlbW9Vc2VydAAWY29tLmJ0Yy5yZWRnLmdlbmVyYXRlZHQAHSJSVC1DRy1NUEZLIi5QVUJMSUMuREVNT19VU0VScQB+AA4=";
    private static TableModel tableModel;

    public static String getSerializedTableModel() {
        return serializedTableModel;
    }

    public static TableModel getTableModel() {
        if (tableModel == null) {
            byte[] data = java.util.Base64.getDecoder().decode(serializedTableModel);
            try {
                java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(data));
                tableModel = (TableModel) ois.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return tableModel;
    }
}