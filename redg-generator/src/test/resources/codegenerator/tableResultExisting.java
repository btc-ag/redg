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

import com.btc.redg.runtime.*;

/**
 * {@inheritDoc}
 */
public class ExistingGDemoUser extends GDemoUser {

    ExistingGDemoUser(AbstractRedG redG, java.math.BigDecimal id) {
        super(false, redG);
        super.id(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoUser id(java.math.BigDecimal value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.math.BigDecimal id() {
        return super.id();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoUser dtype(java.lang.String value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.String dtype() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoUser username(java.lang.String value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.String username() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoUser firstName(java.lang.String value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.String firstName() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoUser lastName(java.lang.String value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.String lastName() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoUser day(java.sql.Timestamp value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Timestamp day() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoUser worksAtDemoCompany(GDemoCompany value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDemoCompany worksAtDemoCompany() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }


    @Override
    public java.math.BigDecimal worksAt() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }


    @Override
    public String getSQLString() {
        return String.format("SELECT COUNT(*) FROM \"DEMO_USER\" WHERE " +
                        "\"ID\" = %s",
                this.redG.getSqlValuesFormatter().formatValue(super.id(),
                        "DECIMAL", "DEMO_USER",
                        "DEMO_USER", "ID")
        );
    }

    @Override
    public String getPreparedStatementString() {
        return "SELECT COUNT(*) FROM \"DEMO_USER\" WHERE " +
                "\"ID\" = ?";
    }

    @Override
    public Object[] getPreparedStatementValues() {
        return new Object[] {
                super.id()
        };
    }

    @Override
    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[] {
                new AttributeMetaInfo("ID", "DEMO_USER", "\"RT-CG-TT\".PUBLIC.DEMO_USER", "DECIMAL", 3, java.math.BigDecimal.class, true)
        };
    }
}