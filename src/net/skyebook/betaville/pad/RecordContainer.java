/**
 * 
 */
package net.skyebook.betaville.pad;

import net.skyebook.padloader.record.Record;

import org.fenggui.Container;

/**
 * @author Skye Book
 *
 */
public abstract class RecordContainer extends Container {
	
	public RecordContainer(){
		super();
	}
	
	/**
	 * 
	 * @param record
	 */
	public abstract void setRecord(Record record);
}
