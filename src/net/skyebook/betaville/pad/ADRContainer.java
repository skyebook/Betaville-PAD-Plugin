/**
 * 
 */
package net.skyebook.betaville.pad;

import java.util.ArrayList;
import java.util.List;

import net.skyebook.padloader.record.ADRRecord;
import net.skyebook.padloader.record.Record;

import org.fenggui.Container;
import org.fenggui.FengGUI;
import org.fenggui.Label;
import org.fenggui.layout.BorderLayout;
import org.fenggui.layout.BorderLayoutData;
import org.fenggui.layout.RowExLayout;
import org.fenggui.layout.RowExLayoutData;

/**
 * @author Skye Book
 *
 */
public class ADRContainer extends RecordContainer {

	private Label boro;
	private Label block;
	private Label lot;
	private Label bin;
	private Label lhnd;
	private Label lhns;
	private Label lcontpar;
	private Label lsos;
	private Label hhnd;
	private Label hhns;
	private Label hcontpar;
	private Label hsos;
	private Label scboro;
	private Label sc5;
	private Label sclgc;
	private Label stname;
	private Label addrtype;
	private Label realb7sc;
	private Label validlgcs;
	private Label parity;
	private Label b10sc;
	private Label segid;
	
	private Label boroData;
	private Label blockData;
	private Label lotData;
	private Label binData;
	private Label lhndData;
	private Label lhnsData;
	private Label lcontparData;
	private Label lsosData;
	private Label hhndData;
	private Label hhnsData;
	private Label hcontparData;
	private Label hsosData;
	private Label scboroData;
	private Label sc5Data;
	private Label sclgcData;
	private Label stnameData;
	private Label addrtypeData;
	private Label realb7scData;
	private Label validlgcsData;
	private Label parityData;
	private Label b10scData;
	private Label segidData;
	
	public ADRContainer() {
		super();
		List<Label> names = setupNameLabels();
		List<Label> data = setupDataLabels();
		setLayoutManager(new RowExLayout());
		
		for(int i=0; i<names.size(); i++){
			Container container = FengGUI.createWidget(Container.class);
			container.setLayoutManager(new BorderLayout());
			container.setLayoutData(new RowExLayoutData(true, false));
			
			container.addWidget(names.get(i), data.get(i));
			
			addWidget(container);
		}
	}
	
