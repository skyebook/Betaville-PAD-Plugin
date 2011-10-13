/**
 * 
 */
package net.skyebook.betaville.pad;

/**
 * @author Skye Book
 *
 */
public class NameMatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "M107_Blk53_Lot_7501";
		System.out.println(name.matches("[A-Z0-9]*_Blk[0-9]*_Lot[0-9]*"));
		
		// find block
		System.out.println(name.substring(name.indexOf("_Blk")+4,name.indexOf("_Lot")));
		
		// find lot
		System.out.println(name.substring(name.indexOf("_Lot")+5));
	}

}
