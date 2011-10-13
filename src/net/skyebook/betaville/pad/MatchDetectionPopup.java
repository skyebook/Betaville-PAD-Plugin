/**
 * 
 */
package net.skyebook.betaville.pad;

import java.util.List;

import net.skyebook.padloader.record.ADRRecord;

import org.fenggui.Button;
import org.fenggui.Container;
import org.fenggui.FengGUI;
import org.fenggui.composite.Window;
import org.fenggui.event.ButtonPressedEvent;
import org.fenggui.event.IButtonPressedListener;
import org.fenggui.layout.BorderLayout;
import org.fenggui.layout.BorderLayoutData;
import org.fenggui.layout.RowExLayout;
import org.fenggui.layout.RowExLayoutData;

import edu.poly.bxmc.betaville.jme.fenggui.extras.IBetavilleWindow;

/**
 * @author Skye Book
 *
 */
public class MatchDetectionPopup extends Window implements IBetavilleWindow {
	
	private List<ADRRecord> adrRecord;
	private int currentRecordIndex = -1;
	
	private Container adrNextPrev;
	private Button adrNext;
	private Button adrPrev;
	private RecordContainer adrContainer;
	
	/**
	 * 
	 */
	public MatchDetectionPopup() {
		super(true, true);
		getContentContainer().setLayoutManager(new RowExLayout());
		
		adrNextPrev = FengGUI.createWidget(Container.class);
		adrNextPrev.setLayoutManager(new BorderLayout());
		adrNextPrev.setLayoutData(new RowExLayoutData(true, false));
		
		adrNext = FengGUI.createWidget(Button.class);
		adrNext.setText("Next");
		adrNext.setLayoutData(BorderLayoutData.EAST);
		adrNext.addButtonPressedListener(new IButtonPressedListener() {
			
			@Override
			public void buttonPressed(Object source, ButtonPressedEvent e) {
				currentRecordIndex++;
				adrContainer.setRecord(adrRecord.get(currentRecordIndex));
				updateNextPrevious();
			}
		});
		
		adrPrev = FengGUI.createWidget(Button.class);
		adrPrev.setText("Previous");
		adrPrev.setLayoutData(BorderLayoutData.WEST);
		adrPrev.addButtonPressedListener(new IButtonPressedListener() {
			
			@Override
			public void buttonPressed(Object source, ButtonPressedEvent e) {
				currentRecordIndex--;
				adrContainer.setRecord(adrRecord.get(currentRecordIndex));
				updateNextPrevious();
			}
		});
		
		adrNextPrev.addWidget(adrNext, adrPrev);
		
		adrContainer = FengGUI.createWidget(ADRContainer.class);
		
		getContentContainer().addWidget(adrNextPrev, adrContainer);
	}
	
	public void loadRecord(List<ADRRecord> records){
		this.adrRecord=records;
		currentRecordIndex = 0;
		adrContainer.setRecord(records.get(0));
		updateNextPrevious();
	}
	
	private void updateNextPrevious(){
		if(currentRecordIndex==0 || adrRecord.size()==0) adrPrev.setEnabled(false);
		else adrPrev.setEnabled(true);
		if(currentRecordIndex==adrRecord.size()-1 || adrRecord.size()==0) adrNext.setEnabled(false);
		else adrNext.setEnabled(true);
	}

	/* (non-Javadoc)
	 * @see edu.poly.bxmc.betaville.jme.fenggui.extras.IBetavilleWindow#finishSetup()
	 */
	@Override
	public void finishSetup() {
		setTitle("Property Address Match");
	}
}
