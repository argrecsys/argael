/**
 * Copyright 2022
 * Andrés Segura-Tinoco
 * Information Retrieval Group at Universidad Autonoma de Madrid
 *
 * This is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the current software. If not, see <http://www.gnu.org/licenses/>.
 */
package es.uam.irg.gui;

import es.uam.irg.io.IOManager;
import es.uam.irg.utils.FunctionUtils;
import java.awt.Color;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.json.JSONObject;

/**
 * HTML report formatter class.
 */
public class ReportFormatter {

    // Class constants
    public static final String APP_URL = "https://www.web.es/";
    public static final Color HIGHLIGHT_COLOR_CURRENT = new Color(0, 100, 0);
    public static final Color HIGHLIGHT_COLOR_DEFAULT = Color.BLUE;
    public static final String MODE_ANNOTATE = "ANNOTATE";
    private static final String REPORTS_PATH = "Resources/views/";

    private final DecimalFormat df;
    private final DateTimeFormatter dtf;
    private Map<String, String> reports;

    /**
     *
     * @param decimalFormat
     * @param dateFormat
     */
    public ReportFormatter(String decimalFormat, String dateFormat) {
        this.df = new DecimalFormat(decimalFormat);
        this.dtf = DateTimeFormatter.ofPattern(dateFormat);
        loadReports();
    }

    /**
     *
     * @param content
     * @param reportType
     * @return
     */
    public String getPrettyReport(String content, String reportType) {
        String result = "";
        StringBuilder body = new StringBuilder();
        reportType = reportType.toUpperCase();
        long start, finish;
        int timeElapsed;
        String[] components = content.split("\n");
        int nRows = components.length;
        System.out.println(nRows);

        // 1. Create user report from JSONL source
        start = System.nanoTime();
        if (reportType.equals("JSONL")) {
            String baseReport = reports.get("PROPOSAL_INFO");

            for (int i = 0; i < nRows; i++) {
                JSONObject json = new JSONObject(components[i]);
                String report = baseReport.replace("$IX$", "" + (i + 1));
                // report = report.replace("$CODE$", json.getString("proposal_id"));
                report = report.replace("$SUMMARY$", json.getString("text"));
                body.append(report);
            }

        } else if (reportType.equals("TXT")) {
            body.append(content);
        }
        finish = System.nanoTime();
        timeElapsed = (int) ((finish - start) / 1000000);

        // Update final report
        result = getProposalsReport(body.toString(), nRows, timeElapsed);
        FunctionUtils.printWithDatestamp(">> The results report has been created");

        return result;
    }

    /**
     *
     * @param toString
     * @param nRows
     * @param timeElapsed
     * @return
     */
    private String getProposalsReport(String body, int nReports, int timeElapsed) {
        String result = reports.get("PROPOSAL_LIST");
        result = result.replace("$N_REPORTS$", "" + nReports);
        result = result.replace("$TIME_ELAPSED$", "" + timeElapsed);
        result = result.replace("$CURRENT_TIME$", dtf.format(LocalDateTime.now()));
        result = result.replace("$CONTENT$", body);
        return result;
    }

    /**
     * Loads all available reports into memory from disk.
     */
    private void loadReports() {
        reports = IOManager.readHtmlReports(REPORTS_PATH);
    }

}
