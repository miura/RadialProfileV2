/**@author Kota Miura (miura@embl.de)
 * radial profile analysis modified for use with scripting. 

javascript example:
imp = IJ.getImage();
rp =  IJ.runPlugIn(imp, "Radial_ProfileV2", "x=100 y=100 radius=50 noplot");
data = rp.getAccumulator();
for (var i = 0; i<data[0].length; i++)
	IJ.log(data[0][i] +": "+ data[1][i]);	
*/

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import emblcmci.radial.*;

public class Radial_ProfileV2 implements PlugInFilter {

	ImagePlus imp;
	String arg;
	RadialProfile rp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		this.arg = arg;
		return DOES_ALL+NO_UNDO;
	}
	public void run(ImageProcessor ip) {
		rp = new RadialProfile();
		rp.doit(imp, arg);
	}

	/** Use this method for retrieving profile data
	 * float[0][] distance from center
	 * float[1][] mean pixel intensity
	 * @return
	 */
	public float[][] getAccumulator() {
		return rp.getAccumulator();
	}
	
	/** To Check if Radius measurement is OK.
	 *  
	 * @return
	 */
	public double[][] getRadius() {
		return rp.getRadius();
	}

	/**plots data in a new window
	 * 
	 */
	public void doPlot(){
		rp.plotter();
	}
}
