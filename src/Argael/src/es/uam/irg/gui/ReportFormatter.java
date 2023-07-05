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
import es.uam.irg.utils.StringUtils;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import org.json.JSONObject;

/**
 * HTML report formatter class.
 */
public class ReportFormatter {

    // Class constants
    private static final String REPORTS_PATH = "Resources/views/";

    private final DecimalFormat df;
    private final DateFormat dsf;
    private final DateTimeFormatter dtf;
    private Map<String, String> templates;

    /**
     *
     * @param decimalFormat
     * @param dateFormat
     */
    public ReportFormatter(String decimalFormat, String dateFormat) {
        this.df = new DecimalFormat(decimalFormat);
        this.dsf = new SimpleDateFormat(dateFormat);
        this.dtf = DateTimeFormatter.ofPattern(dateFormat);
        loadReportTemplates();
    }

    /**
     *
     * @param date
     * @return
     */
    public String formatDate(Date date) {
        return dsf.format(date);
    }

    /**
     *
     * @param now
     * @return
     */
    public String formatDate(LocalDateTime now) {
        return dtf.format(now);
    }

    /**
     *
     * @param num
     * @return
     */
    public String formatNumber(double num) {
        return df.format(num);
    }

    /**
     * Applies a pretty format to the content of a report.
     *
     * @param content
     * @param format
     * @param commentLevel
     * @return
     */
    public String getPrettyReport(String content, String format, Map<Integer, Integer> commentLevel) {
        String result = "";

        if (!StringUtils.isEmpty(content)) {
            String[] components = content.split("\n");
            int nRows = components.length;
            System.out.println(" - Number of sentences: " + nRows);

            StringBuilder body = new StringBuilder();
            format = format.toUpperCase();
            long start, finish;
            int timeElapsed;

            // 1. Create user report from JSONL source
            start = System.nanoTime();
            if (format.equals("JSONL")) {
                String report = templates.get("PROPOSAL_INFO");
                String comment = templates.get("COMMENT_INFO");
                String tagType;
                String textValue;
                StringBuilder commentList = new StringBuilder();

                // Create report
                for (String component : components) {
                    JSONObject json = new JSONObject(component);
                    textValue = json.getString("text");

                    if (FunctionUtils.jsonContainsKey(json, "proposal_id")) {
                        tagType = json.getString("info");

                        switch (tagType) {
                            case "title":
                                report = report.replace("$CODE$", json.getString("proposal_id"));
                                report = report.replace("$TITLE$", textValue);
                                break;
                            case "summary":
                                report = report.replace("$SUMMARY$", textValue);
                                break;
                            case "text":
                                report = report.replace("$TEXT$", textValue);
                                break;
                            default:
                                break;
                        }

                    } else {

                        if (!StringUtils.isEmpty(textValue)) {
                            int commentId = Integer.parseInt(json.getString("comment_id"));
                            String leftPadding = "0";
                            if (commentLevel.containsKey(commentId)) {
                                leftPadding = Integer.toString(commentLevel.get(commentId) * 10);
                            }
                            String currComment = comment.replace("$TEXT$", textValue);
                            currComment = currComment.replace("PADDING-LEFT", leftPadding);
                            commentList.append(currComment);
                        }
                    }
                }

                report = report.replace("$COMMENTS$", commentList.toString());
                body.append(report);

            } else if (format.equals("TXT")) {
                body.append(content);
            }
            finish = System.nanoTime();
            timeElapsed = (int) ((finish - start) / 1000000);

            // Update final report
            result = getProposalsReport(body.toString(), nRows, timeElapsed);
            System.out.println(" - The results report has been created");
        }

        return result;
    }

    /**
     *
     * @param claim
     * @return
     */
    public String highlightClaim(String claim) {
        return "<span style='padding:3px; background-color: #C7DEFA;'>" + claim + "</span>";
    }

    /**
     *
     * @param claim
     * @param bold
     * @return
     */
    public String highlightClaim(String claim, boolean bold) {
        String hlClaim = highlightClaim(claim);
        if (bold) {
            hlClaim = boldSentence(hlClaim);
        }
        return hlClaim;
    }

    /**
     *
     * @param majorClaim
     * @return
     */
    public String highlightMajorClaim(String majorClaim) {
        return "<span style='padding:3px; background-color: #ABD2AC;'>" + majorClaim + "</span>";
    }

    /**
     *
     * @param majorClaim
     * @param bold
     * @return
     */
    public String highlightMajorClaim(String majorClaim, boolean bold) {
        String hlMajorClaim = highlightMajorClaim(majorClaim);
        if (bold) {
            hlMajorClaim = boldSentence(hlMajorClaim);
        }
        return hlMajorClaim;
    }

    /**
     *
     * @param premise
     * @return
     */
    public String highlightPremise(String premise) {
        return "<span style='padding:3px; background-color: #DED7FB;'>" + premise + "</span>";
    }

    /**
     *
     * @param premise
     * @param bold
     * @return
     */
    public String highlightPremise(String premise, boolean bold) {
        String hlPremise = highlightPremise(premise);
        if (bold) {
            hlPremise = boldSentence(hlPremise);
        }
        return hlPremise;
    }

    /**
     *
     * @param hlClaim
     * @return
     */
    private String boldSentence(String text) {
        return "<b><i>" + text + "</i></b>";
    }

    /**
     *
     * @param toString
     * @param nRows
     * @param timeElapsed
     * @return
     */
    private String getProposalsReport(String body, int nReports, int timeElapsed) {
        String result = templates.get("PROPOSAL_LIST");
        result = result.replace("$N_REPORTS$", "" + nReports);
        result = result.replace("$TIME_ELAPSED$", "" + timeElapsed);
        result = result.replace("$CURRENT_TIME$", formatDate(LocalDateTime.now()));
        result = result.replace("$CONTENT$", body);
        return result;
    }

    /**
     * Loads all available reports into memory from disk.
     */
    private void loadReportTemplates() {
        templates = IOManager.readReportFiles(REPORTS_PATH);
    }

}