	private List<Label> setupNameLabels(){
		boro = FengGUI.createWidget(Label.class);
		block = FengGUI.createWidget(Label.class);
		lot = FengGUI.createWidget(Label.class);
		bin = FengGUI.createWidget(Label.class);
		lhnd = FengGUI.createWidget(Label.class);
		lhns = FengGUI.createWidget(Label.class);
		lcontpar = FengGUI.createWidget(Label.class);
		lsos = FengGUI.createWidget(Label.class);
		hhnd = FengGUI.createWidget(Label.class);
		hhns = FengGUI.createWidget(Label.class);
		hcontpar = FengGUI.createWidget(Label.class);
		hsos = FengGUI.createWidget(Label.class);
		scboro = FengGUI.createWidget(Label.class);
		sc5 = FengGUI.createWidget(Label.class);
		sclgc = FengGUI.createWidget(Label.class);
		stname = FengGUI.createWidget(Label.class);
		addrtype = FengGUI.createWidget(Label.class);
		realb7sc = FengGUI.createWidget(Label.class);
		validlgcs = FengGUI.createWidget(Label.class);
		parity = FengGUI.createWidget(Label.class);
		b10sc = FengGUI.createWidget(Label.class);
		segid = FengGUI.createWidget(Label.class);
		
		boro.setText("boro");
		block.setText("block");
		lot.setText("lot");
		bin.setText("bin");
		lhnd.setText("lhnd");
		lhns.setText("lhns");
		lcontpar.setText("lcontpar");
		lsos.setText("lsos");
		hhnd.setText("hhnd");
		hhns.setText("hhns");
		hcontpar.setText("hcontpar");
		hsos.setText("hsos");
		scboro.setText("scboro");
		sc5.setText("sc5");
		sclgc.setText("sclgc");
		stname.setText("stname");
		addrtype.setText("addrtype");
		realb7sc.setText("realb7sc");
		validlgcs.setText("validlgcs");
		parity.setText("parity");
		b10sc.setText("b10sc");
		segid.setText("segid");
		
		boro.setLayoutData(BorderLayoutData.WEST);
		block.setLayoutData(BorderLayoutData.WEST);
		lot.setLayoutData(BorderLayoutData.WEST);
		bin.setLayoutData(BorderLayoutData.WEST);
		lhnd.setLayoutData(BorderLayoutData.WEST);
		lhns.setLayoutData(BorderLayoutData.WEST);
		lcontpar.setLayoutData(BorderLayoutData.WEST);
		lsos.setLayoutData(BorderLayoutData.WEST);
		hhnd.setLayoutData(BorderLayoutData.WEST);
		hhns.setLayoutData(BorderLayoutData.WEST);
		hcontpar.setLayoutData(BorderLayoutData.WEST);
		hsos.setLayoutData(BorderLayoutData.WEST);
		scboro.setLayoutData(BorderLayoutData.WEST);
		sc5.setLayoutData(BorderLayoutData.WEST);
		sclgc.setLayoutData(BorderLayoutData.WEST);
		stname.setLayoutData(BorderLayoutData.WEST);
		addrtype.setLayoutData(BorderLayoutData.WEST);
		realb7sc.setLayoutData(BorderLayoutData.WEST);
		validlgcs.setLayoutData(BorderLayoutData.WEST);
		parity.setLayoutData(BorderLayoutData.WEST);
		b10sc.setLayoutData(BorderLayoutData.WEST);
		segid.setLayoutData(BorderLayoutData.WEST);
		
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add(boro);
		labels.add(block);
		labels.add(lot);
		labels.add(bin);
		labels.add(lhnd);
		labels.add(lhns);
		labels.add(lcontpar);
		labels.add(lsos);
		labels.add(hhnd);
		labels.add(hhns);
		labels.add(hcontpar);
		labels.add(hsos);
		labels.add(scboro);
		labels.add(sc5);
		labels.add(sclgc);
		labels.add(stname);
		labels.add(addrtype);
		labels.add(realb7sc);
		labels.add(validlgcs);
		labels.add(parity);
		labels.add(b10sc);
		labels.add(segid);
		return labels;
	}
	
