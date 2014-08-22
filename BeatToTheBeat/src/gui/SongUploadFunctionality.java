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

import model.music.Genre;
import model.music.Song;
import services.musichandler.MusicFacade;
import services.HomogeneousFileHandler;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class SongUploadFunctionality {
	private SongUploadPanel uploadPanel;
	private Minim minim;
	private List<Song> songList;
	private Genre[] genreList;
	private MusicFacade mf;

	public SongUploadFunctionality(SongUploadPanel uploadPanel, List<Song> songList, Genre[] genreList) {
		this.uploadPanel = uploadPanel;
		this.songList = songList;
		this.genreList = genreList;
		mf = new MusicFacade();
		loadToChoice();
	}

	/**
	 * @param songFile
	 * copies songFile to the songs directory in Beat to the Beat
	 */
	private void copyFileToBTTB(File songFile) {
		File dest = new File(System.getProperty("user.dir") + "\\songs\\" + songFile.getName());
		try {
			Files.copy(songFile.toPath(), dest.toPath());
		} catch (IOException e) {
			uploadPanel.setResponse("An error has occured, please try again", false);
		} 
	}

	/**
	 * @return a new Song created from the information in the fields in the SongUploadPanel
	 */
	private Song songFromInput() {
		File songFile = new File(uploadPanel.getOriginalFilepathField().getText());
		return new Song("songs\\" + songFile.getName(), uploadPanel.getSongNameField().getText()
				, uploadPanel.getArtistField().getText(), (genreList)[(uploadPanel.getGenreChoice().getSelectedIndex())]);
	}

	public void load(Song song, String filepath) {
		uploadPanel.setResponse("File loaded successfully!", true);
		File songFile = new File(filepath);
		copyFileToBTTB(songFile);
		songList.add(song);
		mf.addSong(song.getFilename(),song.getSongName(),song.getArtist(),song.getGenre());
		
	}

	/**
	 * Checks if loading can be performed
	 */
	public void loadingSequence(String filepath) {
		if(checkInputOk()) {
			load(songFromInput(), filepath);
			new HomogeneousFileHandler().saveAs("songlist.list", songList);
		} else {
			uploadPanel.setResponse("Fields cannot be empty", false);	   
		}
	}
	/**
	 * creates a filechooser and sends the selected file to presentInfo()
	 */
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

	/**
	 * Uses Minim to get the information from the songFile such as artist, name, etc
	 * @param songFile
	 */
	private void presentInfo(File songFile) {
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

	/**
	 * Used by minim
	 * @param fileName
	 * @return
	 */
	public InputStream createInput(String fileName) { // NO_UCD (unused code)
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			return is;
		} catch (Exception e) {
			is = null;
		}
		return is;
	}

	/**
	 * @param filename
	 * @return filename without extension such as .mp3
	 */
	private String returnFileName(String filename) {
		String[] splited = filename.split("\\.");
		return splited[splited.length - 2];
	}

	/**
	 * @return true if all the fields in the uploadPanel contains information
	 */
	private boolean checkInputOk() {
		if(!checkIfFieldOk(uploadPanel.getOriginalFilepathField())) {
			return false;
		} else if (!checkIfFieldOk(uploadPanel.getSongNameField())) {
			return false;
		} else if (!checkIfFieldOk(uploadPanel.getArtistField())) {
			return false;
		}
		return true;
	}

	/**
	 * @param textfield
	 * @return if textfield contains input
	 */
	private boolean checkIfFieldOk(JTextField textfield) {
		if(!textfield.getText().equals("")) {
			return true;
		} 
		return false;
	}

	/**
	 * loads uploadPanels genrechoicer with the genreList
	 */
	private void loadToChoice() {
		for(Genre genre : genreList) {
			uploadPanel.getGenreChoice().add(genre.getName());
		}
	}
}
