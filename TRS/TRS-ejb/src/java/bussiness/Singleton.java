/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import java.util.HashMap;

/**
 *
 * @author user
 */
public class Singleton {

//    private static Singleton uniqueInstance;
    private static Singleton uniqueInstance = new Singleton();

    protected Singleton() {
    }

//    public static synchronized Singleton getInstance() {
//        if (uniqueInstance == null) {
//            uniqueInstance = new Singleton();
//        }
//        return uniqueInstance;
//    }
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    private HashMap param = new HashMap();
    private String format;
    private String fileName;
    private String reportName;
    private String reportIndex;
    private String DownloadedReportName;

    public String getDownloadedReportName() {
        return DownloadedReportName;
    }

    public void setDownloadedReportName(String DownloadedReportName) {
        this.DownloadedReportName = DownloadedReportName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HashMap getParam() {
        return param;
    }

    public void setParam(HashMap param) {
        this.param = param;
    }

    public String getReportIndex() {
        return reportIndex;
    }

    public void setReportIndex(String reportIndex) {
        this.reportIndex = reportIndex;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void getClearSingletonParameter() {

        param.clear();
        format = null;
        fileName = null;
        reportName = null;
        reportIndex = null;
    }
}
