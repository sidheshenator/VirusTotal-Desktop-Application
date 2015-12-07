/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import directorytree.PMFileNode;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;

/**
 *
 * @author SMhatre
 */
public class CopyFileInfoToClipboard extends AbstractAction {

    PMFileNode pmFileNode;

    public CopyFileInfoToClipboard(PMFileNode pmFileNode) {
        putValue(NAME, "Copy to clipboard");
        this.pmFileNode = pmFileNode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(new StringSelection(pmFileNode.pmFile.toString()), null);
    }
}
