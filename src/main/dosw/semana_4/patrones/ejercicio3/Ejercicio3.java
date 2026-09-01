package dosw.semana_4.patrones.ejercicio3;

/**
 * Ejercicio 03 - Sistema de Reportes Empresariales
 * Patrones: Template Method + Factory Method
 */
public class Ejercicio3 {

    static abstract class ReportGenerator {
        public final void generate() {
            fetchData();
            processData();
            applyFormat();
            exportFile();
        }
        void fetchData() { System.out.println("Obteniendo datos..."); }
        void processData() { System.out.println("Procesando informacion..."); }
        abstract void applyFormat();
        abstract void exportFile();
    }
    static class PdfReport extends ReportGenerator {
        void applyFormat() { System.out.println("Aplicando formato PDF"); }
        void exportFile() { System.out.println("Exportando reporte.pdf"); }
    }
    static class ExcelReport extends ReportGenerator {
        void applyFormat() { System.out.println("Aplicando formato Excel"); }
        void exportFile() { System.out.println("Exportando reporte.xlsx"); }
    }
    static class CsvReport extends ReportGenerator {
        void applyFormat() { System.out.println("Aplicando formato CSV"); }
        void exportFile() { System.out.println("Exportando reporte.csv"); }
    }

    static class ReportFactory {
        static ReportGenerator create(String tipo) {
            return switch (tipo) {
                case "PDF" -> new PdfReport();
                case "EXCEL" -> new ExcelReport();
                case "CSV" -> new CsvReport();
                default -> throw new IllegalArgumentException("Tipo de reporte no soportado: " + tipo);
            };
        }
    }

    public static void main(String[] args) {
        ReportGenerator reportePdf = ReportFactory.create("PDF");
        System.out.println("--- Generando reporte PDF ---");
        reportePdf.generate();

        ReportGenerator reporteCsv = ReportFactory.create("CSV");
        System.out.println("--- Generando reporte CSV ---");
        reporteCsv.generate();
    }
}
