package com.example.spring_boot_postgres.Utils;

public enum ReportTypeEnum {
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
    PDF{
        @Override
        public String toString() {
            return "pdf";
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
