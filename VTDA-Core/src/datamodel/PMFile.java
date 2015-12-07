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
    public boolean isScanned = false;
    public String scan_id;
    public String sha2;
    public String sha1;
    public String md5;
    public String scanDate;
    public long positives;
    public long totals;
    public List<Scan> listOfScans = new ArrayList<>();

    public PMFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "PMFile\n{" + "file=" + file + ",\n isScanned="
                + isScanned + ",\n scan_id=" + scan_id + ",\n sha2="
                + sha2 + ",\n sha1=" + sha1 + ",\n md5=" + md5 + ",\n scanDate="
                + scanDate + ",\n positives=" + positives + ",\n negatives="
                + totals + ",\n listOfScans=" + listOfScans + "}\n\n";
    }

}
