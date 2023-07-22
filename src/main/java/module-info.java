module com.wdcoder.xhelper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires io.github.bonigarcia.webdrivermanager;
    requires org.seleniumhq.selenium.ie_driver;
    requires org.seleniumhq.selenium.firefox_driver;
    requires org.seleniumhq.selenium.chrome_driver;


    opens com.wdcoder.xhelper to javafx.fxml;
    exports com.wdcoder.xhelper;
}