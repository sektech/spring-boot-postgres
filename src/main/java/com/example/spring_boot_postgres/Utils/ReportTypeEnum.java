package com.example.spring_boot_postgres.Utils;

public enum ReportTypeEnum {

    PDF{
        @Override
        public String toString() {
            return "pdf";
        }
    },
    CSV {
        @Override
        public String toString() {
            return "csv";
        }
    },
    XLSX {
        @Override
        public String toString() {
            return "xlsx";
        }
    },
    HTML {
        @Override
        public String toString() {
            return "html";
        }
    }
    ,
    XML {
        @Override
        public String toString() {
            return "xml";
        }
    }


    ,
    DOC{
        @Override
        public String toString() {
            return "doc";
        }
    }
}
