module gui_handle.estoril_1942 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens gui_handle.estoril_1942 to javafx.fxml;
    exports gui_handle.estoril_1942;
}