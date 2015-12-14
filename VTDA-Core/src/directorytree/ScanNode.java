/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directorytree;

import datamodel.Scan;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author SMhatre
 */
public class ScanNode extends AbstractNode {

    Scan scan;

    ScanNode(Scan scan) {
        super(Children.LEAF);
        this.scan = scan;
        setDisplayName(scan.name);
    }

}
