package br.com.rarp.view.relatorios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.control.EntradaPacienteCtrl;
import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.MedicoCtrl;
import br.com.rarp.control.PacienteCtrl;
import br.com.rarp.control.UsuarioCtrl;
import br.com.rarp.enums.Funcao;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.Medico;
import br.com.rarp.model.Paciente;
import br.com.rarp.model.Usuario;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.AutoCompleteComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import jfxtras.scene.control.LocalTimeTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioEntradaController implements Initializable {
	
	private Node node;
	
    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnImprimir;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker txtDataIni;

    @FXML
    private DatePicker txtDataFin;

    @FXML
    private LocalTimeTextField txtHoraIni;

    @FXML
    private LocalTimeTextField txtHoraFin;

    @FXML
    private AutoCompleteComboBox<Funcionario> cmbEnfermeira;

    @FXML
    private AutoCompleteComboBox<Funcionario> cmbAtendente;

    @FXML
    private AutoCompleteComboBox<Usuario> cmbUsuario;

    @FXML
    private AutoCompleteComboBox<Paciente> cmbPaciente;

    @FXML
    private AutoCompleteComboBox<Medico> cmbMedico;

    @FXML
    private TextField txtPreTriagem;

    @FXML
    private TableView<EntradaPaciente> tblEntradas;
    
    @FXML
    private TableColumn<EntradaPaciente, String> cmnCodigo;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnData;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnHora;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnUsuario;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnPaciente;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnMedico;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnEnfermeira;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnPreTriagem;

    @FXML
    private TableColumn<EntradaPaciente, String> cmnStatus;

    @FXML
    void imprimir(ActionEvent event) {
    	try {
			JasperReport report = JasperCompileManager.compileReport(getClass().getResource("RelatorioEntrada.jrxml").getFile());
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(tblEntradas.getItems()));
			String outputFilename = "D:\\MeuRelatorio.pdf";
			JasperExportManager.exportReportToPdfFile(print, outputFilename );
    	} catch (JRException e) {
			Utilitarios.erro("Erro ao imprimir relatório");
			e.printStackTrace();
		}
    }

    @FXML
    void voltar(ActionEvent event) {
    	if(((BorderPane) node.getParent()) != null)
			((BorderPane) node.getParent()).setCenter(null);
    }
    
    public RelatorioEntradaController() throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("RelatorioEntrada.fxml"));
			loader.setController(this);
			node = loader.load();
			node.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.ESCAPE)
						voltar(new ActionEvent());
				}	
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void atualizar(ActionEvent event) {
    	EntradaPacienteCtrl entradaPacienteCtrl = new EntradaPacienteCtrl();
    	try {
			tblEntradas.getItems().setAll(entradaPacienteCtrl.consultar(txtDataIni.getValue(), 
					txtDataIni.getValue(), 
					txtHoraIni.getLocalTime(), 
					txtHoraFin.getLocalTime(),
					cmbAtendente.getSelectedValue(),
					cmbEnfermeira.getSelectedValue(),
					cmbMedico.getSelectedValue(),
					cmbPaciente.getSelectedValue(),
					cmbUsuario.getSelectedValue(),
					txtPreTriagem.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao consultar as entradas de pacientes.\n" + "Descrição: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		prepararTela();
	}

	private void prepararTela() {
		try {
			cmbAtendente.getItems().setAll(new FuncionarioCtrl().getFuncionarios(Funcao.atendente));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbEnfermeira.getItems().setAll(new FuncionarioCtrl().getFuncionarios(Funcao.enfermeira));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbMedico.getItems().setAll(new MedicoCtrl().getMedicos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbPaciente.getItems().setAll(new PacienteCtrl().getPacientes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbUsuario.getItems().setAll(new UsuarioCtrl().getUsuarios());
		} catch (Exception e) {
			e.printStackTrace();
		}
		cmnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		cmnData.setCellValueFactory(new PropertyValueFactory<>("dtMovimentacao"));
	    cmnHora.setCellValueFactory(new PropertyValueFactory<>("hrMovimentacao"));
	    cmnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
	    cmnPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
	    cmnMedico.setCellValueFactory(new PropertyValueFactory<>("medico"));
	    cmnEnfermeira.setCellValueFactory(new PropertyValueFactory<>("enfermeira"));
	    cmnPreTriagem.setCellValueFactory(new PropertyValueFactory<>("preTriagem"));
	    cmnStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EntradaPaciente,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<EntradaPaciente, String> param) {
				String value = "";
				if(param.getValue() != null)
					value = param.getValue().isStatus() ? "Sim" : "Não";
				return new SimpleStringProperty(value);
			}
		});
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
