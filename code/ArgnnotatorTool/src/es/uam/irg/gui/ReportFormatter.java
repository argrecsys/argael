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

import java.awt.Color;
import java.text.DecimalFormat;
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
    }

    /**
     *
     * @param content
     * @param reportType
     * @return
     */
    public String getPrettyReport(String content, String reportType) {
        StringBuilder report = new StringBuilder();
        reportType = reportType.toUpperCase();

        if (reportType.equals("JSONL")) {
            String[] components = content.split("\n");

            if (components != null && components.length > 0) {
                System.out.println(components.length);
                for (String comp : components) {
                    JSONObject json = new JSONObject(comp);
                    report.append(json.toString());
                }
            }

        } else if (reportType.equals("TXT")) {
            report.append(content);
        }

        return report.toString();
    }

}
