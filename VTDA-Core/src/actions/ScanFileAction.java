/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import core.VirusTotalAPIHelper;
import datamodel.PMFile;
import datamodel.Scan;
import directorytree.PMFileNode;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import javax.swing.JOptionPane;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openide.util.Exceptions;
import topComponents.MainWindowTopComponent;

/**
 *
 * @author SMhatre
 */
public class ScanFileAction extends AbstractAction {

    private static final JSONParser parser = new JSONParser();

    PMFileNode pmFileNode;

    public ScanFileAction(PMFileNode pmFileNode) {
        this.pmFileNode = pmFileNode;
        putValue(NAME, "Scan File");
    }

    String getStringFromClosableHttpResponse(CloseableHttpResponse response) {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.pmFileNode.pmFile.isIsScanned()) {
            JOptionPane.showMessageDialog(null, "The file has been already scanned.");
        }
        if (VirusTotalAPIHelper.apiKey.equals("")) {
            VirusTotalAPIHelper.apiKey = JOptionPane.showInputDialog("Enter valid API key");
        }
        if (VirusTotalAPIHelper.apiKey.equals("")) {
            return;
        } else {
            // scan file and get the report.
            CloseableHttpResponse scanFileResponse = VirusTotalAPIHelper.scanFile(this.pmFileNode.pmFile.getFile());
            try {
                JSONObject obj = (JSONObject) parser.parse(getStringFromClosableHttpResponse(scanFileResponse));
                this.pmFileNode.pmFile.isScanned = true;
                this.pmFileNode.pmFile.md5 = (String) obj.get("md5");
                this.pmFileNode.pmFile.sha1 = (String) obj.get("sha1");
                this.pmFileNode.pmFile.sha2 = (String) obj.get("sha256");
                this.pmFileNode.pmFile.scan_id = (String) obj.get("scan_id");
            } catch (ParseException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        CloseableHttpResponse reportResponse = VirusTotalAPIHelper.getReport(this.pmFileNode.pmFile.getSha2());
        try {
            JSONObject obj = (JSONObject) parser.parse(getStringFromClosableHttpResponse(reportResponse));
            this.pmFileNode.pmFile.scanDate = (String) obj.get("scan_date");
            this.pmFileNode.pmFile.positives = (Long) obj.get("positives");
            this.pmFileNode.pmFile.totals = (Long) obj.get("total");
            JSONObject scans = (JSONObject) obj.get("scans");
            if (scans != null && !scans.isEmpty()) {
                Iterator iterator = scans.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pair = (Map.Entry) iterator.next();
                    JSONObject scan = (JSONObject) pair.getValue();
                    this.pmFileNode.pmFile.listOfScans.add(new Scan((String) pair.getKey(),
                            true,
                            scan.get("result") == null ? "" : (String) scan.get("result"),
                            scan.get("version") == null ? "" : (String) scan.get("version"),
                            scan.get("update") == null ? "" : (String) scan.get("update")));
                    iterator.remove(); // avoids a ConcurrentModificationException
                    this.pmFileNode.pmFile.numberOfScans++;
                }
            }

        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        MainWindowTopComponent.getInstance().writeOutput(pmFileNode.pmFile.toString());
    }

}
