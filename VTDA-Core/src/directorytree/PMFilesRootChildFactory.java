/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directorytree;

import datamodel.PMFile;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author SMhatre
 */
public class PMFilesRootChildFactory extends ChildFactory<PMFile> {

    List<PMFile> listOfPMFiles = new ArrayList<>();

    @Override
    protected boolean createKeys(List<PMFile> toPopulate) {
        toPopulate.addAll(listOfPMFiles);
        return true;
    }

    @Override
    protected Node createNodeForKey(PMFile key) {
        return new PMFileNode(key);
    }

    public void refresh(PMFile pmFile) {
        listOfPMFiles.add(pmFile);
        refresh(true);
    }
}
