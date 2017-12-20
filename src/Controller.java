import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Controller {
	
	@FXML
	private Button btn, svBtn;
	@FXML
	private ToggleButton bw;
	@FXML
	private TextField tf;
	@FXML
	private GridPane imagePane;
	@FXML
	private Pane z0, z1, z2, z3, z4,
			o0, o1, o2, o3, o4,
			t0, t1, t2, t3, t4,
			tr0, tr1, tr2, tr3, tr4,
			f0, f1, f2, f3, f4;
	
	public void initialize() {
		tf.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				reset();
				String input = tf.getText();
				if (bw.isSelected()) {
					colorizeBW(new IdenticonBW(input));
				}
				else {
					colorize(new Identicon(input));
				}
			}
		});
		btn.setOnAction(event -> {
			reset();
			String input = tf.getText();
			if (bw.isSelected()) {
				colorizeBW(new IdenticonBW(input));
			}
			else {
				colorize(new Identicon(input));
			}
		});
		svBtn.setOnAction(event -> {
			saveFile();
			System.out.println("saved");
		});
	}
	
	private void colorize(Identicon identicon) {
		Color                 color     = identicon.getColor();
		Map<Integer, Integer> paintGrid = identicon.getPaintGrid();
		for (int i = 0; i < paintGrid.size(); i++) {
			if (paintGrid.get(i) == 0) {
				continue;
			}
			Pane currentPane = getPane(i);
			currentPane.setBackground(new Background(new BackgroundFill(color,
			                                                            CornerRadii.EMPTY,
			                                                            Insets.EMPTY)));
		}
	}
	
	private void colorizeBW(IdenticonBW identicon) {
		Color      color = identicon.getColor();
		LinkedList grid  = identicon.getGrid();
		for (int i = 0; i < grid.size(); i++) {
			Pane currentPane = getPane(i);
			int  value       = (int) grid.get(i);
			currentPane.setBackground(new Background(new BackgroundFill(Color.rgb(value,
			                                                                      value,
			                                                                      value),
			                                                            CornerRadii.EMPTY,
			                                                            Insets.EMPTY)));
		}
	}
	
	private void saveFile() {
		// Set Extension filter
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
		           .add(new FileChooser.ExtensionFilter("png",
		                                                ".png"));
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			try {
				WritableImage wi = new WritableImage(250,
				                                     250);
				imagePane.snapshot(null,
				                   wi);
				BufferedImage bufferedImage = SwingFXUtils.fromFXImage(wi,
				                                                       null);
				ImageIO.write(bufferedImage,
				              "PNG",
				              file);
				System.out.println("Saved: " + file);
			} catch (IOException ex) { ex.printStackTrace(); }
		}
	}
	
	private void reset() {
		//First Row
		z0.setBackground(Background.EMPTY);
		z1.setBackground(Background.EMPTY);
		z2.setBackground(Background.EMPTY);
		z3.setBackground(Background.EMPTY);
		z4.setBackground(Background.EMPTY);
		//Second Row
		o0.setBackground(Background.EMPTY);
		o1.setBackground(Background.EMPTY);
		o2.setBackground(Background.EMPTY);
		o3.setBackground(Background.EMPTY);
		o4.setBackground(Background.EMPTY);
		//Third Row
		t0.setBackground(Background.EMPTY);
		t1.setBackground(Background.EMPTY);
		t2.setBackground(Background.EMPTY);
		t3.setBackground(Background.EMPTY);
		t4.setBackground(Background.EMPTY);
		//Fourth Row
		tr0.setBackground(Background.EMPTY);
		tr1.setBackground(Background.EMPTY);
		tr2.setBackground(Background.EMPTY);
		tr3.setBackground(Background.EMPTY);
		tr4.setBackground(Background.EMPTY);
		//Fifth Row
		f0.setBackground(Background.EMPTY);
		f1.setBackground(Background.EMPTY);
		f2.setBackground(Background.EMPTY);
		f3.setBackground(Background.EMPTY);
		f4.setBackground(Background.EMPTY);
	}
	private Pane getPane(int index) {
		switch (index) {
			case 0:
				return z0;
			case 1:
				return z1;
			case 2:
				return z2;
			case 3:
				return z3;
			case 4:
				return z4;
			case 5:
				return o0;
			case 6:
				return o1;
			case 7:
				return o2;
			case 8:
				return o3;
			case 9:
				return o4;
			case 10:
				return t0;
			case 11:
				return t1;
			case 12:
				return t2;
			case 13:
				return t3;
			case 14:
				return t4;
			case 15:
				return tr0;
			case 16:
				return tr1;
			case 17:
				return tr2;
			case 18:
				return tr3;
			case 19:
				return tr4;
			case 20:
				return f0;
			case 21:
				return f1;
			case 22:
				return f2;
			case 23:
				return f3;
			case 24:
				return f4;
		}
		return null;
	}
}
