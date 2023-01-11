package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports(); //tạo một đối tượng cho thư viện Extent

    public synchronized static ExtentReports getExtentReports() {
        //chỉ định chỗ xuất ra report
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/ExtentReport.html");
        reporter.config().setReportName("POM Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Sample");
        extentReports.setSystemInfo("Author", "MinhNN61");
        return extentReports;
    }
}
