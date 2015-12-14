/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directorytree;

import datamodel.PMFile;
import datamodel.Scan;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author SMhatre
 */
public class PMFileChildFactory extends ChildFactory<Scan> {

    List<Scan> listOfScans;

    PMFileChildFactory(List<Scan> listOfScans) {
        this.listOfScans = listOfScans;
    }

    @Override
    protected boolean createKeys(List<Scan> toPopulate) {
        toPopulate.addAll(listOfScans);
        return true;
    }

    @Override
    protected Node createNodeForKey(Scan key) {
        return new ScanNode(key);
    }

    public void refresh(PMFile pmFile) {
        listOfScans.addAll(pmFile.listOfScans);
        refresh(true);
    }
}
