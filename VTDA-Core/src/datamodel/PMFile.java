/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SMhatre
 */
public class PMFile {

    public File file;
    public Boolean isScanned = false;
    public String scan_id;
    public String sha2;
    public String sha1;
    public String md5;
    public String scanDate;
    public Long positives;
    public Long totals;
    public List<Scan> listOfScans = new ArrayList<>();
    public Integer numberOfScans = 0;

    public PMFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        String listOfScans = "";
        for (Scan s : this.listOfScans) {
            listOfScans = listOfScans + s.toString();
        }
        return "PMFile\n{" + "file=" + getFile() + ",\n isScanned="
                + isIsScanned() + ",\n scan_id=" + getScan_id() + ",\n sha2="
                + getSha2() + ",\n sha1=" + getSha1() + ",\n md5=" + getMd5() + ",\n scanDate="
                + getScanDate() + ",\n positives=" + getPositives() + ",\n negatives="
                + getTotals() + ",\n listOfScans=" + listOfScans + "}\n\n";
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the isScanned
     */
    public Boolean isIsScanned() {
        return isScanned;
    }

    /**
     * @return the scan_id
     */
    public String getScan_id() {
        return scan_id;
    }

    /**
     * @return the sha2
     */
    public String getSha2() {
        return sha2;
    }

    /**
     * @return the sha1
     */
    public String getSha1() {
        return sha1;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @return the scanDate
     */
    public String getScanDate() {
        return scanDate;
    }

    /**
     * @return the positives
     */
    public Long getPositives() {
        return positives;
    }

    /**
     * @return the totals
     */
    public Long getTotals() {
        return totals;
    }

    /**
     * @return the numberOfScans
     */
    public Integer getNumberOfScans() {
        return numberOfScans;
    }

}
