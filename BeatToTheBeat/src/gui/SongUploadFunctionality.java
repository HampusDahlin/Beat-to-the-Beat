package gui;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import musichandler.Genre;
import musichandler.Song;
import services.HomogeneousFileHandler;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
//derp
public class SongUploadFunctionality {
	private SongUploadPanel uploadPanel;
	private Minim minim;

	public SongUploadFunctionality(SongUploadPanel uploadPanel) {
		this.uploadPanel = uploadPanel;
	}

	private void copyFileToBTTB(File songFile) {
		File dest = new File(System.getProperty("user.dir") + "\\songs\\" + songFile.getName());
		try {
			Files.copy(songFile.toPath(), dest.toPath());
		} catch (IOException e) {
			uploadPanel.getSuccesLabel().setText("An error occured, please try again");
			uploadPanel.getSuccesLabel().setForeground(Color.red);
		} 
	}

	public void browse() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"MP3, WAV & WMA music", "mp3", "wav", "wma");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(uploadPanel);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			uploadPanel.getOriginalFilepathField().setText(
					chooser.getSelectedFile().getAbsolutePath());
			File songFile = new File(chooser.getSelectedFile().getAbsolutePath());
			presentInfo(songFile);
		}
	}

	public void presentInfo(File songFile) {
		AudioPlayer player = null;
		try {
			minim = new Minim(this);
			player = minim.loadFile(uploadPanel.getOriginalFilepathField().getText(), 1000);
		} catch (Exception e) {
			System.out.println(uploadPanel.getOriginalFilepathField().getText());
		}

		try {
			uploadPanel.getArtistField().setText(player.getMetaData().author());
		} catch (NullPointerException e) {
			uploadPanel.getArtistField().setText("");
		}

		try {
			uploadPanel.getSongNameField().setText(player.getMetaData().title());
		} catch (NullPointerException e) {
			uploadPanel.getSongNameField().setText(returnFileName(songFile.getName()));
		}
	}

	public InputStream createInput(String fileName) {
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			return is;
		} catch (Exception e) {
			is = null;
		}
		return is;
	}

	public String returnFileName(String filename) {
		String[] splited = filename.split("\\.");
		return splited[splited.length - 2];
	}

	public boolean checkInputOk() {
		if(!checkIfFieldOk(uploadPanel.getOriginalFilepathField())) {
			return false;
		} else if (!checkIfFieldOk(uploadPanel.getSongNameField())) {
			return false;
		} else if (!checkIfFieldOk(uploadPanel.getArtistField())) {
			return false;
		}
		return true;
	}

	public boolean checkIfFieldOk(JTextField textfield) {
		if(!textfield.getText().equals("")) {
			return true;
		} 
		return false;
	}

	public void loadToChoice(Genre[] genreList) {
		for(Genre genre : genreList) {
			uploadPanel.getGenreChoice().add(genre.getName());
		}
	}

	public void setResponse(String response, boolean success) {
		uploadPanel.getSuccesLabel().setVisible(true);
		uploadPanel.getSuccesLabel().setText(response);
		if(success) {
			uploadPanel.getSuccesLabel().setForeground(Color.green);
		} else {
			uploadPanel.getSuccesLabel().setForeground(Color.red);
		}
	}

	public void load(List<Song> songList, Genre[] genreList) {
		if(checkInputOk()) {
			setResponse("File loaded successfully!", true);
			File songFile = new File(uploadPanel.getOriginalFilepathField().getText());
			copyFileToBTTB(songFile);
			Song song = new Song("songs\\" + songFile.getName(), uploadPanel.getSongNameField().getText()
					, uploadPanel.getArtistField().getText(), genreList[(uploadPanel.getGenreChoice().getSelectedIndex())]);
			songList.add(song);
			new HomogeneousFileHandler().saveAs("songlist.list", songList);
		} else {
			setResponse("Fields cannot be empty", false);	   
		}
	}

}
