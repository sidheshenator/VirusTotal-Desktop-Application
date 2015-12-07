/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import datamodel.PMFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;
import topComponents.ExplorerWindowTopComponentTopComponent;

@ActionID(
        category = "Edit",
        id = "com.sidhesh.virusTotalDesktopApplication.core.actions.AddFileAction"
)
@ActionRegistration(
        displayName = "#CTL_AddFileAction"
)
@ActionReference(path = "Menu/File", position = 1300)
@Messages("CTL_AddFileAction=Add File to Scan")
public final class AddFileAction implements ActionListener {

    private final JFileChooser fileChooser = new JFileChooser();

    public AddFileAction() {
        fileChooser.setDragEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnValue = fileChooser.showOpenDialog(WindowManager.getDefault().getMainWindow());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // create new PMFile()
            // create node for PMFile()
            // update PMFilesRootNodeCF
            ExplorerWindowTopComponentTopComponent.getInstance().getRootChildFactory().
                    refresh(new PMFile(fileChooser.getSelectedFile()));
        }
    }
}
