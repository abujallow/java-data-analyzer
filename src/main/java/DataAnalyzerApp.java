import controllers.DataAnalyzerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class DataAnalyzerApp extends Application {
	
	@Override public void start(final Stage primaryStage) {
		
		primaryStage.setTitle("Data Analyzer");
		
		final var loader = new FXMLLoader(DataAnalyzerApp.class.getResource("/fxml/data-analyzer.fxml"));
		try { primaryStage.setScene(loader.load()); }
		catch (IOException e) { throw new RuntimeException(e); }

		final DataAnalyzerController controller = loader.getController();

		primaryStage.show();
	}
	
}
