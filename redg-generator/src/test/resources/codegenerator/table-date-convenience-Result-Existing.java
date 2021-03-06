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
public class ExistingGDatesTable extends GDatesTable {

    ExistingGDatesTable(AbstractRedG redG) {
        super(false, redG);
        throw new UnsupportedOperationException("Cannot reference an existing entity if the table has no primary keys");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable aDate(java.sql.Date value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Date aDate() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable aTime(java.sql.Time value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Time aTime() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable aTimestamp(java.sql.Timestamp value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Timestamp aTimestamp() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable aTimestampWtz(java.time.OffsetDateTime value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.time.OffsetDateTime aTimestampWtz() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable bDate(java.sql.Date value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Date bDate() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable bTime(java.sql.Time value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Time bTime() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable bTimestamp(java.sql.Timestamp value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Timestamp bTimestamp() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable bTimestampWtz(java.time.OffsetDateTime value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.time.OffsetDateTime bTimestampWtz() {
        throw new UnsupportedOperationException("Cannot read values of entities declared as existing that are not part of the primary key.");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable aTimestamp(java.util.String value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GDatesTable bTimestamp(java.util.String value) {
        throw new UnsupportedOperationException("Cannot change values of entities declared as existing.");
    }




    @Override
    public String getSQLString() {
        return "Your table has no primary key(s), referincing existing entities is not possible!";
    }

    @Override
    public String getPreparedStatementString() {
        return "Your table has no primary key(s), referencing existing entities is not possible!";
    }

    @Override
    public Object[] getPreparedStatementValues() {
        return new Object[] {
        };
    }

    @Override
    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[] {
        };
    }
}