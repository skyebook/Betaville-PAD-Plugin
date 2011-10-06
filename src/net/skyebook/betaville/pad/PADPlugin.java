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
import java.util.List;

import net.skyebook.padloader.database.DerbyImplementation;
import net.skyebook.padloader.read.ADRReader;
import net.skyebook.padloader.read.BBLReader;
import net.skyebook.padloader.record.Record;

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
	private File extractedPADDirectory;

	private LoadingWindow loadingWindow;

	/**
	 * 
	 */
	public PADPlugin(){
		padArchive = new File(getDataDirectory().toString()+"/"+PAD_FILENAME);
		extractedPADDirectory = new File(getDataDirectory().toString()+"/"+PAD_FILENAME.substring(0, PAD_FILENAME.lastIndexOf("."))+"/");
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
				// If it hasn't, check if the PAD archive has already been downloaded
				if(!doesPADDataExist()){
					System.out.println("Downloading data");
					try {
						downloadData();
					} catch (IOException e) {
						loadingWindow.setDLStatus("Could not download data file");
					}
				}
				
				// Check if the data has already been loaded into Derby
				DerbyImplementation derby = new DerbyImplementation(new File(getDataDirectory().toString()+"/db/").toString());
				if(derby.tablesWereAlreadyCreated()){
					System.out.println("Using pre-existing tables");
				}
				else{
					System.out.println("Fresh tables were created");
				}

				// now we can load the data
				try {
					File padDirectory = new File(getDataDirectory().toString()+"/"+PAD_FILENAME.substring(0, PAD_FILENAME.indexOf("."))+"/");

					File addrFile = new File(padDirectory.toString()+"/bobaadr.txt");
					loadingWindow.setADRStatus("Parsing Raw Data");
					List<Record> adr = new ADRReader().readRecords(addrFile);

					System.out.println(adr.size() + " records read");

					long dbStart = System.currentTimeMillis();

					int updateInterval=1000;
					
					loadingWindow.setADRStatus("Inserting Records");
					
					int counter = 0;
					for(Record record : adr){
						counter++;
						if(counter%updateInterval==0) loadingWindow.setADRProgress(counter/1000, adr.size()/1000);
						derby.addRecord(record);
					}

					System.out.println("ADR database insert took " + (System.currentTimeMillis()-dbStart)+"ms");

					// release the resources
					adr = null;
					
					loadingWindow.setADRStatus("Complete");



					File bblFile = new File(padDirectory.toString()+"/bobabbl.txt");
					loadingWindow.setBBLStatus("Parsing Raw Data");
					List<Record> bbl = new BBLReader().readRecords(bblFile);
					System.out.println(bbl.size() + " records read");

					dbStart = System.currentTimeMillis();

					loadingWindow.setBBLStatus("Inserting Records");
					
					counter = 0;
					for(Record record : bbl){
						counter++;
						if(counter%updateInterval==0) loadingWindow.setBBLProgress(counter/1000, bbl.size()/1000);
						derby.addRecord(record);
					}

					System.out.println("BBL database insert took " + (System.currentTimeMillis()-dbStart)+"ms");

					// release the resources
					bbl = null;
					
					loadingWindow.setBBLStatus("Complete");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void destroy() {
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
			
			/*
			int byteValue = -1;
			int counter = 0;
			while((byteValue = is.read())!=-1){
				counter++;
				if(counter%1000==0)loadingWindow.setDLProgress(counter/1000, size/1000);
				outputStream.write(byteValue);
			}
			outputStream.close();
			*/
			
			int bufferSize = 2048;
			byte[] readBuffer = new byte[bufferSize];
			int lastUpdate = 0;
			int totalRead = 0;
			int n=-1;
			while ((n = is.read(readBuffer, 0, bufferSize)) != -1){
				totalRead+=n;
				if(totalRead-lastUpdate>1000){
					loadingWindow.setDLProgress(totalRead/1000, size/1000);
					lastUpdate = totalRead;
				}
				outputStream.write(readBuffer, 0, n);
			}
			outputStream.close();
			
			

			loadingWindow.setDLStatus("Extracting Files");

			// unzip the files
			System.out.println("Unzipping to " + extractedPADDirectory.toString());
			Unzipper.unzip(padArchive, extractedPADDirectory);

			loadingWindow.setDLStatus("Done!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