	private List<Label> setupDataLabels(){
		boroData = FengGUI.createWidget(Label.class);
		blockData = FengGUI.createWidget(Label.class);
		lotData = FengGUI.createWidget(Label.class);
		binData = FengGUI.createWidget(Label.class);
		lhndData = FengGUI.createWidget(Label.class);
		lhnsData = FengGUI.createWidget(Label.class);
		lcontparData = FengGUI.createWidget(Label.class);
		lsosData = FengGUI.createWidget(Label.class);
		hhndData = FengGUI.createWidget(Label.class);
		hhnsData = FengGUI.createWidget(Label.class);
		hcontparData = FengGUI.createWidget(Label.class);
		hsosData = FengGUI.createWidget(Label.class);
		scboroData = FengGUI.createWidget(Label.class);
		sc5Data = FengGUI.createWidget(Label.class);
		sclgcData = FengGUI.createWidget(Label.class);
		stnameData = FengGUI.createWidget(Label.class);
		addrtypeData = FengGUI.createWidget(Label.class);
		realb7scData = FengGUI.createWidget(Label.class);
		validlgcsData = FengGUI.createWidget(Label.class);
		parityData = FengGUI.createWidget(Label.class);
		b10scData = FengGUI.createWidget(Label.class);
		segidData = FengGUI.createWidget(Label.class);
		
		boroData.setLayoutData(BorderLayoutData.EAST);
		blockData.setLayoutData(BorderLayoutData.EAST);
		lotData.setLayoutData(BorderLayoutData.EAST);
		binData.setLayoutData(BorderLayoutData.EAST);
		lhndData.setLayoutData(BorderLayoutData.EAST);
		lhnsData.setLayoutData(BorderLayoutData.EAST);
		lcontparData.setLayoutData(BorderLayoutData.EAST);
		lsosData.setLayoutData(BorderLayoutData.EAST);
		hhndData.setLayoutData(BorderLayoutData.EAST);
		hhnsData.setLayoutData(BorderLayoutData.EAST);
		hcontparData.setLayoutData(BorderLayoutData.EAST);
		hsosData.setLayoutData(BorderLayoutData.EAST);
		scboroData.setLayoutData(BorderLayoutData.EAST);
		sc5Data.setLayoutData(BorderLayoutData.EAST);
		sclgcData.setLayoutData(BorderLayoutData.EAST);
		stnameData.setLayoutData(BorderLayoutData.EAST);
		addrtypeData.setLayoutData(BorderLayoutData.EAST);
		realb7scData.setLayoutData(BorderLayoutData.EAST);
		validlgcsData.setLayoutData(BorderLayoutData.EAST);
		parityData.setLayoutData(BorderLayoutData.EAST);
		b10scData.setLayoutData(BorderLayoutData.EAST);
		segidData.setLayoutData(BorderLayoutData.EAST);
		
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add(boroData);
		labels.add(blockData);
		labels.add(lotData);
		labels.add(binData);
		labels.add(lhndData);
		labels.add(lhnsData);
		labels.add(lcontparData);
		labels.add(lsosData);
		labels.add(hhndData);
		labels.add(hhnsData);
		labels.add(hcontparData);
		labels.add(hsosData);
		labels.add(scboroData);
		labels.add(sc5Data);
		labels.add(sclgcData);
		labels.add(stnameData);
		labels.add(addrtypeData);
		labels.add(realb7scData);
		labels.add(validlgcsData);
		labels.add(parityData);
		labels.add(b10scData);
		labels.add(segidData);
		return labels;
	}
	
	/* (non-Javadoc)
	 * @see net.skyebook.betaville.pad.RecordContainer#setRecord(net.skyebook.padloader.record.Record)
	 */
	@Override
	public void setRecord(Record record) {
		boroData.setText(""+((ADRRecord)record).getBoro());
		blockData.setText(""+((ADRRecord)record).getBlock());
		lotData.setText(""+((ADRRecord)record).getLot());
		binData.setText(""+((ADRRecord)record).getBin());
		lhndData.setText(((ADRRecord)record).getLhnd());
		lhnsData.setText(((ADRRecord)record).getLhns());
		lcontparData.setText(""+((ADRRecord)record).getLcontpar());
		lsosData.setText(""+((ADRRecord)record).getLsos());
		hhndData.setText(((ADRRecord)record).getHhnd());
		hhnsData.setText(((ADRRecord)record).getHhns());
		hcontparData.setText(""+((ADRRecord)record).getHcontpar());
		hsosData.setText(""+((ADRRecord)record).getHsos());
		scboroData.setText(""+((ADRRecord)record).getScboro());
		sc5Data.setText(""+((ADRRecord)record).getSc5());
		sclgc.setText(""+((ADRRecord)record).getSclgc());
		stnameData.setText(""+((ADRRecord)record).getStname());
		addrtypeData.setText(""+((ADRRecord)record).getAddrtype());
		realb7scData.setText(""+((ADRRecord)record).getRealb7sc());
		validlgcsData.setText(""+((ADRRecord)record).getValidlgcs().toString());
		parityData.setText(""+((ADRRecord)record).getParity());
		b10scData.setText(""+((ADRRecord)record).getB10sc());
		segidData.setText(""+((ADRRecord)record).getSegid());
	}

}
