package tests;

import java.io.File;

import gui.SongUploadFunctionality;
import gui.SongUploadPanel;
import musichandler.MusicFacade;
import musichandler.Song;

public class TestUpload{
	private SongUploadFunctionality uploadFunc;
	private SongUploadPanel panel;
	private MusicFacade mf;
	
	public TestUpload() {
		mf = new MusicFacade();
		panel = new SongUploadPanel();
		uploadFunc = new SongUploadFunctionality(panel, mf.getSongList(), mf.getGenres());
		Song song = new Song("12 Planet Caravan", "Planet Caravan", "Pantera", (mf.getGenres())[2]);
		//replace the filepath with a local filepath and check if it apears in the songs folder after refreshing
		String filepath = "C:\\Users\\Hedström\\Music\\Mystic Prophecy\\Regressus\\02 Eternal Flame.wma";
		uploadFunc.load(song, filepath);
		File songFile = new File(filepath);
		System.out.println(songFile.getAbsolutePath());
		if((mf.getSongList().get(mf.getSongList().size() - 1)).getArtist().equals("Pantera")) {
			
			System.out.println("ADDING TO LIST WORKS");
		} else {
			System.out.println("ADDING TO LIST DOESNT DOESNT WORK");
		}
	}
	
	public static void main(String[] args) {
		TestUpload test = new TestUpload();
	}
}
