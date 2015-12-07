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
import org.openide.ErrorManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
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
        setDisplayName(pmFile.getFile().getName());
        this.pmFile = pmFile;
        this.scanFileAction = new ScanFileAction(this);
        this.copyFileInfoToClipboard = new CopyFileInfoToClipboard(this);
        this.removeFileAction = new RemoveFileAction(this);
    }

    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        PMFile obj = getLookup().lookup(PMFile.class);

        try {
            Property isScannedProp = new PropertySupport.Reflection(obj, Boolean.class, "isIsScanned", null);
            isScannedProp.setName("Is Scanned");
            set.put(isScannedProp);
            Property scan_idProp = new PropertySupport.Reflection(obj, String.class, "getScan_id", null);
            scan_idProp.setName("Scan ID");
            set.put(scan_idProp);
            Property md5Prop = new PropertySupport.Reflection(obj, String.class, "getMd5", null);
            md5Prop.setName("MD5");
            set.put(md5Prop);
            Property sha1Prop = new PropertySupport.Reflection(obj, String.class, "getSha1", null);
            sha1Prop.setName("SHA-1");
            set.put(sha1Prop);
            Property sha2Prop = new PropertySupport.Reflection(obj, String.class, "getSha2", null);
            sha2Prop.setName("SHA-2");
            set.put(sha2Prop);
            Property positivesProp = new PropertySupport.Reflection(obj, Long.class, "getPositives", null);
            positivesProp.setName("Positives");
            set.put(positivesProp);
            Property totalsProp = new PropertySupport.Reflection(obj, Long.class, "getTotals", null);
            totalsProp.setName("No. of times submitted");
            Property totalNoOfScansProp = new PropertySupport.Reflection(obj, Integer.class, "getNumberOfScans", null);
            totalNoOfScansProp.setName("No. of scans perfermed");
            set.put(totalNoOfScansProp);
        } catch (NoSuchMethodException ex) {
            ErrorManager.getDefault();
        }
        sheet.put(set);
        return sheet;
    }

    @Override
    public Action[] getActions(boolean popup) {
        return new Action[]{scanFileAction, copyFileInfoToClipboard,
            removeFileAction};
    }
}
