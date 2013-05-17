package coop.tecso.donde.estaciono.rules;

import java.io.File;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import coop.tecso.donde.estaciono.rules.model.DondeEstacionoRulesTest;

public class XstreanTest {

	@Test
	public void testXstream() {
		
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(DondeEstacionoRulesTest.class);

		
//		xstream.alias("rule-class", RuleClass.class);
//		xstream.alias("donde-estaciono-rules", DondeEstacionoRules.class);
//		xstream.addImplicitCollection(DondeEstacionoRules.class, "rules");
//		xstream.useAttributeFor(RuleClass.class, "id");
//		xstream.aliasAttribute("id","id");
		
		File file = new File("test.xml");
		DondeEstacionoRulesTest obj = (DondeEstacionoRulesTest)xstream.fromXML(file);
		
		System.out.println(obj);
		
	}
	
	
	
	public static void main(String a[]){
        String xmlString;
        // Assume that xmlString has the required XML( as a String) which is to be serialized.
        XStream xStreamObj = new XStream(new DomDriver());
        xStreamObj.alias("Gross",Gross.class);
        xStreamObj.alias("Amount",AmountType.class);
 
        xStreamObj.aliasField("Amount", Gross.class, "amount");
 
        xStreamObj.useAttributeFor(AmountType.class, "currency");
        xStreamObj.aliasAttribute("Currency","currency");
//        xStreamObj.addImplicitCollection(AmountType.class, "value");
        //xStreamObj.omitField(type, fieldName)
        Gross g = new Gross();
        AmountType amountType = new AmountType();
        amountType.setCurrency("USD");
        amountType.setValue("100");
        g.setAmount(amountType);
        System.out.println(xStreamObj.toXML(g));
    }
	
	
}

class Gross{
    private AmountType amount;
 
	public AmountType getAmount() {
		return amount;
	}
 
	public void setAmount(AmountType amount) {
		this.amount = amount;
	}
   
}
 
class AmountType{
    private String currency;
    private String value;
 
	public String getCurrency() {
		return currency;
	}
 
	public void setCurrency(String currency) {
		this.currency = currency;
	}
 
	public String getValue() {
		return value;
	}
 
	public void setValue(String value) {
		this.value = value;
	}
    
}
