package com.example.spring_boot_postgres.controller;


import com.example.spring_boot_postgres.Utils.JasperExportUtil;
import com.example.spring_boot_postgres.Utils.ReportTypeEnum;
import com.example.spring_boot_postgres.model.Tutorial;
import com.example.spring_boot_postgres.repository.TutorialRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.example.spring_boot_postgres.Utils.ReportConstants.*;

@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    JasperExportUtil jasperExportUtil;

    @Autowired
    DataSource dataSource;


    Function<String,String> generateFile = fileName -> REPORT_OUTPUT_PATH + REPORT_NAME + "." + fileName;



    @GetMapping("/report")
    public ResponseEntity<String> generateJasperReport(@RequestParam("format") ReportTypeEnum format ){
        try{
            String filePath =REPORT_TEMPLATE_PATH + "Tutorial.jrxml";
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            tutorialRepository.findAll().forEach(tutorials::add);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("@ReportName", "Rejected Report");
            JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource.getConnection() );
            jasperExportUtil.exportJasperReport(jasperPrint,format);
            return  new ResponseEntity<String>("Report generated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/report/{format}")
    public ResponseEntity<String> generateReport_Path(@PathVariable("format") ReportTypeEnum format ){
        try{
            String filePath =REPORT_TEMPLATE_PATH + "Tutorial.jrxml";
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            tutorialRepository.findAll().forEach(tutorials::add);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("@ReportName", "Rejected Report");
            JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource.getConnection() );
            jasperExportUtil.exportJasperReport(jasperPrint,format);
            return  new ResponseEntity<String>("Report generated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
