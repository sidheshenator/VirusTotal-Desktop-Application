/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directorytree;

import datamodel.PMFilesRoot;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author SMhatre
 */
public class PMFilesRootNode extends AbstractNode {

    static int count = 0;
    PMFilesRoot pmFilesRoot;

    public PMFilesRootNode(PMFilesRoot pmFilesRoot, PMFilesRootChildFactory pmrcf) {
        super(Children.create(pmrcf, true));
        this.pmFilesRoot = pmFilesRoot;
        setDisplayName(count++ + " files added");
    }
}
