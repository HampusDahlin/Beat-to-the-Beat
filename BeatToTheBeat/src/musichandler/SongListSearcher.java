package musichandler;

import java.util.ArrayList;
import java.util.List;


public class SongListSearcher {
	
	public static List<Song> searchSongList(String searchTerm, List<Song> songList) {
		searchTerm = searchTerm.toLowerCase();
		List<Song> searchResults = new ArrayList<Song>();
		if(searchTerm.equals("")) {
			return songList;
		} else {
			for(int i = 0; i < songList.size(); i++) {
				if(((songList.get(i).getSongName()).toLowerCase()).contains(searchTerm)) {
					searchResults.add(songList.get(i));
				} else if(((songList.get(i).getArtist()).toLowerCase()).contains(searchTerm)) {
					searchResults.add(songList.get(i));
				} else if((((songList.get(i).getGenre()).getName())
						.toLowerCase()).contains(searchTerm)) {
					searchResults.add(songList.get(i));
				}
			}
		}
		return searchResults;
	}
}
