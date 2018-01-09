package management.utility;

import management.domain.Report;

import java.util.concurrent.LinkedBlockingDeque;


public class InventoryReportUtility {

    private static volatile LinkedBlockingDeque<Report> inventoryReport;

    private InventoryReportUtility() {

    };

    public static LinkedBlockingDeque<Report> getInventoryReportList() {
        if(inventoryReport == null) {
            synchronized (InventoryReportUtility.class) {
                if(inventoryReport == null) {
                    inventoryReport = new LinkedBlockingDeque<Report>();
                }
            }
        }

        return inventoryReport;
    }
}
