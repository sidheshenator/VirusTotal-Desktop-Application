/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directorytree;

import actions.CopyFileInfoToClipboard;
import actions.RemoveFileAction;
import actions.ScanFileAction;
import datamodel.PMFile;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author SMhatre
 */
public class PMFileNode extends AbstractNode {

    public final PMFile pmFile;
    private final ScanFileAction scanFileAction;
    private final CopyFileInfoToClipboard copyFileInfoToClipboard;
    private final RemoveFileAction removeFileAction;

    PMFileNode(PMFile pmFile) {
        super(Children.LEAF, Lookups.singleton(pmFile));
        setDisplayName(pmFile.file.getName());
        this.pmFile = pmFile;
        this.scanFileAction = new ScanFileAction(this);
        this.copyFileInfoToClipboard = new CopyFileInfoToClipboard(this);
        this.removeFileAction = new RemoveFileAction(this);
    }

    public PMFileNode getPMFileNode() {
        return this;
    }

    // TODO : override propertysheet
    @Override
    public Action[] getActions(boolean popup) {
        return new Action[]{scanFileAction, copyFileInfoToClipboard,
            removeFileAction};
    }
}
