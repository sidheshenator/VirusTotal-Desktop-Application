/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

/**
 *
 * @author SMhatre
 */
public class Scan {

    public String name;
    public boolean detected;
    public String version;
    public String result;
    public String update;

    public Scan(String name, boolean detected, String result, String version, String update) {
        this.name = name;
        this.detected = detected;
        this.version = version;
        this.result = result;
        this.update = update;
    }

    @Override
    public String toString() {
        return "Scan{" + "name=" + name + ", detected=" + detected + ", version=" + version + ", result=" + result + ", update=" + update + "}\n";
    }
    
    
}
