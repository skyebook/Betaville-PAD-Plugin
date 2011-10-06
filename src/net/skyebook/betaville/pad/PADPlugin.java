/**
 * 
 */
package net.skyebook.betaville.pad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.fenggui.FengGUI;

import edu.poly.bxmc.betaville.SettingsPreferences;
import edu.poly.bxmc.betaville.jme.fenggui.extras.FengUtils;
import edu.poly.bxmc.betaville.jme.gamestates.GUIGameState;
import edu.poly.bxmc.betaville.jme.loaders.util.Unzipper;
import edu.poly.bxmc.betaville.plugin.Plugin;

/**
 * @author Skye Book
 *
 */
public class PADPlugin extends Plugin{

	private static final String PAD_URL = "http://www.nyc.gov/html/dcp/download/bytes/pad10d.zip";
	private static final String PAD_FILENAME = "pad10d.zip";
	private File padArchive;

	private LoadingWindow loadingWindow;

	/**
	 * 
	 */
	public PADPlugin(){
		padArchive = new File(getDataDirectory().toString()+"/"+PAD_FILENAME);
	}

	@Override
	public void initialize() {
		System.out.println("initializing");
		loadingWindow = FengGUI.createWidget(LoadingWindow.class);
		loadingWindow.setTitle("PAD Data");
		System.out.println("loading window created");

		FengUtils.putAtMiddle(loadingWindow, GUIGameState.getInstance().getDisp());
		
		System.out.println("loading window placed in scene");

		SettingsPreferences.getThreadPool().execute(new Runnable() {
			
			@Override
			public void run() {
				// Check if the data has already been loaded into Derby

				// If it hasn't, check if the PAD archive has already been downloaded
				if(!doesPADDataExist()){
					System.out.println("Downloading data");
					try {
						downloadData();
					} catch (IOException e) {
						loadingWindow.setDLStatus("Could not download data file");
					}
				}
			}
		});
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	private boolean doesPADDataExist(){
		return padArchive.exists();
	}

	private void downloadData() throws IOException{
		try {
			URL padURL = new URL(PAD_URL);
			FileOutputStream outputStream = new FileOutputStream(padArchive);
			URLConnection connection = padURL.openConnection();

			// get the size of the file being downloaded
			String length = connection.getHeaderField("content-length");
			int size = 0;
			try{
				size = Integer.parseInt(length);
			}catch(NumberFormatException e){
				// content-length header invalid or not supplied
				size = 0;
			}

			loadingWindow.setDLStatus("Downloading data");

			// perform the download
			InputStream is = connection.getInputStream();
			int byteValue = -1;
			int counter = 0;
			while((byteValue = is.read())!=-1){
				counter++;
				if(counter%1000==0)loadingWindow.setDLProgress(counter/1000, size/1000);
				outputStream.write(byteValue);
			}
			outputStream.close();
			
			loadingWindow.setDLStatus("Extracting Files");
			
			// unzip the files
			Unzipper.unzip(padArchive, null);
			
			loadingWindow.setDLStatus("Done!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
