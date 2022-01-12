package lab_app.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lab_app.dao.*;
import lab_app.entity.*;
import lab_app.util.JDBCConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements Initializable {
    @FXML
    private TextField txtNamaBarang;

    @FXML
    private TableColumn<JadwalMaintenanceEntity, String> kolomIdJadwal;

    @FXML
    private GridPane pnJurnal;

    @FXML
    private TableColumn<JurnalEntity, String> kolomKetTugasJurnal;

    @FXML
    private ComboBox<LaboratoriumEntity> comboLabJadwal;

    @FXML
    private ComboBox<AnggotaStaffEntity> comboPelaksanaTugas;

    @FXML
    private ComboBox<AnggotaStaffEntity> comboPemberiTugas;

    @FXML
    private TableColumn<InventarisEntity, String> kolomLabInventaris;

    @FXML
    private ComboBox<String> comboJabatan = new ComboBox<String>();

    @FXML
    private DatePicker tanggalDibuatJurnal;

    @FXML
    private TableColumn<JurnalEntity,String> kolomStatusJurnal;

    @FXML
    private ComboBox<LaboratoriumEntity> comboLabInventaris;

    @FXML
    private TableColumn<JadwalMaintenanceEntity, String> kolomAnggota2Jadwal;

    @FXML
    private TableColumn<JadwalMaintenanceEntity, String> kolomTanggal;

    @FXML
    private TextField txtKetTugas;

    @FXML
    private GridPane pnInventaris;

    @FXML
    private TableColumn<JadwalMaintenanceEntity, String> kolomLabJadwal;

    @FXML
    private TableColumn<AnggotaStaffEntity, String> kolomThnMasuk;

    @FXML
    private Label lblStatus;

    @FXML
    private TableColumn<JurnalEntity, String> kolomPelaksanaTugasJurnal;

    @FXML
    private TextField txtLast;

    @FXML
    private Button btnJurnal;


    @FXML
    private Button btnJadwal;

    @FXML
    private Pane pnlStatus;

    @FXML
    private TableView<JurnalEntity> tableJurnal;

    @FXML
    private DatePicker dateJadwal;

    @FXML
    private TableColumn<JadwalMaintenanceEntity, String> kolomAnggota1Jadwal;

    @FXML
    private ComboBox<AnggotaStaffEntity> comboAnggotaMt2;

    @FXML
    private TextField txtFirst;

    @FXML
    private ComboBox<AnggotaStaffEntity> comboAnggotaMt1;

    @FXML
    private TableColumn<InventarisEntity, String> kolomJumlahBarang;

    @FXML
    private Button btnAnggota;

    @FXML
    private TableColumn<AnggotaStaffEntity, String> kolomFirst;

    @FXML
    private Button btnInventaris;

    @FXML
    private TableColumn<JurnalEntity, String> kolomTanggalDibuatJurnal;

    @FXML
    private TextField txtNrp;

    @FXML
    private TableColumn<JurnalEntity, String> kolomIdJurnal;

    @FXML
    private TextField txtJumlahBarang;

    @FXML
    private GridPane pnAnggota;

    @FXML
    private TableView<InventarisEntity> tableInventaris;

    @FXML
    private TableView<JadwalMaintenanceEntity> tableJadwal;

    @FXML
    private TableColumn<AnggotaStaffEntity, String> kolomNrp;

    @FXML
    private GridPane pnJadwal;

    @FXML
    private TableColumn<JurnalEntity, String> kolomPemberiTugasJurnal;

    @FXML
    private TableColumn<InventarisEntity, String> kolomNamaBarang;

    @FXML
    private TableColumn<AnggotaStaffEntity, String> kolomJabatan;

    @FXML
    private TableColumn<InventarisEntity, String> kolomIdInventaris;

    @FXML
    private TableColumn<AnggotaStaffEntity, String> kolomLast;

    @FXML
    private TextField txtKegiatan;

    @FXML
    private ComboBox<String> comboBahasa;

    @FXML
    private TableView<AnggotaStaffEntity> tableAnggota;

    @FXML
    private ComboBox<AnggotaStaffEntity> comboAnggotaKoor;
    @FXML
    private TableColumn<JadwalMaintenanceEntity,String> kolomKoor;
    @FXML
    private TableColumn<JadwalMaintenanceEntity,String> kolomMulai;
    @FXML
    private TableColumn<JadwalMaintenanceEntity,String> kolomSelesai;
    @FXML
    private TableColumn<JadwalMaintenanceEntity,String> kolomTotal;
    @FXML
    private TableColumn<JadwalMaintenanceEntity,String> kolomKegiatan;

    @FXML
    private Button btnReport;
    @FXML
    private Button btnSetting;
    @FXML
    private Button btnClose;

    @FXML
    private AnchorPane pnSetting;

    private Locale locale2 = new Locale("EN");


    @FXML
    private DatePicker dateThnMasuk;

    private ObservableList<AnggotaStaffEntity> anggotaStaffList;
    private ObservableList<InventarisEntity> inventarisList;
    private ObservableList<JurnalEntity> jurnalList;
    private ObservableList<JadwalMaintenanceEntity> jadwalList;

    private AnggotaStaffDaoImpl anggotaStaffDao = new AnggotaStaffDaoImpl();
    private LabDaoImpl labDao = new LabDaoImpl();
    private InventarisDaoImpl inventarisDao = new InventarisDaoImpl();
    private JurnalDaoImpl jurnalDao = new JurnalDaoImpl();
    private JadwalDaoImpl jadwalDao = new JadwalDaoImpl();

    private java.util.Date t1;
    private java.util.Date t2;

    @FXML
    private AnchorPane pnReport;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resourceBundle");

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        lblStatus.setText("  - Lab App - ");
        anggotaStaffList = anggotaStaffDao.getAll();
        inventarisList = (ObservableList<InventarisEntity>) inventarisDao.getAll();
        jurnalList = (ObservableList<JurnalEntity>) jurnalDao.getAll();
        jadwalList = (ObservableList<JadwalMaintenanceEntity>) jadwalDao.getAll();
        // table Anggota Staff
        tableAnggota.setItems(anggotaStaffList);
        kolomNrp.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNrp()));
        kolomFirst.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getFirstName()));
        kolomLast.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getLastName()));
        kolomThnMasuk.setCellValueFactory(data-> new SimpleStringProperty (String.valueOf(data.getValue().getTahunMasuk())));
        kolomJabatan.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getJabatan()));
        // table Inventaris
        tableInventaris.setItems(inventarisList);
        kolomIdInventaris.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getIdInventaris())));
        kolomNamaBarang.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNamaBarang()));
        kolomJumlahBarang.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getJumlah())));
        kolomLabInventaris.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getLaboratoriumByLaboratoriumIdLab().getNamaLab()));
        // table Jurnal
        tableJurnal.setItems(jurnalList);
        kolomIdJurnal.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getIdJurnal())));
        kolomKetTugasJurnal.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getKetTugas()));
        kolomTanggalDibuatJurnal.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTanggalDibuat())));
        kolomPemberiTugasJurnal.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getAnggotaStaffByPemberiTugas().getFirstName()));
        kolomPelaksanaTugasJurnal.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getAnggotaStaffByPelaksanaTugas().getFirstName()));
        kolomStatusJurnal.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getStatus()));
        // table Maintenance
        tableJadwal.setItems(jadwalList);
        kolomIdJadwal.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getIdJadwal())));
        kolomTanggal.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTanggal())));
        kolomKegiatan.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNamaKegiatan()));
        kolomAnggota1Jadwal.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getAnggotaStaffByAnggotaStaffNrp().getFirstName()));
        kolomAnggota2Jadwal.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getAnggotaStaffByAnggotaStaffNrp1().getFirstName()));
        kolomLabJadwal.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getLaboratoriumByLaboratoriumIdLab().getNamaLab()));
        kolomKoor.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getAnggotaStaffByKoor().getFirstName()));
        kolomMulai.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getWaktuMulai())));
        kolomSelesai.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getWaktuSelesai())));
        kolomTotal.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTotalWaktu())));
        //
        comboLabInventaris.setItems((ObservableList<LaboratoriumEntity>) labDao.getAll());
        comboPemberiTugas.setItems(anggotaStaffDao.getAll());
        comboPelaksanaTugas.setItems(anggotaStaffDao.getAll());
        comboAnggotaKoor.setItems(anggotaStaffDao.getAllKoor());
        comboAnggotaMt1.setItems(anggotaStaffDao.getAll());
        comboAnggotaMt2.setItems(anggotaStaffDao.getAll());
        comboBahasa.setPromptText("Select Languages");
        ObservableList<String> listBahasa = comboBahasa.getItems();
        listBahasa.add("Default");
        listBahasa.add("English-US");
        listBahasa.add("Indonesia-IDN");
        comboLabJadwal.setItems((ObservableList<LaboratoriumEntity>) labDao.getAll());
        ObservableList<String> list = comboJabatan.getItems();
        list.add("Staff");
        list.add("Koordinator");
        list.add("Admin");
        editableColsAnggota();
        editableColsInventaris();
        editableColsJurnal();
        editableColsJadwal();
    }

    @FXML
    private void handleClicks(ActionEvent actionEvent){
        if(actionEvent.getSource() == btnAnggota){
            lblStatus.setText(btnAnggota.getText());
            pnAnggota.toFront();
        } else if (actionEvent.getSource() == btnInventaris) {
            lblStatus.setText(btnInventaris.getText());
            pnInventaris.toFront();
        } else if (actionEvent.getSource()==btnJadwal) {
            lblStatus.setText(btnJadwal.getText());
            pnJadwal.toFront();
        } else if (actionEvent.getSource()==btnJurnal) {
            lblStatus.setText("        "+btnJurnal.getText());
            pnJurnal.toFront();
        } else if (actionEvent.getSource()==btnReport){
            lblStatus.setText("        "+btnReport.getText());
            pnReport.toFront();
        } else if (actionEvent.getSource()==btnSetting){
            lblStatus.setText("    "+btnSetting.getText());
            pnSetting.toFront();
        } else if (actionEvent.getSource()==btnClose){
            Platform.exit();
        }


    }


    public void addAnggota(ActionEvent actionEvent) {
        if (!txtNrp.getText().isEmpty() && !txtFirst.getText().isEmpty() && !txtLast.getText().isEmpty()
        && !comboJabatan.getValue().isEmpty()) {
            AnggotaStaffEntity anggotaStaffEntity = new AnggotaStaffEntity();
            anggotaStaffEntity.setNrp(txtNrp.getText());
            anggotaStaffEntity.setFirstName(txtFirst.getText());
            anggotaStaffEntity.setLastName(txtLast.getText());
            anggotaStaffEntity.setJabatan(comboJabatan.getValue());
            anggotaStaffEntity.setTahunMasuk(Date.valueOf(dateThnMasuk.getValue()));
            anggotaStaffDao.addData(anggotaStaffEntity);
            anggotaStaffList.clear();
            anggotaStaffList.addAll(anggotaStaffDao.getAll());
            comboPelaksanaTugas.setItems(anggotaStaffList);
            comboPemberiTugas.setItems(anggotaStaffList);
            comboAnggotaMt2.setItems(anggotaStaffList);
            comboAnggotaMt1.setItems(anggotaStaffList);
            comboAnggotaKoor.setItems(anggotaStaffList);
        } else {
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        }

    }

    public void addNewJurnal(ActionEvent actionEvent) {
        if (!txtKetTugas.getText().isEmpty() && !comboPemberiTugas.getSelectionModel().isEmpty()
        && !comboPelaksanaTugas.getSelectionModel().isEmpty()) {
            JurnalEntity jurnalEntity = new JurnalEntity();
            jurnalEntity.setKetTugas(txtKetTugas.getText());
            jurnalEntity.setTanggalDibuat(Date.valueOf(tanggalDibuatJurnal.getValue()));
            jurnalEntity.setStatus("NOT DONE");
            jurnalEntity.setAnggotaStaffByPemberiTugas(comboPemberiTugas.getValue());
            jurnalEntity.setAnggotaStaffByPelaksanaTugas(comboPelaksanaTugas.getValue());
            jurnalDao.addData(jurnalEntity);
            jurnalList.clear();
            jurnalList.addAll(jurnalDao.getAll());
        } else {
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        }
    }

    public void deleteJurnal(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this data?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            JurnalEntity jurnalEntity;
            jurnalEntity = tableJurnal.getSelectionModel().getSelectedItem();
            try {
                jurnalDao.delData(jurnalEntity);
            } catch (Exception exception) {
                System.out.println("Tidak ada error");
            }
            jurnalList.clear();
            jurnalList.addAll(jurnalDao.getAll());
        }
    }

    public void deleteBarang(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this data?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            InventarisEntity inventarisEntity;
            inventarisEntity = tableInventaris.getSelectionModel().getSelectedItem();
            try {
                inventarisDao.delData(inventarisEntity);
            } catch (Exception exception) {
                System.out.println("Tidak ada error");
            }
            inventarisList.clear();
            inventarisList.addAll(inventarisDao.getAll());
        }
    }

    public void addNewBarang(ActionEvent actionEvent) {
        if (!txtNamaBarang.getText().isEmpty() && !txtJumlahBarang.getText().isEmpty()
        && !comboLabInventaris.getSelectionModel().isEmpty()) {
            InventarisEntity inventarisEntity = new InventarisEntity();
            inventarisEntity.setNamaBarang(txtNamaBarang.getText());
            inventarisEntity.setJumlah(Integer.parseInt(txtJumlahBarang.getText()));
            inventarisEntity.setLaboratoriumByLaboratoriumIdLab(comboLabInventaris.getValue());
            inventarisDao.addData(inventarisEntity);
            inventarisList.clear();
            inventarisList.addAll(inventarisDao.getAll());
        } else {
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        }
    }

    public void deleteAnggota(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this data?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AnggotaStaffEntity anggotaStaffEntity;
            anggotaStaffEntity = tableAnggota.getSelectionModel().getSelectedItem();
            try {
                anggotaStaffDao.delData(anggotaStaffEntity);
            } catch (Exception exception) {
                System.out.println("Tidak ada error");
            }
            anggotaStaffList.clear();
            anggotaStaffList.addAll(anggotaStaffDao.getAll());
        }

    }

    public void addNewJadwal(ActionEvent actionEvent) {
        if (!comboAnggotaMt1.getSelectionModel().isEmpty() && !comboAnggotaMt2.getSelectionModel().isEmpty()
        && !comboAnggotaKoor.getSelectionModel().isEmpty() && !txtKegiatan.getText().isEmpty() &&
        !comboLabJadwal.getSelectionModel().isEmpty()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            JadwalMaintenanceEntity jadwalMaintenanceEntity = new JadwalMaintenanceEntity();
            jadwalMaintenanceEntity.setAnggotaStaffByAnggotaStaffNrp(comboAnggotaMt1.getValue());
            jadwalMaintenanceEntity.setAnggotaStaffByAnggotaStaffNrp1(comboAnggotaMt2.getValue());
            jadwalMaintenanceEntity.setAnggotaStaffByKoor(comboAnggotaKoor.getValue());
            jadwalMaintenanceEntity.setTanggal(Date.valueOf(dateJadwal.getValue()));
            jadwalMaintenanceEntity.setWaktuMulai(Time.valueOf(String.valueOf(dtf.format(now))));
            jadwalMaintenanceEntity.setNamaKegiatan(txtKegiatan.getText());
            jadwalMaintenanceEntity.setLaboratoriumByLaboratoriumIdLab(comboLabJadwal.getValue());
            jadwalDao.addData(jadwalMaintenanceEntity);
            jadwalList.clear();
            jadwalList.addAll(jadwalDao.getAll());
        } else {
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        }
    }

    public void deleteJadwal(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this data?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            JadwalMaintenanceEntity jadwalMaintenanceEntity;
            jadwalMaintenanceEntity = tableJadwal.getSelectionModel().getSelectedItem();
            try {
                jadwalDao.delData(jadwalMaintenanceEntity);
            } catch (Exception exception) {
                System.out.println("Tidak ada error");
            }
            jadwalList.clear();
            jadwalList.addAll(jadwalDao.getAll());
        }
    }


    private void editableColsAnggota(){
        tableInventaris.setEditable(true);
        kolomJabatan.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomFirst.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomLast.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomThnMasuk.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomFirst.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirstName(e.getNewValue());
            AnggotaStaffEntity selected;
            selected = (AnggotaStaffEntity) tableAnggota.getSelectionModel().getSelectedItem();

            selected.setNrp(selected.getNrp());
            selected.setFirstName(e.getNewValue());
            selected.setLastName(selected.getLastName());
            selected.setJabatan(selected.getJabatan());
            selected.setTahunMasuk(selected.getTahunMasuk());

            anggotaStaffDao.updateData(selected);
            anggotaStaffList.clear();
            anggotaStaffList.addAll(anggotaStaffDao.getAll());
        });
        tableAnggota.setEditable(true);


        kolomLast.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLastName(e.getNewValue());
            AnggotaStaffEntity selected;
            selected = (AnggotaStaffEntity) tableAnggota.getSelectionModel().getSelectedItem();

            selected.setNrp(selected.getNrp());
            selected.setFirstName(selected.getFirstName());
            selected.setLastName(e.getNewValue());
            selected.setJabatan(selected.getJabatan());
            selected.setTahunMasuk(selected.getTahunMasuk());

            anggotaStaffDao.updateData(selected);
            anggotaStaffList.clear();
            anggotaStaffList.addAll(anggotaStaffDao.getAll());
        });

        kolomThnMasuk.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTahunMasuk(Date.valueOf(e.getNewValue()));
            AnggotaStaffEntity selected;
            selected = (AnggotaStaffEntity) tableAnggota.getSelectionModel().getSelectedItem();

            selected.setNrp(selected.getNrp());
            selected.setFirstName(selected.getFirstName());
            selected.setLastName(selected.getLastName());
            selected.setJabatan(selected.getJabatan());
            selected.setTahunMasuk(Date.valueOf(e.getNewValue()));

            anggotaStaffDao.updateData(selected);
            anggotaStaffList.clear();
            anggotaStaffList.addAll(anggotaStaffDao.getAll());
        });

        kolomJabatan.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setJabatan(e.getNewValue());
            AnggotaStaffEntity selected;
            selected = (AnggotaStaffEntity) tableAnggota.getSelectionModel().getSelectedItem();

            selected.setNrp(selected.getNrp());
            selected.setFirstName(selected.getFirstName());
            selected.setLastName(selected.getLastName());
            selected.setJabatan(e.getNewValue());
            selected.setTahunMasuk(selected.getTahunMasuk());

            anggotaStaffDao.updateData(selected);
            anggotaStaffList.clear();
            anggotaStaffList.addAll(anggotaStaffDao.getAll());
        });
    }

    private void editableColsInventaris(){
        kolomNamaBarang.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomJumlahBarang.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomNamaBarang.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNamaBarang(e.getNewValue());
            InventarisEntity selected;
            selected = tableInventaris.getSelectionModel().getSelectedItem();
            selected.setNamaBarang(e.getNewValue());
            inventarisDao.updateData(selected);
            inventarisList.clear();
            inventarisList.addAll(inventarisDao.getAll());
        });

        kolomJumlahBarang.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setJumlah(Integer.parseInt(e.getNewValue()));
            InventarisEntity selected;
            selected = tableInventaris.getSelectionModel().getSelectedItem();
            selected.setJumlah(Integer.parseInt(e.getNewValue()));
            inventarisDao.updateData(selected);
            inventarisList.clear();
            inventarisList.addAll(inventarisDao.getAll());
        });
    }

    private void editableColsJurnal(){
        tableJurnal.setEditable(true);
        kolomKetTugasJurnal.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomStatusJurnal.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomTanggalDibuatJurnal.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomKetTugasJurnal.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setKetTugas(e.getNewValue());
            JurnalEntity selected;
            selected = tableJurnal.getSelectionModel().getSelectedItem();
            selected.setKetTugas(e.getNewValue());
            jurnalDao.updateData(selected);
            jurnalList.clear();
            jurnalList.addAll(jurnalDao.getAll());
        });
        kolomStatusJurnal.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setStatus(e.getNewValue());
            JurnalEntity selected;
            selected = tableJurnal.getSelectionModel().getSelectedItem();
            selected.setStatus(e.getNewValue());
            jurnalDao.updateData(selected);
            jurnalList.clear();
            jurnalList.addAll(jurnalDao.getAll());
        });
        kolomTanggalDibuatJurnal.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTanggalDibuat(Date.valueOf(e.getNewValue()));
            JurnalEntity selected;
            selected = tableJurnal.getSelectionModel().getSelectedItem();
            selected.setTanggalDibuat(Date.valueOf(e.getNewValue()));
            jurnalDao.updateData(selected);
            jurnalList.clear();
            jurnalList.addAll(jurnalDao.getAll());
        });
    }

    private void editableColsJadwal(){
        tableJadwal.setEditable(true);
        kolomSelesai.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomSelesai.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setWaktuSelesai(Time.valueOf(e.getNewValue()));
            JadwalMaintenanceEntity selected;
            selected = tableJadwal.getSelectionModel().getSelectedItem();
            SimpleDateFormat formatter = new SimpleDateFormat("h:mm");
            try {
                t1 =  formatter.parse(e.getNewValue());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            try {
                t2 =  formatter.parse(String.valueOf(selected.getWaktuMulai()));
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            selected.setWaktuSelesai(Time.valueOf(e.getNewValue()));
            long timeDiff = t1.getTime() - t2.getTime();
            String time = String.valueOf(timeDiff/3600000)+':'+String.valueOf((timeDiff%3600000)/60000);
            selected.setTotalWaktu(Math.toIntExact(timeDiff / 3600000));
            jadwalDao.updateData(selected);
            jadwalList.clear();
            jadwalList.addAll(jadwalDao.getAll());
        });
    }

    public void anggotaReport(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                JasperPrint jp;
                Map param = new HashMap();
                try {
                    jp = JasperFillManager.fillReport("Reports/LaporanAnggotaStaffLab.jasper",
                            param,
                            JDBCConnection.getConnection());
                    JasperViewer viewer = new JasperViewer(jp, false);
                    viewer.setTitle("Rekap Anggota Staff Lab");
                    viewer.setVisible(true);
                } catch (JRException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();


    }

    public void maintenanceReport(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                JasperPrint jp;
                Map param = new HashMap();
                try {
                    jp = JasperFillManager.fillReport("Reports/RekapJamMaintenance.jasper",
                            param,
                            JDBCConnection.getConnection());
                    JasperViewer viewer = new JasperViewer(jp, false);
                    viewer.setTitle("Rekap Jam Maintenance");
                    viewer.setVisible(true);
                } catch (JRException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();

    }

    public void inventarisReport(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                JasperPrint jp;
                Map param = new HashMap();
                try {
                    jp = JasperFillManager.fillReport("Reports/LaporanInventarisLab.jasper",
                            param,
                            JDBCConnection.getConnection());
                    JasperViewer viewer = new JasperViewer(jp, false);
                    viewer.setTitle("Rekap Inventaris Lab");
                    viewer.setVisible(true);

                } catch (JRException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }


    public void LoadView(Locale locale) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/Main.fxml"));
        loader.setResources(ResourceBundle.getBundle("resourceBundle", locale));
        Parent root = loader.load();
        Stage s = (Stage) comboBahasa.getScene().getWindow();
        Scene tes = new Scene(root);
        s.setScene(tes);
    }

    public void changeLanguage(ActionEvent actionEvent) throws IOException {
        if (comboBahasa.getSelectionModel().getSelectedItem().equals("English-US"))  {
            Locale l = new Locale("EN");
            locale2 = l;
            LoadView(l);
        } else if (comboBahasa.getSelectionModel().getSelectedItem().equals("Indonesia-IDN")) {
            Locale l = new Locale("IN");
            locale2 = l;
            resourceBundle = ResourceBundle.getBundle("resourceBundle", locale2);;
            LoadView(l);
        } else {
            Locale l = new Locale("EN");
            locale2 = l;
            LoadView(l);
        }

    }
}
