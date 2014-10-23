/* ====================================================================
 * Limited Evaluation License:
 *
 * The exclusive owner of this work is Tiger Shore Management Ltd.
 * This work, including all associated documents and components
 * is Copyright Tiger Shore Management Limited 2006-2012.
 *
 * The following restrictions apply unless they are expressly relaxed in a
 * contractual agreement between the license holder or one of its officially
 * assigned agents and you or your organisation:
 *
 * 1) This work may not be disclosed, either in full or in part, in any form
 *    electronic or physical, to any third party. This includes both in the
 *    form of source code and compiled modules.
 * 2) This work contains trade secrets in the form of architecture, algorithms
 *    methods and technologies. These trade secrets may not be disclosed to
 *    third parties in any form, either directly or in summary or paraphrased
 *    form, nor may these trade secrets be used to construct products of a
 *    similar or competing nature either by you or third parties.
 * 3) This work may not be included in full or in part in any application.
 * 4) You may not remove or alter any proprietary legends or notices contained
 *    in or on this work.
 * 5) This software may not be reverse-engineered or otherwise decompiled, if
 *    you received this work in a compiled form.
 * 6) This work is licensed, not sold. Possession of this software does not
 *    imply or grant any right to you.
 * 7) You agree to disclose any changes to this work to the copyright holder
 *    and that the copyright holder may include any such changes at its own
 *    discretion into the work
 * 8) You agree not to derive other works from the trade secrets in this work,
 *    and that any such derivation may make you liable to pay damages to the
 *    copyright holder
 * 9) You agree to use this software exclusively for evaluation purposes, and
 *    that you shall not use this software to derive commercial profit or
 *    support your business or personal activities.
 *
 * This software is provided "as is" and any expressed or impled warranties,
 * including, but not limited to, the impled warranties of merchantability
 * and fitness for a particular purpose are disclaimed. In no event shall
 * Tiger Shore Management or its officially assigned agents be liable to any
 * direct, indirect, incidental, special, exemplary, or consequential damages
 * (including but not limited to, procurement of substitute goods or services;
 * Loss of use, data, or profits; or any business interruption) however caused
 * and on theory of liability, whether in contract, strict liability, or tort
 * (including negligence or otherwise) arising in any way out of the use of
 * this software, even if advised of the possibility of such damage.
 * This software contains portions by The Apache Software Foundation, Robert
 * Half International.
 * ====================================================================
 */
package eonerate.reformat2.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import ElcRate.adapter.file.FlatFileOutputAdapter;
import ElcRate.record.FlatRecord;
import ElcRate.record.IRecord;
import eonerate.reformat2.entity.RateRecord;

/**
 * The Output Adapter is reponsible for writing the completed records to the
 * target file.
 */
public class FileOutputAdapter extends FlatFileOutputAdapter {

	private void readConfig() {
		// Check size, time, number of records
		//		maxSize in MB
		//		maxTime in hour
		//		maxRecord

		String filePath = "config/constraints.xml";
		try {

			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			NodeList nList1 = doc.getElementsByTagName("maxTime");
			NodeList nList2 = doc.getElementsByTagName("maxRecord");
			NodeList nList3 = doc.getElementsByTagName("maxSize");

			super.maxTime = Double.parseDouble(nList1.item(0).getTextContent());
			super.maxRecord = Long.parseLong(nList2.item(0).getTextContent());
			super.maxSize = Double.parseDouble(nList3.item(0).getTextContent());

		} catch (Exception ex) {
			System.err.println("Couldn't read " + filePath + " config");
			System.exit(1);
		}
	}

	/**
	 * Constructor for SimpleOutputAdapter.
	 */
	public FileOutputAdapter() {
		super();

		readConfig();
	}

	/**
	 * We transform the records here so that they are ready to output making any
	 * specific changes to the record that are necessary to make it ready for
	 * output.
	 *
	 * As we are using the FlatFileOutput adapter, we should transform the records
	 * into FlatRecords, storing the data to be written using the setData() method.
	 * This means that we do not have to know about the internal workings of the
	 * output adapter.
	 *
	 * Note that this is just undoing the transformation that we did in the input
	 * adapter.
	 */
	@Override
	public Collection<IRecord> procValidRecord(IRecord r)

	{
		FlatRecord tmpOutRecord;
		RateRecord tmpInRecord;

		Collection<IRecord> Outbatch;
		Outbatch = new ArrayList<IRecord>();

		tmpOutRecord = new FlatRecord();
		tmpInRecord = (RateRecord) r;
		tmpOutRecord.setData(tmpInRecord.unmapOriginalData());

		Outbatch.add((IRecord) tmpOutRecord);

		//System.out.println(Outbatch.size());

		return Outbatch;

	}

	/**
	 * Handle any error records here so that they are ready to output making any
	 * specific changes to the record that are necessary to make it ready for
	 * output.
	 */
	@Override
	public Collection<IRecord> procErrorRecord(IRecord r) {
		return null;
	}

	//	@Override
	//	protected int getValidRecordStreamNumber(IRecord OutRec) {
	//		// TODO Auto-generated method stub
	//		return 2;
	//	}
}
