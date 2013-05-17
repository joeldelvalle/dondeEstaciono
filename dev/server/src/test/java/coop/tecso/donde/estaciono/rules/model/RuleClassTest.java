package coop.tecso.donde.estaciono.rules.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * @author joel.delvalle
 * 
 */
@XStreamAlias("rule")
public class RuleClassTest {

	@XStreamAlias("id")
	private String id;

	@XStreamAlias("clazz")
	private String clazz;

	@XStreamAlias("nombre")
	@XStreamAsAttribute
	private String nombreRule;

	public String getId() {
		return id;
	}

	public String getClazz() {
		return clazz;
	}

	public String getNombreRule() {
		return nombreRule;
	}

}
