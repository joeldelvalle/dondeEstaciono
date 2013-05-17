package coop.tecso.donde.estaciono.rules.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("donde-estaciono-rules")
public class DondeEstacionoRulesTest {

	@XStreamImplicit(itemFieldName = "rule-class")
	private List<String> clase;
	
//	@XStreamAlias("rule")
	@XStreamImplicit(itemFieldName = "rule")
	private List<RuleClassTest> ruleUnica;

	public List<String> getClase() {
		return clase;
	}

	public void setClase(List<String> clase) {
		this.clase = clase;
	}

	public List<RuleClassTest> getRuleUnica() {
		return ruleUnica;
	}

	public void setRuleUnica(List<RuleClassTest> ruleUnica) {
		this.ruleUnica = ruleUnica;
	}


	
//	@XStreamImplicit(itemFieldName = "rule-class")
//	List<RuleClass> rules = new ArrayList<RuleClass>();
//
//	public List<RuleClass> getRules() {
//		return rules;
//	}
//
//	public void setRules(List<RuleClass> rules) {
//		this.rules = rules;
//	}
//
//	public void add(RuleClass rule) {
//		rules.add(rule);
//	}


}
