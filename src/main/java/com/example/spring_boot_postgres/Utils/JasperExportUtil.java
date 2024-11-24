package com.example.spring_boot_postgres.Utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static com.example.spring_boot_postgres.Utils.ReportConstants.REPORT_NAME;
import static com.example.spring_boot_postgres.Utils.ReportConstants.REPORT_OUTPUT_PATH;

@Component
public class JasperExportUtil {



    public boolean exportJasperReport(JasperPrint jasperPrint, ReportTypeEnum reportType) throws JRException {
        boolean reportGenerated = false;
        // Enhanced switch expression with lambda-style cases
        switch (reportType) {
            case CSV -> {
                // Export to CSV
                JRCsvExporter csvExporter = new JRCsvExporter();
                csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                csvExporter.setExporterOutput(new SimpleWriterExporterOutput(generateFile.apply(ReportTypeEnum.CSV.toString())));
                csvExporter.exportReport();
                reportGenerated = true;
            }
            case XLSX -> {
                // Export to XLSX
                JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(generateFile.apply(ReportTypeEnum.XLSX.toString())));
                xlsxExporter.exportReport();
                reportGenerated = true;
            }
            case HTML -> {
                // Export to HTML
                JasperExportManager.exportReportToHtmlFile(jasperPrint,generateFile.apply(ReportTypeEnum.HTML.toString()));
                reportGenerated = true;
            }
            case XML -> {
                // Export to XML
                JRXmlExporter xmlExporter = new JRXmlExporter();
                xmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                xmlExporter.setExporterOutput(new SimpleXmlExporterOutput(generateFile.apply(ReportTypeEnum.XML.toString())));
                xmlExporter.exportReport();
                reportGenerated = true;
            }
            case DOC -> {
                // Export to DOCX (RTF format)
                JRRtfExporter docxExporter = new JRRtfExporter();
                docxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                docxExporter.setExporterOutput(new SimpleWriterExporterOutput(generateFile.apply(ReportTypeEnum.DOC.toString())));
                docxExporter.exportReport();
                reportGenerated = true;
            }
            default -> {
                JasperExportManager.exportReportToPdfFile(jasperPrint,generateFile.apply(ReportTypeEnum.PDF.toString()));
                reportGenerated = true;
            }

        }

        return reportGenerated;
    }

    Function<String,String> generateFile = fileName -> REPORT_OUTPUT_PATH + REPORT_NAME + "." + fileName;
}
